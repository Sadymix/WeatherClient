package models.dto;

import models.entity.Coord;
import models.entity.Main;
import models.entity.Weather;
import models.entity.Wind;

import java.util.List;

public class WeatherDto {
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Long dt;
    private String name;
}
