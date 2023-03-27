import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class OptionalTest {

    @Test
    public void whenCreatesEmptyOptional_thenCorrect() {
        Optional<String> empty = Optional.empty();
        assertFalse(empty.isPresent());
    }
}
