package jpa0.section10.second;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;
import jpa0.section10.first.Address;

/**
 * 참조 객체의 사이드 이펙트
 */
public class SecondTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address = new Address();
            address.setCity("시티");
            address.setStreet("스트리트");
            address.setZipCode(10000);
            Member member1 = new Member();
            member1.setHomeAddress(address);
            Member member2 = new Member();
            member2.setHomeAddress(address);

            em.persist(member1);
            em.persist(member2);

            address.setCity("시티333");

            Member findMember = em.find(Member.class, member1.getId());
            findMember.getHomeAddress().setCity("시티222");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
