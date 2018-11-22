package ru.stqa.pft.sanbox;

public class MyFirst {
  public static void main(String[] args) {
    // First lesson
    hello("World");
    hello("user");
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + "=" + s.area());
    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());


    // Homework #1
    Point a = new Point(1, 4);
    Point b = new Point(5, 7);
    // Вычисление через функцию
    System.out.println("Расстояние между точками = " + distance(a, b));
    // Вычисление через метод
    System.out.println("Расстояние между точкой А и точкой Б , через метод = " + a.distance(b));

  }

  public static void hello(String world) {
    System.out.println("Hello" + world);
  }

  /**
   * Пункт 3, задание №2
   *
   * @param p1 Первая точка
   * @param p2 Вторая точка
   * @return Расстоянияе между точками
   */
  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
  }


}