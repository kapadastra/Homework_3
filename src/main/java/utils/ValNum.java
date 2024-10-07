package utils;

import java.util.Scanner;

public class ValNum {


    private Scanner scanner;

    public ValNum(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputValNum(String mes) {
        boolean isVal = false;
        int inputNum = 0;

        while (!isVal) {
            System.out.println(mes + ", значение должно быть от 1 до 100");
            if (scanner.hasNextInt()) {
                inputNum = scanner.nextInt();
                if (inputNum >= 1 && inputNum <= 100) {
                    isVal = true;
                } else {
                    System.out.println("Число вне диапазона. Попробуйте снова.");
                }
            } else {
                scanner.next();
                System.out.println("Некорректный ввод.");
            }
        }
        return inputNum;
    }


}