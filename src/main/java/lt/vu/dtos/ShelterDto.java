package lt.vu.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.entities.Shelter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShelterDto {

    private Long id;
    private String name;
    private String address;
    private List<AnimalDto> animals;

    public static ShelterDto from(Shelter shelter) {
        return ShelterDto.builder()
                .id(shelter.getId())
                .name(shelter.getName())
                .address(shelter.getAddress())
                .animals(shelter.getAnimals()
                        .stream()
                        .map(AnimalDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
