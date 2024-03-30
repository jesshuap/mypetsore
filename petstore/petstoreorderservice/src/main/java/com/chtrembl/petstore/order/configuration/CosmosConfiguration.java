package com.chtrembl.petstore.order.configuration;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CosmosConfiguration {

    @Value("${azure.cosmos.uri}")
    private String accountEndpoint;

    @Value("${azure.cosmos.key}")
    private String accountKey;

    @Bean
    public CosmosClient cosmosClient() {
        return new CosmosClientBuilder()
                .endpoint(accountEndpoint)
                .key(accountKey)
                .buildClient();
    }
}
