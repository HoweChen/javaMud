import org.junit.jupiter.api.Test;

public class ClassTest {

    @Test
    public void classTest() {
        ChildClass cc = new ChildClass();
        cc.method();
    }

    public static class ParentClass {
        public void method() {
            System.out.println("parent");
        }
    }

    public static class ChildClass extends ParentClass {
        @Override
        public void method() {
            super.method();
        }
    }
}
