package bankingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Login {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bank_login";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Clf@56.9fce";

    public void loginInfo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== Login Page =====");
            System.out.print("Enter your username: ");
            String username = sc.nextLine();
            System.out.print("Enter your user ID: ");
            String userID = sc.nextLine();
            System.out.println("Enter your Password:");
            String pass = sc.nextLine();
            try {
                // Show "Transacting process" for 3 seconds
                System.out
                        .println("Logging in.............................................please wait................");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // Handle the exception if needed
                e.printStackTrace();
            }
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                if (isValidLogin(connection, userID, username, pass)) {
                    // Successful login
                    System.out.println("Login successful!\n");
                    Account a1 = new Account(username, userID);
                    a1.showMenue();
                } else {
                    // Failed login
                    System.out.println("Login failed. Please enter correct username or password. Exiting...");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isValidLogin(Connection connection, String userId, String username, String pass) {
        String query = "SELECT * FROM Login_credintals WHERE User_Id = ? AND User_Name = ? AND Password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, pass);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Check if there is any result
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
