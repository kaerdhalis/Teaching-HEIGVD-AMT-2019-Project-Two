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

    public ResponseEntity<Object> createPlayer(@ApiParam(value = "", required = true) @Valid @RequestBody Player player) {
        PlayerEntity newPlayerEntity = toPlayerEntity(player);
        playerRepository.save(newPlayerEntity);
        Long id = newPlayerEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPlayerEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    public ResponseEntity deletePlayer(long playerId) {

        if(playerRepository.existsById(playerId)) {
            playerRepository.deleteById(playerId);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


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
    public ResponseEntity<Player> getPlayer(long playerId) {

        if(playerRepository.existsById(playerId)) {
            Player player = toPlayer(playerRepository.findById(playerId).get());
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<List<Player>> getPlayers() {
        List<Player> players = new ArrayList<>();
        for (PlayerEntity playerEntity : playerRepository.findAll()) {
            players.add(toPlayer(playerEntity));
        }

        return ResponseEntity.ok(players);
    }


    private PlayerEntity toPlayerEntity(Player player) {
        PlayerEntity entity = new PlayerEntity();
        entity.setFirst_name(player.getFirstName());
        entity.setLast_name(player.getLastName());
        entity.setGamertag(player.getGamertag());
        return entity;
    }

    private Player toPlayer(PlayerEntity entity) {
        Player player = new Player();
        player.setFirstName(entity.getFirst_name());
        player.setLastName(entity.getLast_name());
        player.setGamertag(entity.getGamertag());
        return player;
    }

}
