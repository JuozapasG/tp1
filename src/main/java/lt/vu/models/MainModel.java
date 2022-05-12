package lt.vu.models;

import lombok.Getter;
import lombok.Setter;
import lt.vu.cdi.NameGenerator;
import lt.vu.constants.AnimalTypeConstants;
import lt.vu.dtos.AnimalDto;
import lt.vu.dtos.FoodDto;
import lt.vu.dtos.ShelterDto;
import lt.vu.services.AnimalService;
import lt.vu.services.FoodService;
import lt.vu.services.ShelterService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
public class MainModel implements Serializable {

    @Inject
    private ShelterService shelterService;

    @Inject
    private AnimalService animalService;

    @Inject
    private FoodService foodService;

    @Setter
    @Getter
    private AnimalDto newAnimal = new AnimalDto();

    @Setter
    @Getter
    private ShelterDto newShelter = new ShelterDto();

    @Setter
    @Getter
    private FoodDto newFood = new FoodDto();

    @Getter
    private List<AnimalDto> animals = new ArrayList<>();

    @Getter
    private List<ShelterDto> shelters = new ArrayList<>();

    @Getter
    private List<FoodDto> food = new ArrayList<>();

    @Getter
    private final AnimalTypeConstants animalTypeConstants = new AnimalTypeConstants();

    @Inject
    private NameGenerator nameGenerator;

    @PostConstruct
    public void init() {
        animals = animalService.getAll();
        animals.forEach(animal -> animal.setName(nameGenerator.generateUniqueName(animal.getName(), animal.getId())));
        shelters = shelterService.getAll();
        food = foodService.getAll();
    }

    public String addAnimal() {
        animalService.save(newAnimal);
        return "success";
    }

    public String createNewShelter() {
        return shelterService.save(newShelter);
    }

    public String createNewFood() {
        return foodService.save(newFood);
    }

}
