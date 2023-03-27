package core;

import jakarta.validation.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ValidatorTest {
    private static final Validator validator;

    static {
        Configuration<?> config = Validation.byProvider(HibernateValidator.class).configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }

    private final static class TestClass {
        @NotEmpty
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ParentClass<T extends ValidatorTest.InnerClass> {
        @NotEmpty
        private String name;

        @NotNull
        private T innerClass;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public T getInnerClass() {
            return innerClass;
        }

        public void setInnerClass(T innerClass) {
            this.innerClass = innerClass;
        }
    }

    public static class ChildClass extends ParentClass<InnerClass> {
        @NotEmpty
        private String age;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    public static class InnerClass {
        @NotEmpty
        private String innerName;

        public String getName() {
            return innerName;
        }

        public void setName(String name) {
            this.innerName = name;
        }

    }

    @Test
    public void givenHibernateValidator_whenValidateField_thenShouldGetCorrectFieldName() {
        // given
        TestClass tc = new TestClass();
        // when
        Set<ConstraintViolation<TestClass>> validate = validator.validate(tc);
        // then
        if (!validate.isEmpty()) {
            ConstraintViolation<TestClass> violation = validate.iterator().next();
            System.out.println(violation.getPropertyPath());
        }
    }

    @Test
    public void givenChildClassWithInheritance_whenValidateField_thenShouldNotDetectInnerAnnotation() {
        // given
        ChildClass cc = new ChildClass();
        cc.setAge("1");
        cc.setName("name");
        cc.setInnerClass(new InnerClass());

        // when
        Set<ConstraintViolation<ParentClass>> validate = validator.validate(cc);
        // then validate should be empty
        if (!validate.isEmpty()) {
            ConstraintViolation<ParentClass> violation = validate.iterator().next();
            System.out.println(violation.getPropertyPath());
        }
    }

    @Test
    public void givenChildClassWithInheritance_whenValidateField_thenShouldGetCorrectFieldName() {
        // given
        ChildClass cc = new ChildClass();
        cc.setAge("1");
        cc.setName("name");
        cc.setInnerClass(new InnerClass());

        // when
        Set<ConstraintViolation<Object>> validate = validator.validate(cc);
        validate = validator.validate(cc.getInnerClass());
        // then validate should be empty
        if (!validate.isEmpty()) {
            ConstraintViolation<Object> violation = validate.iterator().next();
            System.out.println(violation.getPropertyPath());
        }
    }
}
