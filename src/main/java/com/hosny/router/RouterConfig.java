package com.hosny.router;

import com.hosny.handler.CustomerHandler;
import com.hosny.handler.CustomerHandlerStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerHandlerStream handlerStream;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers)
                .GET("/router/customers/stream" , handlerStream::loadCustomers)
                .GET("/router/customers/{input}" , handler::findCustomerById)
                .POST("/router/customer/save" ,handler::saveCustomer )
                .build();
    }
}
