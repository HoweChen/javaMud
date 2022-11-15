package spi;

public class BaiduServiceProvider implements ServiceProvider{

  @Override
  public void init() {
    System.out.println("Baidu init");
  }

  @Override
  public void reload() {
    System.out.println("Baidu reload");

  }
}
