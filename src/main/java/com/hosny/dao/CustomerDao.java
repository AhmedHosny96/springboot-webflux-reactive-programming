package com.hosny.dao;


import com.hosny.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<Customer> getCustomer() {
        List<Customer> customers = IntStream.rangeClosed(0, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println(i))
                .mapToObj(i -> new Customer(i, "customer name")).collect(Collectors.toList());

        return customers;
    }

    public Flux<Customer> getCustomerStream() {
        Flux<Customer> customers = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(count -> System.out.println("count = " + count))
                .map(c -> new Customer(c, "customer name" + c));
        return customers;
    }

    public Flux<Customer> getCustomerList() {
        Flux<Customer> customers = Flux.range(1, 10)
                .doOnNext(count -> System.out.println("count = " + count))
                .map(c -> new Customer(c, "customer name" + c));
        return customers;
    }
}
