import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class bankingapplication {
    public static void main(String[] args) {
        Login l1 = new Login();
        l1.loginInfo();
    }
}

class Login {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bank_login";
    private static final String DB_USER = "root";   // user name of my loacal host
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
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {  // try to connect a connection
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

class Account {
    int balance = 0;
    int previousTransaction;
    String customerNmae;
    String customerId;
    int currentBalance2;

    Account(String cName, String cID) {
        customerNmae = cName;
        customerId = cID;
    }

    public void deposit(int amount) {
        if (amount != 0) {
            previousTransaction = amount;
            System.out.println(
                    "Thank you for depositing " + amount);
            balance = balance + amount;
            System.out.println("your current balance now is :" + balance);
        }
    }

    public void processTransactionWithDelay() {

        try {
            // Show "Transacting process" for 3 seconds
            System.out.println(
                    "Transacting process.............................................please wait................");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Handle the exception if needed
            e.printStackTrace();
        }
        // Perform the transaction logic here (after the 3-second delay)
        System.out.println("Transaction successful :)");
    }

    public void withdraw(int amount) {
        int currentBalance2 = balance - amount;
        if (amount != 0) {
            if (balance >= amount) {
                previousTransaction = amount;
                System.out.println(
                        "Thank you for withdrawing " + amount + "." + " Now your current balance is: "
                                + currentBalance2);
                balance = currentBalance2 - balance;
            } else {
                System.out.println("insufficient  balance to withdraw please check your balance");
            }
        }
    }

    public void previousTransaction() {
        if (previousTransaction > 0) {
            System.out.println("Your most recent transaction was: " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Your most recent transaction was: " + Math.abs(previousTransaction));
        } else {
            System.out.println("No transaction occured recently.");
        }
    }

    public void processTransactionWithDelay2() {

        try {
            // Show "Transacting process" for 3 seconds
            System.out.println(
                    "Transacting Loading.............................................please wait................");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Handle the exception if needed
            e.printStackTrace();
        }

    }

    public void showMenue() {
        System.out.println("\n\tBanking Application Menu:\n");
        Scanner scanner = new Scanner(System.in);
        char option = '0';
        System.out.println("Welcome " + customerNmae);
        System.out.println("Your ID is: " + customerId);
        System.out.println("\n");
        System.out.println("A. Deposit");
        System.out.println("B. Withdraw");
        System.out.println("C. Previous Transaction");
        System.out.println("D. Exit");
        while (true) {
            System.out.println(
                    "========================================================================================");
            System.out.println("Enter an Option:");
            System.out.println(
                    "=========================================================================================");
            option = scanner.next().charAt(0);
            System.out.println("\n");

            switch (option) {

                case 'A':
                    System.out.println("==========================================================================");
                    System.out.println("Enter an amount to deposit: ");
                    System.out.println("=============================================================================");
                    int amount = scanner.nextInt();
                    processTransactionWithDelay();
                    deposit(amount);
                    System.out.println("\n");
                    break;

                case 'B':
                    System.out.println("============================================================================");
                    System.out.println("Enter an amount to withdraw: ");
                    System.out.println("=============================================================================");
                    int amount2 = scanner.nextInt();
                    processTransactionWithDelay();
                    withdraw(amount2);
                    System.out.println("\n");
                    break;
                case 'C':
                    System.out.println("==========================================================================");
                    processTransactionWithDelay2();
                    previousTransaction();
                    System.out.println("===========================================================================");
                    System.out.println("\n");
                    break;
                case 'D':
                    System.out.println("Thank you for using our services");
                    return;
                default:
                    System.out.println("Please enter a valid option!!!");

            }

        }

    }

}
