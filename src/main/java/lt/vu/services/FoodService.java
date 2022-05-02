package lt.vu.services;

import lt.vu.dao.AnimalDAO;
import lt.vu.dao.FoodDAO;
import lt.vu.dtos.FoodDto;
import lt.vu.entities.Food;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class FoodService {

    @Inject
    private FoodDAO foodDAO;

    @Inject
    private AnimalDAO animalDAO;

    public List<FoodDto> getAll() {
        return foodDAO.getAll().stream().map(FoodDto::from).collect(Collectors.toList());
    }

    public String save(FoodDto newFood) {
        var food = Food.builder()
                .name(newFood.getName())
                .build();
        foodDAO.persist(food);
        return "success";
    }

    public List<FoodDto> getAllExceptAnimal(Long animalId) {
        return foodDAO.getAll()
                .stream()
                .filter(food -> !food.getAnimalConsumers()
                        .stream()
                        .map(animal -> animal.getId())
                        .collect(Collectors.toList())
                        .contains(animalId)
                )
                .map(FoodDto::from)
                .collect(Collectors.toList());
    }

    public void addNewFoodToAnimal(Long animalId, Long foodId) {
        var animal = animalDAO.getById(animalId);
        var food = foodDAO.getById(foodId);
        food.getAnimalConsumers().add(animal);
        animal.getFoods().add(food);
    }
}
