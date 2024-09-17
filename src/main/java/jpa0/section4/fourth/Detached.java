package jpa0.section4.fourth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 4. 준영속 상태
 * `준영속 상태`는 `영속성 컨텍스트`에서 `분리`된 상태를 말한다.
 * 영속성 컨텍스트의 기능을 사용하지 못한다.
 * <p>
 * 준영속 상태로 만드는 방법
 * -em.detach(Entity) -> 특정 `Entity`를 `영속성 컨텍스트`에서 `분리`한다. -> 준영속 상태로 전환한다.
 * -em.clear() -> 엔티티 매니저 안에 있는 `영속성 컨텍스트`를 전체 다 비운다.
 * -em.close() -> `영속성 컨텍스트`를 `종료`한다.
 */
public class Detached {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // find(Entity)에서 `Entity`는 데이터베이스에서 조회하고 1차 캐시에 올려진다.
            //`1차 캐시`에 올려졌다는 거는 `영속 상태`라는 의미이다.
            // 1차 캐시에 올려진 조회 회원을 가져온다.
            Member findMember = em.find(Member.class, 800L);
            findMember.setName("section4 detach -> change_name");

            //영속 상태에서 준영속 상태로 전환된다.
            //따라서 영속성 컨텍스트 기능은 사용하지 못한다. -> 데이터 수정 사항이 데이터베이스에 반영되지 않는다.
            em.detach(findMember);

            System.out.println("== 커밋 시점 시작 ==");
            System.out.println("`findMember`는 준영속 상태이므로, 플러시가 발생해도 영향을 받지 않는다.");
            System.out.println("즉, 데이터 변경 사항이 데이터 베이스에 반영되지 않는다.");
            tx.commit();
            System.out.println("== 커밋 시점 종료 ==");
        } catch (Exception e) {
            tx.rollback();
        } finally {

        }

        emf.close();

    }
}
