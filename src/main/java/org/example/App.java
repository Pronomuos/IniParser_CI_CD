package org.example;

import org.example.exception.InvalidTypeConversion;
import org.example.exception.NotFoundPropertyException;
import org.example.exception.NotFoundSectionException;
import org.example.exception.ParserException;
import org.example.model.ParsedIniFile;

import java.io.File;
import java.io.FileNotFoundException;

public class App
{
    public static void main(String[] args) {
        if (args.length < 3 || args.length > 4) {
            System.err.println("Invalid args count");
            return;
        }
        try {
            ParsedIniFile iniFile = IniParser.parse(args[0]);
            switch (args[3]) {
                case "int":
                    System.out.println(
                            iniFile.getSection(args[1])
                                    .getInt(args[2])
                    );
                    break;
                case "double":
                    System.out.println(
                            iniFile.getSection(args[1])
                                    .getDouble(args[2])
                    );
                    break;
                case "string":
                    System.out.println(
                            iniFile.getSection(args[1])
                                    .getString(args[2])
                    );
                    break;
                default:
                    System.err.println("Invalid type");
            }
        } catch (NotFoundSectionException ex) {
            System.err.println("File not found");
        } catch (NotFoundPropertyException | InvalidTypeConversion ex) {
            System.err.println("Parsing error");
            ex.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
}
