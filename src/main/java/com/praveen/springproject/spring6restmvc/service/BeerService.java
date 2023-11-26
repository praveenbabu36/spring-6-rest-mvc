package com.praveen.springproject.spring6restmvc.service;

import com.praveen.springproject.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);
}
