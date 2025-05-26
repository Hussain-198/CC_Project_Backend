package com.example.AdminAPI.repository;

import com.example.AdminAPI.model.ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ticketRepository extends JpaRepository<ticket, Long> {
    List<ticket> findByUserEmail(String userEmail);
}
