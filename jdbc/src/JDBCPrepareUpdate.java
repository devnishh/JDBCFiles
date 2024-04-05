import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class JDBCPrepareUpdate {
    private static Driver driver;
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static String query;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter id");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline character left by nextInt()

            System.out.println("Enter new password");
            String newPassword = scanner.nextLine();

            try {
                openConnection();
                query = "UPDATE user SET password = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setInt(2, id);
                int res = preparedStatement.executeUpdate();
                System.out.println(res + " row(s) affected");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
					closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }

    private static void openConnection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        File file = new File("D://File//db.info.txt");
        FileReader fileReader = new FileReader(file);
        Properties properties = new Properties();
        properties.load(fileReader);
        connection = DriverManager.getConnection(properties.getProperty("url"), properties);
    }

    private static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (driver != null) {
            DriverManager.deregisterDriver(driver);
        }
    }
}
