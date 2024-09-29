package jpa0.section10.first;


import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Period {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
