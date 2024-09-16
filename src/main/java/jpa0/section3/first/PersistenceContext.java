package jpa0.section3.first;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 1. 영속성 컨텍스트 [Persistence Context]
 * <p>
 * 영속성 컨텍스트 = 엔티티를 영구 저장하는 환경
 * 엔티티 매니저를 통해서 영속성 컨텍스트에 접근한다.
 * 비영속 [new / transient (일시적으로 머무르는)] -> 영속성 컨텍스트와 관계 없는, 새로운 상태
 * 영속 [managed] -> em.persist(Entity) -> 이때부터 영속성 컨텍스트에 관리된다.
 * 준영속 [detached (거리를 두는)] -> 영속성 컨텍스트에 있다가 분리 되었을 때
 * 삭제 [removed] -> 말 그대로 삭제 된 상태
 * <p>
 * em.persist(Entity)를 호출한다고, 바로 쿼리문이 나가는 게 아니다.
 * 트랜잭션이 커밋될 때, 데이터 변경 쿼리를 내보낸다.
 */
public class PersistenceContext {

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
            member.setId(100L);
            member.setName("section3 MemberA");

            //영속 -> 객체를 저장한 상태
            System.out.println("== BEFORE ==");
            em.persist(member);
            System.out.println("== AFTER  ==");
            //준영속 -> 회원 객체[member] 엔티티를 영속성 컨텍스트에서 분리한 상태 -> 변경사항이 DB에 저장 되지 않음.
            em.detach(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
