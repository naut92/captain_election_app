package com.election.persistence;

import com.election.model.TeamMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends MongoRepository<TeamMember, Long> {
    Optional<TeamMember> findTeamMemberByTeamId(String teamId);
}
