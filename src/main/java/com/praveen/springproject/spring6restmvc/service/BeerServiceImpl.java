package com.praveen.springproject.spring6restmvc.service;

import com.praveen.springproject.spring6restmvc.model.Beer;
import com.praveen.springproject.spring6restmvc.model.BeerStyle;

import java.math.BigDecimal;
import java.util.UUID;

public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        return Beer.builder()
                .id(id)
                .name("India Pale Ale")
                .beerStyle(BeerStyle.INDIA_PALE_ALE)
                .qtyOnHand(50)
                .price(new BigDecimal(19.99))
                .build();

    }
}
