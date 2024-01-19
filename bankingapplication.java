import java.util.Scanner;

class bankingapplication {
    public static void main(String[] args) {
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
            if (isValidLogin(username, pass)) {
                // Successful login
                System.out.println("Login successful!\n");
                Account a1 = new Account(username, userID);
                a1.showMenue();
            } else {
                // Failed login
                System.out.println("Login failed. please enter correct usename or password Exiting...");
            }

        }
    }

    public static boolean isValidLogin(String username, String pass) {
        // Replace this logic with your actual authentication mechanism
        return username.equals("Bimal Itani") && pass.equals("123321");
    }
}

class Account {
    int balance;
    int previousTransaction;
    String customerNmae;
    String customerId;
    int currentBalance2;

    Account(String cName, String cID) {
        customerNmae = cName;
        customerId = cID;
    }

    public void deposit(int amount) {
        int currentBalance1 = amount + balance;
        if (amount != 0) {
            amount = amount + balance;
            previousTransaction = amount;
            System.out.println(
                    "Thank you for depositing " + amount + "." + " Now your current balance is: " + currentBalance1);

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
            amount = balance - amount;
            previousTransaction = -amount;
            System.out.println(
                    "Thank you for withdrawing " + amount + "." + " Now your current balance is: " + currentBalance2);
        }
    }

    public void previousTransaction() {
        if (previousTransaction > 0) {
            System.out.println("The amount deposited is: " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("The amount withdrawn is: " + Math.abs(previousTransaction));
        } else {
            System.out.println("No transaction occured. Please try again!!!");
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
        System.out.println("Welcome" + customerNmae);
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