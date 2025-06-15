import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        System.out.println("Welcome to TODO list app!\n");
        Scanner scanner = new Scanner(System.in);
        TODOApp.initializeTODO();
        DataBase.loadData();
        DataBase.loadRecurringTask();

        boolean running = true;
        while (running) {
            System.out.println("\n========= TODO Main Menu =========");
            System.out.println("1. Create New Task");
            System.out.println("2. Show All Tasks");
            System.out.println("3. Mark Task as Complete/Incomplete");
            System.out.println("4. Edit Task");
            System.out.println("5. Edit Recurring Task");
            System.out.println("6. Print Statistics");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("1. Create New Task");
                    System.out.println("2. Create New Recurring Task");

                    int choiceTask = Integer.parseInt(scanner.nextLine());

                    boolean exitAddTask = false;
                    while (!exitAddTask){
                    switch (choiceTask) {
                        case 1:
                            System.out.print("Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Description: ");
                            String desc = scanner.nextLine();
                            System.out.print("Due Date (dd-MM-yyyy): ");
                            String date = scanner.nextLine();
                            System.out.print("Category: ");
                            String cat = scanner.nextLine();
                            System.out.print("Priority (1=Low, 2=Med, 3=High): ");
                            int prio = Integer.parseInt(scanner.nextLine());
                            TODOApp.addTask(name, desc, date, cat, prio);
                            exitAddTask = true;
                            break;
                        case 2:
                            System.out.print("Name: ");
                            String name2 = scanner.nextLine();
                            System.out.print("Description: ");
                            String desc2 = scanner.nextLine();
                            System.out.print("Category: ");
                            String cat2 = scanner.nextLine();
                            System.out.print("Priority (1=Low, 2=Med, 3=High): ");
                            int prio2 = Integer.parseInt(scanner.nextLine());
                            System.out.print("Recurrency (1=Daily, 2=Weekly, 3=Monthly)");
                            int rec = Integer.parseInt(scanner.nextLine());
                            TODOApp.addTask(name2, desc2, cat2, prio2, rec);
                            exitAddTask = true;
                            break;
                        case 0:
                            exitAddTask = true;
                            break;
                        default: System.out.println("Invalid input. Please try again.");
                    }
                    }

                case 2:
                    PrintTask.printCompactTaskList();
                    break;

                case 3:
                    PrintTask.printCompactTaskList();
                    System.out.print("Set task completion: ");
                    int markIndex = Integer.parseInt(scanner.nextLine());
                    TODOApp.markCompletion(markIndex);
                    break;

                case 4:
                    System.out.println("\n======== Edit Task  ========");
                    PrintTask.printCompactTaskList();
                    System.out.print("Select task index to edit: ");
                    int editIndex = Integer.parseInt(scanner.nextLine());
                    TODOApp.setActiveTask(editIndex);

                    boolean editing = true;
                    while (editing) {
                        System.out.println("\n===== Edit Task: " + TODOApp.getActiveTask().getName() + " =====");
                        System.out.println("1. Edit Name");
                        System.out.println("2. Edit Description");
                        System.out.println("3. Edit Due Date");
                        System.out.println("4. Edit Category");
                        System.out.println("5. Edit Priority");
                        System.out.println("6. Edit Dependency");
                        System.out.println("7. Delete Task");
                        System.out.println("0. Go Back");
                        System.out.print("Your choice: ");
                        int editChoice = Integer.parseInt(scanner.nextLine());

                        switch (editChoice) {
                            case 1:
                                System.out.print("New Name: ");
                                String newName = scanner.nextLine();
                                TaskManager.editName(newName);
                                break;
                            case 2:
                                System.out.print("New Description: ");
                                String newDesc = scanner.nextLine();
                                TaskManager.editDetail(newDesc);
                                break;
                            case 3:
                                System.out.print("New Due Date (dd-MM-yyyy): ");
                                String newDate = scanner.nextLine();
                                TaskManager.editDueDate(newDate);
                                break;
                            case 4:
                                System.out.print("New Category: ");
                                String newCat = scanner.nextLine();
                                TaskManager.editCategory(newCat);
                                break;
                            case 5:
                                System.out.print("New Priority (1=Low, 2=Med, 3=High): ");
                                int newPrio = Integer.parseInt(scanner.nextLine());
                                TaskManager.editPriority(newPrio);
                                break;
                            case 6:
                                boolean exit = false;
                                while(!exit){
                                    PrintTask.printCompactTaskList();
                                    System.out.println("\n===== Edit Task: " + TODOApp.getActiveTask().getName() + " =====");
                                    System.out.println("\nSelect dependency options:");
                                    System.out.println("1. Add dependency");
                                    if (TODOApp.getActiveTask().getDependency() != null){
                                        System.out.println("2. Remove dependency (Depend on: "+TODOApp.printDependency(TODOApp.getActiveTask())+")");
                                    }
                                    System.out.println("0. Back");

                                    int editDependency = Integer.parseInt(scanner.nextLine());
                                    switch (editDependency){
                                        case 1:
                                            System.out.println("Enter the task for "+TODOApp.getActiveTask().getName()+" to depend on: ");
                                            TaskDependency.addDependency(TODOApp.getListTask().get(Integer.parseInt(scanner.nextLine())));
                                            break;
                                        case 2:
                                            TaskDependency.removeDependency();
                                            break;
                                        case 0: exit = true;
                                        default: System.out.println("Invalid option. Please try again.");
                                    }
                                }
                                break;
                            case 7:
                                TaskManager.deleteTask();
                                editing = false;
                                break;
                            case 0:
                                editing = false;
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    }
                    break;
                case 5:
                    PrintTask.printRecurring();
                    break;
                case 6:
                    TaskStatistics.printStatistics();
                    break;

                case 0:
                    DataBase.saveData();
                    DataBase.saveRecurringTask();
                    running = false;
                    System.out.println("Exiting... Bye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

    }
}

/*
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TODOApp.initializeTODO();
        TODOApp.addTask("Task1","JustDoIt","28-09-2025","Personal", 3);
        Task recur = new Task("Name", "Desc","School", 2, 1);
        TaskRecurrence.addDaily(recur);
        DataBase.saveRecurringTask();
        DataBase.loadRecurringTask();
    }
}
*/

