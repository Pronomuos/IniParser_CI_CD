package org.example.model;

import lombok.Value;
import org.example.exception.InvalidPropertyException;
import org.example.exception.InvalidTypeConversion;
import org.example.exception.NotFoundPropertyException;
import org.example.exception.NotFoundSectionException;

import java.util.*;

@Value
public class ParsedIniFile {
    Map<String, Section> data;

    public ParsedIniFile (Map<String, Section> data) {
        this.data = data;
    }

    public Section getSection(String name) throws NotFoundSectionException {
        return Optional
                .ofNullable(data.get(name))
                .orElseThrow(() -> new NotFoundSectionException(" section " + name + "."));
    }

    public void addSection(Section section) {
        data.put(section.getName(), section);
    }

    public void removeSection(String name) {
        data.remove(name);
    }

    public Integer getIntegerVal(String sectionKey, String propertyKey) throws InvalidTypeConversion, NotFoundPropertyException, NotFoundSectionException {
        return getSection(sectionKey)
                .getInt(propertyKey);
    }

    public Double getDoubleVal(String sectionKey, String propertyKey) throws InvalidTypeConversion, NotFoundPropertyException, NotFoundSectionException {
        return getSection(sectionKey)
                .getDouble(propertyKey);
    }

    public String getStringVal(String sectionKey, String propertyKey) throws InvalidTypeConversion, NotFoundPropertyException, NotFoundSectionException {
        return getSection(sectionKey)
                .getString(propertyKey);
    }

    public void addVal(String sectionName, String key, String value) throws InvalidPropertyException, NotFoundSectionException {
        getSection(sectionName).addProperty(key, value);
    }

    public void removeVal(String sectionName, String key) throws NotFoundSectionException {
        getSection(sectionName).removeProperty(key);
    }

    public void printInfo() {
        data.forEach((sectionName, section) -> {
            System.out.println("[" + sectionName + "]");
            section.getData().forEach((key, value) ->
                    System.out.println(key + " = " + value));
        });
    }

     public static class IniFileBuilder {
        Map<String, Section> data;

        public IniFileBuilder() {
            data = new LinkedHashMap<String, Section>();
        }

        public void addSection (Section section) {
            data.put(section.getName(), section);
        }

        public ParsedIniFile build() {
            return new ParsedIniFile(data);
        }
    }
}
