package com.chtrembl.petstore.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import com.chtrembl.petstore.order.model.Order;

@Service
public class OrderService {
    @Autowired
    private CosmosClient cosmosClient;

    private final String databaseName = "PetStoreOrders";
    private final String containerName = "orders";

   static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public Order createOrUpdateOrder(Order order) {
        // Create or update the order in the orders container
        CosmosItemResponse<Order> response = cosmosClient.getDatabase(databaseName)
                .getContainer(containerName)
                .upsertItem(order, new PartitionKey(order.getEmail()), new CosmosItemRequestOptions());

        return response.getItem();
    }

    public Order findOrderById(String id, String email) {
        // Retrieve the order by its ID from the orders container
        try{
            CosmosItemResponse<Order> response = cosmosClient.getDatabase(databaseName)
                .getContainer(containerName)
                .readItem(id, new PartitionKey(email), Order.class);
            return response.getItem();
        }catch(Exception ex){
            log.info("Exception happened");
            return new Order();
        }
    }
    
}
