package com.sensemore.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
 
public class JsonConverter {
    public static String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
