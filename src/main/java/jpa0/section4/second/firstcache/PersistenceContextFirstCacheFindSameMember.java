package jpa0.section4.second.firstcache;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 2. 영속성 컨텍스트 [Persistence Context]
 * 영속성 컨텍스트 안에 `1차 캐시`를 `저장하는 공간`이 존재한다.
 * em.persist(Entity)를 호출하면, `1차 캐시`에 저장한다.
 * 트랜잭션 커밋이 호출되기 직전에 `1차 캐시`에 있는 데이터를 기반으로 인설트(insert) 쿼리문이 나간다.
 * <p>
 * em.find(Entity.class, Id)를 호출하면,
 * 첫 번째 : 1차 캐시를 뒤져서, 있으면 1차 캐시에 있는 것을 가져온다.
 */
public class PersistenceContextFirstCacheFindSameMember {

    public static void main(String[] args) {
        //엔티티 매니저 팩토리
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //엔티티 매니저 팩토리에서 엔티티 매니저를 생성
        EntityManager em = emf.createEntityManager();

        //엔티티 매니저를 통해 가져온 트랜잭션
        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();

        try {

            System.out.println("== BEFORE ==");
            System.out.println("1차 캐시에 없으면, 셀렉 쿼리를 날려서 데이터를 조회함");
            Member findMember1 = em.find(Member.class, 200L);
            System.out.println("== AFTER  ==");

            System.out.println("== BEFORE ==");
            System.out.println("1차 캐시에 존재해서 셀렉 쿼리를 날리지 않는다.");
            Member findMember2 = em.find(Member.class, 200L);
            System.out.println("== AFTER  ==");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
