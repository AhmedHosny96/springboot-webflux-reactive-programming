package com.hosny.service;


import com.hosny.dao.CustomerDao;
import com.hosny.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    public List<Customer> getCustomers() {

        Long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomer();
        Long end = System.currentTimeMillis();
        System.out.println("Execution time = " + (end - start));
        return customers;

    }

    public Flux<Customer> getCustomerStream() {
        Long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomerStream();
        Long end = System.currentTimeMillis();
        System.out.println("Execution time = " + (end - start));
        return customers;

    }

}
