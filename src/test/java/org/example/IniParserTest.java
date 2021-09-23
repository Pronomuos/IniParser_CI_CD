package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.example.exception.*;
import org.example.model.ParsedIniFile;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IniParserTest {

    @Test
    public void parse_WhenValidFile_ReturnParsed() throws URISyntaxException, ParserException {
        File iniFile = new File(this.getClass().getResource("/valid.ini").toURI());

        ParsedIniFile parsedIniFile = IniParser.parse(iniFile);

        assertEquals(5000, parsedIniFile.getSection("COMMON").getInt("StatisterTimeMs"));
        assertEquals("/sata/panorama", parsedIniFile.getSection("COMMON").getString("DiskCachePath"));
        assertEquals(0.65, parsedIniFile.getSection("ADC_DEV").getDouble("BufferLenSeconds"), 0.05);
    }


    @ParameterizedTest
    @ValueSource(strings = {"/invalid.ini"})
    public void parse_WhenInvalidFile_ThrowException(String filename) throws URISyntaxException {
        File iniFile = new File(this.getClass().getResource(filename).toURI());

        assertThrows(InvalidFormatException.class, () -> IniParser.parse(iniFile));
    }
}
