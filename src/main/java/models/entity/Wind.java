package models.entity;

import lombok.Builder;

@Builder
public class Wind {
    private Double speed;
    private Long deg;
}
