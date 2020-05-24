package org.ubiwhere.phonebook.services;

import javassist.NotFoundException;
import org.ubiwhere.phonebook.persistence.model.PhoneNumber;

public interface PhoneNumberService {

    PhoneNumber getById(Integer id) throws NotFoundException;

    void delete(int id);
}
