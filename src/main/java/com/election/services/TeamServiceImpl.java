package com.election.services;

import com.election.intf.TeamService;
import com.election.model.TeamMember;
import com.election.persistence.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

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
            if (member.get().isCap()) {
                return "Вы - кэп!";
            } else {
                for (TeamMember m : all) {
                    if (m.isCap()) {
                        return "У команды есть кэп, это: " + m.getParticipantIdentifier() + " № " + m.getParticipantId();
                    }
                }
            }
        }
        return "Если вы хотите быть капитаном, нажмите кнопку";
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
            synchronized (this){
                candidate.setCap(true);
                repository.save(candidate);
            }
        }
        return "Капитан команды назначен в rabbitmq";
    }

    public String rejectIfIsCap(String id, TeamMember captain){
        Optional <TeamMember> member = repository.findTeamMemberByTeamId(id);
        System.out.println("member " + member.get().getParticipantIdentifier());
        if (member.isPresent()){
            if (member.get().isCap()) {
                captain.setCap(false);
                repository.save(captain);
                return "Теперь Вы опять не капитан!";
            }
        }
        return "Если вы хотите быть капитаном, нажмите кнопку";
    }
}
