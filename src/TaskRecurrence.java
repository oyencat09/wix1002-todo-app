import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Daily will run daily, Weekly and Monthly will run to create smaller schedule
// that run based on each task due date
class TaskRecurrence {
    private static List<Task> daily = new ArrayList<>();
    private static List<Task> weekly = new ArrayList<>();
    private static List<Task> monthly = new ArrayList<>();

    public static List<Task> getDaily() {
        return daily;
    }
    public static List<Task> getWeekly() {
        return weekly;
    }
    public static List<Task> getMonthly() {
        return monthly;
    }

    private static Runnable runDaily = new Runnable() {
        @Override
        public void run() {
            for (Task a : daily) {
                LocalDate dueDate = LocalDate.now().plusDays(1);
                TODOApp.addTask(a.getName(), a.getDescription(), DateFormat.convertToString(dueDate), a.getCategory(), a.getPriority(), 1);
            }
        }
    };

    private static Runnable runWeekly = new Runnable() {
        @Override
        public void run() {
            for (Task a : weekly) {
                LocalDate dueDate = LocalDate.now().plusDays(7);
                TODOApp.addTask(a.getName(), a.getDescription(), DateFormat.convertToString(dueDate), a.getCategory(), a.getPriority(), 2);
            }
        }
    };

    private static Runnable runMonthly = new Runnable() {
        @Override
        public void run() {
            for (Task a : monthly) {
                LocalDate dueDate = LocalDate.now().plusMonths(1);
                TODOApp.addTask(a.getName(), a.getDescription(), DateFormat.convertToString(dueDate), a.getCategory(), a.getPriority(), 3);
            }
        }
    };

    public static void addDaily(Task a) {
        daily.add(a);
    }

    public static void addWeekly(Task a) {
        weekly.add(a);
    }

    public static void addMonthly(Task a) {
        monthly.add(a);
    }

    public static void removeDaily(Task a) {
        daily.remove(a);
    }

    public static void removeWeekly(Task a) {
        weekly.remove(a);
    }

    public static void removeMonthly(Task a) {
        monthly.remove(a);
    }

    public static boolean checkExistingRun() {
        boolean alreadyRun = false;
        return alreadyRun;
    }

    public static void initializeTaskRecurrence() {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(runDaily, 0, 1, TimeUnit.DAYS);
            scheduler.scheduleAtFixedRate(runWeekly, 0, 7, TimeUnit.DAYS);
            scheduler.scheduleAtFixedRate(runMonthly, 0, 30, TimeUnit.DAYS);
    }
}
