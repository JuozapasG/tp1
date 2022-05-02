package lt.vu.constants;

import lombok.Getter;

import java.util.List;

@Getter
public class AnimalTypeConstants {

    public final String DOG = "DOG";
    public final String CAT = "CAT";
    public final String RABBIT = "RABBIT";

    List<String> types = List.of(DOG, CAT, RABBIT);

}
