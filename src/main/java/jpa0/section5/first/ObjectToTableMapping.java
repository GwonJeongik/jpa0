package jpa0.section5.first;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 1. 객체와 테이블 맵핑
 * `@Entity`가 붙은 클래스는 JPA에서 관리한다. => 엔티티라 부른다.
 * <p>
 * @Entity(name="yyy")
 * `JPA 내부`에서 사용할 `엔티티 이름`을 지정한다.
 * [JPQL] -> `select z from yyy z where 1=1;`처럼 `JPQL`에서 사용(참조)되는 이름을 지정한다.
 * 테이블 이름에는 영향을 주지 않는다.
 * <p>
 * @Table(name="user") `실제 데이터 베이스`의 맵핑할 `테이블 이름`을 지정한다.
 * `ObjectToTableMapping 클래스`는 `user 테이블`에 맵핑된다.
 * select * from user -> `SQL`에서 접근할 때, `user 테이블`에 접근한다.
 */
@Entity
public class ObjectToTableMapping {

    @Id
    private Long id;
    private String username;

}
