package org.example.model;

import org.junit.Test;
import org.example.exception.InvalidPropertyException;

import static org.junit.Assert.assertEquals;

public class PropertyTest {
    @Test
    public void createProperty_WhenInt_ReturnIntProperty() throws InvalidPropertyException {
        assertEquals(1, Property.createProperty("test", "1").getValue());
        assertEquals(100, Property.createProperty("test", "100").getValue());
    }
}
