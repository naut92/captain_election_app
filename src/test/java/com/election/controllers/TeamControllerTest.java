package com.election.controllers;

import com.election.model.TeamMember;
import org.junit.Assert;
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
import java.util.Collection;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {
    @Autowired
    private TestRestTemplate template;

    private static final String URI = "/api/team/";

    @Test
    public void whoIsCap() {
        ResponseEntity<String> responseEntity = template.getForEntity(URI + "{id}",
                String.class, 5);
        int status = responseEntity.getStatusCodeValue();
        String resultById = responseEntity.getBody();

        Assert.assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
        Assert.assertNotNull(resultById);
        Assert.assertEquals("Если вы хотите быть капитаном, нажмите кнопку", resultById);
    }

    @Test
    public void electCap() {

        HttpEntity<String> requestEntity = new HttpEntity<>("Капитан команды назначен в rabbitmq");

        ResponseEntity<Void> responseEntity = template.exchange(URI + 5, HttpMethod.PUT, requestEntity, Void.class);

        int status = responseEntity.getStatusCodeValue();
        Assert.assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
    }

    @Test
    public void rejectIfIsCap() {
    }
}