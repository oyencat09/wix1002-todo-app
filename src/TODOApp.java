import java.util.ArrayList;
import java.util.List;

class TODOApp {
    static private List<Task> listTask;
    static private List<Task> searchTask;
    static private Task ActiveTask;
    static private String SortBy = "None";

    // Getter for objects (listTask, SortBy)
    static public List<Task> getListTask() {
        return listTask;
    }

    static public Task getActiveTask() {
        return ActiveTask;
    }

    static public String getSortBy() {
        return SortBy;
    }

    static public Task getTaskFromUUID(String s) {
        for (Task a : listTask) {
            if (a.getUUID().equals(s)) {
                return a;
            }
        }
        return null;
    }

    static public void removeFromListTask(Task a) {
        listTask.remove(a);
    }

    static public String getUUIDFromListTask(int i) {
        return listTask.get(i).getUUID();
    }

    //Setter for objects
    static public void setSortBy(String s) {
        SortBy = s;
    }

    static public void setActiveTask(int i) {
        ActiveTask = listTask.get(i);
    }

    static public void userSetActiveTask(int i) {
        ActiveTask = listTask.get(i);
        System.out.println("Task " + ActiveTask.getName() + " is selected.");
    }

    //Wrapper for Status, Priority, Dependency and Recurring
    static public String printStatus(boolean b) {
        return (b) ? "Completed" : "In progress...";
    }

    static public String printPriority(int i) {
        String print = "";
        if (i == 3) {
            print = "High";
        }
        if (i == 2) {
            print = "Medium";
        }
        if (i == 1) {
            print = "Low";
        }
        return print;
    }

    static public String printDependency(Task a) {
        if (a.getDependency() != null) {
            return TODOApp.getTaskFromUUID(a.getDependency()).getName();
        }
        return "None";
    }

    static public String printRecurring(int i) {
        String print = "";
        if (i == 3) {
            print = "Monthly";
        }
        if (i == 2) {
            print = "Weekly";
        }
        if (i == 1) {
            print = "Daily";
        }
        return print;
    }

    static public void initializeTODO() {
        listTask = new ArrayList<>();
    }

    //Method overloading for creating normal task type
    static public void addTask(String n, String d, String due, String category, int priority) {
        listTask.add(new Task(n, d, due, category, priority));
        System.out.println(n + " successfully created!");
    }

    //Method overloading for creating recurring task type
    static public void addTask(String name, String description, String category, int priority, int recurrent) {
        Task newTask = new Task(name, description, category, priority, recurrent);
        switch (recurrent) {
            case 1:
                TaskRecurrence.addDaily(newTask);
                break;
            case 2:
                TaskRecurrence.addWeekly(newTask);
                break;
            case 3:
                TaskRecurrence.addMonthly(newTask);
                break;
        }
        System.out.println("Recurring task " + name + "successfully created!");
    }

    //Method overloading for creating task from recurring task type
    static public void addTask(String n, String d, String duedate, String category, int priority, int recurring) {
        Task newTask = new Task(recurring, n, d, duedate, category, priority);
        listTask.add(newTask);
    }

    //Reload task from savedData
    static public void loadTask(String uuid, String n, String d, String due, boolean status, String category, int priority, String dependency, int recurring) {
        listTask.add(new Task(uuid, n, d, due, status, category, priority, dependency, recurring));
        System.out.println(n + " successfully loaded!");
    }

    //Reload recurring_task from savedData
    static public void loadRecurringTask(String uuid, String n, String d, String due, int priority, int recurring) {
        switch (recurring) {
            case 1:
                TaskRecurrence.addDaily(new Task(uuid, n, d, due, priority, recurring));
                break;
            case 2:
                TaskRecurrence.addWeekly(new Task(uuid, n, d, due, priority, recurring));
                break;
            case 3:
                TaskRecurrence.addMonthly(new Task(uuid, n, d, due, priority, recurring));
                break;
        }
    }

    static public void searchTask(String s) {
        System.out.println("\nSearch results for keyword: " + s);
        for (int i = 0; i < listTask.size(); i++) {
            if (listTask.get(i).getName().toLowerCase().contains(s.toLowerCase()) || listTask.get(i).getDescription().toLowerCase().contains(s.toLowerCase())) {
                PrintTask.printOneTask(i);
            }
        }
    }

    static public void markCompletion(int i) {
        setActiveTask(i);

        if (ActiveTask.getStatus()) {
            ActiveTask.setStatus(false);
            System.out.println(ActiveTask.getName() + " marked as incomplete.");
            return;
        }

        if (TaskDependency.canComplete(ActiveTask)) {
            ActiveTask.setStatus(true);
            System.out.println(ActiveTask.getName() + " marked as complete!");
        } else {
            Task dep = TODOApp.getTaskFromUUID(ActiveTask.getDependency());
            System.out.println(ActiveTask.getName() + " cannot be completed. It depends on task \"" + dep.getName() + "\". Please complete it first.");
        }
    }
}


