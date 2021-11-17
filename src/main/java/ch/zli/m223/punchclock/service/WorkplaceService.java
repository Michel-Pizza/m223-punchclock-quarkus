package ch.zli.m223.punchclock.service;


import ch.zli.m223.punchclock.domain.Workplace;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@RolesAllowed("Admin")
public class WorkplaceService {

    @Inject
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    public List<Workplace> findAll() {
        var query = entityManager.createQuery("FROM Workplace");
        return query.getResultList();
    }



    @Transactional
    public Workplace createWorkplace(Workplace workplace) {
        entityManager.persist(workplace);
        return workplace;
    }

    @Transactional
    public Workplace getSingleWorkplace(Long id){
        return entityManager.find(Workplace.class, id);
    }


    //braucht einen check, ob der workplace gebraucht wird.
    @Transactional
    public String deleteWorkplace(Long id) {
        Workplace entry = entityManager.find(Workplace.class, id);
        entityManager.remove(entry);
        return "Successfully removed Workplace " + id;
    }

    @Transactional
    public Workplace changeWorkplace(Workplace workplace) {
        Workplace newWorkplace = entityManager.find(Workplace.class, workplace.getId());
        newWorkplace.setWorkplaceName(workplace.getWorkplaceName());
        return newWorkplace;
    }


}
