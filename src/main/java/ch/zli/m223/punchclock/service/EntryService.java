package ch.zli.m223.punchclock.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.Workplace;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.JSONValue;


@ApplicationScoped
@Log
@Log4j2
@Slf4j
public class EntryService {

    @Inject
    EntityManager entityManager;

    @Inject
    UserService userService;


    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("SELECT e FROM Entry e");
        return query.getResultList();
    }

    @Transactional
    public List<Entry> findAllFromUser(String name){
        User i = userService.getSingleUserUsername(name);
        var query = entityManager.createQuery("SELECT e FROM Entry e where e.user.id = :id");
        query.setParameter("id", i.getId());
        return query.getResultList();
    }

    @Transactional
    public Entry getSingleEntry(Long id){

        return entityManager.find(Entry.class, id);
    }

    @Transactional
    public Entry createEntry(Entry entry, String name) {

        entry.setIsLogin(false);
        entry.setCheckIn(LocalDateTime.now());
        entry.setCheckOut(LocalDateTime.now());
        User user = userService.getSingleUserUsername(name);
        entry.setUser(user);
        entry.setWorkplace(entry.getWorkplace());
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
    public Entry changeBasicEntry(Entry entry) {
        Entry entryy = entityManager.find(Entry.class, entry.getId());
        entryy.setDescription(entry.getDescription());
        entryy.setWorkplace(entry.getWorkplace());
        entityManager.persist(entryy);

        return entryy;
    }

    @Transactional
    public Entry finishEntry(Entry entry) {
        Entry entryy = entityManager.find(Entry.class, entry.getId());
        entryy.setCheckOut(LocalDateTime.now());
        entityManager.persist(entryy);

        return entryy;
    }

    @Transactional
    public String deleteEntry(Long id) {
        Entry entry = entityManager.find(Entry.class, id);
        entityManager.remove(entry);
        return "Successfully removed Entry " + id;
    }

    @Transactional
    public Entry createLoginEntry(Entry data) {


        Entry entry = new Entry();
        entry.setIsLogin(true);
        entry.setCheckIn(LocalDateTime.now());
        entry.setCheckOut(LocalDateTime.now());
        entry.setDescription(data.getDescription());
        entry.setWorkplace(data.getWorkplace());
        User user = userService.getSingleUserUsername(data.getUser().getUsername());
        entry.setUser(user);
        entityManager.persist(entry);
        return entry;
    }
}
