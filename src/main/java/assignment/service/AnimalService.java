package assignment.service;

import assignment.entity.Animal;
import assignment.repository.AnimalRepository;

import java.util.List;

public class AnimalService {
    private AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public void addAnimal(String newAnimal){
        Animal newAnimalObject = new Animal(repository.getMaxIndex() + 1, newAnimal, null);
        repository.add(newAnimalObject);
    }

    public List<Animal> getAllAnimals(){
        return repository.getAll();
    }

    public boolean checkAnimalIndex(Long id, boolean named){
        return repository.getAll().stream()
                .filter(animal -> animal.getId() == id)
                .anyMatch(animal -> (animal.getName() != null) == named);
    }

    public long countAnimals(boolean named){
        return repository.getAll().stream()
                .filter(animal -> (animal.getName() != null) == named)
                .count();
    }

    public void editName(Long id, String name){
        repository.updateName(id, name);
    }

    public String getName(Long id){
        return repository.get(id).getName();
    }

    public void deleteAnimal(Long id){
        repository.delete(id);
    }
}
