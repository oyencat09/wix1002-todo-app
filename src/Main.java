import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.format.DateTimeFormatter;
// TODO Learn csv IO
// TODO Learn dependency
/*
* 1. Create Task class
* 2. Task attributes: name, description, due date, status,
    * category, priority, dependency
* */

public class Main {
    public static void main(String[] args) {
        TODOApp.initializeTODO();
        TODOApp.addTask("Task1","Just a test", "10-06-2025", false, "School", 1);
        TODOApp.addTask("Task2","Just a test", "11-06-2025", false, "School", 2);
        TODOApp.addTask("Task3","Not a test", "12-06-2025", false, "School", 3);
        TODOApp.searchTask("Not");
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
    private String name, description, category, dependency;
    private LocalDate dueDate, formattedDate;
    int priority;
    private boolean status;

    Task(String name, String detail, String dueDate, boolean status, String category, int priority){
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

   static public void initializeTODO(){
       listTask = new ArrayList<>();
       searchTask = new ArrayList<>();
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
   static public void showTask(){
       System.out.println("\nTask List. (Sorted by= "+SortBy+")");
       int count=1;
       for (Task t:listTask){
           System.out.println(count+". "+t.getName());
           count++;
       }
   }
   static public void addTask(String n, String d, String due, boolean status, String category, int priority){
       listTask.add(new Task(n, d, due, status, category, priority));
       System.out.println(n+" successfully created!");
   }
   static public void searchTask(String s){
       System.out.println("\nSearch results:");
       for (int i=0;i<listTask.size();i++){
           if (listTask.get(i).getName().contains(s) || listTask.get(i).getDescription().contains(s)){
               TODOApp.getTask(i+1);
           }
       }
   }
   static public void setActiveTask(int taskNumber){
      ActiveTask = listTask.get(taskNumber-1);
      System.out.println("Task "+ActiveTask.getName()+" is selected.");
   }

   //Mark complete
    static public void markCompletion(int i) {
        ActiveTask = listTask.get(i - 1);
        System.out.println(ActiveTask.getStatus());
        if (ActiveTask.getStatus()) {
            ActiveTask.setStatus(false);
            System.out.println(ActiveTask.getName() + " marked as complete!");
        } else {
            ActiveTask.setStatus(true);
            System.out.println(ActiveTask.getName() + " marked as incomplete.");
        }
    }
    static public void getTask(int i){
      ActiveTask = listTask.get(i-1);
      System.out.println("===============================================");
      System.out.println("Name: "+ActiveTask.getName());
      System.out.println("Details: "+ActiveTask.getDescription());
      System.out.println("\nCategory: "+ActiveTask.getCategory());
      System.out.println("Priority: "+TODOApp.printPriority(ActiveTask.getPriority()));
      System.out.println("\nDue Date: "+ActiveTask.getDate());
      System.out.println("\nStatus: "+TODOApp.printStatus(ActiveTask.getStatus()));
      System.out.println("===============================================");
    }

   // Task Management (Edit: name, detail, due-date)
   //Edit task field of ActiveTask
    static public void editName(String s){
       String oldName = ActiveTask.getName();
       ActiveTask.setName(s);
       System.out.println(oldName+" has been changed to "+ActiveTask.getName());
   }
    static public void editDetail(String s){
        String oldName = ActiveTask.getName();
        ActiveTask.setDescription(s);
        System.out.println(oldName+" description has been updated!\n"+ActiveTask.getDescription());
    }
    static public void editDueDate(String s){
        String oldName = ActiveTask.getName();
        ActiveTask.setDate(s);
        System.out.println(oldName+" due date has been updated to: "+ActiveTask.getDate());
    }
    static public void editPriority(int s){
        String oldName = ActiveTask.getName();
        ActiveTask.setPriority(s);
        System.out.println(oldName+" priority has been updated to: "+TODOApp.printPriority(ActiveTask.getPriority()));
    }
    static public void editCategory(String s){
        String oldName = ActiveTask.getName();
        ActiveTask.setCategory(s);
        System.out.println(oldName+" category has been updated to: "+ActiveTask.getCategory());
    }

   //Delete task
    static public void deleteTask(int i){
       String oldName = listTask.get(i-1).getName();
       listTask.remove(i-1);
       System.out.println(oldName+" has been deleted.");
    }

    // Task Sorting
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
        SortBy = "Due Date(Ascending)";
        System.out.println("List has been sorted by Due Date (Ascending)");
    }
    static public void sortTaskDescendingDue(){
            Task temp;

            for (int i=0;i<listTask.size();i++){
                for (int j=0;j<listTask.size()-1;j++){
                    if (j==0){temp = listTask.get(j);}
                    if ((listTask.get(j).getDate()).compareTo((listTask.get(j+1).getDate()))<0){
                        temp = listTask.get(j);
                        listTask.set(j, listTask.get(j+1));
                        listTask.set(j+1, temp);
                    }
                }
            }
       SortBy = "Due Date(Descending)";
       System.out.println("List has been sorted by Due Date (Descending)");
    }
    static public void sortTaskAscendingPriority(){
       Task temp;
       for (int i=0;i<listTask.size();i++){
           for (int j=0;j<listTask.size()-1;j++){
               Task leftSide = listTask.get(j);
               Task rightSide = listTask.get(j+1);

               if (j==0){temp = listTask.get(j);}
               if (leftSide.getPriority() > rightSide.getPriority()){
                  temp = leftSide;
                  listTask.set(j, rightSide);
                  listTask.set(j+1, temp);

               }
           }
       }
        SortBy = "Priority (Ascending)";
        System.out.println("List has been sorted by Priority (Ascending)");
    }
    static public void sortTaskDescendingPriority(){
        Task temp;
        for (int i=0;i<listTask.size();i++){
            for (int j=0;j<listTask.size()-1;j++){
                Task leftSide = listTask.get(j);
                Task rightSide = listTask.get(j+1);

                if (j==0){temp = listTask.get(j);}
                if (leftSide.getPriority() < rightSide.getPriority()){
                    temp = leftSide;
                    listTask.set(j, rightSide);
                    listTask.set(j+1, temp);

                }
            }
        }
        SortBy = "Priority (Descending)";
        System.out.println("List has been sorted by Priority (Descending)");
    }
   // TODO load, if pass, overdue!!
   // TODO display list to edit, 99 for next instead of printing everything
}