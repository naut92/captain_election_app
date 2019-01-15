package com.election.services;

import com.election.model.TeamMember;
import com.election.persistence.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;

    public List<TeamMember> findAll(){
        return repository.findAll();
    }

    public String whoIsCap(String id){
        List<TeamMember> all = repository.findAll();
        Optional <TeamMember> member = repository.findTeamMemberByTeamId(id);
        System.out.println("member " + member.get().getParticipantIdentifier());
        if (member.isPresent()){
            for (TeamMember m : all) {
                if (m.isCap()) {
                    return "У команды есть кэп, это: № " + m.getParticipantId() + " " + m.getParticipantIdentifier();
                }
            }
        }
        return "Капитан не выбран";
    }

    public String electCap(String teamId, TeamMember candidate){
        List<TeamMember> all = repository.findAll();
        Optional <TeamMember> member = repository.findTeamMemberByTeamId(teamId);
        System.out.println("member " + member.get().getParticipantIdentifier() + " " + member.get().isCap());
        if (member.isPresent()){
            for (TeamMember m : all) {
                if (m.isCap()) {
                    return "success: " + false;
                }
            }
            candidate.setCap(true);
            repository.save(candidate);
        }
        return "капитан для команды назначен в rabbitmq";
    }

    public String reject(String teamId){
        return "Отказ. Капитан выбран";
    }
}
