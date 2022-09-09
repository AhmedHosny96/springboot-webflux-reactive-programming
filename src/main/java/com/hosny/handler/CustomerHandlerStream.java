package com.hosny.handler;

import com.hosny.dao.CustomerDao;
import com.hosny.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandlerStream {

        @Autowired
        private CustomerDao dao;

        public Mono<ServerResponse> loadCustomers(ServerRequest request){
            Flux<Customer> customerList = dao.getCustomerStream();
            return ServerResponse
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(customerList, Customer.class);

        }
    }


