package lt.vu.async;

import lombok.Getter;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Model
public class SearchService implements Serializable {

    @Inject
    private AnimalSearch animalSearch;

    private Future<String> animalNameTask = null;

    public void searchAnimalById(Long id){
        animalNameTask = animalSearch.getAnimalNameById(id);
    }

    public boolean isTaskRunning(){
        return animalNameTask != null && !animalNameTask.isDone();
    }

    public String getAnimalByIdStatus() throws ExecutionException, InterruptedException {
        if(animalNameTask == null){
            return null;
        }
        return isTaskRunning() ? "Searching" : animalNameTask.get();
    }
}
