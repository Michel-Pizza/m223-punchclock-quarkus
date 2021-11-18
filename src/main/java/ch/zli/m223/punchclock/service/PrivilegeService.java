package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Privilege;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped

public class PrivilegeService {

    @Inject
    EntityManager entityManager;


    @SuppressWarnings ("unchecked")
    public List<Privilege> findAll() {
        var query = entityManager.createQuery("FROM Privilege");
        return query.getResultList();
    }


    @Transactional
    public Privilege createPrivilege(Privilege privilege) {
        entityManager.persist(privilege);
        return privilege;
    }

    @Transactional
    public Privilege getSinglePrivilege(Long id) {
        return entityManager.find(Privilege.class, id);
    }


    //braucht einen check, ob der Privilege gebraucht wird.
    @Transactional
    public String deletePrivilege(Long id) {
        Privilege privilege = entityManager.find(Privilege.class, id);
        entityManager.remove(privilege);
        return "Successfully removed Privilege " + id;
    }

    @Transactional
    public Privilege changePrivilege(Privilege privilege) {
        Privilege newPrivilege = entityManager.find(Privilege.class, privilege.getId());
        //define what he changes
        return newPrivilege;
    }


}