package lt.vu.services;

import lt.vu.dao.AnimalDAO;
import lt.vu.dao.FoodDAO;
import lt.vu.dao.ShelterDAO;
import lt.vu.dtos.AnimalDto;
import lt.vu.entities.Animal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class AnimalService {

    @Inject
    private AnimalDAO animalDAO;

    @Inject
    private ShelterDAO shelterDAO;

    @Inject
    private FoodDAO foodDAO;

    public String save(AnimalDto animalDto){
        var animal = Animal.builder()
                .name(animalDto.getName())
                .type(animalDto.getType())
                .shelter(shelterDAO.getById(animalDto.getShelter().getId()))
                .build();
        var food = foodDAO.getById(animalDto.getNewFoodId());
        animal.getFoods().add(food);
        animalDAO.persist(animal);
        food.getAnimalConsumers().add(animal);
        foodDAO.persist(food);
        return "success";
    }

    public AnimalDto getById(Long id){
        return AnimalDto.from(animalDAO.getById(id));
    }


    public List<AnimalDto> getAll() {
        return animalDAO.getAll().stream().map(AnimalDto::from).collect(Collectors.toList());
    }
}
