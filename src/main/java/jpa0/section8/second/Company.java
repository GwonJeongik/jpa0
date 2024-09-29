package jpa0.section8.second;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * 2. MappedSuperclass
 * @MappedSuperclass : 공통된 속성만 사용할 때, 사용한다.
 * 테이블이 생성되는 게 아니라, 이걸 상속받은 엔티티에 속성만 적용된다. -> 상속 관계 맵핑이 아니라는 뜻이다.
 * 추상 클래스를 이용한다.
 * <p>
 * 참고 : @Entity 클래스 : `@MappedSuperclass` 또는 `@Entity`로 지정한 클레스만 상속이 가능하다.
 */
@Entity
public class Company extends MappedSuperClass {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    //Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
