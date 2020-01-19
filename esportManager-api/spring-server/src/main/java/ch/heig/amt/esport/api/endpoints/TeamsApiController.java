package ch.heig.amt.esport.api.endpoints;

import ch.heig.amt.esport.api.TeamsApi;
import ch.heig.amt.esport.api.model.Team;
import ch.heig.amt.esport.entities.TeamEntity;
import ch.heig.amt.esport.repositories.TeamRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class TeamsApiController implements TeamsApi {

    @Autowired
    TeamRepository teamRepository;

    /**
     * creates a team and saves it to the database
     * @param team the team to be added
     * @return a response entity corresponding to the success or failure of the operation
     */
    public ResponseEntity<Object> createTeam(@ApiParam(value = "", required = true) @Valid @RequestBody Team team) {
        TeamEntity newTeamEntity = toTeamEntity(team);
        teamRepository.save(newTeamEntity);
        Long id = newTeamEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newTeamEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * get a team by its id
     * @param teamId the id of the team we want to fetch
     * @return a response entity corresponding to the success or failure of the operation
     */
    public ResponseEntity<Team> getTeam(long teamId) {

        if(teamRepository.existsById(teamId)) {
            Team team = toTeam(teamRepository.findById(teamId).get());
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * edits a team using a team id and new team valuse
     * @param teamId the id of the team to edit
     * @param team the new values
     * @return a response entity corresponding to the success or failure of the operation
     */
    public ResponseEntity editTeam(long teamId,Team team) {

        if(teamRepository.existsById(teamId)) {
            TeamEntity teamEntity =teamRepository.findById(teamId).get();
            teamEntity.setName(team.getName());

            teamRepository.save(teamEntity);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Deletes a team using a team id
     * @param teamId the id of the team to delete
     * @return a response entity corresponding to the success or failure of the operation
     */
    public ResponseEntity deleteTeam(long teamId) {

        if(teamRepository.existsById(teamId)) {
            teamRepository.deleteById(teamId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  gets all the existing teams
     * @return a response entity with a list of all the teams
     */
    public ResponseEntity<List<Team>> getTeams() {
        List<Team> teams = new ArrayList<>();
        for (TeamEntity teamEntity : teamRepository.findAll()) {
            teams.add(toTeam(teamEntity));
        }

        return ResponseEntity.ok(teams);
    }

    /**
     * transforms a Team into a TeamEntity
     * @param team the team to transform
     * @return the corresponding teamEntity
     */

    private TeamEntity toTeamEntity(Team team) {
        TeamEntity entity = new TeamEntity();
        entity.setName(team.getName());

        return entity;
    }

    /**
     * * transforms a TeamEntity into a Team
     * @param entity the teamentity to transform
     * @return the corresponding team
     */
    private Team toTeam(TeamEntity entity) {
        Team team = new Team();
        team.setName(entity.getName());

        return team;
    }

}
