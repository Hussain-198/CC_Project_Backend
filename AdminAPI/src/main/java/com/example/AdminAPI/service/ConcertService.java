package com.example.AdminAPI.service;

import com.example.AdminAPI.model.Concert;
import com.example.AdminAPI.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    public List<Concert> getALlConcerts() {
        return concertRepository.findAll();
    }

    public Optional<Concert> getConcertById(Long id) {
        return concertRepository.findById(id);
    }

    public Concert addConcert(Concert concert) {
        return concertRepository.save(concert);
    }

    public Concert updateConcert(Long id, Concert concertDetails) {
        return concertRepository.findById(id).map(concert -> {
            concert.setArtist(concertDetails.getArtist());
            concert.setDate(concertDetails.getDate());
            concert.setVenue(concertDetails.getVenue());
            concert.setCity(concertDetails.getCity());
            concert.setTime(concertDetails.getTime());
            concert.setGenre(concertDetails.getGenre());
            concert.setImageUrl(concertDetails.getImageUrl());
            concert.setTicketprice(concertDetails.getTicketprice());
            return concertRepository.save(concert);
        }).orElseThrow(() -> new RuntimeException("Concert not found with id: " + id));
    }

    public void deleteConcert(Long id) {
        concertRepository.deleteById(id);
    }

    public List<Concert> searchConcerts(String artist, String city, String genre){
        return concertRepository.searchConcerts(
                artist != null ? artist.toLowerCase() : null,
                city != null ? city.toLowerCase() : null,
                genre != null ? genre.toLowerCase() : null
        );
    }
}
