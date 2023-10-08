import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;



public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectpulse";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";
    private static final Logger logger = Logger.getLogger(DatabaseHandler.class.getName());

    public static Connection establishConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while establishing a database connection", e);
            throw new DatabaseException("Database connection error", e);
        }
    }

    public static void uploadUsersFromCSV(String csvFilePath) {
        // Läs CSV-filen och konvertera data till en lista av User-objekt.
        List<User> userList = CSVParser.parseUsers(csvFilePath);
        
        // Registrera användare med hjälp av UserManager.
        UserManager.registerUsersFromCSV(userList);
    }
    
    public static void addUser(User user) {
        String insertUserSQL = "INSERT INTO User (employee_id, name, email, mobile_number, password, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {

            preparedStatement.setString(1, user.getEmployeeId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getMobileNumber());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while adding a user to the database", e);
            throw new DatabaseException("Error adding user to the database", e);
        }
    }

    public static void addProject(Project project) {
        String insertProjectSQL = "INSERT INTO Project (project_name, reporting_frequency, time_deadline, manager_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertProjectSQL)) {

            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getReportingFrequency());
            preparedStatement.setString(3, project.getTimeDeadline());
            preparedStatement.setInt(4, project.getManagerId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while adding a project to the database", e);
            throw new DatabaseException("Error adding project to the database", e);
        }
    }

    public static void addReport(Report report) {
        String insertReportSQL = "INSERT INTO Report (content, status, project_id, submitter_id, due_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertReportSQL)) {

            preparedStatement.setString(1, report.getContent());
            preparedStatement.setString(2, report.getStatus());
            preparedStatement.setInt(3, report.getProjectId());
            preparedStatement.setInt(4, report.getSubmitterId());
            preparedStatement.setString(5, report.getDueDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while adding a report to the database", e);
            throw new DatabaseException("Error adding report to the database", e);
        }
    }

    public static List<Report> getReportsForProject(int projectId) {
        List<Report> reports = new ArrayList<>();
        String selectReportsSQL = "SELECT * FROM Report WHERE project_id = ?";

        try (Connection connection = establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectReportsSQL)) {

            preparedStatement.setInt(1, projectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int reportId = resultSet.getInt("report_id");
                    String content = resultSet.getString("content");
                    String status = resultSet.getString("status");
                    int submitterId = resultSet.getInt("submitter_id");
                    String dueDate = resultSet.getString("due_date");

                    Report report = new Report(reportId, content, status, projectId, submitterId, dueDate);
                    reports.add(report);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while fetching reports from the database", e);
            throw new DatabaseException("Error fetching reports from the database", e);
        }

        return reports;
    }

    public static void addComment(Comment comment) {
        String insertCommentSQL = "INSERT INTO Comment (comment_text, report_id, commenter_id) VALUES (?, ?, ?)";

        try (Connection connection = establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertCommentSQL)) {

            preparedStatement.setString(1, comment.getCommentText());
            preparedStatement.setInt(2, comment.getReportId());
            preparedStatement.setInt(3, comment.getCommenterId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while adding a comment to the database", e);
            throw new DatabaseException("Error adding comment to the database", e);
        }
    }

    public static List<Comment> getCommentsForReport(int reportId) {
        List<Comment> comments = new ArrayList<>();
        String selectCommentsSQL = "SELECT * FROM Comment WHERE report_id = ?";

        try (Connection connection = establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectCommentsSQL)) {

            preparedStatement.setInt(1, reportId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int commentId = resultSet.getInt("comment_id");
                    String commentText = resultSet.getString("comment_text");
                    int commenterId = resultSet.getInt("commenter_id");

                    Comment comment = new Comment(commentId, commentText, reportId, commenterId);
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while fetching comments from the database", e);
            throw new DatabaseException("Error fetching comments from the database", e);
        }

        return comments;
    }
}
