package org.example.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.example.exception.InvalidPropertyException;
import org.example.exception.InvalidTypeConversion;
import org.example.exception.NotFoundPropertyException;

import static org.junit.Assert.assertEquals;
class SectionTest {

    private static Section section;

    @BeforeAll
    static void setUp() throws InvalidPropertyException {
        section = new Section("TEST",
                Property.createProperty("int", "1"),
                Property.createProperty("double", "2.0"),
                Property.createProperty("str", "string")
        );
    }

    @Test
    public void getProperty_WhenPropertyExists_ReturnProperty() throws InvalidTypeConversion, NotFoundPropertyException {
        assertEquals(1, section.getInt("int"));
        assertEquals(2.0, section.getDouble("double"), 0.05);
        assertEquals("string", section.getString("str"));
    }
}