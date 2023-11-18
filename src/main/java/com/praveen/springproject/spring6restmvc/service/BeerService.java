package com.praveen.springproject.spring6restmvc.service;

import com.praveen.springproject.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);
}
