package jpa0.section8.first;

import jakarta.persistence.Entity;

@Entity
public class Movie extends Item {

    private String director;
    private String actor;

    //Getter & Setter

        public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
