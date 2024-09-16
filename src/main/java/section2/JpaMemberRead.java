package section2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * 2. Hello JPA - 애플리케이션 개발
 * `JPA`를 사용할 때, `모든 데이터 변경`은 `트랜잭션 안`에서 이루어져야한다.
 * `JPQL`은 객체지향 쿼리이다. -> 테이블 대상이 아니라, 객체를 대상으로 조회한다.
 * `EntityManagerFactory`는 하나만 생성해서, 애플리케이션 전체에서 공유
 * `EntityManager`은 쓰레드간에 공유하면 안 된다. -> 한 명의 사용자만 사용
 */
public class JpaMemberRead {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //`JPA`를 이용해서, `Member`를 데이터베이스에서 `조회`한다.
        Member findMember = em.find(Member.class, 1L);
        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.name = " + findMember.getName());

        em.close();
        emf.close();
    }
}
