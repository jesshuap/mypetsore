package com.chtrembl.petstore.pet.model;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Pet.StatusEnum, String>{
    @Override
    public String convertToDatabaseColumn(Pet.StatusEnum status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public Pet.StatusEnum convertToEntityAttribute(String status) {
        if (status == null) {
            return null;
        }

        return Stream.of(Pet.StatusEnum.values())
          .filter(c -> c.getValue().equals(status))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}
