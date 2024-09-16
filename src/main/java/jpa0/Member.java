package jpa0;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 2. Hello JPA - 애플리케이션 개발
 * `JPA`를 사용할 때, `모든 데이터 변경`은 `트랜잭션 안`에서 이루어져야한다.
 * `JPQL`은 객체지향 쿼리이다. -> 테이블 대상이 아니라, 객체를 대상으로 조회한다.
 * `EntityManagerFactory`는 하나만 생성해서, 애플리케이션 전체에서 공유
 * `EntityManager`은 쓰레드간에 공유하면 안 된다. -> 한 명의 사용자만 사용
 */
@Entity
public class Member {

    @Id
    private Long id;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
