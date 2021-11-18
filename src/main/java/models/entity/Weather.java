package models.entity;

import lombok.Builder;

@Builder
public class Weather {
    private String main;
    private String description;
}
