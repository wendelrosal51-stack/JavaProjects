import java.util.InputMismatchException;
import java.util.Scanner;

public class PaymentSystem {
    public static void main(String[] args) {
        systemStart();
    }

    static void systemStart() {
        Scanner userInput = new Scanner(System.in);
        PaymentMethod method;
        int choice = 0;
        boolean isRunning = true;

        while (isRunning) {
            try {
                System.out.println("\n== Welcome to our payment system ==");
                System.out.println("1. Credit Card");
                System.out.println("2. Paypal");
                System.out.println("3. Bank Transfer");
                System.out.println("4. Exit");
                System.out.print("Your choice: ");
                choice = userInput.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("\nPlease enter only a number.");
                System.out.println();
                userInput.nextLine();
                continue;
            }
            switch (choice) {
                case 1 -> {
                    method = new CreditCard();
                    method.paymentProcess();
                }
                case 2 -> {
                    method = new Paypal();
                    method.paymentProcess();
                }
                case 3 -> {
                    method = new BankTransfer();
                    method.paymentProcess();
                }
                case 4 -> {
                    System.out.println("\nThank you for using our system!");
                    System.out.println("Exiting...");
                    isRunning = false;
                }
                default -> System.out.println("\nInvalid choice please enter from number 1-3 only.");
            }
        }
    }
}

abstract class PaymentMethod {
    double payment;
    Scanner userInput = new Scanner(System.in);

    abstract void paymentProcess();

    void paymentAmount() {
        while (true) {
            try {
                System.out.print("\nEnter payment amount: ");
                payment = userInput.nextDouble();
                userInput.nextLine();
                break;
            } catch (InputMismatchException ime) {
                System.out.println("Please enter only numbers");
                userInput.nextLine();
            }
        }
    }

    void recipt() {
        System.out.println("Successfully transferred $" + payment);
    }
}

class CreditCard extends PaymentMethod {

    void paymentProcess() {
        validCard();
        super.paymentAmount();
        super.recipt();

        System.out.println("---------------------------------------");
    }

    boolean validCard() {
        int cardNumber = 0;

        while (true) {
            try {
                System.out.println("\n-------------------------------------");
                System.out.println("You have chosen Credit card payment.");
                System.out.println("-------------------------------------");
                System.out.print("\nEnter your card number: ");
                cardNumber = userInput.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("\nPlease input numbers only.");
                System.out.println();
                userInput.nextLine();
                continue;
            }

            if (String.valueOf(cardNumber).length() != 5) {
                if (String.valueOf(cardNumber).isEmpty()) {
                    System.out.println("\nEmpty card number inputted.");
                } else {
                    System.out.println("\nIncomplete card number. Please enter 5 numbers of your card.");
                }
            } else {
                System.out.println("Valid Credit card.");
                return true;
            }
        }
    }
}

class Paypal extends PaymentMethod {

    void paymentProcess() {
        validAccount();
        super.paymentAmount();
        super.recipt();
    }

    boolean validAccount() {
        return emailCheck() && passwordCheck();
    }

    boolean emailCheck() {
        boolean validEmail = false;
        while (!validEmail) {
            System.out.println("\n-----------------------");
            System.out.println("You have chosen Paypal.");
            System.out.println("-------------------------");
            System.out.print("Enter your email: ");
            String email = userInput.nextLine();

            if (email.isEmpty()) {
                System.out.println("Email can't be empty.");
            } else if (email.contains(" ")) {
                System.out.println("Email should not contain spaces.");
            } else if (email.indexOf("@") < 5) {
                System.out.println("Email should be atleast 5 characters long");
            } else if (email.contains("@") && email.contains(".com")) {
                validEmail = true;
            } else {
                System.out.println("Email should contain @ and .com");
            }
        }
        return validEmail;
    }

    boolean passwordCheck() {
        boolean validPassword = false;
        String simulatePassword = "Rosal123";

        while (!validPassword) {
            System.out.print("Enter password: ");
            String userPass = userInput.nextLine();

            if (userPass.equals(simulatePassword)) {
                return true;
            } else {
                System.out.println("\nIncorrect password.");
            }
        }
        return validPassword;
    }
}

class BankTransfer extends PaymentMethod {
    static String name;

    void paymentProcess() {
        validUser();
        super.paymentAmount();
        recipt();
    }

    @Override
    void recipt() {
        super.recipt();
        System.out.println("Thank you for using our system, " + name);
    }

    boolean validUser() {
        boolean authenticUser = false;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("\nActive banks to use: ");
            System.out.println("A.Metrobank \nB.BPI \nC.BDO");
            System.out.print("Enter bank to use: ");
            String choose = userInput.next();
            userInput.nextLine();

            if (choose.length() == 1) {
                char chosenBank = Character.toUpperCase(choose.charAt(0));
                if (Character.isDigit(chosenBank)) {
                    System.out.println("Cannot input numbers.");
                    continue;
                }
                switch (chosenBank) {
                    case 'A' -> {
                        System.out.println("You are now using the Metrobank system.");
                        validChoice = true;
                    }
                    case 'B' -> {
                        System.out.println("You are now using the BPI system.");
                        validChoice = true;
                    }
                    case 'C' -> {
                        System.out.println("You are now using the BDO system.");
                        validChoice = true;
                    }
                    default -> System.out.println("Invalid choice, please choose from the given choices.");
                }
            } else if (Character.isDigit(choose.charAt(0))) {
                System.out.println("Numbers are not accepted.");
            } else {
                System.out.println("Please enter only a character.");
            }
        }

        while (!authenticUser) {
            int accNumber = 0;
            System.out.print("Enter your account name: ");
            name = userInput.nextLine();

            try {
                System.out.print("Enter account number: ");
                accNumber = userInput.nextInt();
                userInput.nextLine();
            } catch (InputMismatchException ife) {
                System.out.println("Please enter numbers only.");
                userInput.nextLine();
                continue;
            }

            String numberLength = Integer.toString(accNumber);
            if (numberLength.length() != 5) {
                System.out.println("\nPlease enter 5 numbers.");
                continue;
            } else {
                return true;
            }
        }
        return false;
    }
}
