package jpa0.section5.fourth.primarykeymappingstrategy.tablestrategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 4. 기본 키 맵핑 전략
 * `Table` 전략 테스트
 */
public class TableStrategyTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            TableStrategy user = new TableStrategy();
            user.setName("user_table_strategy");

            em.persist(user);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}