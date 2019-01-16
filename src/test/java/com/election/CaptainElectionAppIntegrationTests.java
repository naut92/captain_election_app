package com.election;

import com.election.model.TeamMember;
import com.election.persistence.TeamRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaptainElectionAppIntegrationTests {
	@Autowired
	private TestRestTemplate template;
	@Autowired
	private TeamRepository repository;

	private static final String URI = "/api/team/";

	@Before
	public void init() {
		List<TeamMember> players = new ArrayList<>();
		repository.deleteAll();
		players.add(new TeamMember("5", 90L, "Player 5 fn5, ln5", true));
		repository.saveAll(players);
	}

	@Test
	public void whoIsCap() {
		ResponseEntity<String> responseEntity = template.getForEntity(URI + "{id}",
				String.class, 5);
		int status = responseEntity.getStatusCodeValue();
		String resultById = responseEntity.getBody();

		Assert.assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
		Assert.assertNotNull(resultById);
		Assert.assertEquals("Вы - кэп!", resultById);
	}

	@Test
	public void electCap() {
		TeamMember member = new TeamMember();
		member.setTeamId("5");
		member.setParticipantId(17L);
		member.setParticipantIdentifier("some test fn, secn");
		member.setCap(true);

		HttpEntity<TeamMember> requestEntity = new HttpEntity<>(member);
		ResponseEntity<Void> responseEntity = template.exchange(URI + 5, HttpMethod.PUT, requestEntity, Void.class);

		int status = responseEntity.getStatusCodeValue();
		Assert.assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
	}

	@Test
	public void rejectIfIsCap() {
		TeamMember member = new TeamMember();
		member.setTeamId("5");
		member.setParticipantId(17L);
		member.setParticipantIdentifier("some test fn, sn");
		member.setCap(false);

		HttpEntity<TeamMember> requestEntity = new HttpEntity<>(member);
		ResponseEntity<Void> responseEntity = template.exchange(URI + "/reject/" + 5, HttpMethod.PUT, requestEntity, Void.class);

		int status = responseEntity.getStatusCodeValue();
		Assert.assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
	}

}

