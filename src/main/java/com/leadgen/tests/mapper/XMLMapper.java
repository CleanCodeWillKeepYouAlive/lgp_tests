package com.leadgen.tests.mapper;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XMLMapper implements IMapper {
    private final XmlMapper XML_MAPPER = new XmlMapper();

    @Override
    public XmlMapper getXmlMapper() {
        return XML_MAPPER;
    }

    @Override
    public <T> T xmlToObjectSafe(String xml, Class<T> tClass) {
        try {
            return xmlToObject(xml, tClass);
        }catch (Exception ex) {
            log.error("Mapping error: {}; Message: {}", xml, ex.getMessage());
        }
        return null;
    }

    @Override
    public String objectToXmlSafe(Object obj) {
        try {
            return objectToXml(obj);
        } catch (Exception ex) {
            log.error("Mapping error: ", ex);
        }
        return null;
    }

    private String objectToXml(Object obj) throws Exception {
        return XML_MAPPER.writeValueAsString(obj);
    }

    private <T> T xmlToObject(String xml, Class<T> tClass) throws Exception {
        return XML_MAPPER.readValue(xml, tClass);
    }

    @Override
    public String objectToJsonSafeWithPrettyPrint(Object obj) {return null;}

    @Override
    public <T> T jsonToObjectSafe(String json, Class<T> tClass) {return null;}

    @Override
    public ObjectMapper getJsonMapper() {return null;}
}
