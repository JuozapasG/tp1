package lt.vu.models;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lt.vu.constants.ParamsConstants;
import lt.vu.dtos.AnimalDto;
import lt.vu.dtos.FoodDto;
import lt.vu.services.AnimalServiceInterface;
import lt.vu.services.FoodService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Model
public class AnimalModel implements Serializable {

    @Inject
    private AnimalServiceInterface animalService;

    @Inject
    private FoodService foodService;

    @Getter
    private AnimalDto animal;

    @Setter
    @Getter
    private Long newFoodId;

    @Getter
    private List<FoodDto> foodList;

    @Getter
    private boolean error;

    @SneakyThrows
    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        var id = req.getParameter(ParamsConstants.ANIMAL_ID);
        var error = req.getParameter(ParamsConstants.ERROR);
        if (error != null && !error.isEmpty()) {
            this.error = Boolean.parseBoolean(error);
        }
        animal = animalService.getById(Long.valueOf(id));
        foodList = foodService.getAllExceptAnimal(Long.valueOf(id));
    }

    public String addNewFood() throws InterruptedException {
        try {
            foodService.addNewFoodToAnimalException(animal.getId(), newFoodId);
        } catch (Exception e) {
            return "animal-info.xhtml?animalId=" + animal.getId() + "&error=true&faces-redirect=true";
        }

        return "index?faces-redirect=true";
    }
}
