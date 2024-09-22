package jpa0.section6;

import jakarta.persistence.*;

/**
 * 1. 단방향 연관관계
 *
 * @ManyToOne : 하나의 `Team`은 여러명의 `Fellow`를 가질 수 있다. -> `Team`은 `One`의 입장, `Fellow`는 `Many`의 입장이다.
 * @JoinColumn(name="team_id") : `JPA`가 조인할 때 사용할 `외래 키`를 지정해준다.
 * 현재, `Fellow`만 `Team`에 연관관계를 가지고 있다.
 */
@Entity
public class Fellow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fellow_id")
    private Long id;
    private String fellowname;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    //Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFellowname() {
        return fellowname;
    }

    public void setFellowname(String fellowname) {
        this.fellowname = fellowname;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
