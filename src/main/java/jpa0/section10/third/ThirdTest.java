package jpa0.section10.third;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;
import jpa0.section10.first.Address;

import java.util.Set;

/**
 * 3. 컬렉션 값 타입
 *
 */
public class ThirdTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.getFavoriteFoods().add("짜장면");
            member1.getFavoriteFoods().add("짬뽕");
            member1.getFavoriteFoods().add("탕수육");

            em.persist(member1);

            em.flush();
            em.clear();

            System.out.println("==============START===================");
            Member findMember = em.find(Member.class, member1.getId());

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            favoriteFoods.remove("짬뽕");
            favoriteFoods.add("한식");

            findMember.getAddressEntity().add(new AddressEntity(new Address("old1212", "거리", 10000)));
            findMember.getAddressEntity().add(new AddressEntity(new Address("new", "거리", 10000)));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
