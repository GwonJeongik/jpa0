package jpa0.section4.second.writebehind;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 2. 영속성 컨텍스트 [Persistence Context]
 * 영속성 컨텍스트 안에 쓰기 지연 쿼리 저장소가 존재한다.
 * 엔티티 등록할 때 쓰기 지연
 * em.persist(Entity)를 호출하면,
 * 1차 캐시에 저장,
 * 쓰기 지연 SQL 저장소에 인설트(insert) 쿼리문을 저장한다.
 * 트랜잭션 커밋 직전에 저장된 쿼리문을 데이터 베이스에 전달해서 회원을 등록한다.
 */
public class PersistenceContextWriteBehind {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setId(400L);
            member1.setName("section4 Write_Delay_A");

            Member member2 = new Member();
            member2.setId(500L);
            member2.setName("section4 Write_Delay_B");

            //영속성 컨텍스트, `1차 캐시`에 저장 -> `쓰기 지연 SQL 저장소`에 인설트(insert) 쿼리문을 저장
            em.persist(member1);
            em.persist(member2);

            System.out.println("== tx.commit()할 때, 쓰기 지연 SQL 저장소에 있는 쿼리문을 데이터 베이스에 전달한다. ==");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
