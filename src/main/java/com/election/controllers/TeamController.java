package com.election.controllers;

import com.election.intf.TeamService;
import com.election.model.TeamMember;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    //For dev mode only:
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

    @PutMapping("/team/reject/{teamId}")
    public ResponseEntity <?> rejectIfIsCap
            (@PathVariable String teamId, @Valid @RequestBody TeamMember member) {
        String resp = service.rejectIfIsCap(teamId, member);
        System.out.println("response: " + resp);
        return ResponseEntity.ok().body(resp);
    }
}
