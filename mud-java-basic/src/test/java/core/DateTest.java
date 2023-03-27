package java.core;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

public class DateTest {

    @Test
    public void givenTwoDates_whenGetDuration_thenShouldPrintCorrectDuration() {
        Date date1 = new Date(2019, 1, 1);
        Date date2 = new Date(2019, 1, 2);
        long l = Duration.between(date1.toInstant(), date2.toInstant()).abs().toMinutes();
        System.out.println(l);
    }
}
