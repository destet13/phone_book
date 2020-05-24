package org.ubiwhere.phonebook.persistence.dao;

import org.ubiwhere.phonebook.persistence.model.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public class EntryDao implements Dao<Entry> {

    @PersistenceContext
    private EntityManager em;

    public void setEm() {
        this.em = Persistence.createEntityManagerFactory("phonebookPU").createEntityManager();;
    }

    @Override
    public List<Entry> findAll() {
        List<Entry> entries = null;

        try {
            entries = em.createQuery("from Entry").getResultList();
        } catch (Exception e) {
            System.err.println("Something went wrong - details: " + e.getMessage());
        } finally {
            em.close();
        }

        return entries;
    }

    @Override
    public Entry findById(Integer id) {

        Entry entry = null;

        try {
            entry = em.find(Entry.class, id);
        } catch (Exception e) {
            System.err.println("Something went wrong - details: " + e.getMessage());
        } finally {
            em.close();
        }
        return entry;
    }

    @Override
    public Entry saveOrUpdate(Entry modelObject) {
        try {
            // remove when fixed problem with EM
            setEm();
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

            em.remove(em.find(Entry.class, id));

            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Something went wrong - details: " + e.getMessage());

            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
