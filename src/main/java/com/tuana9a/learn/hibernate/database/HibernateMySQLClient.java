package com.tuana9a.learn.hibernate.database;

import com.tuana9a.learn.hibernate.configs.AppConfig;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMySQLClient {
    private static final HibernateMySQLClient instance = new HibernateMySQLClient();

    private SessionFactory sessionFactory;

    private HibernateMySQLClient() {

    }

    public static HibernateMySQLClient getInstance() {
        return instance;
    }

    public SessionFactory createSessionFactory(List<String> mapResources) {
        Configuration cfg = new Configuration();
        mapResources.forEach(cfg::addResource);
        sessionFactory = cfg.configure().buildSessionFactory();
        return sessionFactory;
    }

    public SessionFactory createSessionFactory() {
        Configuration cfg = new Configuration();
        AppConfig config = AppConfig.getInstance();
        String mappers = config.HIBERNATE_MAPPERS();
        if (mappers != null) {
            for (String path : mappers.split("(,|\\s+)")) {
                cfg.addResource(path);
            }
        }
        sessionFactory = cfg.configure(config.HIBERNATE_CONFIG_PATH()).buildSessionFactory();
        return sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
