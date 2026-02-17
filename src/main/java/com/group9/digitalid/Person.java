import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class Person {
   private String personID;
   private String firstName;
   private String lastName;
   private String address;
   private String birthdate; // Format: DD-MM-YYYY
   private HashMap<Date, Integer> demeritPoints = new HashMap<>();
   private boolean isSuspended;

   // Constructor to initialize required fields for logic testing
   public Person(String personID, String birthdate) {
       this.personID = personID;
       this.birthdate = birthdate;
       this.isSuspended = false;
   }
   public String addDemeritPoints(String offenseDateStr, int points) {
       // Create date formatter with strict parsing
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
       dateFormat.setLenient(false);

       try {
           // Parse offense date (will throw exception if invalid format)
           Date offenseDate = dateFormat.parse(offenseDateStr);
           // Validate that points are within allowed range (1-6)
           if (points < 1 || points > 6) {
               return "Failed"; // Invalid points
           }

           // Add the points to the HashMap with offense date as key
           demeritPoints.put(offenseDate, points);

           // Placeholder: return Success for now
           return "Success";

       } catch (ParseException e) {
           // Return Failed if offense date format is invalid
           return "Failed";
       }
   }
}
