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

    //list customers
    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    //list customer by id
    @Override
    public Customer getCustomerById(UUID id) {
        log.debug("In CustomerServiceImpl. ");
        return customerMap.get(id);
    }


    //save customer
    @Override
    public Customer saveCustomer(Customer customer){

        Customer newCustomer = Customer.builder()
                                    .id(UUID.randomUUID())
                                    .name(customer.getName())
                                    .createdDate(new java.util.Date())
                                    .comments(customer.getComments())
                                    .build();

        customerMap.put(newCustomer.getId(), newCustomer);

        return customerMap.get(newCustomer.getId());
    }


    //Update customer
    @Override
    public void updateCustomer(UUID id, Customer customer) {

        Customer existing = getCustomerById(id);

        existing.setName(customer.getName());
        existing.setComments(customer.getComments());
    }


    //delete customer by id
    @Override
    public void deleteCustomer(UUID customerId) {
        customerMap.remove(customerId);
    }
}
