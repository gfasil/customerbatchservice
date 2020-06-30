package com.customerinfo.batchjobs.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ApplicationProperties {

    @Value("${batch.input.file-name}")
    private String inputFile;

    @Value("${batch.output.addis.file-name}")
    private String americansFile;

}
