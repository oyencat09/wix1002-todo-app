import java.time.LocalDate;
import java.util.UUID;

class Task {
    private String uuid, name, description, category, dependency;
    private LocalDate dueDate, formattedDate;
    int priority, recurring;
    private boolean status;

    //Normal Task creation
    Task(String name, String description, String dueDate, String category, int priority) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.dueDate = DateFormat.convertToDate(dueDate);
        this.status = false;
        this.category = category;
        this.priority = priority;
        this.dependency = null;
        this.recurring = 0;
    }

    //Loading Normal Task from savedData
    Task(String uuid, String name, String description, String dueDate, boolean status, String category, int priority, String dependency, int recurring) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.dueDate = DateFormat.convertToDate(dueDate);
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.dependency = dependency;
        this.recurring = recurring;
    }

    //Recurring Task Type
    Task(String name, String description, String category, int priority, int recurring) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.recurring = recurring;
    }

    //Loading Recurring Task Type from savedData
    Task(String uuid, String name, String description, String category, int priority, int recurring) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.recurring = recurring;
    }

    //Task created by Recurring Task
    Task(int recurring, String name, String description, String dueDate, String category, int priority) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.dueDate = DateFormat.convertToDate(dueDate);
        this.status = false;
        this.category = category;
        this.priority = priority;
        this.dependency = null;
        this.recurring = recurring;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setDescription(String s) {
        this.description = s;
    }

    public void setDate(String s) {
        this.dueDate = DateFormat.convertToDate(s);
    }

    public void setStatus(boolean s) {
        this.status = s;
    }

    public void setCategory(String s) {
        this.category = s;
    }

    public void setPriority(int s) {
        this.priority = s;
    }

    public void setDependency(String s) {
        this.dependency = s;
    }

    public void setRecurring(int s) {
        this.recurring = s;
    }

    public void setDependencyNull() {
        this.dependency = null;
    }

    public String getUUID() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return DateFormat.convertToString(this.dueDate);
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getCategory() {
        return this.category;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getDependency() {
        return this.dependency;
    }

    public int getRecurring() {
        return this.recurring;
    }

}
