package designPattern.decorator;

public class RedTea extends Drink {

  @Override
  public String print() {
    return "红茶";
  }

  @Override
  public double printCost() {
    return 20;
  }
}
