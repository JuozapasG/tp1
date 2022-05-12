package lt.vu.cdi;

import lt.vu.dtos.AnimalDto;
import lt.vu.services.AnimalServiceInterface;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class AnimaServiceDecorator implements AnimalServiceInterface {

    @Inject
    @Delegate
    @Any
    AnimalServiceInterface animalServiceInterface;

    public AnimalDto getById(Long id){
        System.out.println("Selected animal by id " + id );
        return animalServiceInterface.getById(id);
    }
}
