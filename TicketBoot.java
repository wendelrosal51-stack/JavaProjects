import java.util.Scanner;
public class TicketBoot{
    public static void main(String[]args){
        Scanner userInput = new Scanner(System.in);
        String[][] seats = new String[5][5];
        ProcessSeat process = new ProcessSeat(seats);

        boolean isRunning=true;

        do{
            System.out.println("\n== Welcome to the Cinema Seat Booking ==");
            System.out.println("1. Assign seat");
            System.out.println("2. View available seats");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = userInput.nextInt();
            userInput.nextLine();

            switch(choice){
                case 1 -> {
                    System.out.print("\nEnter name: ");
                    String name = userInput.nextLine().toUpperCase();
                    
                    System.out.print("Enter Row: ");
                    int row = userInput.nextInt();

                    System.out.print("Enter Column: ");
                    int column = userInput.nextInt();

                    seats[row][column] = name;
                    process = new ProcessSeat(seats);

                    System.out.println("\nYour seat has been processed!");
                    break;
                }

                case 2 -> process.displaySeats();
                case 3 -> {
                    System.out.println("Exiting...");
                    isRunning=false;
                }
            }
        }while(isRunning);
    }

    static class ProcessSeat{
        String[][] seats;

        ProcessSeat(String[][]seats){
            this.seats=seats;
        }

        void displaySeats(){
            for(String[] seat : seats){
                for(String person : seat){
                    if(person == null){
                        System.out.print("[Empty]");
                    }
                    else{
                        System.out.print("["+person+"]");
                    }
                }
                System.out.println();
            }
        }

    }
}