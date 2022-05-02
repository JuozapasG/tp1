package lt.vu.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.entities.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {
    private Long id;
    private String name;
    @Builder.Default
    private List<AnimalDto> animalConsumers = new ArrayList<>();

    public static FoodDto from(Food food) {
        return FoodDto.builder()
                .id(food.getId())
                .name(food.getName())
                .animalConsumers(food.getAnimalConsumers()
                        .stream()
                        .map(AnimalDto::from)
                        .collect(Collectors.toList()))
                .build();
    }

    public String getConsumersString() {
        if(animalConsumers.isEmpty()){
            return "none";
        }
        return animalConsumers.stream()
                .map(AnimalDto::getName)
                .collect(Collectors.joining(", "));
    }
}
