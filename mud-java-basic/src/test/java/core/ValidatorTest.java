package core;

import jakarta.validation.*;
import jakarta.validation.constraints.NotEmpty;
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
}
