package lt.vu.async;

import lt.vu.services.AnimalService;
import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.concurrent.Future;

@ApplicationScoped
public class AnimalSearch implements Serializable {

    @Inject
    private AnimalService animalService;

    @Futureable
    public Future<String> getAnimalNameById(Long id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(animalService.getById(id).getName());
    }

}
