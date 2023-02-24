package com.leadgen.tests.services.parser;

import com.leadgen.tests.dto.ParsedResponse;
import com.leadgen.tests.mapper.IMapper;
import com.leadgen.tests.mapper.JsonMapper;
import com.leadgen.tests.mapper.XMLMapper;
import com.leadgen.tests.services.http.IResponse;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

@UtilityClass
public class ParserFactory {
    private final IMapper XML_MAPPER = new XMLMapper();
    private final IMapper JSON_MAPPER = new JsonMapper();

    @Nullable
    public ParsedResponse from(@Nonnull IResponse response) {
       final ParsedResponse json = JSON_MAPPER.jsonToObjectSafe(response.getResponseBody(), ParsedResponse.class);
       if (Objects.nonNull(json)) {
           return json;
       }

       final ParsedResponse xml = XML_MAPPER.xmlToObjectSafe(response.getResponseBody(), ParsedResponse.class);
       if (Objects.nonNull(xml)) {
           return xml;
       }
       return null;
    }
}
