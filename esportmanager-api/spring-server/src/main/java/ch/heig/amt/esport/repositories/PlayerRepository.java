package ch.heig.amt.esport.repositories;

import ch.heig.amt.esport.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface PlayerRepository extends CrudRepository<PlayerEntity, Long>{

}
