package org.example.model;

import org.junit.Test;
import org.example.exception.InvalidPropertyException;

import static org.junit.Assert.assertEquals;

public class PropertyTest {
    @Test
    public void createProperty_WhenDouble_ReturnDoubleProperty() throws InvalidPropertyException {
        assertEquals(1.5, Property.createProperty("test", "1.5").getValue());
        assertEquals(10.1, Property.createProperty("test", "10.1").getValue());
    }

    @Test
    public void createProperty_WhenInt_ReturnIntProperty() throws InvalidPropertyException {
        assertEquals(1, Property.createProperty("test", "1").getValue());
        assertEquals(10, Property.createProperty("test", "10").getValue());
    }

    @Test
    public void createProperty_WhenString_ReturnStrProperty() throws InvalidPropertyException {
        assertEquals("str", Property.createProperty("test", "str").getValue());
        assertEquals("STR", Property.createProperty("test", "STR").getValue());

    }
}
