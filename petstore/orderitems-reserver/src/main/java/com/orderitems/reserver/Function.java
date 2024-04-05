package com.orderitems.reserver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;
import com.orderitems.reserver.model.Order;


/**
 * Azure Functions with Service Bus Trigger.
 */
public class Function {
    /**
     * This function listens at service bus queue
     * @throws JsonProcessingException 
     */
    @FunctionName("OrderItemsReserver")
    public void run(
            @ServiceBusQueueTrigger(
                name = "message", 
                queueName = "OrdersQueue", 
                connection = "AzureWebJobsMyServiceBus") 
                Order message,
            final ExecutionContext context) throws JsonProcessingException {
        context.getLogger().info("Java service bus trigger processed a request.");

        // Parse parameter
        if (message != null) {
            // outputItem.setValue(message);
            context.getLogger().info("Message from SB");
            context.getLogger().info(message.toString());
        }
        String jsonString = new ObjectMapper().writeValueAsString(message);
        context.getLogger().info("JSON transformation:");
        context.getLogger().info(jsonString);

        uploadToBlob(System.getenv("AzureWebJobsStorage"), "petstore-orders", message.getId(), jsonString);
    }

    private void uploadToBlob(String connectionString, String containerName, String blobPath, String content) {
        // Use Azure Storage Blob client library to upload the content to the blob
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(blobPath);

        // Upload JSON data to the blob
         // Convert JSON data to bytes first
        byte[] jsonDataBytes = content.getBytes(StandardCharsets.UTF_8);

        try (ByteArrayInputStream dataStream = new ByteArrayInputStream(jsonDataBytes)) {
            blobClient.upload(dataStream, jsonDataBytes.length, true);
            System.out.println("JSON data uploaded successfully to blob: " + blobClient.getBlobUrl());
        } catch (IOException e) {
            System.err.println("Error uploading JSON data to blob: " + e.getMessage());
        }
    }
}
