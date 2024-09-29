package jpa0.section8.second;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

/**
 * 2. MappedSuperclass
 * @MappedSuperclass : 엔티티에 공통된 속성을 사용할 때, 사용한다.
 * `테이블이 생성되는 게 아니라`, 이걸 상속받은 엔티티에 속성만 적용된다. -> 상속 관계 맵핑이 아니라는 뜻이다.
 * 추상 클래스를 이용한다.
 * <p>
 * 참고 : @Entity 클래스 : `@MappedSuperclass` 또는 `@Entity`로 지정한 클레스만 상속이 가능하다.
 */
@MappedSuperclass
public abstract class MappedSuperClass {

    private String createBy;
    private String lastModifiedBy;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    //Getter & Setter

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
