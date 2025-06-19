import java.util.List;

//Constraint: Only one dependency for each task
class TaskDependency {
    private static List<Task> listTask = TODOApp.getListTask();


    static public boolean checkDependency(Task a) {
        boolean canComplete = true;
        Task activeTask = TODOApp.getActiveTask();
        Task toAddTask = a;

        while(toAddTask != null){
            if (toAddTask.getUUID().equals(activeTask.getUUID())) {
                canComplete = false;
                System.out.println("Cannot add dependency! " + a.getName() + " is already dependent on " + activeTask.getName()
                        + ".\nIt will cause dependency loop in task completion.");
                break;
            }

            toAddTask = TODOApp.getTaskFromUUID(toAddTask.getDependency());
        }
        return canComplete;
    }

    static public boolean canComplete(Task a){
        boolean dependencyCompletion = true;
        if (a.getDependency() != null){
            if (!TODOApp.getTaskFromUUID(a.getDependency()).getStatus()){
                dependencyCompletion = false;
            }
        }
        return dependencyCompletion;
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
