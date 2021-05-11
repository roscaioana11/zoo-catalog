package assignment.entity;

import java.util.Objects;

public class Animal {
    private Long id;
    private String animal;
    private String name;

    public Animal(Long id, String animal, String name) {
        this.id = id;
        this.animal = animal;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal1 = (Animal) o;
        return Objects.equals(id, animal1.id) && Objects.equals(animal, animal1.animal) && Objects.equals(name, animal1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animal, name);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", animal='" + animal + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
