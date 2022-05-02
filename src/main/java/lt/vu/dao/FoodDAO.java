package lt.vu.dao;


import lombok.Setter;
import lt.vu.entities.Food;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@ApplicationScoped
public class FoodDAO {

    @Setter
    @PersistenceContext
    private EntityManager em;

    public Collection<Food> getAll() {
        return em.createNamedQuery("Food.findAll", Food.class).getResultList();
    }

    public void persist(Food food) {
        this.em.persist(food);
    }

    public Food getById(Long id) {
        return em.find(Food.class, id);
    }
}
