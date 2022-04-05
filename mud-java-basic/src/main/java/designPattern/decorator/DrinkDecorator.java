package designPattern.decorator;

/**
 * @author yuhaochen
 */
public abstract class DrinkDecorator extends Drink {
  private Drink drink;

  public DrinkDecorator(Drink drink) {
    this.drink = drink;
  }

  @Override
  public abstract String print();

  @Override
  public abstract double printCost();

  public Drink getDrink() {
    return drink;
  }
}
