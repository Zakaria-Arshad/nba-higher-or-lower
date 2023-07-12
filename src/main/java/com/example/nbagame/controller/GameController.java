package com.example.nbagame.controller;

import com.example.nbagame.domain.Player;
import com.example.nbagame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GameController {

    private final PlayerService playerService;

    @Autowired
    public GameController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/")
    public String playGame(Model model) {
        // Retrieve two random players
        List<Player> playerList = playerService.randomPlayers();
        Player player1 = playerList.get(0);
        Player player2 = playerList.get(1);

        // Compare the players' stats and determine the winner
        Player winner = playerService.compareStats(player1, player2, "points");

        // Simulate user's answer
        String userAnswer = "Player A"; // Assuming the user entered their answer

        // Compare user's answer to the real answer and update points
        int currentPoints = 0; // Initialize currentPoints

        currentPoints = playerService.compareUserAnswer(userAnswer, winner, currentPoints);

        // Display currentPoints
        model.addAttribute("points", currentPoints);

        // Return the name of the HTML template to render
        return "game-result";
    }
}

