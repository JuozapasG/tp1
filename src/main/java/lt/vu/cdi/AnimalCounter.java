package lt.vu.cdi;

import lt.vu.dtos.AnimalDto;

import java.util.List;
import java.util.Map;

public interface AnimalCounter {
    public Map<String, Integer> getAnimalsByTypeCount(List<AnimalDto> animals);
}
