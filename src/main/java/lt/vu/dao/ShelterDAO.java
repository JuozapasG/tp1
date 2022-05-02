package lt.vu.dao;

import lombok.AccessLevel;
import lombok.Setter;
import lt.vu.entities.Shelter;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@ApplicationScoped
public class ShelterDAO {

    @Setter(AccessLevel.PRIVATE)
    @PersistenceContext
    private EntityManager em;

    public Collection<Shelter> getAll() {
        return em.createNamedQuery("Shelter.findAll", Shelter.class).getResultList();
    }

    public void persist(Shelter shelter) {
        this.em.persist(shelter);
    }

    public Shelter getById(Long id) {
        return em.find(Shelter.class, id);
    }
}
