package com.remirobert;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * Created by remirobert on 06/01/2017.
 */

@Configuration
public class MongodbConfiguration extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "rss";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1");
    }
}
