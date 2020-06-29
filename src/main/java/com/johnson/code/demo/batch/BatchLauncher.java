package com.johnson.code.demo.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class BatchLauncher {

    private BatchPDFRunner batch;

    @Autowired
    public BatchLauncher(BatchPDFRunner batch) {
        this.batch = batch;
    }

    @PostConstruct
    private void init() {
        batch.execute();
    }
}
