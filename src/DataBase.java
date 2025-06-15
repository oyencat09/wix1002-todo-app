import java.io.File;
import java.sql.*;

class DataBase {
    public static void saveData() {
        try {
            System.out.println("\nSaving file...");
            Connection connectDataBase = DriverManager.getConnection("jdbc:sqlite:task.db");

            String createSql = "CREATE TABLE IF NOT EXISTS task(id INTEGER PRIMARY KEY AUTOINCREMENT, uuid TEXT,Name TEXT," +
                    "Description TEXT, DueDate TEXT, Completion BOOLEAN, Category TEXT, Priority INTEGER, Dependency TEXT, Recurring INTEGER)";
            PreparedStatement initializingStatement = connectDataBase.prepareStatement(createSql);
            initializingStatement.execute();

            String clearTable = "DELETE FROM task";
            PreparedStatement clearStatement = connectDataBase.prepareStatement(clearTable);
            clearStatement.execute();

            String sql = "INSERT INTO task(uuid,Name,Description,DueDate,Completion,Category,Priority,Dependency,Recurring) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connectDataBase.prepareStatement(sql);
            for (Task task : TODOApp.getListTask()) {
                statement.setString(1, task.getUUID());
                statement.setString(2, task.getName());
                statement.setString(3, task.getDescription());
                statement.setString(4, task.getDate());
                statement.setBoolean(5, task.getStatus());
                statement.setString(6, task.getCategory());
                statement.setInt(7, task.getPriority());
                statement.setString(8, task.getDependency());
                statement.setInt(9, task.getRecurring());
                statement.executeUpdate();
            }
            System.out.println("File is successfully saved to 'task.db'!");
        } catch (SQLException e) {
            System.out.println("Error while saving file to SQLite database.\nError: " + e);
        }
    }

    public static void loadData() {
        try {
            System.out.println("Searching for 'task.db' save file...");
            File dbFile = new File("task.db");
            System.out.println((dbFile.exists() ? "\n'task.db is found.\n Loading the tasks...'" : "\nNo 'task.db' found!\nCreating a new 'task.db' file..."));


            if (dbFile.exists()) {
                Connection connectDataBase = DriverManager.getConnection("jdbc:sqlite:task.db");
                String sql = "SELECT uuid, Name, Description, DueDate, Completion, Category, Priority, Dependency, Recurring FROM task";
                PreparedStatement statement = connectDataBase.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    String dueDate = resultSet.getString("DueDate");
                    boolean status = resultSet.getBoolean("Completion");
                    String category = resultSet.getString("Category");
                    int priority = resultSet.getInt("Priority");
                    String dependency = resultSet.getString("Dependency");
                    int recurring = resultSet.getInt("Recurring");

                    TODOApp.loadTask(uuid, name, description, dueDate, status, category, priority, dependency, recurring);
                }

            }


            System.out.println("All tasks successfully loaded from 'task.db'!");
        } catch (SQLException e) {
            System.out.println("Error while loading Tasks from SQLite database.\nError: " + e);
        }
    }

    public static void saveRecurringTask() {
        try {
            Connection connectDataBase = DriverManager.getConnection("jdbc:sqlite:task.db");

            String createSql = "CREATE TABLE IF NOT EXISTS recurring_task(id INTEGER PRIMARY KEY AUTOINCREMENT, uuid TEXT,Name TEXT," +
                    "Description TEXT, Category TEXT, Priority INTEGER, Recurring INTEGER)";

            PreparedStatement initializingStatement = connectDataBase.prepareStatement(createSql);
            initializingStatement.execute();

            String clearTable = "DELETE FROM recurring_task";
            PreparedStatement clearStatement = connectDataBase.prepareStatement(clearTable);
            clearStatement.execute();

            String sql = "INSERT INTO recurring_task(uuid,Name,Description,Category,Priority,Recurring) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connectDataBase.prepareStatement(sql);
            for (Task task : TaskRecurrence.getDaily()) {
                statement.setString(1, task.getUUID());
                statement.setString(2, task.getName());
                statement.setString(3, task.getDescription());
                statement.setString(4, task.getCategory());
                statement.setInt(5, task.getPriority());
                statement.setInt(6, task.getRecurring());
                statement.executeUpdate();
            }

            for (Task task : TaskRecurrence.getWeekly()) {
                statement.setString(1, task.getUUID());
                statement.setString(2, task.getName());
                statement.setString(3, task.getDescription());
                statement.setString(4, task.getCategory());
                statement.setInt(5, task.getPriority());
                statement.setInt(6, task.getRecurring());
                statement.executeUpdate();
            }

            for (Task task : TaskRecurrence.getMonthly()) {
                statement.setString(1, task.getUUID());
                statement.setString(2, task.getName());
                statement.setString(3, task.getDescription());
                statement.setString(4, task.getCategory());
                statement.setInt(5, task.getPriority());
                statement.setInt(6, task.getRecurring());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error while saving recurring task to SQLite database.\nError: " + e);
        }
    }

    public static void loadRecurringTask() {
        try {
            File dbFile = new File("task.db");

            if (dbFile.exists()) {
                Connection connectDataBase = DriverManager.getConnection("jdbc:sqlite:task.db");
                String sql = "SELECT uuid, Name, Description, Category, Priority, Recurring FROM recurring_task";
                PreparedStatement statement = connectDataBase.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    String category = resultSet.getString("Category");
                    int priority = resultSet.getInt("Priority");
                    int recurring = resultSet.getInt("Recurring");

                    TODOApp.loadRecurringTask(uuid, name, description, category, priority, recurring);
                }

            }
        } catch (SQLException e) {
            System.out.println("Error while loading Tasks from SQLite database.\nError: " + e);
        }
    }
}
