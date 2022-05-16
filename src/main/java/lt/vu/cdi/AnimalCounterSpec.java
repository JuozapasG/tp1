package lt.vu.cdi;

import lt.vu.constants.AnimalTypeConstants;
import lt.vu.dtos.AnimalDto;

import javax.enterprise.inject.Specializes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Specializes
public class AnimalCounterSpec extends AnimalCounterImpl {

    public Map<String, Integer> getAnimalsByTypeCount(List<AnimalDto> animals) {
        var resultMap = new HashMap<String, Integer>();
        AnimalTypeConstants.types.forEach(type -> {
            var count = animals.stream()
                    .filter(animal-> type.equals(animal.getType()))
                    .count();
            resultMap.put(type, (int) count);
        });
        return resultMap;
    }
}
