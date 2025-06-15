import java.util.List;

class TaskManager extends TODOApp {
    private static List<Task> listTask = getListTask();

    static public void editName(String s) {
        String oldName = getActiveTask().getName();
        getActiveTask().setName(s);
        System.out.println(oldName + " has been changed to " + getActiveTask().getName());
    }

    static public void editDetail(String s) {
        String oldName = getActiveTask().getName();
        getActiveTask().setDescription(s);
        System.out.println(oldName + " description has been updated!\n" + getActiveTask().getDescription());
    }

    static public void editDueDate(String s) {
        String oldName = getActiveTask().getName();
        getActiveTask().setDate(s);
        System.out.println(oldName + " due date has been updated to: " + getActiveTask().getDate());
    }

    static public void editPriority(int s) {
        String oldName = getActiveTask().getName();
        getActiveTask().setPriority(s);
        System.out.println(oldName + " priority has been updated to: " + TODOApp.printPriority(getActiveTask().getPriority()));
    }

    static public void editCategory(String s) {
        String oldName = getActiveTask().getName();
        getActiveTask().setCategory(s);
        System.out.println(oldName + " category has been updated to: " + getActiveTask().getCategory());
    }

    static public void deleteTask() {
        String oldName = getActiveTask().getName();
        listTask.remove(getActiveTask());
        System.out.println(oldName + " has been deleted.");
    }
}
