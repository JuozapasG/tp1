package lt.vu.services;

import lombok.extern.slf4j.Slf4j;
import lt.vu.dao.AnimalDAO;
import lt.vu.dao.ShelterDAO;
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
        return ShelterDto.from(shelterDAO.getById(id));
    }

    public String migrateAnimal(Long animalId, Long newShelterId) {
        var animal = animalDAO.getById(animalId);
        var newShelter = shelterDAO.getById(newShelterId);
        if( animal.getShelter() != null){
            var oldShelter = animal.getShelter();
            oldShelter.getAnimals().remove(animal);
        }
        animal.setShelter(newShelter);
        newShelter.getAnimals().add(animal);
        return "successMigrating";
    }
}
