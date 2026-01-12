package ec.edu.ups.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class GenericDAO<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void delete(Object id) {
        T entity = find(id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    public List<T> findAll() {
        return em.createQuery(
            "SELECT e FROM " + entityClass.getSimpleName() + " e",
            entityClass
        ).getResultList();
    }
}
