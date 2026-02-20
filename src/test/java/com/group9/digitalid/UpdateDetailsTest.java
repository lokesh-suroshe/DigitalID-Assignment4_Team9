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
    public void testStandardValidUpdate() {
        // Testing that adults can update their name and address normally
        boolean result = manager.updatePersonalDetails("111", "111", "John Doe", "New Street", "01-01-1990");
        assertTrue(result, "Adult should be able to update name and address.");
    }

    @Test
    public void testEvenIDChangeFails() throws IOException {
        // IDs that start with even numbers (0,2,4,6,8) should be locked from changes
        List<String> testData = Arrays.asList("222|Alice|Street3|15-05-1985");
        Files.write(Paths.get(TEST_FILE), testData);
        
        boolean result = manager.updatePersonalDetails("222", "223", "Alice", "Street3", "15-05-1985");
        assertFalse(result, "System must reject ID changes if original ID starts with an even digit.");
    }

    @Test
    public void testMinorAddressChangeFails() {
        // Minors shouldn't be able to change their address
        boolean result = manager.updatePersonalDetails("333", "333", "Kid", "NewStreet", "01-01-2015");
        assertFalse(result, "System must block address changes for citizens under 18.");
    }

    @Test
    public void testBirthdayChangeSuccess() {
        // Should allow updating just the birthdate without changing other fields
        boolean result = manager.updatePersonalDetails("111", "111", "John", "Street1", "02-02-1990");
        assertTrue(result, "DOB-only update should be permitted.");
    }

    @Test
    public void testBirthdayWithOtherChangeFails() {
        // Can't change birthdate and name at the same time
        boolean result = manager.updatePersonalDetails("111", "111", "Jonathan", "Street1", "05-05-1990");
        assertFalse(result, "System must reject updates where DOB and Name are changed simultaneously.");
    }
}
