package org.ubiwhere.phonebook.services;

import javassist.NotFoundException;
import org.ubiwhere.phonebook.persistence.model.Entry;
import org.ubiwhere.phonebook.persistence.model.PhoneNumber;

import java.util.List;

public interface EntryService {

    Entry getById(Integer id) throws NotFoundException;

    List<Entry> getAll();

    Entry addOrUpdate(Entry entry);

    void delete(Integer id);

    void removePhoneNumber(Integer id, Integer phoneNumberId) throws NotFoundException;

    void addPhoneNumber(Integer id, PhoneNumber phoneNumber) throws NotFoundException;
}
