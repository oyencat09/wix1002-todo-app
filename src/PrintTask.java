import java.util.List;

class PrintTask extends TODOApp {
    private static List<Task> listTask = TODOApp.getListTask();

    static public void printAllTask() {
        System.out.println("\nTask List. (Sorted by= " + getSortBy() + ")");
        int count = 1;
        for (Task t : listTask) {
            System.out.println(count + ". " + t.getName());
            count++;
        }
    }

    static public void printOneTask(int i) {
        setActiveTask(i);
        System.out.println("===============================================");
        System.out.println("Name: " + getActiveTask().getName());
        System.out.println("Details: " + getActiveTask().getDescription());
        System.out.println("\nCategory: " + getActiveTask().getCategory());
        System.out.println("Priority: " + TODOApp.printPriority(getActiveTask().getPriority()));
        System.out.println("Task dependency: " + TODOApp.printDependency(getActiveTask()));
        System.out.println("\nDue Date: " + getActiveTask().getDate());
        System.out.println("\nStatus: " + TODOApp.printStatus(getActiveTask().getStatus()));
        System.out.println("\nRecurring: " + TODOApp.printRecurring(getActiveTask().getRecurring()));
        System.out.println("===============================================");
    }

    static public void printCompactTaskList() {
        List<Task> listTask = TODOApp.getListTask();  // Fetch fresh every time
        System.out.println("\nTasks (Sorted by: " + getSortBy() + ")");
        for (int i = 0; i < listTask.size(); i++) {
            Task t = listTask.get(i);
            System.out.println(i + ". " + t.getName() + " | Due: " + t.getDate() + " | " + printStatus(t.getStatus()) + " | " + printPriority(t.getPriority()));
        }
    }

    static public void printRecurring() {
        System.out.println("\nRecurring Tasks (Daily)");
        List<Task> listTask = TaskRecurrence.getDaily();
        for (int i = 0; i < listTask.size(); i++) {
            Task t = listTask.get(i);
            System.out.println(i + ". " + t.getName() +" | " + printPriority(t.getPriority()));
        }
        System.out.println("\nRecurring Tasks (Weekly)");
        List<Task> listTaskWeekly = TaskRecurrence.getWeekly();
        for (int i = 0; i < listTaskWeekly.size(); i++) {
            Task t = listTaskWeekly.get(i);
            System.out.println(i + ". " + t.getName() +" | "+printPriority(t.getPriority()));
        }
        System.out.println("\nRecurring Tasks (Monthly)");
        List<Task> listTaskMonthly = TaskRecurrence.getMonthly();
        for (int i = 0; i < listTaskMonthly.size(); i++) {
            Task t = listTaskMonthly.get(i);
            System.out.println(i + ". " + t.getName() +" | "+ printPriority(t.getPriority()));
        }
    }
}
