package com.example.demo.multitenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class CustomerRouter {
	
	@Autowired private CustomerRepository customerRepository;
	
	@Bean
	public RouterFunction<ServerResponse> postStatusReviews(){
		return RouterFunctions.route(RequestPredicates.POST("/"),request -> {
			
			
			Mono<Customer> customer = request.bodyToMono(Customer.class)
					.flatMap(cust -> {
						System.out.println(cust);
						return customerRepository.save(cust);
					});	
			return ServerResponse.ok().body(BodyInserters.fromPublisher(customer, Customer.class));});
	}
	
	
	@Bean
	public RouterFunction<ServerResponse> getStatusReviews(){
		return RouterFunctions.route(RequestPredicates.GET("/"),request -> {
			Flux<Customer> cutomers =customerRepository.findAll();			
			return ServerResponse.ok().body(BodyInserters.fromPublisher(cutomers, Customer.class));});
	}
	
	
	
	

}
