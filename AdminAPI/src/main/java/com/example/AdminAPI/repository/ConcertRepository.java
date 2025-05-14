package com.example.AdminAPI.repository;

import com.example.AdminAPI.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    @Query("SELECT c FROM Concert c WHERE (" +
            "(:artist IS NOT NULL AND LOWER(c.artist) LIKE %:artist%) OR " +
            "(:city IS NOT NULL AND LOWER(c.city) LIKE %:city%) OR " +
            "(:genre IS NOT NULL AND LOWER(c.genre) LIKE %:genre%)) AND " +
            "c.date >= CURRENT_DATE")
    List<Concert> searchConcerts(
            @Param("artist") String artist,
            @Param("city") String city,
            @Param("genre") String genre
    );
}
