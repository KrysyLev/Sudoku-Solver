import java.sql.*;
import java.util.*;

public class UserProfile {
    private String username;
    private Connection connection;

    public UserProfile(String username) {
        this.username = username;
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:userdata.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS statistics (username TEXT, puzzleSize INTEGER, solvingTime INTEGER, accuracy DOUBLE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveStatistics(int puzzleSize, long solvingTime, double accuracy) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO statistics (username, puzzleSize, solvingTime, accuracy) VALUES (?, ?, ?, ?)");
            statement.setString(1, username);
            statement.setInt(2, puzzleSize);
            statement.setLong(3, solvingTime);
            statement.setDouble(4, accuracy);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> getStatistics() {
        List<Map<String, Object>> statistics = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM statistics WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> stat = new HashMap<>();
                stat.put("puzzleSize", resultSet.getInt("puzzleSize"));
                stat.put("solvingTime", resultSet.getLong("solvingTime"));
                stat.put("accuracy", resultSet.getDouble("accuracy"));
                statistics.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistics;
    }
}
