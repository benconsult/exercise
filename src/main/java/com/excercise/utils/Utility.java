package com.excercise.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utility {

    private static final ObjectMapper SINGLETON_OBJECT_MAPPER_INSTANCE;

    static {
        SINGLETON_OBJECT_MAPPER_INSTANCE = new ObjectMapper()
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public ObjectMapper getSingletonObjectMapperInstance() {
        return SINGLETON_OBJECT_MAPPER_INSTANCE;
    }

}
