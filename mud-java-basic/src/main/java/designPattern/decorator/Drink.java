package designPattern.decorator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuhaochen
 */
public abstract class Drink {

  List<DrinkDecorator> decoratorList = new ArrayList<>();

  public abstract String print();

  public abstract double printCost();

  public List<DrinkDecorator> getDecoratorList() {
    return decoratorList;
  }
}
