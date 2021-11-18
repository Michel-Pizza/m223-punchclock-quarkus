package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;

@ApplicationScoped

public class EntryService {

    @Inject
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("SELECT e FROM Entry e");
        return query.getResultList();
    }

    @Transactional
    public Entry getSingleEntry(Long id){

        return entityManager.find(Entry.class, id);
    }

    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional
    public Entry changeEntry(Entry entry) {
        Entry entryy = entityManager.find(Entry.class, entry.getId());
        entryy.setCheckIn(entry.getCheckIn());
        entryy.setCheckOut(entry.getCheckOut());
        entryy.setDescription(entry.getDescription());
        entityManager.persist(entryy);

        return entryy;
    }

    @Transactional
    public String deleteEntry(Long id) {
        Entry entry = entityManager.find(Entry.class, id);
        entityManager.remove(entry);
        return "Successfully removed Entry " + id;
    }


}
