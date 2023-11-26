package com.praveen.springproject.spring6restmvc.service;

import com.praveen.springproject.spring6restmvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{


    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                        .id(UUID.randomUUID())
                        .name("Praveen")
                        .createdDate(new Date())
                        .comments("Prefers Stout")
                        .build();

        Customer customer2 = Customer.builder()
                        .id(UUID.randomUUID())
                        .name("Raj")
                        .createdDate(new Date())
                        .comments("Like LAGER, PORTER")
                        .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        log.debug("In CustomerServiceImpl. ");
        return customerMap.get(id);
    }
}
