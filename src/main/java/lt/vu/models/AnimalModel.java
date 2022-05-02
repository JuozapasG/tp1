package lt.vu.models;

import lombok.Getter;
import lombok.Setter;
import lt.vu.constants.ParamsConstants;
import lt.vu.dtos.AnimalDto;
import lt.vu.dtos.FoodDto;
import lt.vu.services.AnimalService;
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
    private AnimalService animalService;

    @Inject
    private FoodService foodService;

    @Getter
    private AnimalDto animal;

    @Setter
    @Getter
    private Long newFoodId;

    @Getter
    private List<FoodDto> foodList;

    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        var id = req.getParameter(ParamsConstants.ANIMAL_ID);
        animal = animalService.getById(Long.valueOf(id));
        foodList = foodService.getAllExceptAnimal(Long.valueOf(id));
    }

    public String addNewFood() {
        foodService.addNewFoodToAnimal(animal.getId(), newFoodId);
        return "index?faces-redirect=true";
    }
}
