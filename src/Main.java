import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.sql.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args)  {
        TODOApp.initializeTODO();
        TODOApp.addTask("Task1","JustDoIt","28-09-2025",false,"Personal",3);
        TODOApp.addTask("Task2","JustDoIt","25-09-2025",false,"Personal",3);
        PrintTask.printOneTask(0);
        TODOApp.setActiveTask(0);
        TaskManager.editCategory("School");
        PrintTask.printOneTask(0);
    }
}

class DateFormat{
    private static String DateFormat = "dd-MM-yyyy";
    private static DateTimeFormatter formatter= DateTimeFormatter.ofPattern(DateFormat);

    public static LocalDate convertToDate(String s){
        return LocalDate.parse(s, formatter);}

    public static String convertToString(LocalDate s){
        return s.format(formatter);
    }
}

class Task{
    // TODO dependency
    private String uuid, name, description, category, dependency;
    private LocalDate dueDate, formattedDate;
    int priority;
    private boolean status;

    Task(String name, String detail, String dueDate, boolean status, String category, int priority){
       this.uuid = UUID.randomUUID().toString();
       this.name = name;
       this.description = detail;
       this.dueDate = DateFormat.convertToDate(dueDate);
       this.status = status;
       this.category = category;
       this.priority = priority;
    }

    public void setName(String s){this.name = s;}
    public void setDescription(String s){this.description= s;}
    public void setDate(String s){this.dueDate= DateFormat.convertToDate(s);}
    public void setStatus(boolean s){this.status= s;}
    public void setCategory(String s){this.category= s;}
    public void setPriority(int s){this.priority=s;}

    public String getUUID(){return this.uuid;}
    public String getName(){return this.name;}
    public String getDescription(){return this.description;}
    public String getDate(){return DateFormat.convertToString(this.dueDate);}
    public boolean getStatus(){return this.status;}
    public String getCategory(){return this.category;}
    public int getPriority(){return this.priority;}

}

class TODOApp{
   static private List<Task> listTask;
   static private List<Task> searchTask;
   static private Task ActiveTask;
   static private String SortBy = "None";

   // Getter for objects (listTask, SortBy)
   static public List<Task> getListTask(){return listTask;}
   static public Task getActiveTask(){return ActiveTask;}
   static public String getSortBy(){return SortBy;}

   //Setter method
   static public void setSortBy(String s){SortBy = s;}
   static public void setActiveTask(int i){ActiveTask = listTask.get(i);}
   static public void userSetActiveTask(int i){
        ActiveTask = listTask.get(i);
        System.out.println("Task "+ActiveTask.getName()+" is selected.");
    }
    //Wrapper for Status and Priority
    static public String printStatus(boolean b){return (b)? "Completed":"In progress...";}
    static public String printPriority(int i){
        String print = "";
        if (i==3) {print = "High";}
        if (i==2) {print = "Medium";}
        if (i==1) {print = "Low";}
        return print;
    }

    static public void initializeTODO() {
        listTask = new ArrayList<>();
    }

    static public void addTask(String n, String d, String due, boolean status, String category, int priority) {
        listTask.add(new Task(n, d, due, status, category, priority));
        System.out.println(n + " successfully created!");
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
        System.out.println(ActiveTask.getStatus());
        if (ActiveTask.getStatus()) {
            ActiveTask.setStatus(false);
            System.out.println(ActiveTask.getName() + " marked as complete!");
        } else {
            ActiveTask.setStatus(true);
            System.out.println(ActiveTask.getName() + " marked as incomplete.");
        }
    }
}
// TODO load, if pass, overdue!!
// TODO display list to edit, 99 for next instead of printing everything
class TaskManager extends TODOApp{
    private static List<Task> listTask = getListTask();

