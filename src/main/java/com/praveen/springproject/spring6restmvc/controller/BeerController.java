package com.praveen.springproject.spring6restmvc.controller;

import com.praveen.springproject.spring6restmvc.model.Beer;
import com.praveen.springproject.spring6restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
@Controller
public class BeerController {

    private final BeerService beerService;

    public Beer getMyFavBeer() {
        return beerService.getBeerById(UUID.randomUUID());
    }
}
