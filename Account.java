package bankingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Account {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bank_login";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Clf@56.9fce";
    int previousTransaction;
    String customerNmae;
    String customerId;

    Account(String cName, String cID) {
        customerNmae = cName;
        customerId = cID;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            previousTransaction = amount;
            int currentBalance = getCurrentBalance();
            currentBalance += amount;
            updateBalanceInDatabase(currentBalance, "Deposit: +" + amount);
            System.out.println(
                    "Thank you for depositing " + amount);
            System.out.println("your current balance now is :" + currentBalance);
        } else {
            System.out.println("Invalid deposit please enter a positive value");
        }
    }

    private int getCurrentBalance() {
        // Use JDBC to retrieve the current balance from the User_Accounts table
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT Current_Balance FROM User_Accounts WHERE User_Id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, customerId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("Current_Balance");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if an error occurs
    }

    private String getAccountnumber() {
        // Use JDBC to retrieve the current balance from the User_Accounts table
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT Account_Number FROM User_Info WHERE Account_Holder_Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, customerNmae);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("Account_Number");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""; // Return empty string if an error occurs
    }
    private String getMailingadd() {
        // Use JDBC to retrieve the current balance from the User_Accounts table
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT Mailing_Address FROM User_Info WHERE Account_Holder_Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, customerNmae);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString(" Mailing_Address");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""; // Return empty string if an error occurs
    }

    private void updateBalanceInDatabase(int newBalance, String transactionDescription) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updateQuery = "UPDATE User_Accounts SET Current_Balance = ? WHERE User_Id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, newBalance);
                preparedStatement.setString(2, customerId);

                // Execute the update statement
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void userProfile() {

        String Account_number= getAccountnumber();
        String ma = getMailingadd();
        System.out.println("Account number : "+Account_number);
        System.out.println("Mailing Address: "+ma);

    }

    public void withdraw(int amount) {

        if (amount > 0) {
            int currentBalance = getCurrentBalance();
            if (amount < currentBalance) {
                currentBalance -= amount;
                updateBalanceInDatabase(currentBalance, "Withdrawal: -" + amount);
                System.out.println(
                        "Thank you for withdrawing " + amount + "." + " Now your current balance is: "
                                + currentBalance);

            } else {
                System.out.println("insufficient  balance to withdraw please check your balance");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive amount.");

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
        System.out.println(" Funds : $" + getCurrentBalance());
        System.out.println("\n");
        System.out.println("A. Deposit");
        System.out.println("B. Withdraw");
        System.out.println("C. Previous Transaction");
        System.out.println("D. My Profile");
        System.out.println("E. Exit");

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
                    System.out.println("===========================================================================");
                    System.out.println("Full Name:" + customerNmae);
                    userProfile();

                    break;

                case 'E':
                    System.out.println("Thank you for using our services");
                    return;
                default:
                    System.out.println("Please enter a valid option!!!");

            }

        }

    }

}
