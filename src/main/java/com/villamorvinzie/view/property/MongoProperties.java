package com.villamorvinzie.view.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mongodb")
public record MongoProperties(String uri, String name) {

}
