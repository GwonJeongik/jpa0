package jpa0.section7;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OneToMany {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            User user = new User();
            user.setUsername("테스트 유저");

            System.out.println("========1========");
            em.persist(user);
            System.out.println("========1========");


            Locker locker = new Locker();
            locker.setLockerNumber(5);
            user.setLocker(locker);

            System.out.println("========2========");
            em.persist(locker);
            System.out.println("========2========");

            System.out.println("========3========");
            tx.commit();
            System.out.println("========3========");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
