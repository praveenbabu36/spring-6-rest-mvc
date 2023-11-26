package com.praveen.springproject.spring6restmvc.controller;


import com.praveen.springproject.spring6restmvc.model.Customer;
import com.praveen.springproject.spring6restmvc.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    private List<Customer> listCustomers(){

        log.debug("In Customer Controller listCustomers()...");

        return customerService.listCustomers();
    }

    @RequestMapping(value="{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {

        log.debug("In Customer Controller getCustomerById()...");

        return customerService.getCustomerById(customerId);
    }



}
