package models.entity;

import lombok.Builder;

@Builder
public class Main {
    private Double temp;
    private Double tempMin;
    private Double tempMax;
    private Long pressure;
    private Long humidity;
}
