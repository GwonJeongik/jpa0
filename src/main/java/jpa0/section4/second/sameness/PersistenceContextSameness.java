package jpa0.section4.second.sameness;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 2. 영속성 컨텍스트 [Persistence Context]
 * 영속성 컨텍스트, 1차 캐시에서 꺼내온 영속 엔티티의 동일성을 보장한다.
 * em.find(Member.class, 200L) == em.find(Member.class, 200L);
 */
public class PersistenceContextSameness {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member findMember1 = em.find(Member.class, 200L);
            Member findMember2 = em.find(Member.class, 200L);

            System.out.println("sameness = " + (findMember1 == findMember2));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
