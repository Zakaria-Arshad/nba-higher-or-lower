package com.example.nbagame;

import com.example.nbagame.domain.Player;
import com.example.nbagame.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class NBAGameApplication {

    private static final Logger log = LoggerFactory.getLogger(NBAGameApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NBAGameApplication.class, args);
    }

    @Bean // returns a CommandLineRunner Bean that automatically runs when the application is run
    public CommandLineRunner demo(PlayerRepository repository){
        return (args) -> {
            log.info("Two random players");
            List<Player> PlayerList = repository.findRandomPlayers();
            Player player_1 = PlayerList.get(0);
            Player player_2 = PlayerList.get(1);
            log.info(player_1.getName());
            log.info(player_1.getTeam());
            log.info(player_1.getAge().toString());
            log.info(player_2.getName());
            log.info(player_2.getTeam());
            log.info(player_2.getAge().toString());

        };
    }

}
