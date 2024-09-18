package jpa0.section5.fourth.primarykeymappingstrategy.tablestrategy;

import jakarta.persistence.*;

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
 * 두 번째 전략 : `sequence` 전략 -> `Sequence Object`를 사용해서 할당한다.
 * 세 번째 전략 : `table` 전략 -> `키 생성용 테이블`을 만들어서 할당한다. -> 모든 데이터베이스에 사용할 수 있지만, 성능문제가 있다.
 * @TableGenerator(name="USER_TABLE_SEQUENCE_GENERATOR",table="USER_TABLE_SEQUENCE",pkColumnValue="USER_SEQUENCE",allocationSize=50) .
 * @GeneratedValue(strategy=GenerationType.TABLE,generator="USER_TABLE_SEQUENCE_GENERATOR") .
 * `name` 속성 -> `JPA`에서 참조하는데 사용되는 이름이다.
 * `table` 속성 -> 키 생성 테이블 이름이다.
 * `pkColumnValue` 속성 -> `key=value`에서 `key`로 사용할 값의 이름이다.
 * `allocationSize` 속성 -> 한 번 호출할 때, 미리 할당할 PK의 갯수이다. -> 데이터베이스에 여러번 접근하지 않아도 된다. -> 성능 최적화에 사용한다.
 * `allocation = 50`이라하면, `50`까지의 `PK값`을 메모리에 할당한다.
 * <p>
 * 권장하는 식별자[기본 키 맵핑] 전략 -> Long 타입 + 대체키 (<-> 자연키) + 키 생성 전략
 */
@Entity
@TableGenerator(
        name = "USER_TABLE_SEQUENCE_GENERATOR",
        table = "USER_TABLE_SEQUENCE",
        pkColumnValue = "USER_SEQUENCE",
        allocationSize = 50)
public class TableStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_TABLE_SEQUENCE_GENERATOR")
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