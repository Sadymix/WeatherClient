package com.example.weather.client.configuration;

import com.example.weather.client.events.WeatherDataCascadeSaveMongoEventListener;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories("com.example")
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Value("${app.mongo.name}")
    private String dbName;
    @Value("${app.mongo.url}")
    private String dbUrl;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(dbUrl + getDatabaseName());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.example");
    }

    @Bean
    public WeatherDataCascadeSaveMongoEventListener weatherDataCascadeSaveMongoEventListener() {
        return new WeatherDataCascadeSaveMongoEventListener();
    }
}
