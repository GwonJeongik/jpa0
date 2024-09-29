package jpa0.section8.first;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 1. 고급 맵핑
 * -조인 전략 : @Inheritance(strategy = InheritanceType.JOINED)
 * -단일 테이블 전략 : @Inheritance(strategy = Inheritance.SINGLE_TABLE)
 * -각 구현 클래스 테이블 전략 (따로따로 전략) : @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 *
 * @DiscriminatorColumn : (name = DTYPE) -> Default
 * @DiscriminatorValue : (name = Movie) -> Default (자식이 Movie 클래스 기준으로 말한 거다 오해하지 말자!)
 */
public class firstTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setName("영화이름1");
            movie.setPrice(10000);
            movie.setDirector("감독이름1");
            movie.setActor("배우이름1");

            em.persist(movie);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
