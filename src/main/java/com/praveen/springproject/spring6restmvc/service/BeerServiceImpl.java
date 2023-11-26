package com.praveen.springproject.spring6restmvc.service;

import com.praveen.springproject.spring6restmvc.model.Beer;
import com.praveen.springproject.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {


    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {

        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                        .id(UUID.randomUUID())
                        .name("Spider IPA")
                        .beerStyle(BeerStyle.IPA)
                        .qtyOnHand(25)
                        .price(new BigDecimal(10.00))
                        .build();

        Beer beer2 = Beer.builder()
                        .id(UUID.randomUUID())
                        .name("Allagash Tripel")
                        .beerStyle(BeerStyle.TRIPEL)
                        .qtyOnHand(30)
                        .price(new BigDecimal(7.00))
                        .build();

        Beer beer3 = Beer.builder()
                        .id(UUID.randomUUID())
                        .name("American Imperial Stout")
                        .beerStyle(BeerStyle.STOUT)
                        .qtyOnHand(45)
                        .price(new BigDecimal(5.00))
                        .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);

    }

    @Override
    public List<Beer> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer getBeerById(UUID id) {

        log.debug("In BeerServiceImpl. Get Beer by ID " + id);

        return beerMap.get(id);

    }
}
