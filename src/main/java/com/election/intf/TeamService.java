package com.election.intf;

import com.election.model.TeamMember;

import java.util.List;

public interface TeamService {
    /**
     * Find All Players
     *
     * @param
     * @return          - List <TeamMember>
     */
    //List<TeamMember> findAll();

    /**
     * Find Player by Id and check is Player Captain or not
     *
     * @param teamId - player Id
     * @return       - String result
     */
    String whoIsCap(String teamId);

    /**
     * Update Player status as Captain
     *
     * @param teamId       - Animal Id
     * @param candidate    - TeamMember Entity
     * @return             - String result
     */
    String electCap(String teamId, TeamMember candidate);

    /**
     * Update Player status as not Captain
     *
     * @param teamId       - Animal Id
     * @return             - String result
     */
    String rejectIfIsCap(String teamId, TeamMember captain);
}
