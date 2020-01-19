package ch.heig.amt.esport.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Luca de Carvalho
 */
@Entity
public class TeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String gamertag) {
        this.name = name;
    }
}
