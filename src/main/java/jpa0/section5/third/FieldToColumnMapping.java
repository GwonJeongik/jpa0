package jpa0.section5.third;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 3. 필드와 컬럼 맵핑
 *
 * @Column -> `컬럼`을 맵핑한다.
 * `@Column(name = "username")`처럼 `name` 속성을 사용하면 이 `필드`는 `username`컬럼과 맵핑된다.
 * @Temporal -> `날짜 타입`을 맵핑한다.
 * @Transient -> 특정 필드를 컬럼에서 `맵핑하지 않는다`.
 * @Lob -> `BLOB` | `CLOB`를 맵핑한다.
 * `대용량 데이터`를 데이터베이스에 저장할 때 사용한다.
 * `BLOB` -> 문자 타입이 아닐 때, 맵핑된다.
 * `CLOB` -> 문자 타입일 때, 맵핑된다..
 * @Enumerated -> `enum  타입`을 맵핑한다.
 * `@Enumerated(EnumType.ORDINAL)` -> `enum` 순서를 데이터베이스에 저장 -> `enum`에  순서가 바뀌면 꼬여버린다.
 * `@Enumerated(EnumType.STRING)` -> `enum` 이름을 데이터베이스에 저장 -> 이것만 `사용`해야 한다.
 * [번외]
 * @Table(uniqueConstraints = {@UniqueConstraint(columnNames = "name")}) -> 유니크 제약조건은 테이블에 걸어준다.
 * `primary key`와 `uniqueConstraint[유니크 제약조건]` 차이점 : `null`이어도 `되냐` `안 되냐`
 * @Column(name = "name", length = 10, nullable = false, insertable = false, updatable = false)
 * 이 부분은 유추 가능하니 자세한 설명은 생략한다.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class FieldToColumnMapping {

    @Id
    private Long id;

    @Column(name = "name", length = 10, nullable = false, insertable = false, updatable = false)
    private String username;

    //@Temporal(TemporalType.DATE) -> `자바 8` 이상부터는 `LocalDate`를 사용하면 애노테이션을 붙여주지 않아도 된다.
    private LocalDate localDate;
    //@Temporal(TemporalType.TIMESTAMP) -> `자바 8` 이상부터는 `LocalDateTime`을 사용하면 애노테이션을 붙여주지 않아도 된다.
    private LocalDateTime localDateTime;

    @Transient
    private int age;

    @Lob
    private String description;

    //`EnumType.STRING`는 디폴트로 넣어줘야 한다고 생각하면 된다.
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //Getter & Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
