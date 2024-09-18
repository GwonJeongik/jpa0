package jpa0.section6.onewayassociation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 1. 단방향 연관관계
 * 단방향 연관관계 [Fellow -> Team] 테스트
 */
public class OneWayAssociationCreateTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setTeamname("TeamC");
            //영속성 컨텍스트 1차 캐시에 저장되려면 `primary key`가 있어야 한다. -> em.persist(Entity) 호출하면 `Id`가 할당된다.
            em.persist(team);

            Fellow fellow = new Fellow();
            fellow.setFellowname("동료C");
            fellow.setTeam(team);
            //영속성 컨텍스트 1차 캐시에 저장되려면 `primary key`가 있어야 한다. -> em.persist(Entity) 호출하면 `Id`가 할당된다.
            em.persist(fellow);

            System.out.println("== 영속성 컨텍스트 플러시 호출 시점 시작  ==");
            //`insert` 쿼리를 지금 날려서 데이터 베이스에 즉시 반영한다.
            em.flush();
            System.out.println("== 영속성 컨텍스트 플러시 호출 시점 종료  ==");

            //플러시를 호출해도 영속성 컨테스트 1차 캐시는 남아있어서, `em.clear()`을 호출해서 직접 비워준다.
            em.clear();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
