package spi;

public class GoogleServiceProvider implements ServiceProvider{

  @Override
  public void init() {
    System.out.println("Google init");
  }

  @Override
  public void reload() {
    System.out.println("Google reload");

  }
}