    static public void editName(String s){
        String oldName = getActiveTask().getName();
        getActiveTask().setName(s);
        System.out.println(oldName+" has been changed to "+getActiveTask().getName());
    }
    static public void editDetail(String s){
        String oldName = getActiveTask().getName();
        getActiveTask().setDescription(s);
        System.out.println(oldName+" description has been updated!\n"+getActiveTask().getDescription());
    }
    static public void editDueDate(String s){
        String oldName = getActiveTask().getName();
        getActiveTask().setDate(s);
        System.out.println(oldName+" due date has been updated to: "+getActiveTask().getDate());
    }
    static public void editPriority(int s){
        String oldName = getActiveTask().getName();
        getActiveTask().setPriority(s);
        System.out.println(oldName+" priority has been updated to: "+TODOApp.printPriority(getActiveTask().getPriority()));
    }
    static public void editCategory(String s){
        String oldName = getActiveTask().getName();
        getActiveTask().setCategory(s);
        System.out.println(oldName+" category has been updated to: "+getActiveTask().getCategory());
    }

    static public void deleteTask(){
        String oldName = getActiveTask().getName();
        listTask.remove(getActiveTask());
        System.out.println(oldName+" has been deleted.");
    }
}
class PrintTask extends TODOApp{
    private static List<Task> listTask = getListTask();

    static public void printAllTask(){
        System.out.println("\nTask List. (Sorted by= "+getSortBy()+")");
        int count=1;
        for (Task t:listTask){
            System.out.println(count+". "+t.getName());
            count++;
        }
    }
    static public void printOneTask(int i){
        setActiveTask(i);
        System.out.println("===============================================");
        System.out.println("Name: "+getActiveTask().getName());
        System.out.println("Details: "+getActiveTask().getDescription());
        System.out.println("\nCategory: "+getActiveTask().getCategory());
        System.out.println("Priority: "+TODOApp.printPriority(getActiveTask().getPriority()));
        System.out.println("\nDue Date: "+getActiveTask().getDate());
        System.out.println("\nStatus: "+TODOApp.printStatus(getActiveTask().getStatus()));
        System.out.println("===============================================");
    }

}
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

class DataBase {
    public static void saveData () {
        try{
            System.out.println("\nSaving file...");
            Connection connectDataBase = DriverManager.getConnection("jdbc:sqlite:task.db");

            String createSql = "CREATE TABLE IF NOT EXISTS task(id INTEGER PRIMARY KEY AUTOINCREMENT, uuid TEXT,Name TEXT,"+
                                "Description TEXT, DueDate TEXT, Completion BOOLEAN, Category TEXT, Priority INTEGER)";
            PreparedStatement initializingStatement = connectDataBase.prepareStatement(createSql);
            initializingStatement.execute();

            String clearTable = "DELETE FROM task";
            PreparedStatement clearStatement = connectDataBase.prepareStatement(clearTable);
            clearStatement.execute();

            String sql = "INSERT INTO task(uuid,Name,Description,DueDate,Completion,Category,Priority) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = connectDataBase.prepareStatement(sql);
            for (Task task: TODOApp.getListTask()){
                statement.setString(1, task.getUUID());
                statement.setString(2, task.getName());
                statement.setString(3, task.getDescription());
                statement.setString(4, task.getDate());
                statement.setBoolean(5, task.getStatus());
                statement.setString(6, task.getCategory());
                statement.setInt(7, task.getPriority());
                statement.executeUpdate();
            }
            System.out.println("File is successfully saved to 'task.db'!");
        } catch (SQLException e) {
            System.out.println("Error while saving file to SQLite database.\nError: "+e);
        }
    }

    public static void loadData() {
        try {
            System.out.println("Searching for 'task.db' save file...");
            File dbFile = new File("task.db");
            System.out.println((dbFile.exists()? "\n'task.db is found.\n Loading the tasks...'":"\nNo 'task.db' found!\nCreating a new 'task.db' file..."));



            if(dbFile.exists()){
                Connection connectDataBase = DriverManager.getConnection("jdbc:sqlite:task.db");
                String sql = "SELECT Name, Description, DueDate, Completion, Category, Priority FROM task";
                PreparedStatement statement = connectDataBase.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    String dueDate = resultSet.getString("DueDate");
                    boolean status = resultSet.getBoolean("Completion");
                    String category = resultSet.getString("Category");
                    int priority = resultSet.getInt("Priority");

                    TODOApp.addTask(name, description, dueDate, status, category, priority);
                }

            }


            System.out.println("All tasks successfully loaded from 'task.db'!");
        } catch (SQLException e) {
            System.out.println("Error while loading Tasks from SQLite database.\nError: " + e);
        }
    }

}