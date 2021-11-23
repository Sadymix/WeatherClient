package com.example.weather.client.events;

import com.example.weather.client.models.entity.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class WeatherDataCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

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
