package com.praveen.springproject.spring6restmvc.controller;


import com.praveen.springproject.spring6restmvc.model.Customer;
import com.praveen.springproject.spring6restmvc.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    // list customers
    @RequestMapping(method = RequestMethod.GET)
    private List<Customer> listCustomers(){

        log.debug("In Customer Controller listCustomers()...");

        return customerService.listCustomers();
    }

    //list customers by id
    @RequestMapping(value="{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {

        log.debug("In Customer Controller getCustomerById()...1234");

        return customerService.getCustomerById(customerId);
    }

    //Create new customer
    @PostMapping
    public ResponseEntity saveCustomer(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }


    //Update existing customer
    @PutMapping(value="{customerId}")
    public ResponseEntity updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {

        customerService.updateCustomer(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
