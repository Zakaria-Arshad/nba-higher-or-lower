package com.example.nbagame.repository;

import com.example.nbagame.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    // randomly select two players from stats table and store them in a list
    @Query(value = "SELECT * FROM stats ORDER BY RAND() LIMIT 2", nativeQuery = true)
    List<Player> findRandomPlayers();
}

