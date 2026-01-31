package com.shiva.stockfeed.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.shiva.stockfeed.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.mongodb.database}")
    private String database;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUri);

        String username = connectionString.getUsername();
        char[] password = connectionString.getPassword();

        MongoClientSettings.Builder settingsBuilder = MongoClientSettings.builder()
                .applyConnectionString(connectionString);

        // Explicitly set credential with admin as auth database
        if (username != null && password != null) {
            MongoCredential credential = MongoCredential.createCredential(
                    username,
                    "admin",  // Force auth against admin database
                    password
            );
            settingsBuilder.credential(credential);
        }

        return MongoClients.create(settingsBuilder.build());
    }
}
