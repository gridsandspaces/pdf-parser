package com.johnson.code.demo.batch;

public class BatchUtils {

    public static String generateIndexName(String document) {
        return document.toLowerCase().replace(" ","-");
    }
}
