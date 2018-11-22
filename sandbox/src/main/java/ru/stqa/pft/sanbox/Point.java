package ru.stqa.pft.sanbox;

public class Point {
  double x;
  double y;

  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Пункт 4, Задание №2
   *
   * @param a Точка, с которой нужно вычислить расстояние
   * @return расстояние
   */
  public double distance(Point a) {
    return Math.sqrt(Math.pow((this.x - a.x), 2) + Math.pow((this.y - a.y), 2));
  }
}
