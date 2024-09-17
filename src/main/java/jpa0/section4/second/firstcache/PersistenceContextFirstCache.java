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
 * 트랜잭션 커밋이 호출되기 직전에 `1차 캐시`에 있는 데이터를 기반으로 쿼리문이 나간다.
 */
public class PersistenceContextFirstCache {

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
            //비영속 -> 객체를 생성한 상태
            Member member = new Member();
            member.setId(200L);
            member.setName("section4 FirstCache");

            //1차 캐시에 저장
            em.persist(member);

            System.out.println("== BEFORE ==");
            System.out.println("캐시에서 가져오는 게 아니라면, 회원을 찾는 쿼리가 나가야함");
            //1차 캐시에 찾는 데이터가 있을 때,
            //1차 캐시에 있는 데이터를 꺼내옴
            Member findMember = em.find(Member.class, 200L);
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
