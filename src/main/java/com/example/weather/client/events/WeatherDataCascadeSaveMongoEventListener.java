package com.example.weather.client.events;

import com.example.weather.client.models.entity.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
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

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        var id = mongoOperations.findById(event.getSource().getObjectId("_id"), event.getType());
        if (id instanceof WeatherData source) {
            source.getWeathers().forEach(mongoOperations::remove);
            mongoOperations.remove(source.getCoordinates());
            mongoOperations.remove(source.getConditions());
            mongoOperations.remove(source.getWind());
        }
    }
}
