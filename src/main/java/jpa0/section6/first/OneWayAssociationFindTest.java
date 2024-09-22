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
 * `Fellow`를 찾는다.
 * `Fellow`가 가진 `Team`을 찾아서 확인한다.
 */
public class OneWayAssociationFindTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Fellow findFellow = em.find(Fellow.class, 1L);
            Team findTeam = findFellow.getTeam();

            System.out.println("findTeam.TeamName = " + findTeam.getTeamname());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
