import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static List<User> users = new ArrayList<>();

    public static void registerUsersFromCSV(List<User> userList) {
        users.addAll(userList);
        // Optionally, you might also want to save these users to the database
        for (User user : userList) {
            DatabaseHandler.addUser(user);
        }
    }

    public static User getUserByEmployeeId(String employeeId) {
        for (User user : users) {
            if (user.getEmployeeId().equals(employeeId)) {
                return user;
            }
        }
        return null;
    }

    // For example, you can add a method to fetch all users from the database and update the users list.
    public static void fetchAllUsersFromDB() {
        // Assuming a method in DatabaseHandler exists for this purpose
        users = DatabaseHandler.getAllUsers();
    }

    // Other user management methods can also be implemented here.
}
