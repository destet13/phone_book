import org.ubiwhere.phonebook.persistence.dao.EntryDao;
import org.ubiwhere.phonebook.persistence.model.Entry;
import org.ubiwhere.phonebook.persistence.model.PhoneNumber;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntryDao entryDao = new EntryDao();

        entryDao.setEm(Persistence.createEntityManagerFactory("phonebookPU").createEntityManager());

        /*Entry entry = new Entry();
        PhoneNumber phoneNumber = new PhoneNumber();

        entry.setName("Pedro");
        entry.setAddress("Pedro street");
        entry.setEmail("pedro@mail.com");
        phoneNumber.setNumber("915883744");
        entry.addPhoneNumber(phoneNumber);*/

        System.out.println(entryDao.findAll());
    }
}
