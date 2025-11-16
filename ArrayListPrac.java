import java.util.Scanner;
import java.util.ArrayList;
public class ArrayListPrac{
    public static void main(String[]args){
        Scanner userInput = new Scanner(System.in);
        Infos info = new Infos();

        info.systemStart();
    }
}

class Infos{
    ArrayList<String> names = new ArrayList<String>();
    Scanner userInput = new Scanner(System.in);

    void systemStart(){
        boolean isRunning = true;
        while(isRunning){
            System.out.println("1. Register");
            System.out.println("2. View users");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int choice = userInput.nextInt();
            userInput.nextLine();   

            switch(choice){
                case 1 -> inputInfos();
                case 2 -> displayInfo();
                case 3 -> {System.out.println("Exiting.... "); isRunning = false;}
            }
        }
    }


    void inputInfos(){
        System.out.print("Enter name: ");
        String name = userInput.nextLine();

        System.out.print("Enter second name: ");
        String name2 = userInput.nextLine();

        names.add(name);
        names.add(name2);
    }

    void displayInfo(){

        if(names.size()==0){
            System.out.println("No registered users yet.");
        }
        else{
            for(String name : names){
                System.out.println("Name: "+name);
            }
        }
    }
}








