package com.leadgen.tests.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public interface IMapper {

    String objectToJsonSafeWithPrettyPrint(Object obj);

    <T> T jsonToObjectSafe(String json, Class<T> tClass);


    String objectToXmlSafe(Object obj);

    <T> T xmlToObjectSafe(String xml, Class<T> tClass);


    ObjectMapper getJsonMapper();

    XmlMapper getXmlMapper();

}
