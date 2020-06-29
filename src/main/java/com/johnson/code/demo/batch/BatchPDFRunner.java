package com.johnson.code.demo.batch;

import com.johnson.code.demo.config.BookConfig;
import com.johnson.code.demo.model.PDFPage;
import com.johnson.code.demo.pdf.PDFParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@EnableAsync
public class BatchPDFRunner {

    private BookConfig bookConfig;

    @Autowired
    public BatchPDFRunner(BookConfig bookConfig) {
        this.bookConfig = bookConfig;
    }

    @Async
    public void execute() {
        log.info("ELASTIC-BATCH :::::::::::::::::::::::::::::::::");
        long init = System.currentTimeMillis();

        bookConfig.getPdf().parallelStream()
                .map(PDFParser::loadDocumentFromSource)
                .forEach(pdfDocument -> {
                    final int start = pdfDocument.getSource().getStart();
                    final int stop = pdfDocument.getSource().getStop();
                    final PDDocument document = pdfDocument.getDocument();
                    final String filename = pdfDocument.getSource().getName();

                    for(int i = start; i <= stop; i++){
                        PDFPage page = PDFParser.loadPageFromDocument(document, filename, i);
                        log.info("PDFPage: {}, {}, {}", BatchUtils.generateIndexName(page.getTitle()), page.getTitle(), page.getPage());
                    }

                    try {
                        document.close();
                    } catch(IOException e) {
                        log.error("Batch Error: {}", e.getMessage());
                    }
                });

        long complete = System.currentTimeMillis();
        double seconds = (complete - init) / 1000.0;

        log.info("ELASTIC-BATCH Completed in {}s", seconds);
        log.info("ELASTIC-BATCH :::::::::::::::::::::::::::::::::");
    }
}
