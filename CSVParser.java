import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public static List<User> parseUsers(String csvFilePath) {
        List<User> userList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            // Skip header row
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                int userId = Integer.parseInt(nextLine[0]);
                String userName = nextLine[1];
                String email = nextLine[2];
                String password = nextLine[3];

                User user = new User(userId, userName, email, password);
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    // You can add more methods to parse other entities from different CSV files.
}
