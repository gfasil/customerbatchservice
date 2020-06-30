package com.customerinfo.batchjobs.Util;

import com.customerinfo.batchjobs.itemreader.CustomerInput;

import java.util.function.BiPredicate;

public class FilterCustomer {

    public static BiPredicate<CustomerInput,String> filterCustomer=(customer, country)-> country.equals(customer.getCountry());

}
