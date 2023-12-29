package com.praveen.springproject.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praveen.springproject.spring6restmvc.model.Beer;
import com.praveen.springproject.spring6restmvc.model.BeerStyle;
import com.praveen.springproject.spring6restmvc.service.BeerService;
import com.praveen.springproject.spring6restmvc.service.BeerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    //@Autowired
    //BeerController beerController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> uuidCaptor;

    @Captor
    ArgumentCaptor<Beer> beerCaptor;


    BeerServiceImpl beerSrvcImpl;

    @BeforeEach
    void setup(){
        beerSrvcImpl = new BeerServiceImpl();
        uuidCaptor = ArgumentCaptor.forClass(UUID.class);
    }


    //test patch method
    @Test
    void testPatchBeer() throws Exception {

        //get first beer obj
        Beer firstBeer = beerSrvcImpl.listBeers().get(0);

        //New beer object to pass in the body
        Map<String, Object> newBeer = new HashMap<>();
        newBeer.put("name", "Tiger IPA");


        //perform patch
        mockMvc.perform(
                    patch("/api/v1/beer/" + firstBeer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBeer))
                )
                .andExpect(status().isNoContent())
                ;

        verify(beerService).patchBeer(uuidCaptor.capture(), beerCaptor.capture());

        // pass
        // assertThat(newBeer.get("name")).isEqualTo(beerCaptor.getValue().getName());

        // fail
        assertThat("Fail Beer").isEqualTo(beerCaptor.getValue().getName());
    }

    //test delete beer
    @Test
    void testDeleteBeer() throws Exception {

        //get first beer obj
        Beer testBeer = beerSrvcImpl.listBeers().get(0);

        mockMvc.perform(
                    delete("/api/v1/beer/" + testBeer.getId())
                )
                .andExpect(status().isNoContent())
        ;

        verify(beerService).deleteBeerById(uuidCaptor.capture());

        assertThat(123).isEqualTo(uuidCaptor.getValue());

    }


    //test put
    @Test
    void testUpdateBeer() throws Exception{

        //get first beer obj
        Beer testBeer = beerSrvcImpl.listBeers().get(0);

        //update beer name
        testBeer.setName("Goose Island");
        testBeer.setBeerStyle(BeerStyle.LAGER);

        //perform put
        mockMvc.perform(
                    put("/api/v1/beer/" + testBeer.getId())
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(testBeer))
                )
                .andExpect(status().isNoContent())
        ;

        //to verify whether the underlying service method is called
        verify(beerService).updateBeerById(any(UUID.class), any(Beer.class));

        //negative test
        verify(beerService).listBeers();

        System.out.println(beerSrvcImpl.listBeers());
    }


    // test post
    @Test
    void testCreateNewBeer() throws Exception {

        // new beer
        Beer newBeer = Beer.builder()
                        .name("Budweiser Platinum")
                        .beerStyle(BeerStyle.LAGER)
                        .qtyOnHand(10)
                        .price(new BigDecimal(6.99))
                        .build();

        //setup methods
        given(beerService.saveBeer(any(Beer.class))).willReturn(beerSrvcImpl.saveBeer(newBeer));

        //perform post
        mockMvc.perform(
                        post("/api/v1/beer")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(newBeer))
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
        ;

        System.out.println(beerSrvcImpl.listBeers());
    }


    // test get
    @Test
    void testListBeers() throws Exception {

        //setup the methods to use when called
        given(beerService.listBeers()).willReturn(beerSrvcImpl.listBeers());

        //perform get and confirm the response has 3 json objects
        mockMvc.perform(
                    get("/api/v1/beer")
                            .accept(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)))
        ;


    }

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







