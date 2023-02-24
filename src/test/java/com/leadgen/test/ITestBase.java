package com.leadgen.test;

import com.leadgen.tests.utils.Cases;
import com.leadgen.tests.utils.ConfigUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

import static com.leadgen.tests.utils.Constants.ENV_PROD;
import static com.leadgen.tests.utils.Constants.ENV_QA;

/**
 * Основной интерфейс для тестов! Каждый тест должен наследовать
 * интерфейс, что бы была возможность взять URL для отправки запроса.
 * Перечислеие @Cases так же являеться основным классом,
 * с помощью которого, строки URL будут браться с конфигурационных файлов
 */
public interface ITestBase {

    default String getURL(Cases cases) {
        final String env = ENV_PROD.equals(System.getProperty("env")) ? ENV_PROD : ENV_QA;

        final Map<String, String> confMap = ConfigUtils.toMap(
                ConfigUtils.readAll("application.conf")
                    .getConfig(env)
                    .getConfig("env")
                    .getConfig("url")
        );
        return confMap.getOrDefault(cases.getParam(), StringUtils.EMPTY);
    }
}
