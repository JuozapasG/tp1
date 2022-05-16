package lt.vu.services;

import lt.vu.dao.AnimalDAO;
import lt.vu.dao.ShelterDAO;
import lt.vu.dtos.AnimalDto;
import lt.vu.dtos.ShelterDto;
import lt.vu.mybatis.dao.ShelterMapper;
import lt.vu.mybatis.model.Shelter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class ShelterService {

    @Inject
    private AnimalDAO animalDAO;

    @Inject
    private ShelterDAO shelterDAO;

    @Inject
    private ShelterMapper shelterMapper;

    public List<ShelterDto> getAll() {
        return shelterDAO.getAll().stream().map(ShelterDto::from).collect(Collectors.toList());
    }

    public String save(ShelterDto newShelter) {
        var shelter = new Shelter();
        shelter.setName(newShelter.getName());
        shelter.setAddress(newShelter.getAddress());
        shelterMapper.insert(shelter);
        return "success";
    }

    public ShelterDto getById(Long id) {
        var shelter = shelterMapper.selectByPrimaryKey(id);
        return ShelterDto.builder()
                .id(shelter.getId())
                .address(shelter.getAddress())
                .name(shelter.getName())
                .animals(shelter.getAnimals()
                        .stream()
                        .map(animal -> AnimalDto.builder()
                                .id(animal.getId())
                                .type(animal.getType())
                                .name(animal.getName())
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }

    public String migrateAnimal(Long animalId, Long newShelterId) {
        var animal = animalDAO.getByIdLock(animalId);
        var newShelter = shelterDAO.getById(newShelterId);
        if (animal.getShelter() != null) {
            var oldShelter = animal.getShelter();
            oldShelter.getAnimals().remove(animal);
        }
        animal.setShelter(newShelter);
        newShelter.getAnimals().add(animal);
        return "successMigrating";
    }

    public List<AnimalDto> getOtherAnimals(Long shelterId) {
        var animalList = shelterMapper.selectOtherAnimals(shelterId);
        return animalList.stream().map(animal -> {
            var shelter = shelterMapper.selectByPrimaryKey(animal.getShelterId());
            return AnimalDto.builder()
                    .id(animal.getId())
                    .name(animal.getName())
                    .type(animal.getType())
                    .shelter(ShelterDto.builder()
                            .name(shelter.getName())
                            .address(shelter.getAddress())
                            .build()
                    )
                    .build();
        }).collect(Collectors.toList());
    }
}
