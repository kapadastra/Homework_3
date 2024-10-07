import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import animals.Animal;
import data.Command;

import data.AnimalData;

import factory.AnimalFactory;
import tables.AnimalTable;
import utils.ValNum;



public class Main {


       public static void main(String[] args) throws IOException, SQLException {

           AnimalTable animalTable = new AnimalTable();
           List<String> columnsAnimalTable = new ArrayList<>();
           columnsAnimalTable.add("id INT AUTO_INCREMENT PRIMARY KEY");
           columnsAnimalTable.add("color VARCHAR(20)");
           columnsAnimalTable.add("name VARCHAR(20)");
           columnsAnimalTable.add("weight INT");
           columnsAnimalTable.add("type VARCHAR(20)");
           columnsAnimalTable.add("age INT");

           animalTable.create(columnsAnimalTable);

            List<String> animals = new ArrayList<>();

            Scanner scanner = new Scanner(System.in);

            ValNum valNum = new ValNum(scanner);

            while (true) {
                String inputCom = "";
                System.out.println("Введите команду из списка ADD,LIST,EDIT,FILTER,EXIT: ");
                while (inputCom.length() == 0) {
                    inputCom = scanner.nextLine().toUpperCase().trim();
                }

                boolean isComValid = false;
                for (Command command : Command.values()) {
                    if (command.name().equals(inputCom)) {
                        isComValid = true;
                        break;
                    }
                }

                if (!isComValid) {
                    System.out.println("Недопустимая команда.\n");
                    continue;
                }

                Command menu = Command.valueOf(inputCom);

                switch (menu) {
                    case ADD: {
                        while (true) {
                            System.out.println("Введите тип животного: ");

                            String animalType = scanner.nextLine().toUpperCase().trim();

                            boolean isAnimalValid = false;
                            for (AnimalData animalData : AnimalData.values()) {
                                if (animalData.name().equals(animalType)) {
                                    isAnimalValid = true;
                                    break;
                                }
                            }

                            if (!isAnimalValid) {
                                System.out.println("Недопустимый тип животного.\n");
                                continue;
                            }


                            System.out.println("Введите окрас животного: ");
                            String animalColor = scanner.nextLine();

                            System.out.println("Введите имя животного: ");
                            String animalName = scanner.nextLine();

                            int animalAge = valNum.inputValNum("Введите возраст животного");

                            int animalWeight = valNum.inputValNum("Введите вес животного");

                            try {
                                Animal newAnimal = AnimalFactory.createAnimal(animalType, animalName, animalAge, animalWeight, animalColor);
                                animals.add(String.valueOf(newAnimal));
                                animalTable.write(newAnimal);
                                newAnimal.say();

                                String as = newAnimal.getType();
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }

                            break;
                        }
                    }

                    case LIST: {
                        List<Animal> animals1 = animalTable.read();
                        for (Animal animal : animals1) {
                            System.out.println(animal);
                        }
                        break;

                    }


                    case EDIT: {
                        System.out.println("Введите ID животного, которое вы хотите редактировать:");
                        int animalId = valNum.inputValNum("ID животного");

                        Animal animalToUpdate = animalTable.getAnimalById(animalId);
                        if (animalToUpdate == null) {
                            System.out.println("Животное с таким ID не найдено.");
                            break;
                        }

                        System.out.println("Введите новый окрас (оставьте пустым для пропуска): ");
                        String newColor = scanner.nextLine();
                        if (!newColor.isEmpty()) {
                            animalToUpdate.setColor(newColor);
                        }

                        System.out.println("Введите новое имя (оставьте пустым для пропуска): ");
                        String newName = scanner.nextLine();
                        if (!newName.isEmpty()) {
                            animalToUpdate.setName(newName);
                        }

                        int newAge = valNum.inputValNum("Введите новый возраст (или 0 для пропуска)");
                        if (newAge > 0) {
                            animalToUpdate.setAge(newAge);
                        }

                        int newWeight = valNum.inputValNum("Введите новый вес (или 0 для пропуска)");
                        if (newWeight > 0) {
                            animalToUpdate.setWeight(newWeight);
                        }

                        animalTable.updateAnimal(animalToUpdate);
                        System.out.println("Животное обновлено.");
                        break;
                    }

                    case FILTER: {
                        System.out.println("Введите тип животного для фильтрации (CAT, DOG, DUCK): ");
                        String filterType = scanner.nextLine().toUpperCase().trim();

                        List<Animal> filteredAnimals = animalTable.getAnimalsByType(filterType);
                        if (filteredAnimals.isEmpty()) {
                            System.out.println("Животные данного типа не найдены.");
                        } else {
                            for (Animal animal : filteredAnimals) {
                                System.out.println(animal);
                            }
                        }
                        break;
                    }


                    case EXIT: {
                        scanner.close();
                        System.exit(0);
                    }


                }


            }

            }

}



