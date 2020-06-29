package com.johnson.code.demo.pdf;

import com.johnson.code.demo.model.PDFDocument;
import com.johnson.code.demo.model.PDFPage;
import com.johnson.code.demo.model.PDFSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class PDFParser {

    public static PDFDocument loadDocumentFromSource(PDFSource source) {
        PDDocument document;
        PDFDocument resource = null;

        try {
            File pdfFile = new ClassPathResource("pdf/" + source.getSource()).getFile();
            document = PDDocument.load(pdfFile);
            resource = new PDFDocument(document, source);
        } catch(IOException e) {
            log.error(e.getMessage());
        }

        return resource;
    }

    public static PDFPage loadPageFromDocument(PDDocument document, String pdfName, int pdfPage) {
        PDFPage page = null;

        try {
            PDFTextStripper reader = new PDFTextStripper();
            reader.setStartPage(pdfPage);
            reader.setEndPage(pdfPage);
            reader.setSortByPosition(false);
            String pageText = reader.getText(document);
            page = new PDFPage(pdfPage, pdfName, pageText);
        } catch(IOException e) {
            log.error(e.getMessage());
        }

        return page;
    }
}
