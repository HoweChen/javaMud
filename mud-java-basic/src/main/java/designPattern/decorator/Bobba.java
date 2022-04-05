package designPattern.decorator;

public class Bobba extends DrinkDecorator {

  public Bobba(Drink drink) {
    super(drink);
  }

  @Override
  public String print() {
    return "加珍珠";
  }

  @Override
  public double printCost() {
    return super.getDrink().printCost() + 3;
  }
}
