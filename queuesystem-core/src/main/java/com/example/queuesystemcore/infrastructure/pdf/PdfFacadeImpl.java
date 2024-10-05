package com.example.queuesystemcore.infrastructure.pdf;

import com.example.queuesystemcore.infrastructure.pdf.template.QueueNumberTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
class PdfFacadeImpl implements PdfFacade {

    private final QueueNumberTemplate queueNumberTemplate;

    public String generateQueueNUmberPdf(Integer queueNumber, String logoFilePath, String institutionName) {
       return Base64.getEncoder().encodeToString(queueNumberTemplate.generate(queueNumber, logoFilePath, institutionName));
    }

}
