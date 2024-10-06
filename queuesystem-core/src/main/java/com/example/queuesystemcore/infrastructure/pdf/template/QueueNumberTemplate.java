package com.example.queuesystemcore.infrastructure.pdf.template;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Slf4j
@Component
public class QueueNumberTemplate {

    public byte[] generate(String queueNumber, String logoFilePath, String institutionName) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);

            document.open();
            document.add(prepareInstitutionNameSection(institutionName));
            document.add(prepareImage(logoFilePath));
            document.add(new Paragraph(" "));
            document.add(prepareSectionWithQueueNumber(queueNumber));
            document.close();

        } catch (Exception ex) {
            log.error("Error during print queueNumber: {}", queueNumber, ex);
        }

        return out.toByteArray();
    }

    private Paragraph prepareInstitutionNameSection(String institutionName) {
        Font companyFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
        Paragraph companyNameParagraph = new Paragraph(institutionName, companyFont);
        companyNameParagraph.setAlignment(Element.ALIGN_CENTER);
        return companyNameParagraph;
    }

    private Image prepareImage(String logoFilePath) throws Exception {
        ClassPathResource logoFile = new ClassPathResource(logoFilePath);
        Image logo = Image.getInstance(logoFile.getURL());
        logo.scaleToFit(75, 75);
        logo.setAlignment(Element.ALIGN_CENTER);
        return logo;
    }

    private Paragraph prepareSectionWithQueueNumber(String queueNumber) {
        Font numberFont = new Font(Font.FontFamily.HELVETICA, 35, Font.BOLD);
        Font textFont = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL);

        Paragraph queueNumberSection = new Paragraph("Twój numer to:", textFont);
        queueNumberSection.setAlignment(Element.ALIGN_CENTER);

        Paragraph queueNumberParagraph = new Paragraph(String.valueOf(queueNumber), numberFont);
        queueNumberParagraph.setAlignment(Element.ALIGN_CENTER);

        queueNumberSection.add(queueNumberParagraph);
        return queueNumberSection;
    }
}
