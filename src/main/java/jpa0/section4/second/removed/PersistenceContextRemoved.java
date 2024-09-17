package jpa0.section4.second.removed;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 2. 영속성 컨텍스트 [Persistence Context]
 */
public class PersistenceContextRemoved {

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
            Member member = new Member();
            member.setId(700L);
            member.setName("section4 Delete_Member");

            em.persist(member);


            System.out.println("== 조회 시작==");
            System.out.println("1차 캐시에 있는 회원을 가져와서 쿼리문 날아가지 않음");
            Member findMember = em.find(Member.class, 700L);
            System.out.println("== 조회 종료==");

            //em.remove(entity)를 호출하면, 어차피 삭제될 `Entity`인 걸 알고, 업데이트 할 필요가 없으므로, 업데이트 쿼리문은 날아가지 않는다.
            findMember.setName("section4 Delete_Member -> change_name");

            em.remove(member);

            System.out.println("== 커밋 시점 시작 ==");
            System.out.println("쓰기 지연 SQL 저장소에 있는 인서트(insert) 쿼리문을 날린다.");
            System.out.println("쓰기 지연 SQL 저장소에 있는 딜리트(delete) 쿼리문을 날린다.");
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
