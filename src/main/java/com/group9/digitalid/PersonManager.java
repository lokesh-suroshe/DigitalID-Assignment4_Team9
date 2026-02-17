package com.group9.digitalid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PersonManager {
    private static final String FILENAME = "citizens.txt";

    public boolean updatePersonalDetails(String oldID, String newID, String newName, 
                                        String newAddress, String newBirthdate) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILENAME));
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
