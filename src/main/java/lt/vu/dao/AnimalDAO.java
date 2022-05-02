package lt.vu.dao;

import lombok.Setter;
import lt.vu.entities.Animal;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@ApplicationScoped
public class AnimalDAO {

    @Setter
    @PersistenceContext
    private EntityManager em;

    public Collection<Animal> getAll() {
        return em.createNamedQuery("Animal.findAll", Animal.class).getResultList();
    }

    public void persist(Animal animal) {
        this.em.persist(animal);
    }

    public Animal getById(Long id) {
        return em.find(Animal.class, id);
    }
}
