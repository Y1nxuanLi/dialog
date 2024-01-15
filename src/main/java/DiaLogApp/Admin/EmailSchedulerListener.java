// The email notification is not complete

//package DiaLogApp.GeneralMethod;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@WebListener
//public class EmailSchedulerListener implements ServletContextListener {
//
//    private ScheduledExecutorService scheduler;
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(this::checkAndSendEmails, 0, 1, TimeUnit.MINUTES);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        scheduler.shutdownNow();
//    }
//
//    private void checkAndSendEmails() {
//        // Check database for any timestamps that have been exceeded
//        String timeStamp = getTimeStampFromDatabase(); // Assume this method exists
//        String currentTime = getCurrentTime(); // Implement this method to get current time
//
//        if (currentTime.compareTo(timeStamp) > 0) {
//            sendEmail("notification@dialog.com", "Time Exceeded", "The task due has been reached.");
//        }
//    }
//
//}
