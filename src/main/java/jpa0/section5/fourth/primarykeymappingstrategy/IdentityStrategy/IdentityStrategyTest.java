package jpa0.section5.fourth.primarykeymappingstrategy.IdentityStrategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 4. 기본 키 맵핑 전략
 * `identity` 전략 테스트 -> em.persist(Entity) 실행 시점에 insert 쿼리가 데이터베이스에 전달된다.
 */
public class IdentityStrategyTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            IdentityStrategy user = new IdentityStrategy();
            user.setName("user_identity_strategy");

            System.out.println("== em.persist(user) 시점 시작 ==");
            System.out.println("`identity 전략`은 em.persist(Entity) 시점에 insert 쿼리가 데이터 베이스에 전달된다.");
            em.persist(user);
            System.out.println("== em.persist(user) 시점 종료 ==");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}