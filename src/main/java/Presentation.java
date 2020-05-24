import com.mysql.cj.util.StringUtils;
import org.ubiwhere.phonebook.persistence.model.Entry;

import java.util.List;

// this class has all the printing data to present in the terminal
public class Presentation {

    public static void init(List<Entry> allEntries) {
        System.out.println("WELCOME TO YOUR PERSONAL PHONE BOOK\n");
        System.out.println(String.format("%10s %10s %10s %5s %20s %5s %20s %5s %10s", "id", "|", "Name", "|", "Address", "|", "Email", "|", "PhoneNumbers"));
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
        presentAll(allEntries);
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
        loadMainMenuControls();
    }

    public static void presentOne(Entry entry) {
        System.out.println(String.format("%10s %10s %10s %5s %20s %5s %20s", "id", "|", "Name", "|", "Address", "|", "Email"));
        System.out.println(String.format("%10s %10s %10s %5s %20s %5s %20s", entry.getId(), "|", entry.getName(), "|", entry.getAddress(), "|", entry.getEmail()));
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
        System.out.println("Phone numbers:");
        System.out.println(String.format("%5s %5s %15s %10s", "id", "|", "number", "|"));
        presentAll(entry.getPhoneNumbers());
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
        loadEntryControls();
    }

    private static void loadMainMenuControls() {
        System.out.println("\n " +
                "ENTER entry id followed by ',' and 'e' to edit or 'r' to remove\n" +
                "ENTER 'a' to add new entry\n" +
                "to exit ENTER 'end'");
    }

    private static void loadEntryControls() {
        System.out.println("\n" +
                "ENTER phone number id followed by ',' and 'ep' to edit or 'rp' to remove\n" +
                "ENTER entry id followed by ',' and 'ap' to add new phone number\n" +
                "to return ENTER 'all'\n" +
                "to exit ENTER 'end'");
    }

    // generic method to print all object in a list
    private static void presentAll(List<?> l) {
        for (Object o :
                l) {
            System.out.println(o);
        }
    }
}
