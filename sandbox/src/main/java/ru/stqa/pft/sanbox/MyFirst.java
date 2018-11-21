package ru.stqa.pft.sanbox;

public class MyFirst {
  public static void main(String[] args) {
    hello("World");
    hello("user");
    double len = 6;
    System.out.println("Площадь квадрата со стороной " + len + "=" + area(len));

    double a = 4;
    double b = 6;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));  }

  public static void hello(String world) {
    System.out.println("Hello" + world);


  }

  public static double area(double l) {
    return l * l;
  }

  public static double area(double a, double b) {
    return a * b;
  }
}