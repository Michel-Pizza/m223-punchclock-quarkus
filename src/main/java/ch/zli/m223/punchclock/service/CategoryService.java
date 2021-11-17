package ch.zli.m223.punchclock.service;


import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CategoryService {

    @Inject
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category");
        return query.getResultList();
    }



    @Transactional
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Transactional
    public Category getSingleCategory(Long id){

        return entityManager.find(Category.class, id);
    }


    @Transactional
    public String deleteCategory(Long id) {
        Category entry = entityManager.find(Category.class, id);
        entityManager.remove(entry);
        return "Successfully removed Category " + id;
    }

    @Transactional
    public Category changeCategory(Category category) {
        Category newCategory = entityManager.find(Category.class, category.getId());
        newCategory.setTitle(category.getTitle());
        entityManager.persist(newCategory);

        return newCategory;
    }


}
