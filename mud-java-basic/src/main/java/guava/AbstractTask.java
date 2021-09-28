package guava;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

public abstract class AbstractTask implements Runnable {
  protected final Log log = LogFactory.get(this.getClass());
}
