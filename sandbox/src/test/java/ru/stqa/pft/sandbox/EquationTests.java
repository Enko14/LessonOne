package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sanbox.Equation;

public class EquationTests {

  @Test
  public void test0(){
  Equation n =new Equation(1,1,1);
    Assert.assertEquals(n.rootNumber(),0);
  }
  @Test
  public void test1(){
    Equation n =new Equation(1,2,1);
    Assert.assertEquals(n.rootNumber(),1);
  }
  @Test
  public void test2(){
    Equation n =new Equation(1,5,6);
    Assert.assertEquals(n.rootNumber(),2);
  }
  @Test
  public void testLinear(){
    Equation n =new Equation(0,1,1);
    Assert.assertEquals(n.rootNumber(),1);
  }
  @Test
  public void testConstant(){
    Equation n =new Equation(0,0,1);
    Assert.assertEquals(n.rootNumber(),0);
  }
  @Test
  public void testZero(){
    Equation n =new Equation(0,0,0);
    Assert.assertEquals(n.rootNumber(),-1);
  }
}
