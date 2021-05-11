package assignment;

import assignment.repository.AnimalRepository;
import assignment.service.AnimalService;
import assignment.ui.Ui;

public class ZooCatalog {
    public static void main(String[] args) {
        Ui ui = new Ui(new AnimalService(new AnimalRepository()));
    }
}
