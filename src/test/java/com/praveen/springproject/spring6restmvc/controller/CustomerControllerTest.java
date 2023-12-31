package com.praveen.springproject.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praveen.springproject.spring6restmvc.model.Customer;
import com.praveen.springproject.spring6restmvc.service.CustomerService;
import com.praveen.springproject.spring6restmvc.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import static org.hamcrest.core.Is.is;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl custSrvcImpl;

    @Captor
    ArgumentCaptor<UUID> uuidCaptor;

    @Captor
    ArgumentCaptor<Customer> customerCaptor;


    @BeforeEach
    void setup() {
        custSrvcImpl = new CustomerServiceImpl();
    }



    // test PATCH method
    @Test
    void patchCustomer() throws Exception {

        //get first customer obj
        Customer cust = custSrvcImpl.listCustomers().get(0);

        // Customer Object to use to update existing
        Map custMap = new HashMap<>();
        custMap.put("name", "Lance");

        // perform patch
        mockMvc.perform(
                    patch("/api/v1/customer/" + cust.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(custMap))
                )
                .andExpect(status().isNoContent())
                ;

        // capture arg and verify
        verify(customerService).patchCustomer(uuidCaptor.capture(), customerCaptor.capture());

        // pass
        //assertThat(custMap.get("name")).isEqualTo(customerCaptor.getValue().getName())

        // fail
        assertThat("Mathew").isEqualTo(customerCaptor.getValue().getName());

    }


    // test DELETE method
    @Test
    void deleteCustomer() throws Exception {

        //get first customer obj
        Customer cust = custSrvcImpl.listCustomers().get(0);

        mockMvc.perform(
                    delete("/api/v1/customer/" + cust.getId())
                )
                .andExpect(status().isNoContent())
        ;

        verify(customerService).deleteCustomer(uuidCaptor.capture());

        //pass
        //assertThat(cust.getId()).isEqualTo(uuidCaptor.getValue());

        //fail
        assertThat(123).isEqualTo(uuidCaptor.getValue());
    }


    //test update customer
    @Test
    void updateCustomer() throws Exception {

        //get first customer obj
        Customer cust = custSrvcImpl.listCustomers().get(0);
        cust.setComments("Prefers Lager");
        cust.setName("Mr. Green");

        //perform put
        mockMvc.perform(
                    put("/api/v1/customer/" + cust.getId())
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(cust))
                )
                .andExpect(status().isNoContent())
        ;

        //to verify whether the underlying service method is called
        verify(customerService).updateCustomer(any(UUID.class), any(Customer.class));

        System.out.println(custSrvcImpl.listCustomers());
    }


    //test create new customer
    @Test
    void createNewCustomer() throws Exception {

        //new customer
        Customer newCust = Customer.builder()
                                .name("Rock")
                                .createdDate(new Date())
                                .comments("Like Brown ALE")
                                .build();

        //setup methods
        given(customerService.saveCustomer(any(Customer.class))).willReturn(custSrvcImpl.saveCustomer(newCust));

        //perform post
        mockMvc.perform(
                        post("/api/v1/customer")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(newCust))
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
        ;

        System.out.println(custSrvcImpl.listCustomers());
    }

    //test get customer list
    @Test
    void listCustomers() throws Exception {

        //setup methods to use when called
        given(customerService.listCustomers()).willReturn(custSrvcImpl.listCustomers());

        //perform get and confirm the response has 2 customer json objects
        mockMvc.perform(
                    get("/api/v1/customer")
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)))
        ;

    }


    @Test
    void getCustomerById() throws Exception {

        Customer tempCust = custSrvcImpl.listCustomers().get(0);

        //setup method to use when called
        given(customerService.getCustomerById(tempCust.getId())).willReturn(tempCust);

        mockMvc.perform(
                    get("/api/v1/customer/" + tempCust.getId())
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Praveen")))
        ;

    }

}















