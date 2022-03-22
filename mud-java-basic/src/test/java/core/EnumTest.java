package core;

import org.junit.jupiter.api.Test;

public class EnumTest {

  enum ENUM{
    ONE,
    TWO
  }

  @Test
  public void givenEnumOneAsParameterInFunction_whenDirectChangeEnumReferenceInsideFunction_thenEnumValueShouldBeTwo(){
    ENUM valOne = ENUM.ONE;
    changeEnum(valOne);

    // will be false
    System.out.println(valOne==ENUM.TWO);
  }

  @Test
  public void givenEnumOneAsParameterInFunction_whenChangeEnumReferenceOutsideTheFunction_thenEnumValueShouldBeTwo(){
    ENUM valOne = ENUM.ONE;
    printEnum(valOne);
    valOne = ENUM.TWO;
    printEnum(valOne);
    System.out.println(valOne==ENUM.TWO);
  }

  private void changeEnum(ENUM value){
    value = ENUM.TWO;
  }

  private void printEnum(ENUM value){
    System.out.println(value);
  }

}
