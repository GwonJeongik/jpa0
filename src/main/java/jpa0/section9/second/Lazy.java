package jpa0.section9.second;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.section6.Fellow;
import jpa0.section6.Team;

import java.util.List;

/**
 * 2. 즉시로딩과 지연로딩
 * <p>
 * 즉시 로딩 : @ManyToOne(Fetch = FetchType.EAGER) 가지고 있어야 할, 실제 엔티티를 다 가져옴
 * -`JPQL`사용할 때, N+1 문제가 일어난다. : 쿼리 더 많이 보내게 된다. -> 예상치 못한  SQL문 나가게 됨.
 * -`@OneToMany, @ManyToMany`는 기본이 `지연 로딩`이다.
 * <p>
 * 지연 로딩 : @ManyToOne(Fetch = FetchType.LAZY) 프록시가 들어가있고, 실제 사용할 때, 데이터베이스에 쿼리문을 날림
 * -기본을 지연로딩으로 사용하자
 */
public class Lazy {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team1 = new Team();
            team1.setTeamname("팀1");

            Team team2 = new Team();
            team2.setTeamname("팀2");

            em.persist(team1);
            em.persist(team2);

            Fellow fellow1 = new Fellow();
            fellow1.setFellowname("동료1");
            fellow1.changeTeam(team1);

            Fellow fellow2 = new Fellow();
            fellow2.setFellowname("동료2");
            fellow2.changeTeam(team2);

            em.persist(fellow1);
            em.persist(fellow2);

            em.flush();
            em.clear();
            System.out.println("== em.clear() ==");

            //jpql n+1 문제
            List<Fellow> result = em.createQuery("select f from Fellow f", Fellow.class)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
