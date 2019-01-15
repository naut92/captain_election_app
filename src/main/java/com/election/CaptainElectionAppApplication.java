package com.election;

import com.election.model.TeamMember;
import com.election.persistence.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CaptainElectionAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CaptainElectionAppApplication.class, args);
	}

	@Autowired
	private TeamRepository repository;

	public void run(String... strings){
		List<TeamMember> players = new ArrayList<>();

		repository.deleteAll();

		players.add(new TeamMember("1", 12L, "Player 1 fn1, ln1", false));
		players.add(new TeamMember("2", 22L, "Player 2 fn2, ln2", false));
		players.add(new TeamMember("3", 69L, "Player 3 fn3, ln3", false));
		players.add(new TeamMember("4", 11L, "Player 4 fn4, ln4", false));
		players.add(new TeamMember("5", 90L, "Player 5 fn5, ln5", false));
		players.add(new TeamMember("6", 77L, "Player 6 fn6, ln6", false));
		players.add(new TeamMember("7", 1L, "Player 7 fn7, ln7", false));

		repository.saveAll(players);
	}
}
