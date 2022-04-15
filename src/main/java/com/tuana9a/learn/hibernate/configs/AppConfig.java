package com.tuana9a.learn.hibernate.configs;

import com.mysql.cj.util.LogUtils;
import com.tuana9a.learn.hibernate.utils.IoUtils;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

public class AppConfig {
    public Properties properties;

    public String HIBERNATE_CONFIG_PATH() {
        return properties.getProperty("hibernate.config_path");
    }

    public String HIBERNATE_MAPPERS() {
        return properties.getProperty("hibernate.mappers");
    }

    private AppConfig() {

    }

    private static final AppConfig instance = new AppConfig();

    public static AppConfig getInstance() {
        return instance;
    }

    public void load() {
        properties = new Properties();
        IoUtils ioUtils = IoUtils.getInstance();
        InputStream inputStream = null;
        String configPath = "application.properties";
        try {
            inputStream = new FileInputStream(configPath);
            properties.load(inputStream);
        } catch (Exception e) {
            System.err.println(e);
        }
        ioUtils.close(inputStream);
    }
}
