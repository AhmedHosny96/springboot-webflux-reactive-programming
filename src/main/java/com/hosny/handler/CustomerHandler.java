package com.hosny.handler;


import com.hosny.dao.CustomerDao;
import com.hosny.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao dao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customerList = dao.getCustomerList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> findCustomerById(ServerRequest request) {

        // get the path variable
        Integer customerId = Integer.valueOf(request.pathVariable("input"));
        Mono<Customer> customerMono = dao.getCustomerList().filter(
                customer -> customer.getId() == customerId
        ).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {

        Mono<Customer> customer = request.bodyToMono(Customer.class);
        Mono<String> customerMono = customer.map(dto -> dto.getId() + ":" + dto.getName());

        return ServerResponse.ok().body(customerMono, Customer.class);
    }
}
