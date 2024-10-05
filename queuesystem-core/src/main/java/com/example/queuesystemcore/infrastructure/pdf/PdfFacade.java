package com.example.queuesystemcore.infrastructure.pdf;

public interface PdfFacade {

    String generateQueueNUmberPdf(Integer queueNumber, String logoFilePath, String institutionName);
}
