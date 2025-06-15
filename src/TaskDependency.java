import java.util.List;

//Constraint: Only one dependency for each task
class TaskDependency {
    private static List<Task> listTask = TODOApp.getListTask();

    static public boolean checkDependency(Task a) {
        boolean canComplete = true;
        Task loopTask = a;

        while (loopTask.getDependency() != null) {
            if (loopTask.getDependency().equals(TODOApp.getActiveTask().getUUID())) {
                canComplete = false;
                System.out.println("Cannot add dependency! " + loopTask.getName() + " is dependent on " + TODOApp.getActiveTask().getName()
                        + ". \n It will cause dependency loop in task completion.");
                break;
            } else {
                loopTask = TODOApp.getTaskFromUUID(loopTask.getDependency());
            }
        }
        return canComplete;
    }

    static public void addDependency(Task a) {
        if (checkDependency(a)) {
            TODOApp.getActiveTask().setDependency(a.getUUID());
            System.out.println(TODOApp.getActiveTask().getName() + " successfully updated to depend on " + a.getName());
        }
    }

    static public void removeDependency() {
        if (TODOApp.getActiveTask().getDependency() == null) {
            System.out.println(TODOApp.getActiveTask().getName() + " does not depend on any other task.");
            return;
        }
        String oldDependency = TODOApp.getActiveTask().getDependency();
        TODOApp.getActiveTask().setDependencyNull();
        System.out.println(TODOApp.getActiveTask().getName() + " dependency successfully removed. (" + TODOApp.getTaskFromUUID(oldDependency).getName() + ")");
    }
}
