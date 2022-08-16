import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SimpleTest {

  public static class BizWrapper{
    List<String> list = new ArrayList<>();
    public void reject(String reason){
      list.add(reason);
    }
  }

  @Test
  public void test(){
    BizWrapper bw = new BizWrapper();
    voidMethod(bw);
    System.out.println(bw.list);
    voidMethod(bw);
    System.out.println(bw.list);
  }

  @Test
  public void linkedListTest(){
    LinkedList<String> lst = new LinkedList<>();
    lst.push("123");
    lst.push(null);
    System.out.println(lst);
  }

  private void voidMethod(BizWrapper bw) {
    bw.reject("123");
  }

}
