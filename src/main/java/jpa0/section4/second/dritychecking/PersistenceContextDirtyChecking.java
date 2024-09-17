package jpa0.section4.second.dritychecking;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 2. 영속성 컨텍스트 [Persistence Context]
 * `JPA`에서 데이터를 수정할 때는 변경 감지를 한다.
 * 영속성 컨텍스트에는 `1차 캐시` 공간이 있고,
 * 데이터를 1차 캐시에 최초로 저장할 때 `스냅샷`을 만들어 둔다. -> 스냅샷 : 1차 캐시에 처음 저장될 때의 `Entity`가 저장되어있다.
 * 이후 데이터를 수정하면,
 * 1차 캐시에 저장된 `Entity`와 `스냅샷`을 비교해서 수정된 부분이 존재하면, 쓰기 지연 SQL 저장소에 업데이트(update) 쿼리문을 저장한다.
 */
public class PersistenceContextDirtyChecking {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //1차 캐시에 저장 -> 스냅샷을 떠둠
            Member findMember = em.find(Member.class, 600L);
            // 커밋 시점에 1차 캐시에 저장된 `Entity` 떠둔 `스냅샷`을 비교한다.
            // `스냅샷`과 `Entity`가 다르므로 변경된 것을 알 수 있다.
            // 업데이트 쿼리문을 쓰기 지연 SQL 저장소에 저장
            findMember.setName("section4 Dirty_Checking -> change_name");

            System.out.println("== 커밋 시점 시작 ==");
            System.out.println("`1차 캐시`와 `스냅샷`을 비교한다.");
            System.out.println("`쓰기 지연 SQL 저장소`에 업데이트 쿼리문을 저장");
            System.out.println("업데이트 쿼리문을 데이터 베이스에 전달해서 데이터를 수정한다.");
            tx.commit();
            System.out.println("== 커밋 시점 완료 ==");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
