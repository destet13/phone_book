package org.ubiwhere.phonebook.services;

import javassist.NotFoundException;
import org.ubiwhere.phonebook.persistence.dao.EntryDao;
import org.ubiwhere.phonebook.persistence.dao.PhoneNumberDao;
import org.ubiwhere.phonebook.persistence.model.Entry;
import org.ubiwhere.phonebook.persistence.model.PhoneNumber;

import java.util.List;

public class EntryServiceImpl implements EntryService {

    private EntryDao entryDao;
    private PhoneNumberDao phoneNumberDao;

    public void setEntryDao(EntryDao entryDao) {
        // this is to be removed once rest is working
        entryDao.setEm();
        this.entryDao = entryDao;
    }

    public void setPhoneNumberDao(PhoneNumberDao phoneNumberDao) {
        // this is to be removed once rest is working
        phoneNumberDao.setEm();
        this.phoneNumberDao = phoneNumberDao;
    }

    @Override
    public Entry getById(Integer id) throws NotFoundException {
        Entry entry = entryDao.findById(id);

        if (entry == null) {
            throw new NotFoundException("Entry not found");
        }

        return entry;
    }

    @Override
    public List<Entry> getAll() {
        return entryDao.findAll();
    }

    @Override
    public Entry addOrUpdate(Entry entry) {
        return entryDao.saveOrUpdate(entry);
    }

    @Override
    public void delete(Integer id) {
        entryDao.delete(id);
    }

    @Override
    public void removePhoneNumber(Integer id, Integer phoneNumberId) throws NotFoundException {

        Entry entry = entryDao.findById(id);
        PhoneNumber phoneNumber = phoneNumberDao.findById(phoneNumberId);

        if (entry == null) {
            throw new NotFoundException("Entry not found");
        }

        if (phoneNumber == null || !phoneNumber.getEntry().getId().equals(id)) {
            throw new NotFoundException("PhoneNumber not found");
        }

        entry.removePhoneNumber(phoneNumber);
        entryDao.saveOrUpdate(entry);

    }

    @Override
    public void addPhoneNumber(Integer id, PhoneNumber phoneNumber) throws NotFoundException {

        Entry entry = entryDao.findById(id);

        if (entry == null) {
            throw new NotFoundException("Entry not found");
        }

        entry.addPhoneNumber(phoneNumber);
        entryDao.saveOrUpdate(entry);
    }
}
