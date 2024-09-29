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
            System.out.println("========1========");
            Locker locker = new Locker();
            locker.setLockerNumber(5);

            em.persist(locker);
            System.out.println("========1========");


            System.out.println("========2========");
            User user = new User();
            user.setUsername("테스트 유저");
            user.setLocker(locker);

            em.persist(user);
            System.out.println("========2========");

            System.out.println("========3========");
            User findUser = em.find(User.class, user.getId());
            System.out.println("유저 ID : " + user.getId());
            System.out.println("락커 번호 : " + findUser.getLocker().getLockerNumber());
            System.out.println("========3========");

            System.out.println("========4========");
            tx.commit();
            System.out.println("========4========");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
