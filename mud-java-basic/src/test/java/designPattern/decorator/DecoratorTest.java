package designPattern.decorator;

import org.junit.jupiter.api.Test;

public class DecoratorTest {

  @Test
  public void givenOneBaseDrink_thenAddDecorator_thenPrintCost() {
    GreenTea gt = new GreenTea();
    System.out.println(gt.print());
    System.out.println(gt.printCost());
    Lemon lemon = new Lemon(gt);
    System.out.println(lemon.print());
    System.out.println(lemon.printCost());
  }
}
