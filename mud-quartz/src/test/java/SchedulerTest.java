import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerTest {

    @Test
    public void schedulerTest() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.addJob(JobBuilder.newJob(SimpleJob.class).storeDurably().build(), true);

        scheduler.start();

    }

    protected static class SimpleJob implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Hello World!");
        }
    }
}
