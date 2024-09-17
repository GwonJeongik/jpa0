package jpa0.section5.third;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 3. 필드와 컬럼 맵핑
 * `FieldToColumnMapping`를 테스트 해본다.
 */
public class FieldToColumnMappingTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            FieldToColumnMapping user = new FieldToColumnMapping();
            user.setId(3L);
            user.setUsername("section5 FieldToColumnMappingTest3");
            user.setAge(28);
            user.setRoleType(RoleType.USER);
            user.setLocalDate(LocalDate.now());
            //.truncatedTo(ChronoUnit.SECONDS) -> 초 단위까지만 남기고 `나노초 이하`는 `제거`
            user.setLocalDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

            em.persist(user);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
