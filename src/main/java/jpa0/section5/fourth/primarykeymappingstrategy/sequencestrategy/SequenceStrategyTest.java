package jpa0.section5.fourth.primarykeymappingstrategy.sequencestrategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 4. 기본 키 맵핑 전략
 * `sequence` 전략 테스트
 */
public class SequenceStrategyTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            SequenceStrategy user = new SequenceStrategy();
            user.setName("user_sequence_strategy");

            em.persist(user);

            System.out.println("== 커밋 시점 시작 ==");
            System.out.println("`Sequence 전략`은 커밋할 때, 인서트 쿼리를 데이터베이스에 전달한다.");
            tx.commit();
            System.out.println("== 커밋 시점 종료 ==");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}