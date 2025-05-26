package com.example.AdminAPI.controller;

import com.example.AdminAPI.model.Concert;
import com.example.AdminAPI.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/concerts")
@CrossOrigin("*")
public class ConcertController {

    @Autowired
    private ConcertService concertService;


    @GetMapping
    public ResponseEntity<List<Concert>> getAllConcerts() {
        List<Concert> concerts = concertService.getAllConcerts();
        return new ResponseEntity<>(concerts, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Concert> getConcertById(@PathVariable Long id) {
        Optional<Concert> concert = concertService.getConcertById(id);
        return concert.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Concert>> searchConcerts(
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String genre
    )

    {
        List<Concert> results = concertService.searchConcerts(artist, city, genre);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Concert> addConcert(@RequestBody Concert concert) {
        System.out.println("Adding New Concert:");
        System.out.println("Artist: " + concert.getArtist());
        System.out.println("Date: " + concert.getDate());
        System.out.println("Ticket Prices: " + concert.getTicketprice());


        concert.setId(null);

        Concert savedConcert = concertService.addConcert(concert);
        return new ResponseEntity<>(savedConcert, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Concert> updateConcert(@PathVariable Long id, @RequestBody Concert concert) {
        Concert updatedConcert = concertService.updateConcert(id, concert);
        return new ResponseEntity<>(updatedConcert, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConcert(@PathVariable Long id) {
        concertService.deleteConcert(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
