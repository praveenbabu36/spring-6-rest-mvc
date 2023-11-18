package com.praveen.springproject.spring6restmvc.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/*
 Using LOMBOK
 */

@Data
public class Beer {
    private UUID id;
    private String name;
    private BeerStyle beerStyle;
    private Integer qtyOnHand;
    private BigDecimal price;
}
