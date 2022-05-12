package lt.vu.services;

import lombok.SneakyThrows;
import lt.vu.dao.AnimalDAO;
import lt.vu.dao.FoodDAO;
import lt.vu.dtos.FoodDto;
import lt.vu.entities.Animal;
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
                        .map(Animal::getId)
                        .collect(Collectors.toList())
                        .contains(animalId)
                )
                .map(FoodDto::from)
                .collect(Collectors.toList());
    }

    public void addNewFoodToAnimalException(Long animalId, Long foodId) throws InterruptedException {
        var animal = animalDAO.getByIdLock(animalId);
        Thread.sleep(5000);
        var food = foodDAO.getById(foodId);
        food.getAnimalConsumers().add(animal);
        animal.getFoods().add(food);
    }

    @SneakyThrows
    public void addNewFoodToAnimal(Long animalId, Long foodId) {
        var animal = animalDAO.getByIdLock(animalId);
        var food = foodDAO.getById(foodId);
        food.getAnimalConsumers().add(animal);
        animal.getFoods().add(food);
    }
}
