package com.example.queuesystemcore.infrastructure.pdf;

public interface PdfFacade {

    String generateQueueNUmberPdf(String queueNumber, String logoFilePath, String institutionName);
}
