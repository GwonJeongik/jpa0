package jpa0.section8.second;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 2. MappedSuperclass
 * 테스트
 */
public class SecondTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Company company = new Company();
            company.setName("정익컴퍼니");
            company.setCreateBy("정익");
            company.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

            em.persist(company);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
