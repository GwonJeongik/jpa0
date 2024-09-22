package jpa0.section6.first;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.section6.Fellow;
import jpa0.section6.Team;

/**
 * 1. 단방향 연관관계
 * 단방향 연관관계 [Fellow -> Team] 테스트
 * `Fellow`의 `Team`을 바꾼다.
 */
public class OneWayAssociationUpdateTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setTeamname("TeamD");

            em.persist(team);

            Fellow findFellow = em.find(Fellow.class, 1L);
            findFellow.setTeam(team);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
