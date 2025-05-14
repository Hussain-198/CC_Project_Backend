package com.example.AdminAPI.service;


import com.example.AdminAPI.model.ticket;
import com.example.AdminAPI.repository.ticketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ticketService {

    @Autowired
    private ticketRepository ticketRepository;

    public ticket saveTicket(ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List<ticket> getTicketsByUserEmail(String email){
        return ticketRepository.findByUserEmail(email);
    }
}
