package com.example.AdminAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "concert_details")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String artist;
    private LocalDate date;
    private String venue;
    private String city;
    private String time;
    private String genre;
    @Column(name = "imageurl")
    private String imageUrl;

    @ElementCollection
    @CollectionTable(
            name = "concert_ticket_prices",
            joinColumns = @JoinColumn(name = "concert_id")
    )
    @MapKeyColumn(name = "ticket_type")
    @Column(name = "ticket_price")
    private Map<String, Integer> ticketprice = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Map<String, Integer> getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(Map<String, Integer> ticketprice) {
        this.ticketprice = ticketprice;
    }
}
