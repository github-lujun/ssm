import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/***
 * https://www.w3cschool.cn/quartz_doc/
 */
public class Program {

    public static void main(String[] args) throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail myJob = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob")
                .build();
        Trigger myTrigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();
        scheduler.scheduleJob(myJob,myTrigger);

        scheduler.start();
    }
}