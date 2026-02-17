// Import JUnit classes for assertions and tests
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// Test class for Person demerit points functionality
class PersonDemeritTest {

    @Test
   void testValidDemeritAddition() {
       // Create a person with valid birthdate
       Person p = new Person("56s_d%&fAB", "15-11-1990");

       // Add valid demerit points and expect success
       assertEquals("Success", p.addDemeritPoints("10-02-2026", 3), "Should succeed with valid inputs");
   }
      @Test
   void testInvalidDateFormat() {
       // Create person
       Person p = new Person("56s_d%&fAB", "15-11-1990");

       // Invalid date format (slashes instead of dashes) should fail
       assertEquals("Failed", p.addDemeritPoints("10/02/2026", 3), "Should fail due to invalid date format");
   }

   @Test
   void testPointsOutsideRange() {
       // Create person
       Person p = new Person("56s_d%&fAB", "15-11-1990");

       // Points outside valid range should fail
       assertEquals("Failed", p.addDemeritPoints("10-02-2026", 7), "Should fail because points exceed 6");
   }


}
