package lt.vu.services;

import lt.vu.dtos.AnimalDto;

import java.util.List;

public interface AnimalServiceInterface {
    Long save(AnimalDto animalDto);
    AnimalDto getById(Long id);
    List<AnimalDto> getAll();
}
