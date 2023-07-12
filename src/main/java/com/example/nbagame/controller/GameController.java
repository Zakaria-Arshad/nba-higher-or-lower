package com.example.nbagame.controller;

import com.example.nbagame.domain.Player;
import com.example.nbagame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GameController {

    private final PlayerService playerService;
    private int points = 0;
    private Player player1;
    private Player player2;
    private Player winner;

    @Autowired
    public GameController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/")
    public String playGame(Model model) {
        // add points
        model.addAttribute("points", points);
        // retrieve two random players
        List<Player> playerList = playerService.randomPlayers();
        player1 = playerList.get(0);
        player2 = playerList.get(1);

        // add players to the model
        model.addAttribute("player1", player1);
        model.addAttribute("player2", player2);

        // compare the players' stats and determine the winner
        winner = playerService.compareStats(player1, player2, "points");

        // return the name of the HTML template to render
        return "game";
    }

    @PostMapping("/game/submit") // @PostMapping used for submitting data
    public String submitAnswer(Model model, @RequestParam("answer") String userAnswer) {
        // compare user's answer to the real answer and update points
        boolean playerGotQuestionCorrect = playerService.compareUserAnswer(userAnswer, winner);

        if (playerGotQuestionCorrect) {
            points += 1;
        } else {
            return "redirect:/end";
        }

        // redirect back to game
        return "redirect:/";
    }

    @GetMapping("/end")
    public String endGame(Model model) {
        // add the final points and winner to the model
        model.addAttribute("points", points);

        // return the name of the HTML template to render
        return "game-end";
    }
}


