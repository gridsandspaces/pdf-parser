package com.johnson.code.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PDFPage {
    private int page;
    private String title;
    private String text;
}
