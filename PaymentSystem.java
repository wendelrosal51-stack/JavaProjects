import java.util.Scanner;
public class PaymentSystem{
    public static void main(String[]args){
        systemStart();
    }

    static void systemStart(){
        Scanner userInput = new Scanner(System.in);
        PaymentMethod method;

        System.out.println("== Welcome to our payment system ==");
        System.out.println("1. Credit Card");
        System.out.println("2. Paypal");
        System.out.println("3. Bank Transfer");
        System.out.print("Your choice: ");
        int choice = userInput.nextInt();

        switch(choice){
            case 1 ->{
                method = new CreditCard();
                method.paymentProcess();
            }
            case 2 ->{
                method = new Paypal();
                method.paymentProcess();
            }
            case 3 ->{
                method = new BankTransfer();
                method.paymentProcess();
            }
        }
    }
}

abstract class PaymentMethod{
    double payment;
    Scanner userInput = new Scanner(System.in);
    abstract void paymentProcess();

    void paymentAmount(){
        System.out.print("\nEnter payment amount: ");
        payment = userInput.nextDouble();
        userInput.nextLine();
    }

    void recipt(){
        System.out.println("Successfully transferred $"+payment);
    }
}

class CreditCard extends PaymentMethod{
   
    void paymentProcess(){
        super.paymentAmount();

        if(validCard()){
            System.out.println("\nValid card number...");
            super.recipt();
        }
        else{
            System.out.println("Exiting...");
        }
        System.out.println("---------------------------------------");
    }
    
    boolean validCard(){
        System.out.println("\n-------------------------------------");
        System.out.println("You have chosen Credit card payment.");
        System.out.println("---------------------------------------");
        System.out.print("Enter your card number: ");
        int cardNumber = userInput.nextInt();

        if(String.valueOf(cardNumber).length() != 5){
            if(String.valueOf(cardNumber).isEmpty()){
                System.out.println("Empty card number inputted.");
            }
            else{
                System.out.println("Incomplete card number.");
            }
            return false;
        }
        else{
            return true;
        }
    }
} 

class Paypal extends PaymentMethod{
    
    void paymentProcess(){
        super.paymentAmount();
        if(validEmail()){
            System.out.println("\nValid email.");
            super.recipt();
        }
        else{
            System.out.println("\nInvalid email, exiting...");
        }
    }

    boolean validEmail(){
        String simulatePassword = "Rosal123";
        boolean authenticEmail = false;
        boolean authenticPass = false;

        System.out.println("\n-----------------------");
        System.out.println("You have chosen Paypal.");
        System.out.println("-------------------------");

        while(!authenticEmail){
            System.out.print("Enter your email: ");
            String email = userInput.nextLine();
    
            if(email.isEmpty()){
                System.out.println("Email can't be empty.");
                continue;
            }
    
            if(email.contains(" ")){
                System.out.println("Email should not contain spaces.");
                continue;
            }

            if(email.indexOf("@")<5){
                System.out.println("Email should be atleast 5 characters long");
                continue;
            }
    
            if(email.contains("@") && email.contains(".com")){
                authenticEmail=true;
            }
            else{
                System.out.println("Email should contain @ and .com");
            }
            
        }
       
        while(!authenticPass){
            System.out.print("Enter password: ");
            String userPass = userInput.nextLine();
    
            if(userPass.equals(simulatePassword)){
                return true;
            }
            else{
                System.out.println("Incorrect password.");
            }
        }
        return false;
    }
}

class BankTransfer extends PaymentMethod{
    
    void paymentProcess(){
        super.paymentAmount();
        System.out.println("Bank Transfer");
    }

    boolean validUser(){
        boolean authenticUser = false;
        boolean validChoice = false;
        while(!validChoice){
            System.out.println("Active banks to use: ");
            System.out.println("A. Metrobank \nB.BPI \nC.BDO");
            System.out.print("Enter bank to use: ");
            char chosenBank = Character.toUpperCase(userInput.next().charAt(0));
    
            switch(chosenBank){
                case 'A' -> {
                    System.out.println("You are now using the Metrobank system."); validChoice = true;
                }
                case 'B' -> {
                    System.out.println("You are now using the BPI system."); validChoice = true;
                }
                case 'C' -> {
                    System.out.println("You are now using the BDO system."); validChoice = true;
                }
                default -> System.out.println("Invalid choice, please choose from the given banks.");
            }
        }

        

        while(!authenticUser){
            System.out.print("Enter your account name: ");
            String name  = userInput.nextLine();
            System.out.println(name);
        }
    


        return false;
    }

} 








