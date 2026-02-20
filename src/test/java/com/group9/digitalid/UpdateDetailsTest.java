package com.group9.digitalid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateDetailsTest {
    private static final String TEST_FILE = "citizens.txt";
    private PersonManager manager;

    @BeforeEach
    public void setUp() throws IOException {
        manager = new PersonManager();
        
        List<String> testData = Arrays.asList(
            "111|John|Street1|01-01-1990",
            "333|Kid|Street2|01-01-2015"
        );
        Files.write(Paths.get(TEST_FILE), testData);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    public void testEvenIDChangeFails() throws IOException {
        List<String> testData = Arrays.asList("222|Alice|Street3|15-05-1985");
        Files.write(Paths.get(TEST_FILE), testData);
        
        boolean result = manager.updatePersonalDetails("222", "223", "Alice", "Street3", "15-05-1985");
        assertFalse(result);
    }

    @Test
    public void testMinorAddressChangeFails() {
        boolean result = manager.updatePersonalDetails("333", "333", "Kid", "NewStreet", "01-01-2015");
        assertFalse(result);
    }

    @Test
    public void testBirthdayChangeSuccess() {
        boolean result = manager.updatePersonalDetails("111", "111", "John", "Street1", "02-02-1990");
        assertTrue(result);
    }

    @Test
    public void testBirthdayWithOtherChangeFails() {
        boolean result = manager.updatePersonalDetails("111", "111", "Jonathan", "Street1", "02-02-1990");
        assertFalse(result);
    }
}
