package com.chtrembl.petstore.product.model;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Product.StatusEnum, String>{
    @Override
    public String convertToDatabaseColumn(Product.StatusEnum status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public Product.StatusEnum convertToEntityAttribute(String status) {
        if (status == null) {
            return null;
        }

        return Stream.of(Product.StatusEnum.values())
          .filter(c -> c.getValue().equals(status))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}
