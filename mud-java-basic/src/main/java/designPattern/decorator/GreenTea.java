package designPattern.decorator;

public class GreenTea extends Drink{

  @Override
  public String print() {
    return "绿茶";
  }

  @Override
  public double printCost() {
    return 10;
  }
}
