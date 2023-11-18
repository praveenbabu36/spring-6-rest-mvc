package com.praveen.springproject.spring6restmvc.service;

import com.praveen.springproject.spring6restmvc.model.Beer;
import com.praveen.springproject.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("In BeerServiceImpl. Get Beer by ID " + id);
        return Beer.builder()
                .id(id)
                .name("India Pale Ale")
                .beerStyle(BeerStyle.INDIA_PALE_ALE)
                .qtyOnHand(50)
                .price(new BigDecimal(19.99))
                .build();

    }
}
