package com.election.controllers;

import com.election.model.TeamMember;
import com.election.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamController {
    @Autowired
    TeamService service;

    @GetMapping("/team")
    public List<TeamMember> findAllPlayers(){
        return service.findAll();
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<?> whoIsCap(@PathVariable String teamId){
        String resp = service.whoIsCap(teamId);
        System.out.println("response: " + resp);
        return ResponseEntity.ok().body(resp);
    }

    @PutMapping("/team/{teamId}")
    public ResponseEntity <?> electCap
            (@PathVariable String teamId, @Valid @RequestBody TeamMember member) {
        String resp = service.electCap(teamId, member);
        System.out.println("response: " + resp);
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping("/team/{teamId}")
    public ResponseEntity <?> reject
            (@PathVariable String teamId) {
        String resp = service.reject(teamId);
        System.out.println("response: " + resp);
        return ResponseEntity.ok().body(resp);
    }
}
