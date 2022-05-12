package lt.vu.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalFoodUpdateDto {
    private Long animalId;
    private Long foodId;
}
