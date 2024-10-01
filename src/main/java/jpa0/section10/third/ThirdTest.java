package jpa0.section10.third;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;
import jpa0.section10.first.Address;

import java.util.List;
import java.util.Set;

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

            member1.getAddressHistory().add(new Address("old12", "거리", 10000));
            member1.getAddressHistory().add(new Address("old22", "거리", 10000));

            em.persist(member1);

            em.flush();
            em.clear();

            System.out.println("==============START===================");
            Member findMember = em.find(Member.class, member1.getId());

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            favoriteFoods.remove("짬뽕");
            favoriteFoods.add("한식");

            findMember.getAddressHistory().remove(new Address("old12", "거리", 10000));
            findMember.getAddressHistory().add(new Address("new", "거리", 10000));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
