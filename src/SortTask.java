import java.util.List;

class SortTask extends TODOApp {
    // Task Sorting
    private static List<Task> listTask = getListTask();

    static public void sortTaskAscendingDue() {
        Task temp;

        for (int i = 0; i < listTask.size(); i++) {
            for (int j = 0; j < listTask.size() - 1; j++) {
                if (j == 0) {
                    temp = listTask.get(j);
                }
                if ((listTask.get(j).getDate()).compareTo((listTask.get(j + 1).getDate())) > 0) {
                    temp = listTask.get(j);
                    listTask.set(j, listTask.get(j + 1));
                    listTask.set(j + 1, temp);
                }
            }
        }
        TODOApp.setSortBy("Due Date(Ascending)");
        System.out.println("\nList has been sorted by Due Date (Ascending)");
    }

    static public void sortTaskDescendingDue() {
        Task temp;

        for (int i = 0; i < listTask.size(); i++) {
            for (int j = 0; j < listTask.size() - 1; j++) {
                if (j == 0) {
                    temp = listTask.get(j);
                }
                if ((listTask.get(j).getDate()).compareTo((listTask.get(j + 1).getDate())) < 0) {
                    temp = listTask.get(j);
                    listTask.set(j, listTask.get(j + 1));
                    listTask.set(j + 1, temp);
                }
            }
        }
        TODOApp.setSortBy("Due Date(Descending)");
        System.out.println("\nList has been sorted by Due Date (Descending)");
    }

    static public void sortTaskAscendingPriority() {
        Task temp;
        for (int i = 0; i < listTask.size(); i++) {
            for (int j = 0; j < listTask.size() - 1; j++) {
                Task leftSide = listTask.get(j);
                Task rightSide = listTask.get(j + 1);

                if (j == 0) {
                    temp = listTask.get(j);
                }
                if (leftSide.getPriority() > rightSide.getPriority()) {
                    temp = leftSide;
                    listTask.set(j, rightSide);
                    listTask.set(j + 1, temp);

                }
            }
        }
        TODOApp.setSortBy("Priority (Ascending)");
        System.out.println("\nList has been sorted by Priority (Ascending)");
    }

    static public void sortTaskDescendingPriority() {
        Task temp;
        for (int i = 0; i < listTask.size(); i++) {
            for (int j = 0; j < listTask.size() - 1; j++) {
                Task leftSide = listTask.get(j);
                Task rightSide = listTask.get(j + 1);

                if (j == 0) {
                    temp = listTask.get(j);
                }
                if (leftSide.getPriority() < rightSide.getPriority()) {
                    temp = leftSide;
                    listTask.set(j, rightSide);
                    listTask.set(j + 1, temp);

                }
            }
        }
        TODOApp.setSortBy("Priority (Descending)");
        System.out.println("\nList has been sorted by Priority (Descending)");
    }
}
