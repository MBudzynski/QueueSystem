package com.example.queuesystemcore.infrastructure.pdf.template;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class QueueNumberTemplate {

    public byte[] generate(Integer queueNumber, String logoFilePath, String institutionName) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            ClassPathResource logoFile = new ClassPathResource(logoFilePath);
            Image logo = Image.getInstance(logoFile.getURL());
            logo.scaleToFit(100, 100);  // Rozmiar logo
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);

            // Dodaj nazwę firmy
            Font companyFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph companyName = new Paragraph(institutionName, companyFont);
            companyName.setAlignment(Element.ALIGN_CENTER);
            document.add(companyName);

            // Dodaj odstęp
            document.add(new Paragraph(" "));  // pusta linia jako odstęp

            // Dodaj napis "Twój numer to:"
            Font textFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
            Paragraph queueText = new Paragraph("Twój numer to:", textFont);
            queueText.setAlignment(Element.ALIGN_CENTER);
            document.add(queueText);

            // Dodaj numer
            Font numberFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph queueNumberParagraph = new Paragraph(String.valueOf(queueNumber), numberFont);
            queueNumberParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(queueNumberParagraph);

            document.close();
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }

        return out.toByteArray();
    }
}
