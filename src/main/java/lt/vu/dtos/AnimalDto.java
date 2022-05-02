package lt.vu.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.entities.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalDto {

    private Long id;
    private String name;
    private String type;
    private ShelterDto shelter = new ShelterDto();
    private List<FoodDto> food = new ArrayList<>();
    private Long newFoodId;

    public static AnimalDto from(Animal animal) {
        return AnimalDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .type(animal.getType())
                .shelter(
                        animal.getShelter() == null
                                ? null
                                : ShelterDto.builder()
                                .id(animal.getShelter().getId())
                                .address(animal.getShelter().getAddress())
                                .name(animal.getShelter().getName())
                                .build())
                .food(
                        animal.getFoods().stream()
                                .map(food -> FoodDto.builder()
                                        .id(food.getId())
                                        .name(food.getName())
                                        .build())
                                .collect(Collectors.toList())
                )
                .build();
    }

}
