package animals.pets;

import animals.Animal;

public class Dog extends Animal {

    public Dog(String name, Integer age, Integer weight, String color, String type) {
        super(name, age, weight, color, type);
    }

    @Override
    public void say() {
        System.out.println("Гав");
    }
}