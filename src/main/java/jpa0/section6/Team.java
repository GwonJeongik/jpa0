package jpa0.section6;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 단방향 연관관계
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String teamname;

    @OneToMany(mappedBy = "team")
    List<Fellow> fellows = new ArrayList<>();

    //Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public List<Fellow> getFellows() {
        return fellows;
    }

    public void setFellows(List<Fellow> fellows) {
        this.fellows = fellows;
    }
}
