package com.example.AdminAPI.repository;

import com.example.AdminAPI.model.ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ticketRepository extends JpaRepository<ticket, Long> {
    List<ticket> findByUserEmail(String userEmail);
}
