package designPattern.decorator;

public class Lemon extends DrinkDecorator {

  public Lemon(Drink drink) {
    super(drink);
  }

  @Override
  public String print() {
    return "加柠檬";
  }

  @Override
  public double printCost() {
    return super.getDrink().printCost() + 5;
  }
}
