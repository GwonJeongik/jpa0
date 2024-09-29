package jpa0.section8.first;

import jakarta.persistence.*;

@Entity
//조인 전략
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
//단일 테이블 전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//각 구현 클래스 테이블 전략 (따로따로 전략)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
