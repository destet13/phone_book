package org.ubiwhere.phonebook.services;

import javassist.NotFoundException;
import org.ubiwhere.phonebook.persistence.dao.EntryDao;
import org.ubiwhere.phonebook.persistence.dao.PhoneNumberDao;
import org.ubiwhere.phonebook.persistence.model.PhoneNumber;

public class PhoneNumberServiceImpl implements PhoneNumberService {

    private PhoneNumberDao phoneNumberDao;
    private EntryDao entryDao;

    public void setPhoneNumberDao(PhoneNumberDao phoneNumberDao) {
        // this is to be removed once rest is working
        phoneNumberDao.setEm();
        this.phoneNumberDao = phoneNumberDao;
    }

    public void setEntryDao(EntryDao entryDao) {
        // this is to be removed once rest is working
        phoneNumberDao.setEm();
        this.entryDao = entryDao;
    }

    @Override
    public PhoneNumber getById(Integer id) throws NotFoundException {
        PhoneNumber phoneNumber = phoneNumberDao.findById(id);

        if (phoneNumber == null) {
            throw new NotFoundException("PhoneNumber nor found");
        }

        return phoneNumber;
    }

    @Override
    public void delete(int id) {
        phoneNumberDao.delete(id);
    }
}
