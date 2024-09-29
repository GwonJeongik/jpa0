package jpa0.section10.first;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.Member;

/**
 * 2. 임베디드 타입
 * JPA 데이터 타입 : 엔티티 타입 | 값 타입
 * 값 타입 : 기본 값 타입 | 임베디드 타입 | 컬렉션 값 타입
 * 임베디드 타입 : 엔티티의 값일 뿐이다.
 * 자바로 따지면 객체지향적으로 클래스를 나눈 것을 말한다. -> 데이터 베이스에서 보이는 형식에 변화는 없다.
 *
 * @Embeddable : 값 타임을 정의하는 곳에 표시한다.
 * @Embedded : 정의한 값 타입을 사용하는 곳에 표시한다.
 * @AuttributeOverrides( {
 * @AttributeOverride(name="xxx",column=@Column(name="yyy")) }
 * ) : 하나의 엔티티에서 `같은 값 타입`을 사용하면 컬럼명이 중복된다. 이를 해결할 때 사용한다.
 */
public class EmbeddedType {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}