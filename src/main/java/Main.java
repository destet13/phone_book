import javassist.NotFoundException;
import org.ubiwhere.phonebook.persistence.dao.EntryDao;
import org.ubiwhere.phonebook.persistence.dao.PhoneNumberDao;
import org.ubiwhere.phonebook.persistence.model.Entry;
import org.ubiwhere.phonebook.services.EntryServiceImpl;
import org.ubiwhere.phonebook.services.PhoneNumberServiceImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NotFoundException {

        EntryServiceImpl entryService = new EntryServiceImpl();
        PhoneNumberServiceImpl phoneNumberService = new PhoneNumberServiceImpl();

        EntryDao entryDao = new EntryDao();
        PhoneNumberDao phoneNumberDao = new PhoneNumberDao();

        entryService.setEntryDao(entryDao);
        phoneNumberService.setPhoneNumberDao(phoneNumberDao);

        Scanner scanner = new Scanner(System.in);

        boolean toEnd = true;

        Presentation.init(entryService.getAll());

        while (toEnd) {
            String input = scanner.nextLine();

            ActionObject action = Helpers.getAction(input);
            System.out.println(action);
            switch (action.getAction()) {
                case ADD:
                case EDIT:
                    entryService.setEntryDao(entryDao);
                    entryService.addOrUpdate(Helpers.buildEntry(action, scanner));
                    entryService.setEntryDao(entryDao);
                    Presentation.init(entryService.getAll());
                    break;
                case REMOVE:
                    entryService.setEntryDao(entryDao);
                    entryService.delete(action.getId());
                    entryService.setEntryDao(entryDao);
                    Presentation.init(entryService.getAll());
                    break;
                case GET:
                    entryService.setEntryDao(entryDao);
                    Entry entry = entryService.getById(action.getId());
                    Presentation.presentOne(entry);
                    break;
                case GET_ALL:
                    entryService.setEntryDao(entryDao);
                    Presentation.init(entryService.getAll());
                    break;
                case ADD_NUMBER:
                    entryService.setEntryDao(entryDao);
                    entryService.addPhoneNumber(action.getId(), Helpers.buildPhoneNumber(scanner));
                    entryService.setEntryDao(entryDao);
                    Presentation.presentOne(entryService.getById(action.getId()));
                case TERMINATE:
                    toEnd = false;
                    System.out.println("Closing Phone Book");
                    break;
            }
        }
    }
}