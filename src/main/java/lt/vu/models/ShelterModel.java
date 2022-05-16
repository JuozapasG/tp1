package lt.vu.models;

import lombok.Getter;
import lombok.Setter;
import lt.vu.cdi.AnimalCounter;
import lt.vu.cdi.AnimalCounterImpl;
import lt.vu.constants.ParamsConstants;
import lt.vu.dtos.AnimalDto;
import lt.vu.dtos.MigrationInfo;
import lt.vu.dtos.ShelterDto;
import lt.vu.services.AnimalService;
import lt.vu.services.ShelterService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model
@ViewScoped
public class ShelterModel implements Serializable {

    @Inject
    ShelterService shelterService;

    @Inject
    AnimalService animalService;

    @Inject
    AnimalCounter animalCounter;

    @Getter
    @Setter
    private ShelterDto shelter;

    @Getter
    @Setter
    private MigrationInfo migrationInfo = new MigrationInfo();

    @Getter
    private List<AnimalDto> otherAnimals;

    @Getter
    private Map<String, Integer> animalsByTypeCount = new HashMap<>();

    @Getter
    private List<String> animalsByTypeList = new ArrayList<>();


    @PostConstruct
    void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        var id = req.getParameter(ParamsConstants.SHELTER_ID);
        if (id == null) {
            return;
        }
        shelter = shelterService.getById(Long.valueOf(id));
        otherAnimals = shelterService.getOtherAnimals(Long.valueOf(id));
        migrationInfo.setShelterId(shelter.getId());
        animalsByTypeCount = animalCounter.getAnimalsByTypeCount(shelter.getAnimals());
        animalsByTypeCount.keySet().forEach(key -> {
            if (animalsByTypeCount.get(key) > 0) {
                animalsByTypeList.add(key);
            }
        });
    }

    public String migrateAnimal() {
        return shelterService.migrateAnimal(migrationInfo.getAnimalId(), migrationInfo.getShelterId());
    }

}
