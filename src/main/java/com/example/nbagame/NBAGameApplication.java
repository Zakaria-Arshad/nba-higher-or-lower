package com.example.nbagame;

import com.example.nbagame.domain.Player;
import com.example.nbagame.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.nbagame.service.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class NBAGameApplication {

    @Autowired
    private PlayerService playerService;

    private static final Logger log = LoggerFactory.getLogger(NBAGameApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NBAGameApplication.class, args);
    }

    @Bean // returns a CommandLineRunner Bean that automatically runs when the application is run
    public CommandLineRunner demo(){
        return (args) -> {
            log.info("Two random players");
            List<Player> PlayerList = playerService.randomPlayers();
            Player player_1 = PlayerList.get(0);
            Player player_2 = PlayerList.get(1);

            // Compare the players' stats and determine the winner
            Player winner = playerService.compareStats(player_1, player_2, "points");

            // Simulate user's answer
            String userAnswer = "Player A"; // Assuming the user entered their answer

            // Compare user's answer to the real answer and update points
            int currentPoints = 0; // Initialize currentPoints

            currentPoints = playerService.compareUserAnswer(userAnswer, winner, currentPoints);

            // Display currentPoints
            System.out.println("Current Points: " + currentPoints);

            // Simulate correct answer
            String userAnswerTwo = winner.getName();

            currentPoints = playerService.compareUserAnswer(userAnswerTwo, winner, currentPoints);

            // Display the currentPoints or perform further actions based on the result
            System.out.println("Current Points: " + currentPoints);
        };
    }
}
