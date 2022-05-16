package lt.vu.cdi;

import lt.vu.constants.AnimalTypeConstants;
import lt.vu.dtos.AnimalDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class AnimalCounterImpl implements AnimalCounter {

    @Override
    public Map<String, Integer> getAnimalsByTypeCount(List<AnimalDto> animals) {
        var filteredAnimals = animals.stream()
                .filter(animal -> AnimalTypeConstants.DOG.equals(animal.getType()))
                .collect(Collectors.toList());
        return Map.of(AnimalTypeConstants.DOG, filteredAnimals.size());
    }
}
