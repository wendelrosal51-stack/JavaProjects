import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
public class SmartFitnessCenter {
    public static void main(String[] args) {
        UserInfo user = new UserInfo();

        user.systemStart();

    }
}

class UserInfo {
    static Scanner userInput = new Scanner(System.in);
    private String name;
    private int age;

    void systemStart() {
        Members[] members = { new Members(fillUpName(),fillUpAge())};

        for(Members member : members){
            member.display();
        }

    }

    String fillUpName() {
        
        while (true) {
            System.out.print("Enter your Name: ");
            name = userInput.nextLine().trim().toUpperCase();

            if (validateUserName(name)) {
                System.out.println("Valid username");
                return name;
            } else {
                System.out.println("Please try again.");
                System.out.println();
            }
        }
    }

    int fillUpAge(){

        while(true){
            try{
                System.out.print("Enter your age: ");
                age = userInput.nextInt();
                break;
            }
            catch(InputMismatchException ime){
                System.out.println("Please do not type any letters or special characters");
                System.out.println();
                userInput.nextLine();
            }
        }


        
        return age;
    }



// Input validations
    boolean validateUserName(String name) {
        if (name.isEmpty()) {
            System.out.println("\nName cannot be empty.");
            return false;
        }

        for (char character : name.toCharArray()) {
            if (Character.isDigit(character) || !Character.isLetterOrDigit(character)) {
                if (Character.isWhitespace(character)) {
                    continue;
                }
                System.out.println("\nName cannot have any numbers or special characters.");
                return false;
            }
        }
        return true;
    }



}
