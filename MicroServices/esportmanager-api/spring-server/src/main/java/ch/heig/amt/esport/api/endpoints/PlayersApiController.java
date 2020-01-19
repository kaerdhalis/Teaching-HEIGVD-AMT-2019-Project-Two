package ch.heig.amt.esport.api.endpoints;

import ch.heig.amt.esport.entities.PlayerEntity;
import ch.heig.amt.esport.repositories.PlayerRepository;
import ch.heig.amt.esport.api.PlayersApi;
import ch.heig.amt.esport.api.model.Player;
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
public class PlayersApiController implements PlayersApi {

    @Autowired
    PlayerRepository playerRepository;
    /**
     * creates a player and saves it to the database
     * @param player the player to be added
     * @return a response entity corresponding to the success or failure of the operation
     */
    public ResponseEntity<Object> createPlayer(@ApiParam(value = "", required = true) @Valid @RequestBody Player player) {
        PlayerEntity newPlayerEntity = toPlayerEntity(player);
        playerRepository.save(newPlayerEntity);
        Long id = newPlayerEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPlayerEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    /**
     *  Deletes a player using a player id
     * @param playerId the id of the player to delete
     * @return a response entity corresponding to the success or failure of the operation
     */

    public ResponseEntity deletePlayer(long playerId) {

        if(playerRepository.existsById(playerId)) {
            playerRepository.deleteById(playerId);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * edits a player using a player id and new player valuse
     * @param playerId the id of the player to edit
     * @param player the new values
     * @return a response entity corresponding to the success or failure of the operation
     */

    public ResponseEntity editPlayer(long playerId,Player player) {

        if(playerRepository.existsById(playerId)) {
            PlayerEntity playerEntity =playerRepository.findById(playerId).get();
            playerEntity.setLast_name(player.getLastName());
            playerEntity.setFirst_name(player.getFirstName());
            playerEntity.setGamertag(player.getGamertag());
            playerRepository.save(playerEntity);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * get a player by its id
     * @param playerId the id of the player we want to fetch
     * @return a response entity corresponding to the success or failure of the operation
     */
    public ResponseEntity<Player> getPlayer(long playerId) {

        if(playerRepository.existsById(playerId)) {
            Player player = toPlayer(playerRepository.findById(playerId).get());
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  gets all the existing players
     * @return a response entity with a list of all the players
     */
    public ResponseEntity<List<Player>> getPlayers() {
        List<Player> players = new ArrayList<>();
        for (PlayerEntity playerEntity : playerRepository.findAll()) {
            players.add(toPlayer(playerEntity));
        }

        return ResponseEntity.ok(players);
    }

    /**
     * transforms a player into a TeamEntity
     * @param team the player to transform
     * @return the corresponding teamEntity
     */
    private PlayerEntity toPlayerEntity(Player player) {
        PlayerEntity entity = new PlayerEntity();
        entity.setFirst_name(player.getFirstName());
        entity.setLast_name(player.getLastName());
        entity.setGamertag(player.getGamertag());
        return entity;
    }
    /**
     * * transforms a PlayerEntity into a player
     * @param entity the PlayerEntity to transform
     * @return the corresponding Player
     */
    private Player toPlayer(PlayerEntity entity) {
        Player player = new Player();
        player.setFirstName(entity.getFirst_name());
        player.setLastName(entity.getLast_name());
        player.setGamertag(entity.getGamertag());
        return player;
    }

}
