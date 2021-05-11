package assignment.repository;

import assignment.entity.Animal;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.function.Predicate;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class AnimalRepository implements Repository<Animal> {
    private List<Animal> animalList;

    public AnimalRepository() {
        animalList = new ArrayList<>();
        readCsv();
    }

    private void readCsv(){
        try{
            File animalCsv = new File("src/main/resources/zoo-catalog.csv");
            if(animalCsv.exists()){
                Scanner scan = new Scanner(animalCsv);
                long counter = 1;
                while (scan.hasNextLine()){
                    String[] row = scan.nextLine().split(",");
                    animalList.add(new Animal(counter, row[0], row[1].equals("null") ? null : row[1]));
                    counter++;
                }
            }else {
                animalCsv.createNewFile();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void saveToCsvFile(){
        File animalCsv = new File("src/main/resources/zoo-catalog.csv");
        String fileContents = animalList.stream()
                .map(animal -> animal.getAnimal() + "," + animal.getName())
                .collect(joining(lineSeparator()));
        try {
            FileOutputStream writer = new FileOutputStream(animalCsv, false);
            writer.write(fileContents.getBytes());
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Long getMaxIndex(){
        return animalList.stream()
                .mapToLong(animal -> animal.getId())
                .max()
                .orElse(0);
    }

    public void addName(Animal newElement) {
        Optional<Animal> foundAnimalName = animalList.stream()
                .filter(existingAnimalName -> existingAnimalName.getName().equals(newElement.getName()))
                .findFirst();
        if(!foundAnimalName.isPresent()){
            animalList.add(newElement);
            saveToCsvFile();
        }
    }

    public Animal readName(Predicate<Animal> condition) {
        Optional<Animal> foundAnimalName = animalList.stream()
                .filter(condition)
                .findFirst();
        return foundAnimalName.orElseThrow(() -> new RuntimeException("Animal Name not found"));
    }

    public void updateName(Long id, String newName) {
        Animal existingAnimal= animalList.stream()
                .filter(animal -> animal.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Animal with ID not found"));
        existingAnimal.setName(newName);
        saveToCsvFile();
    }


    @Override
    public List<Animal> getAll() {
        return new ArrayList<>(animalList);
    }

    @Override
    public Animal get(Long id) {
        return animalList.stream()
                .filter(animal -> animal.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Animal with ID not found"));
    }

    @Override
    public void add(Animal newElement) {
//        Optional<Animal> foundAnimal = animalList.stream()
//                .filter(existingAnimal -> existingAnimal.getAnimal().equals(newElement.getAnimal()))
//                .findFirst();
//        if(!foundAnimal.isPresent()){
            animalList.add(newElement);
            saveToCsvFile();
//        }
    }

    @Override
    public void delete(Long id) {
        Animal existingAnimal= animalList.stream()
                .filter(animal -> animal.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Animal with id not found"));
        animalList.remove(existingAnimal);
        saveToCsvFile();
    }
}
