package com.leadgen.test.example;

import com.leadgen.test.ITestBase;
import com.leadgen.tests.dto.ContentType;
import com.leadgen.tests.dto.ParsedResponse;
import com.leadgen.tests.dto.RequestTemplate;
import com.leadgen.tests.services.http.HttpFactory;
import com.leadgen.tests.services.http.IResponse;
import com.leadgen.tests.services.parser.ParserFactory;
import com.leadgen.tests.utils.Cases;
import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.Collections;

/**
 * Наследуем 'ITestBase', метод getInitialMap() позволит взять нужный URL
 */
public class LeadRejectedTest implements ITestBase {
    private static RequestTemplate REQUEST;

    /**
     * Используя перечисление '@Constants' можно взять ссылка на нужный тест-кейс.
     * Метод getURL(Cases сase), возвращает ссылку для отправки
     * взависимости от окружения @prod/qa и тест-кейса @enum Cases;
     */
    @BeforeClass
    public void setup() throws Exception {
        final String url = getURL(Cases.LEAD_DEFAULT);

        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("Error! Sending URL cannot be empty or null!");
        }

        REQUEST = RequestTemplate.of(
                30, new URI(url),
                ContentType.APPLICATION_JSON,
                Collections.emptyMap(),
                Collections.emptyMap()
        );
    }

    @Test(dataProvider = "expectedResponse")
    public void leadRejectedTest(ParsedResponse expected) throws Exception {
        final IResponse response = HttpFactory.doGet(REQUEST);
        final ParsedResponse actualResponse = ParserFactory.from(response); // <= can be null!
        System.out.println(actualResponse);
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(actualResponse.getResponseCode(),400);
        Assert.assertEquals(actualResponse.getMessage(), "Lead is already sold");
        Assert.assertEquals(actualResponse.getResult(), "Failed");
    }

    @DataProvider
    public Object[][] expectedResponse() {
        return new Object[][]{
                {
                     new ParsedResponse()
                }
        };
    }


}
