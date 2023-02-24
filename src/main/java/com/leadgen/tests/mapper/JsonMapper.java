package com.leadgen.tests.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMapper implements IMapper {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @Override
    public ObjectMapper getJsonMapper() {
        return JSON_MAPPER;
    }

    @Override
    public String objectToJsonSafeWithPrettyPrint(Object obj) {
        try {
            return JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            log.error("Mapping error: {};", ex.getMessage());
        }
        return null;
    }

    @Override
    public <T> T jsonToObjectSafe(String json, Class<T> tClass) {
        try {
            return jsonToObject(json, tClass);
        } catch (Exception ex) {
            log.error("Mapping error: {}; Message: {}", json, ex.getMessage());
        }
        return null;
    }

    private  <T> T jsonToObject(String json, Class<T> tClass) throws Exception {
        return JSON_MAPPER.readValue(json, tClass);
    }

    @Override
    public String objectToXmlSafe(Object obj) {return null;}

    @Override
    public <T> T xmlToObjectSafe(String xml, Class<T> tClass) {return null;}

    @Override
    public XmlMapper getXmlMapper() {return null;}
}
