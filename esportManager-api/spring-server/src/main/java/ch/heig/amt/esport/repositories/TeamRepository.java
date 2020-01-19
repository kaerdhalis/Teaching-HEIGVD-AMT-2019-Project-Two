package ch.heig.amt.esport.repositories;

import ch.heig.amt.esport.entities.TeamEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface TeamRepository extends CrudRepository<TeamEntity, Long>{

}
