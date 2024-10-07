package animals.pets;

import animals.Animal;

public class Cat extends Animal {

    public Cat(String name, Integer age, Integer weight, String color, String type) {
        super(name, age, weight, color, type);
    }

    @Override
    public void say() {
        System.out.println("Мяу");
    }
}