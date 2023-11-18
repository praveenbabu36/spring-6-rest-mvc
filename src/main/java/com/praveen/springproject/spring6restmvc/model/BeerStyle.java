package com.praveen.springproject.spring6restmvc.model;

import lombok.Data;

import java.util.UUID;

@Data
public class BeerStyle {

    private UUID id;
    private String style;
}
