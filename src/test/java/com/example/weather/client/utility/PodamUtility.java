package com.example.weather.client.utility;

import org.springframework.stereotype.Component;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Component
public class PodamUtility {

    public static <T> T makePojo(Class<T> tClass) {
        PodamFactory factory = new PodamFactoryImpl();
        return factory.manufacturePojo(tClass);
    }
}
