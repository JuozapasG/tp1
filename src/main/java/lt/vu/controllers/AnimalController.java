package lt.vu.controllers;

import lt.vu.dtos.AnimalDto;
import lt.vu.dtos.AnimalFoodUpdateDto;
import lt.vu.interceptors.LoggingInterceptor;
import lt.vu.services.AnimalService;
import lt.vu.services.FoodService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/animal")
@Produces(MediaType.APPLICATION_JSON)
@LoggingInterceptor
public class AnimalController {

    @Inject
    private AnimalService animalService;

    @Inject
    private FoodService foodService;

    @Path("/{animalId}")
    @GET
    public AnimalDto getById(@PathParam("animalId") Long animalId) {
        return animalService.getById(animalId);
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Long createAnimal(AnimalDto animalDto) {
        return animalService.save(animalDto);
    }

    @Path("/add_food")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(AnimalFoodUpdateDto animalFoodUpdateDto) {
        foodService.addNewFoodToAnimal(animalFoodUpdateDto.getAnimalId(), animalFoodUpdateDto.getFoodId());
    }
}
