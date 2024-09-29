package jpa0.section9.first;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 1. 프록시
 * em.find(Member.class, member.getId()) : 실제 엔티티 객체를 받아온다. -> 데이터 베이스에 쿼리 날아감
 * em.getReference(Member.class, member.getId()) : 프록시 객체를 받아온다. -> 데이터 베이스에 쿼리 안 날아감
 * <p>
 * getReference()를 먼저 호출 후, find()호출하면 둘 다 프록시
 * find()를 먼저 호출 후, getReference()호출하면 둘 다 실제 엔티티 : 영속성 컨텍스트에 있으니까!!
 * <p>
 * `프록시`는 `실제 엔티티`를 상속 받고, 실제 엔티티를 참조할 수 있게 되어있다.
 * -프록시 초기화 : Proxy -> 영속성 컨텍스트 -> 데이터 베이스 -> 실제 Entity 생성 -> Proxy가 참조
 * <p>
 * ※참고※ : 프록시와 실제 엔티티의 타입 비교시 [==] 체크 주의
 * <p>
 * 준영속 상태일 때, 프록시를 초기화하면 예외 발생
 */
public class Proxy1 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            Member member2 = new Member();
            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            //getReference()를 먼저 호출 후, find()호출하면 둘 다 프록시
            Member referenceMember = em.getReference(Member.class, member1.getId());
            System.out.println("proxyMember : " + referenceMember.getClass());
            System.out.println("proxy 초기화 여부 : " + emf.getPersistenceUnitUtil().isLoaded(referenceMember));

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("entityMember : " + findMember.getClass());
            System.out.println("proxy 초기화 여부 : " + emf.getPersistenceUnitUtil().isLoaded(findMember));

            System.out.println("== em.clear ==");
            em.clear();

            //※참고※ : 프록시와 실제 엔티티의 타입 비교시 [==] 체크 주의
            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("proxyMember : " + refMember.getClass());


            Member findMember2 = em.find(Member.class, member2.getId());
            System.out.println("entityMember : " + findMember2.getClass());

            System.out.println("proxyMember == entityMember : " + (refMember == findMember2));
            System.out.println("proxyMember instanceOf Member : " + (refMember instanceof Member));
            System.out.println("entityMember instanceOf Member : " + (findMember2 instanceof Member));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
