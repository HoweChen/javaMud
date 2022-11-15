package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiService {

  ServiceLoader<ServiceProvider> loader = ServiceLoader.load(ServiceProvider.class);


  public void load(boolean refresh){
    if (refresh){
      loader.reload();
      for (ServiceProvider spi : loader) {
        spi.reload();
      }
    }else {
      for (ServiceProvider spi : loader) {
        spi.init();
      }
    }
  }

  public static void main(String[] args){
    //
    SpiService service = new SpiService();
    service.load(false);
    service.load(true);
  }

}
