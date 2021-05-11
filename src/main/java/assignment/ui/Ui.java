package assignment.ui;

import assignment.service.AnimalService;

import java.util.Scanner;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class Ui {
    private AnimalService service;
    Scanner scan;

    public Ui(AnimalService service) {
        this.service = service;
        scan = new Scanner(System.in);
        mainMenu();
    }

    private void mainMenu(){
        System.out.println("1. Add a new animal");
        System.out.println("2. Add the name to a specific animal");
        System.out.println("3. Read the name of an animal");
        System.out.println("4. Update the name of a specific animal");
        System.out.println("5. Delete entry of the animal");
        System.out.println("0. Exit");

        int pick = scan.nextInt();

        if(pick == 1){
            addNewAnimal();
        }else if(pick == 2){
            addNameToAnimal();
        }else if(pick == 3){
            readAnimalName();
        }else if(pick == 4){
            updateAnimalName();
        }else if(pick == 5){
            deleteAnimal();
        }else if(pick == 0){
            return;
        }else {
            System.out.println("Invalid option. Please pick again");
            mainMenu();
        }
    }

    private void addNewAnimal(){
        System.out.print("Please enter Animal type: ");
        scan.nextLine();
        String animal = scan.nextLine();

        service.addAnimal(animal);
        mainMenu();
    }

    private void addNameToAnimal(){
        printAnimalList();
        if(service.countAnimals(false) == 0){
            System.out.println("The list does not contain Animals without name");
            mainMenu();
            return;
        }
        System.out.print("Please select an Animal by ID that doesn't have a name: ");
        Long pick = scan.nextLong();
        System.out.println();

        if(!service.checkAnimalIndex(pick, false)){
            System.out.println("Invalid choice.");
            addNameToAnimal();
            return;
        }

        System.out.print("Please enter a Name to your selected Animal: ");
        scan.nextLine();

        String name = scan.nextLine();
        service.editName(pick, name);

        mainMenu();
    }

    private void readAnimalName(){
        printAnimalList();
        if(service.countAnimals(true) == 0){
            System.out.println("The list does not contain named Animals");
            mainMenu();
            return;
        }
        System.out.print("Pick the Animal you wish to have the Name displayed: ");
        Long pick = scan.nextLong();
        System.out.println();

        if(!service.checkAnimalIndex(pick, true)){
            System.out.println("Invalid choice.");
            addNameToAnimal();
            return;
        }

        System.out.println("The selected Animal's Name is: " + service.getName(pick));
        mainMenu();
    }

    private void updateAnimalName(){
        printAnimalList();
        if(service.countAnimals(true) == 0){
            System.out.println("The list does not contain named Animals");
            mainMenu();
            return;
        }
        System.out.print("Select by ID the Animal you want to change its Name: ");
        Long pick = scan.nextLong();
        System.out.println();

        if(!service.checkAnimalIndex(pick, true)){
            System.out.println("Invalid choice.");
            addNameToAnimal();
            return;
        }

        System.out.print("Please enter a Name to your selected Animal: ");
        scan.nextLine();

        String name = scan.nextLine();
        service.editName(pick, name);

        mainMenu();
    }

    private void deleteAnimal(){
        printAnimalList();
        if(service.countAnimals(true) == 0 && service.countAnimals(false) == 0){
            System.out.println("The list does not contain Animals");
            mainMenu();
            return;
        }
        System.out.print("Select by ID the Animal you want to Delete: ");
        Long pick = scan.nextLong();
        System.out.println();

        service.deleteAnimal(pick);
        System.out.println("Successfully deleted!");
        mainMenu();
    }

    private void printAnimalList(){
        System.out.println(service.getAllAnimals().stream()
                            .map(animal -> animal.getId() + ": " + animal.getAnimal() + ", " +
                                    (animal.getName() == null ? "N/A" : animal.getName()))
                            .collect(joining(lineSeparator())));
    }
}
