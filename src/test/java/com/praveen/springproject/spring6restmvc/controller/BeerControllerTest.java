package com.praveen.springproject.spring6restmvc.controller;

import com.praveen.springproject.spring6restmvc.model.Beer;
import com.praveen.springproject.spring6restmvc.service.BeerService;
import com.praveen.springproject.spring6restmvc.service.BeerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.BDDMockito.given;

import java.util.UUID;

import static org.hamcrest.core.Is.is;

//@SpringBootTest
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    //@Autowired
    //BeerController beerController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    BeerServiceImpl beerSrvcImpl = new BeerServiceImpl();

    @Test
    void getBeerById() throws Exception {
        /*System.out.println(
                beerController.getBeerById(UUID.randomUUID())
        );*/

        System.out.println(beerSrvcImpl.listBeers());

        //Get first beer
        Beer testBeer = beerSrvcImpl.listBeers().get(0);

        //return the testBeer for any request to getBeerById() call
        given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer);

        mockMvc.perform(
                    get("/api/v1/beer/" + testBeer.getId())
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Golden Monkey Tripel")))
                ;

        System.out.println("Mock Test Completed");

    }

    @Test
    void getMyFavBeer() {

        /*System.out.println(
                beerController.getMyFavBeer()
        );*/
    }
}