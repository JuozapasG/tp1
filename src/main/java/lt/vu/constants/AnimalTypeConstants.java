package lt.vu.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.Model;
import java.util.List;

@Getter
@Model
public class AnimalTypeConstants {

    public static final String DOG = "DOG";
    public static final String CAT = "CAT";
    public static final String RABBIT = "RABBIT";

    public static final List<String> types = List.of(DOG, CAT, RABBIT);

    public Object getTypes() {
        return types;
    }
}
