package com.johnson.code.demo.config;

import com.johnson.code.demo.model.PDFSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "books")
public class BookConfig {

    private List<PDFSource> pdf;

    @PostConstruct
    void init() {
        log.info("Sources: {}", pdf);
    }
}
