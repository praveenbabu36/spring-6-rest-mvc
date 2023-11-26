package com.praveen.springproject.spring6restmvc.controller;

import com.praveen.springproject.spring6restmvc.model.Beer;
import com.praveen.springproject.spring6restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@AllArgsConstructor
@RestController
public class BeerController {

    private final BeerService beerService;

    public Beer getBeerById(UUID id){
        log.debug("In BeerController. Get Beer by Id.");
        return beerService.getBeerById(id);
    }
    public Beer getMyFavBeer() {

        log.debug("In BeerController. Get my Fav. Beer.");
        return beerService.getBeerById(UUID.randomUUID());
    }

    //list beers
    @RequestMapping("/api/v1/beer")
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }
}
