package org.ubiwhere.phonebook.persistence.dao;

import org.ubiwhere.phonebook.persistence.model.PhoneNumber;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PhoneNumberDao implements Dao<PhoneNumber> {

    @PersistenceContext
    private EntityManager em;

    public void setEm() {
        this.em = Persistence.createEntityManagerFactory("phonebookPU").createEntityManager();;
    }

    @Override
    public List<PhoneNumber> findAll() {
        List<PhoneNumber> entries = null;

        try {
            em.getTransaction().begin();

            entries = em.createQuery("from PhoneNumber").getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Something went wrong - details: " + e.getMessage());
        } finally {
            em.close();
        }

        return entries;
    }

    @Override
    public PhoneNumber findById(Integer id) {

        PhoneNumber entry = null;

        try {
            em.getTransaction().begin();

            entry = em.find(PhoneNumber.class, id);
        } catch (Exception e) {
            System.err.println("Something went wrong - details: " + e.getMessage());
        } finally {
            em.close();
        }
        return entry;
    }

    @Override
    public PhoneNumber saveOrUpdate(PhoneNumber modelObject) {

        try {
            em.getTransaction().begin();

            em.merge(modelObject);

            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Something went wrong - details: " + e.getMessage());

            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return modelObject;
    }

    @Override
    public void delete(Integer id) {
        try {
            em.getTransaction().begin();

            em.remove(em.find(PhoneNumber.class, id));

            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Something went wrong - details: " + e.getMessage());

            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}