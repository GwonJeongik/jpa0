package jpa0.section5.fourth.primarykeymappingstrategy.IdentityStrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 4. 기본 키 맵핑 전략
 *
 * @Id : `primary key`를 `직접` 할당
 * <p>
 * @Id
 * @GeneratedValue -> `primary key`를 `자동 생성`해서 할당
 * <p>
 * [전략 3가지]
 * 첫 번째 전략 : `identity` 전략 -> 데이터베이스에 `primary key` 할당을 위임한다.
 * @GeneratedValue(strategy=GenerationType.IDENTITY) .
 * 영속성 컨텍스트에 저장하려면, `PK`값이 필요한데, 데이터베이스에 insert 쿼리를 날릴 때 `PK`값을 알 수 있다.
 * `em.persist(Entity) 실행 시점`에 insert 쿼리가 데이터베이스에 전달된다.
 * 데이터베이스에 저장할
 * 두 번째 전략 : `sequence` 전략 -> `Sequence Object`를 사용해서 할당한다.
 * 세 번째 전략 : `table` 전략 -> `키 생성용 테이블`을 만들어서 할당한다.
 * <p>
 * 권장하는 식별자[기본 키 맵핑] 전략 -> Long 타입 + 대체키 (<-> 자연키) + 키 생성 전략
 */
@Entity
public class IdentityStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Getter & Setter


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
