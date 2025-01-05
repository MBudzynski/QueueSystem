package com.example.queuesystemcore.infrastructure.pdf;

public interface PdfFacade {

    String generateQueueNumberPdf(String queueNumber, String logoFilePath, String institutionName);
}
