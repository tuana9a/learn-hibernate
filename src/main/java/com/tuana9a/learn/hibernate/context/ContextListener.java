package com.tuana9a.learn.hibernate.context;


import com.tuana9a.learn.hibernate.configs.AppConfig;
import com.tuana9a.learn.hibernate.database.HibernateMySQLClient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        Executor executor = Executors.newFixedThreadPool(8);
        AppConfig config = AppConfig.getInstance();
        config.load();
        executor.execute(() -> HibernateMySQLClient.getInstance().createSessionFactory());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}
