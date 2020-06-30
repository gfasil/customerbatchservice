package com.customerinfo.batchjobs.itemwriter;

import com.customerinfo.batchjobs.config.ApplicationProperties;
import com.customerinfo.batchjobs.model.Customer;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerItemWriter extends FlatFileItemWriter<Customer> {
    // declared constants to better organize the code
    private static final String DEFAULT_DELIMITER = ";";
    private static final String DEFAULT_LINE_BREAK = "\n";
    private static final String ID_HEADER = "Id";
    private static final String FIRST_NAME_HEADER = "first_name";
    private static final String LAST_NAME_HEADER = "last_name";
    private static final String GENDER_HEADER = "gender";
    private static final String BIRTHDAY_HEADER = "birthday";
    private static final String ADDRESS_HEADER = "address";

    // dependency injection of properties class
    @Autowired
    ApplicationProperties properties;
    public CustomerItemWriter(final ApplicationProperties properties) {
        // calls superclass constructor in order to basically configure the object
        super();

        // defining of line aggregator and fieldExtractor
        final DelimitedLineAggregator<Customer> lineAggregator = new DelimitedLineAggregator<Customer>();
        final BeanWrapperFieldExtractor<Customer> fieldExtractor = new BeanWrapperFieldExtractor<Customer>();

        lineAggregator.setDelimiter(DEFAULT_DELIMITER);
        lineAggregator.setFieldExtractor(fieldExtractor);

        // setting output file and default line aggregator
        this.setResource(new FileSystemResource(properties.getAmericansFile()));
        this.setLineAggregator(lineAggregator);
    }
    @Override
    public String doWrite(final List<? extends Customer> items) {
        // creates a StringBuilder which is receive the lines to write
        final StringBuilder linesToWrite = new StringBuilder();

        items.forEach(customer -> {
            // creates line with Customer properties
            final String line = new StringBuilder()
                    .append(customer.getId())
                    .append(DEFAULT_DELIMITER)
                    .append(customer.getFirstName())
                    .append(DEFAULT_DELIMITER)
                    .append(customer.getLastName())
                    .append(DEFAULT_DELIMITER)
                    .append(customer.getBirthday())
                    .append(DEFAULT_DELIMITER)
                    .append(customer.getGender().getValue())
                    .append(DEFAULT_DELIMITER)
                    .append(customer.getAddress().getStreet())
                    .append(DEFAULT_DELIMITER)
                    .append(customer.getAddress().getCountry())
                    .toString();

            // appends line to StringBuilder
            linesToWrite.append(line).append(DEFAULT_LINE_BREAK);
        });

        // returns lines to write (limited to chunk size)
        return linesToWrite.toString();
    }
}
