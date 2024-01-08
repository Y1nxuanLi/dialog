//package DiaLogServlet;
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
//            sendEmail("recipient@example.com", "Time Exceeded", "The specified time has been exceeded.");
//        }
//    }
//
//    // Implement sendEmail() as shown in the previous example
//    // Implement getTimeStampFromDatabase() to retrieve the timestamp
//    // Implement getCurrentTime() to get the current time in the same format as the timestamp
//}
