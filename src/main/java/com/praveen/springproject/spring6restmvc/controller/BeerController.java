package com.praveen.springproject.spring6restmvc.controller;

import com.praveen.springproject.spring6restmvc.model.Beer;
import com.praveen.springproject.spring6restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
@RestController
//@RequestMapping("/api/v1/beer") -- replace this with mathod level mapping
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    public Beer getMyFavBeer() {
        log.debug("In BeerController. Get my Fav. Beer.");
        return beerService.getBeerById(UUID.randomUUID());
    }

    //list beers
    // Use RequestMapping or GetMapping
    // @RequestMapping(value = BEER_PATH, method = RequestMethod.GET)
    @GetMapping(value = BEER_PATH)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }


    //list bear by id
    // Use RequestMapping or GetMapping
    //@RequestMapping(value="{beerId}", method = RequestMethod.GET)
    @GetMapping(value=BEER_PATH_ID)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId){
        log.debug("In BeerController. Get Beer by Id.");
        return beerService.getBeerById(beerId);
    }

    //create new bear
    @PostMapping(value=BEER_PATH)
    public ResponseEntity saveBeer(@RequestBody Beer beer) {

        // save and get the saved one
        Beer savedBear = beerService.saveBeer(beer);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location", BEER_PATH + savedBear.getId() );

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }


    //update exitingg beer
    @PutMapping(value=BEER_PATH_ID)
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId,  @RequestBody Beer beer) {

        beerService.updateBeerById(beerId, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    //delete beer
    @DeleteMapping(value=BEER_PATH_ID)
    public ResponseEntity deleteBeer(@PathVariable("beerId") UUID beerId) {

        beerService.deleteBeerById(beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



    // update method for patch request
    @PatchMapping(value=BEER_PATH_ID)
    public ResponseEntity patchBeer(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {

        beerService.patchBeer(beerId, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
