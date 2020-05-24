import com.mysql.cj.util.StringUtils;
import org.ubiwhere.phonebook.persistence.model.Entry;
import org.ubiwhere.phonebook.persistence.model.PhoneNumber;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Helpers {

    // gets the action after the input from the user
    public static ActionObject getAction(String input) {
        List<String> actions = Arrays.asList(input.replaceAll("\\s+", "").split(","));

        String actionToDo = null;

        // to see if the user didn't add and additional parameter
        // this allows to see if the user want's to just see one entry or do something with that entry/phoneNumber
        if (actions.size() > 1){
            actionToDo = actions.get(1);
        } else {
            actionToDo = actions.get(0);
        }

        if (!StringUtils.isStrictlyNumeric(actionToDo)) {
            switch (actionToDo) {
                case "a":
                    return new ActionObject(0, Actions.ADD);
                case "e":
                    return new ActionObject(Integer.parseInt(actions.get(0)), Actions.EDIT);
                case "r":
                    return new ActionObject(Integer.parseInt(actions.get(0)), Actions.REMOVE);
                case "ap":
                    return new ActionObject(Integer.parseInt(actions.get(0)), Actions.ADD_NUMBER);
                case "ep":
                    return new ActionObject(Integer.parseInt(actions.get(0)), Actions.EDIT_NUMBER);
                case "rp":
                    return new ActionObject(Integer.parseInt(actions.get(0)), Actions.REMOVE_NUMBER);
                case "end":
                    return new ActionObject(0, Actions.TERMINATE);
                case "all":
                default:
                    return new ActionObject(0, Actions.GET_ALL);
            }
        }
        return new ActionObject(Integer.parseInt(actionToDo), Actions.GET);
    }

    // since we dont have rest layer in this case we have to build our own object to insert in out db
    // if we where working with rest, this would be done with a converter
    // where we convert dtoToEntity and EntityToDto depending if we want to insert/update/remove or present de data
    public static Entry buildEntry(ActionObject action, Scanner scanner) {

        Entry entry = new Entry();

        if (action.getId() != 0) {
            entry.setId(action.getId());
        }

        System.out.println("Name:");
        String name = scanner.nextLine();

        System.out.println("Address:");
        String address = scanner.nextLine();

        System.out.println("Email:");
        String email = scanner.nextLine();

        System.out.println("PhoneNumber:\n" +
                "If you want to add more then one separate them with ','");
        String phoneNumbers = scanner.nextLine();

        entry.setName(name);
        entry.setEmail(email);
        entry.setAddress(address);

        for (String number :
                phoneNumbers.split(",")) {
            PhoneNumber phoneNumber = new PhoneNumber();

            phoneNumber.setNumber(number);

            entry.addPhoneNumber(phoneNumber);
        }

        return entry;
    }

    public static PhoneNumber buildPhoneNumber(Scanner scanner) {

        PhoneNumber phoneNumber = new PhoneNumber();

        System.out.println("Number:");
        String name = scanner.nextLine();

        phoneNumber.setNumber(name);

        return phoneNumber;
    }
}