package com.leadgen.tests.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigUtils {

    public static Config readAll(String appRootConfig) {
        return readAll("conf", appRootConfig);
    }

    public static Config readAll(String confDir, String appRootConfig) {
        final Config appConfig = ConfigFactory.parseFile(new File(confDir + File.separator + appRootConfig));
        final Config systemConfig = ConfigFactory.load(appConfig);
        return systemConfig.resolve();
    }

    private static String getOrDefault(String config, String def) {
        final String property = System.getProperty(config);
        return StringUtils.isNotBlank(property) ? property : def;
    }

    public static Config getOrEmpty(Config config, String path) {
        if (config == null || !config.hasPath(path)) {
            return ConfigFactory.empty();
        }
        return config.getConfig(path);
    }

    public static int getIntOrDefault(Config config, String path, int def) {
        if (config == null || !config.hasPath(path)) {
            return def;
        }
        return config.getInt(path);
    }

    public static Map<String, String> toMap(Config config) {
        return CollectionUtils.streamOf(config.entrySet()).collect(Collectors.toMap(
                Map.Entry::getKey, e -> String.valueOf(e.getValue().unwrapped())
        ));
    }

    public static Map<Integer, String> toIntMap(Config config) {
        return CollectionUtils.streamOf(config.entrySet()).collect(Collectors.toMap(
                entry -> Integer.valueOf(entry.getKey()), entry -> String.valueOf(entry.getValue().unwrapped())
        ));
    }

    public static Set<Integer> asIntSet(Config config, String path) {
        return new HashSet<>(config.getIntList(path));
    }

}
