package jpa0.section5.third;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 3. 필드와 컬럼 맵핑
 */
@Entity
public class fieldToColumnMapping {

    @Id
    private Long id;
    private String username;

}
