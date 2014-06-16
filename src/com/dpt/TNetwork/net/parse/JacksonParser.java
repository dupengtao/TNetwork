package com.dpt.TNetwork.net.parse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dupengtao on 2014/6/16.
 */
public class JacksonParser implements IBaseParser {

    private static JacksonParser jacksonParser;
    private final ObjectMapper objectMapper;

    private JacksonParser() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static JacksonParser getInstance() {
        if (jacksonParser == null) {
            jacksonParser = new JacksonParser();
        }
        return jacksonParser;
    }


    public  Map<String, String> readValue(String respons) {
        try {
            return JacksonParser.getInstance().objectMapper.readValue(respons, Map.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>();
    }

    public  <T> T readValue(String response, Class<T> valueType) {
        try {
            return JacksonParser.getInstance().objectMapper.readValue(response, valueType);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public <T> T toParse(String context, Class<T> clazz) {
        return readValue(context,clazz);
    }
}
