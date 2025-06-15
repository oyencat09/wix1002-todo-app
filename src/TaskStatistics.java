import java.util.List;

class TaskStatistics extends TODOApp {

    private static List<Task> listTask = getListTask();

    public static void printStatistics() {
        int total = listTask.size();
        int completed = 0;
        for (Task t : listTask) {
            if (t.getStatus()) completed++;
        }
        int pending = total - completed;
        double rate = (total > 0) ? (completed * 100.0 / total) : 0;

        System.out.println("\n==================== DASHBOARD ====================");
        System.out.println(" Total Tasks        : " + total);
        System.out.println(" Completed Tasks    : " + completed);
        System.out.println(" Pending Tasks      : " + pending);
        System.out.printf(" Completion Rate    : %.2f%%\n", rate);
        System.out.println("===================================================");
    }
}
