package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.User;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped

public class UserService {

    @Inject
    EntityManager entityManager;


    @SuppressWarnings ("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }


    @Transactional
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public User getSingleUser(Long id) {
        return entityManager.find(User.class, id);
    }


    //braucht einen check, ob der User gebraucht wird.
    @Transactional
    public String deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        return "Successfully removed User " + id;
    }

    @Transactional
    public User changeUser(User user) {
        User newUser = entityManager.find(User.class, user.getId());
        //define what he changes
        return newUser;
    }


}