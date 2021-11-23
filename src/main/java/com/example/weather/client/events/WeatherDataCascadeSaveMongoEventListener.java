package com.example.weather.client.events;

import com.example.weather.client.models.entity.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherDataCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    private final MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
        if (event.getSource() instanceof WeatherData source) {
            if (source.getWeathers() != null &&
                    source.getCoordinates() != null &&
                    source.getConditions() != null &&
                    source.getWind() != null) {
                source.getWeathers().forEach(mongoOperations::save);
                mongoOperations.save(source.getCoordinates());
                mongoOperations.save(source.getConditions());
                mongoOperations.save(source.getWind());
            }
        }
    }
}
