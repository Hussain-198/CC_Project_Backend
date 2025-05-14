package com.example.AdminAPI.controller;


import com.example.AdminAPI.model.ticket;
import com.example.AdminAPI.service.ticketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class ticketController {

    @Autowired
    private ticketService ticketService;

    @PostMapping
    public ticket saveTicket(@RequestBody ticket ticket){
        return ticketService.saveTicket(ticket);
    }

    @GetMapping("/user/{email}")
    public List<ticket> getTicketsByUserEmail(@PathVariable String email){
        try{
            return ticketService.getTicketsByUserEmail(email);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching tickets", e);
        }
    }
}
