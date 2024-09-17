package jpa0.section4.third;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 3. 플러시 (flush)
 * 플러시는 `영속성 컨텍스트`의 변경 사항을 `데이터 베이스`에 반영한다.
 * 플러시는 `쓰기 지연 SQL 저장소`에 저장된 쿼리들을 `데이터 베이스`에 날린다.
 * <p>
 * 영속성 컨텍스트를 플러시하는 방법
 * -em.flush() -> `직접` 플러시를 호출
 * -트랜잭션 커밋 시점 -> 플러시 `자동` 호출
 * -JPQL 실행 시점 -> 플러시 `자동` 호출
 * 참고 : 플러시 모드 옵션을 따로 설정할 수 있다.
 * <p>
 * 플러시가 발생하면
 * 첫 번째 : `변경 감지`가 일어나고,
 * 두 번째 : `수정된 엔티티`의 쿼리문을 `쓰기 지연 SQL 저장소`에 저장한다.
 * 세 번째 : `쓰기 지연 SQL 저장소`에 있는 쿼리문을 `데이터 베이스`에 전달한다.
 */
public class Flush {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(700L);
            member.setName("section4 flush");

            em.persist(member);

            //플러시(flush) 직접 호출
            System.out.println("== 플러시 직접 호출 시작==");
            System.out.println("em.flush()로 플러시를 직접 호출해서 `쓰기 지연 SQL 저장소`에 있는 쿼리들 `데이터베이스`에 반영");
            em.flush();
            System.out.println("== 플러시 직접 호출 종료==");

            //쓰기 지연 SQL 저장소에 쿼리문이 없어서, 데이터 베이스에 전달할 쿼리문이 없다.
            System.out.println("== 커밋 시점 시작 ==");
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
