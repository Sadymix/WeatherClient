package models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import models.entity.Coord;
import models.entity.Main;
import models.entity.Weather;
import models.entity.Wind;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class WeatherDto {
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Long dt;
    private String name;
}
