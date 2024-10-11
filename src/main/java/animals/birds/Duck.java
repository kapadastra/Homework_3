package animals.birds;

import animals.Animal;

public class Duck extends Animal implements Flying {


    public Duck(String name, Integer age, Integer weight, String color, String type) {
        super(name, age, weight, color, type);
    }


    @Override
    public void say() {
        System.out.println("Кря");
    }

    @Override
    public void fly() {
        System.out.println("Я лечу");
    }
}
