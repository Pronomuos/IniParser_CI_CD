package org.example.model;

import lombok.Value;
import org.example.exception.InvalidPropertyException;
import org.example.exception.InvalidTypeConversion;
import org.example.exception.NotFoundPropertyException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Value
public class Section {
   String name;
   Map<String, Property<?>> data = new LinkedHashMap<>();

    public Section(String name) {
        this.name = name;
    }

    public Section(String name, Property<?>... properties) {
        this.name = name;
        List.of(properties).forEach(this::addProperty);
    }

    public <T> void addProperty(String key, String value) throws InvalidPropertyException {
        data.put(key, Property.createProperty(key, value));
    }

    public <T> void addProperty(Property<T> property) {
        data.put(property.getKey(), property);
    }

    public void removeProperty(String key) {
        data.remove(key);
    }

    public int getInt(String key) throws NotFoundPropertyException, InvalidTypeConversion {
        return (Integer) getProperty(key, AvailableTypes.INT);

    }

    public double getDouble(String key) throws NotFoundPropertyException, InvalidTypeConversion {
        return (Double) getProperty(key, AvailableTypes.DOUBLE);
    }

    public String getString(String key) throws NotFoundPropertyException, InvalidTypeConversion {
        return (String) getProperty(key, AvailableTypes.STRING);
    }

    private Object getProperty (String key, AvailableTypes type) throws NotFoundPropertyException, InvalidTypeConversion {
        Property<?> property =  Optional
                .ofNullable(data.get(key))
                .orElseThrow(NotFoundPropertyException::new);

        if (property.getType().equals(type))
            return property.getValue();
        else
            throw new InvalidTypeConversion("From " + property.getType() + " to " + type + ".");
    }

}
