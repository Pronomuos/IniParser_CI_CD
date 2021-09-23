package org.example.model;

import lombok.Value;
import org.example.exception.InvalidPropertyException;

@Value
public class Property<T> {
    String key;
    T value;
    AvailableTypes type;

    private Property(String key, T value, AvailableTypes type) {
        this.key = key;
        this.type = type;
        this.value = value;
    }

    public static Property<?> createProperty(String key, String value) throws InvalidPropertyException {
        try {
            if (value.matches("\\d+"))
                return new Property<>(key, Integer.parseInt(value), AvailableTypes.INT);
            else if (value.matches("\\d+\\.\\d+"))
                return new Property<>(key, Double.parseDouble(value), AvailableTypes.DOUBLE);
            else
                return new Property<>(key, value, AvailableTypes.STRING);
        } catch (NumberFormatException ex) {
            throw new InvalidPropertyException("Cannot create property with key " + key + ".");
        }
    }
}
