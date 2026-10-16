package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.common.application.FacilityFacade;
import com.example.queuesystemcore.common.domain.FacilityDto;
import com.example.queuesystemcore.common.domain.QueueDto;
import com.example.queuesystemcore.common.domain.QueueNumberDto;
import com.example.queuesystemcore.ddd.queue.aplication.mapper.QueueNumberMapper;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfiguration;
import com.example.queuesystemcore.infrastructure.message_broker.MessageBrokerClient;
import com.example.queuesystemcore.infrastructure.pdf.PdfFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QueueServiceTest {

    @Mock
    private QueueNumberMapper queueNumberMapper;
    @Mock
    private MessageBrokerClient messageBrokerClient;
    @Mock
    private QueueConfigurationProvider queueConfigurationProvider;
    @Mock
    private FacilityFacade facilityFacade;
    @Mock
    private PdfFacade pdfFacade;

    private QueueService queueService;

    private UUID facilityUuid;
    private UUID queueConfigurationUuid;
    private FacilityDto facility;
    private QueueConfiguration queueConfiguration;

    @BeforeEach
    void setUp() {
        queueService = new QueueService(
                queueNumberMapper,
                messageBrokerClient,
                queueConfigurationProvider,
                facilityFacade,
                pdfFacade
        );

        facilityUuid = UUID.randomUUID();
        queueConfigurationUuid = UUID.randomUUID();

        facility = FacilityDto.builder()
                .facilityId(17L)
                .facilityUUID(facilityUuid)
                .institutionName("Test institution")
                .pathToLogoFile("/tmp/logo.png")
                .queueName("facility-queue")
                .build();

        queueConfiguration = new QueueConfiguration(
                42L,
                queueConfigurationUuid.toString(),
                "General queue",
                17L,
                6,
                1,
                999,
                "A"
        );
    }

    @Test
    void shouldIssueNextNumberGeneratePdfAndPublishMessage() {
        QueueDto publishedDto = QueueDto.builder()
                .sign("A")
                .num(7)
                .fullNumber("A007")
                .facilityId(17L)
                .queueConfigurationId(42L)
                .build();

        QueueNumberDto resultDto = new QueueNumberDto("A007", "pdf-content");

        when(facilityFacade.findFacilityIdByUUID(facilityUuid)).thenReturn(facility);
        when(queueConfigurationProvider.findQueueConfigurationByUUID(queueConfigurationUuid, 17L))
                .thenReturn(queueConfiguration);
        when(queueNumberMapper.toDto("A", 7, "A007", 17L, 42L)).thenReturn(publishedDto);
        when(pdfFacade.generateQueueNumberPdf("A007", "/tmp/logo.png", "Test institution"))
                .thenReturn("pdf-content");
        when(queueNumberMapper.toDto("A007", "pdf-content")).thenReturn(resultDto);

        QueueNumberDto result = queueService.queuePetitioner(queueConfigurationUuid, facilityUuid);

        assertSame(resultDto, result);
        InOrder order = inOrder(
                facilityFacade,
                queueConfigurationProvider,
                queueNumberMapper,
                pdfFacade,
                messageBrokerClient
        );
        order.verify(facilityFacade).findFacilityIdByUUID(facilityUuid);
        order.verify(queueConfigurationProvider)
                .findQueueConfigurationByUUID(queueConfigurationUuid, 17L);
        order.verify(queueNumberMapper).toDto("A", 7, "A007", 17L, 42L);
        order.verify(queueConfigurationProvider).updateCurrentNumber(42L, 7);
        order.verify(pdfFacade).generateQueueNumberPdf(
                "A007", "/tmp/logo.png", "Test institution");
        order.verify(messageBrokerClient).sendNewQueueNumber("facility-queue", publishedDto);
        order.verify(queueNumberMapper).toDto("A007", "pdf-content");
        verifyNoMoreInteractions(messageBrokerClient);
    }

    @Test
    void shouldWrapNumberAtEndOfConfiguredRange() {
        QueueConfiguration lastNumberConfiguration = new QueueConfiguration(
                42L,
                queueConfigurationUuid.toString(),
                "General queue",
                17L,
                999,
                1,
                999,
                "A"
        );
        when(facilityFacade.findFacilityIdByUUID(facilityUuid)).thenReturn(facility);
        when(queueConfigurationProvider.findQueueConfigurationByUUID(queueConfigurationUuid, 17L))
                .thenReturn(lastNumberConfiguration);

        QueueDto publishedDto = QueueDto.builder().build();
        QueueNumberDto resultDto = new QueueNumberDto("A001", "pdf-001");
        when(queueNumberMapper.toDto("A", 1, "A001", 17L, 42L)).thenReturn(publishedDto);
        when(pdfFacade.generateQueueNumberPdf("A001", "/tmp/logo.png", "Test institution"))
                .thenReturn("pdf-001");
        when(queueNumberMapper.toDto("A001", "pdf-001")).thenReturn(resultDto);

        QueueNumberDto result = queueService.queuePetitioner(queueConfigurationUuid, facilityUuid);

        assertEquals("A001", result.getQueueNumber());
        verify(queueConfigurationProvider).updateCurrentNumber(42L, 1);
        verify(messageBrokerClient).sendNewQueueNumber("facility-queue", publishedDto);
    }

    @Test
    void shouldNotContinueWhenFacilityLookupFails() {
        RuntimeException expected = new RuntimeException("Facility not found");
        when(facilityFacade.findFacilityIdByUUID(facilityUuid)).thenThrow(expected);

        RuntimeException actual = assertThrows(
                RuntimeException.class,
                () -> queueService.queuePetitioner(queueConfigurationUuid, facilityUuid)
        );

        assertSame(expected, actual);
        verifyNoInteractions(queueConfigurationProvider, pdfFacade, messageBrokerClient, queueNumberMapper);
    }

    @Test
    void shouldNotGeneratePdfOrPublishMessageWhenConfigurationLookupFails() {
        RuntimeException expected = new RuntimeException("Queue configuration not found");
        when(facilityFacade.findFacilityIdByUUID(facilityUuid)).thenReturn(facility);
        when(queueConfigurationProvider.findQueueConfigurationByUUID(queueConfigurationUuid, 17L))
                .thenThrow(expected);

        RuntimeException actual = assertThrows(
                RuntimeException.class,
                () -> queueService.queuePetitioner(queueConfigurationUuid, facilityUuid)
        );

        assertSame(expected, actual);
        verify(queueConfigurationProvider, never()).updateCurrentNumber(anyLong(), anyInt());
        verifyNoInteractions(pdfFacade, messageBrokerClient, queueNumberMapper);
    }

    @Test
    void shouldPropagatePdfGenerationFailureAndNotPublishMessage() {
        when(facilityFacade.findFacilityIdByUUID(facilityUuid)).thenReturn(facility);
        when(queueConfigurationProvider.findQueueConfigurationByUUID(queueConfigurationUuid, 17L))
                .thenReturn(queueConfiguration);
        when(queueNumberMapper.toDto("A", 7, "A007", 17L, 42L))
                .thenReturn(QueueDto.builder().build());
        when(pdfFacade.generateQueueNumberPdf("A007", "/tmp/logo.png", "Test institution"))
                .thenThrow(new IllegalStateException("PDF generation failed"));

        assertThrows(IllegalStateException.class,
                () -> queueService.queuePetitioner(queueConfigurationUuid, facilityUuid));

        verify(messageBrokerClient, never()).sendNewQueueNumber(anyString(), any(QueueDto.class));
    }
}
