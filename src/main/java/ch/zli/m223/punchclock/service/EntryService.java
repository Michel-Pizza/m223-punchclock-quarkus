package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;

@ApplicationScoped
@RolesAllowed("User")
public class EntryService {
    @Inject
    EntityManager entityManager;

    public EntryService() {
    }



    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }


    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional
    public Entry getSingleEntry(Long id){

        return entityManager.find(Entry.class, id);
    }


    @Transactional
    public String deleteEntry(Long id) {
        Entry entry = entityManager.find(Entry.class, id);
        entityManager.remove(entry);
        return "Successfully removed Entry " + id;
    }

    @Transactional
    public Entry changeEntry(Entry entry) {
        Entry entryy = entityManager.find(Entry.class, entry.getId());
        entryy.setCheckIn(entry.getCheckIn());
        entryy.setCheckOut(entry.getCheckOut());
        entityManager.persist(entryy);

        return entryy;
    }
}
