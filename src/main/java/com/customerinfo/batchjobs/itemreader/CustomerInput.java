package com.customerinfo.batchjobs.itemreader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInput {

    private String id;

    private String firstName;

    private String lastName;

    private String gender;

    private String birthday;

    private String address;

    private String country;

}
