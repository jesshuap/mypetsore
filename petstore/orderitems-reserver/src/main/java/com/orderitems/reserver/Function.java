package com.orderitems.reserver;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.StorageAccount;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.BindingName;

import com.microsoft.azure.functions.OutputBinding;

import java.util.Optional;
import com.orderitems.reserver.model.Order;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/orders/{orderId:identifier}". 
     * To invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/orders/{orderId}
     */
    @FunctionName("OrderItemsReserver")
    @StorageAccount("AzureWebJobsStorage")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "orders/{orderId}")
                HttpRequestMessage<Optional<Order>> request,
            @BindingName("orderId") String orderId,
            @BlobOutput(
                name = "target", 
                path = "petstore-orders/{orderId}")
                OutputBinding<Order> outputItem,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse parameter
        Order order = request.getBody().orElse(new Order());

        if (order == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please provide order").build();
        } else {
            //update on Blob using the output binding
            outputItem.setValue(order);
            return request.createResponseBuilder(HttpStatus.OK)
                      .body("Order updated")
                      .header("Content-Type", "application/json")
                      .build();
        }
    }
}
