package com.election.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TeamMember")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamMember {
    @Id
    private String teamId;
    private Long participantId;
    private String participantIdentifier;
    boolean isCap;
}