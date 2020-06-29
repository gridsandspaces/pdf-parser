package com.johnson.code.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;

@Data
@AllArgsConstructor
public class PDFDocument {
    private PDDocument document;
    private PDFSource source;
}
