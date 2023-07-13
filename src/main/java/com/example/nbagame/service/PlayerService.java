package com.example.nbagame.service;

import com.example.nbagame.domain.Player;
import com.example.nbagame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired // injects PlayerRepository into PlayerService. Ensures dependency of PlayerRepository is provided
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> randomPlayers() {
        List<Player> playerList = playerRepository.findRandomPlayers();
        return playerList;
    }

    public Player compareStats(Player player1, Player player2, String statToCheck) {
        switch (statToCheck) {
            case "points":
                return player1.getPoints().compareTo(player2.getPoints()) > 0 ? player1 : player2;
            case "assists":
                return player1.getAssists().compareTo(player2.getAssists()) > 0 ? player1 : player2;
            case "threePtPercentage":
                return player1.getThreePtPercentage().compareTo(player2.getThreePtPercentage()) > 0 ? player1 : player2;
            default:
                throw new IllegalArgumentException("Invalid stat to check");
        }
    }
    public boolean compareUserAnswer(String userAnswer, Player realAnswer) {
        if (userAnswer.equalsIgnoreCase(realAnswer.getName())) {
            return true; // return true if correct answer
        } else {
            return false; // return false otherwise
        }
    }



}

