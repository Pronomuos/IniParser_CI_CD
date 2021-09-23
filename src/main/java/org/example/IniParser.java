package org.example;

import org.example.exception.InvalidFormatException;
import org.example.exception.ParserException;
import org.example.model.ParsedIniFile;
import org.example.model.Section;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IniParser {
    private static final String sectionName = "\\[(?<name>\\w+)]";
    private static final String property = "(?<key>\\w+)\\s*=\\s*(?<value>[\\w./]+)";
    private static final String comment = ";.*";

    private static final Pattern sectionNamePattern =
            Pattern.compile("^\\s*" + sectionName + "\\s*(" + comment + ")?$");
    private static final Pattern propertyPattern =
            Pattern.compile("^\\s*" + property + "\\s*(" + comment + ")?$");
    private static final Pattern commentPattern =
            Pattern.compile("^\\s*(" + comment + ")?$");

    public static ParsedIniFile parse(String fileName) throws ParserException {
        return parse(new File(fileName));
    }

    public static ParsedIniFile parse(File file) throws ParserException {
        try (Scanner scanner = new Scanner(file)) {
            ParsedIniFile.IniFileBuilder builder = new ParsedIniFile.IniFileBuilder();
            String curSectionName = null;
            Section curSection = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher sectionMatcher = sectionNamePattern.matcher(line);
                Matcher propertyMatcher = propertyPattern.matcher(line);
                Matcher commentMatcher = commentPattern.matcher(line);
                if (sectionMatcher.find()) {
                    if (curSectionName != null)
                        builder.addSection(curSection);
                    curSectionName = sectionMatcher.group("name");
                    curSection = new Section(curSectionName);
                } else if (propertyMatcher.find()) {
                    String key = propertyMatcher.group("key");
                    String value = propertyMatcher.group("value");
                    if (curSection == null)
                        throw new InvalidFormatException();
                    curSection.addProperty(key, value);
                } else if (!commentMatcher.find()) {
                    throw new InvalidFormatException(" parser is not able to read the file.");
                }
            }
            return builder.build();
        } catch (ParserException | FileNotFoundException e) {
            throw new InvalidFormatException();
        }
    }
}