package com.customerinfo.batchjobs.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Customer {

    private UUID id;

    private String firstName;

    private String lastName;

    private Gender gender;

    private LocalDate birthday;

    private Address address;

    private boolean foreign;

}
