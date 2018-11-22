package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;
import ru.stqa.pft.sanbox.Point;
import ru.stqa.pft.sanbox.Square;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class SquareTests {

  @Test(priority = 1)
  public void testArea() {
    Square s = new Square(5);
    assertEquals(s.area(), 25.0);
  }

  @Test(priority = 2)
  public void testDistance() {
    Point a = new Point(2, 4);
    Point b = new Point(2, 12);
    assertEquals(a.distance(b), 8.0);
  }
  @Test
  public void testDistanceIncorrect(){
    Point a = new Point(-1, -2);
    Point b = new Point(1, 2);
    assertNotEquals(a.distance(b),-4);
  }
}
