package com.villamorvinzie.view.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.villamorvinzie.view.property.MongoProperties;

@Configuration
@EnableMongoRepositories
public class MongoDbConfiguration extends AbstractReactiveMongoConfiguration {

    private MongoProperties mongoProperties;

    public MongoDbConfiguration(MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
    }

    @Override
    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoProperties.uri());
    }

    @Override
    protected String getDatabaseName() {
        return mongoProperties.name();
    }

}
