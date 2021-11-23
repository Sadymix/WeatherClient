package com.example.weather.client.utility;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public final class PodamUtility {

    private static PodamFactory FACTORY = new PodamFactoryImpl();

    private PodamUtility() {
    }

    public static <T> T makePojo(Class<T> tClass) {
        return FACTORY.manufacturePojo(tClass);
    }
}
