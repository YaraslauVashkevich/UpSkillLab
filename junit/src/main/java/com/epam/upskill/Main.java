package com.epam.upskill;

//The square equation is an equation of the form ax2 + bx + c = 0,
// where the coefficients a, b, and c are arbitrary numbers, and a ≠ 0.

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double a = enterFromConsole("Input coefficient a");
        double b = enterFromConsole("Input coefficient b");
        double c = enterFromConsole("Input coefficient c");
        System.out.println(equation(a, b, c));
    }

    public static double enterFromConsole(String message) {
        Scanner sc = new Scanner(System.in);
        double value;
        System.out.println(message);
        while (!sc.hasNextDouble()) {
            sc.nextLine();
            System.out.println(message);
        }
        value = sc.nextDouble();
        return value;
    }

    public static ArrayList<Double> equation(double a, double b, double c) {
        ArrayList<Double> array = new ArrayList();
        if (a != 0 && b != 0 && c != 0) {
            double discriminant = b * b - (4 * a * c);
            if (discriminant < 0) {
                System.out.println("No roots");
            } else if (discriminant == 0) {
                double x = (-b + Math.sqrt(discriminant)) / (2 * a);
                array.add(x);
            } else if (discriminant > 0) {
                double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                array.add(x1);
                array.add(x2);
            }
        } else if (a != 0 && b == 0 && c != 0 && -c / a >= 0) {
                double x1 = Math.sqrt(-c / a);
                double x2 = -Math.sqrt(-c / a);
                array.add(x1);
                array.add(x2);
        } else if (a != 0 && b == 0 && c != 0 && -c / a < 0) {
                System.out.println("No roots");
        } else if (a != 0 && b != 0 && c == 0) {
                double x1 = 0;
                double x2 = -b / a;
                array.add(x1);
                array.add(x2);
        } else if (a != 0 && b == 0 && c == 0) {
                double x = 0;
                array.add(x);
        } else {
                System.out.println("Error! Coefficient a = 0.");
        }
        return array;
    }
}
