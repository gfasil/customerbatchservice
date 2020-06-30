package com.customerinfo.batchjobs.config;

import com.customerinfo.batchjobs.itemreader.CustomerInput;
import com.customerinfo.batchjobs.itemreader.CustomerItemReader;
import com.customerinfo.batchjobs.itemwriter.CustomerItemWriter;
import com.customerinfo.batchjobs.model.Customer;
import com.customerinfo.batchjobs.processor.CustomerItemProcessor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class CustomerBatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    private CustomerItemReader customerItemReader;

    @Autowired
    private CustomerItemProcessor customerItemProcessor;

    @Autowired
    private CustomerItemWriter customerItemWriter;

    @Bean
    public TaskletStep importCustomersStep() {
        return stepBuilderFactory.get("STEP-CUSTOMERS-01")
                // defines step input and output domains and chunk size
                .<CustomerInput, Customer>chunk(10)
                // defines the reader
                .reader(customerItemReader)
                // defines the processor
                .processor(customerItemProcessor)
                // defines the writer
                .writer(customerItemWriter)
                .build();
    }
}
