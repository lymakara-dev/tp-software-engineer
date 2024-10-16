import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static List<Reservation> reservations = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int chooseFunction = 0;

        while(chooseFunction != 6){
            listAllFunctions();
        
            chooseFunction = scan.nextInt();
            switch (chooseFunction) {
                case 1:
                    listAllReservations();
                break;
                case 2:
                    addNewReservation(scan);
                    break;
                case 3:
                    System.out.println("3. Cancel/Remove reservation");
                    break;
                case 4:
                    System.out.println("4. Update reservation (IF NOT START!)");
                    break;
                case 5:
                    System.out.println("5. Swap room");
                    break;
                case 6:
                    System.out.println("System exiting...");
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }

        scan.close();
    }

    private static void listAllFunctions() {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        
        System.out.println(GREEN + "=== ROOM RESERVATION ===");
        System.out.println("1. List all reservations");
        System.out.println("2. Add new reservation");
        System.out.println("3. Cancel/Remove reservation");
        System.out.println("4. Update reservation (IF NOT START!)");
        System.out.println("5. Swap room");
        System.out.println("6. Exit");
        System.out.printf("Please choos a function:" + RESET);
    }

    private static void listAllReservations(){
        if(reservations.isEmpty()){
            System.out.println("No reservations found!");
        }else {
            for(Reservation reservation : reservations){
                System.out.println(reservation);
            }
        }
    }

    private static void addNewReservation(Scanner scan){
        System.out.println("=== Add new reservation ===");
        System.out.printf("Enter room number (format: A-301): ");
        String roomNumber = scan.nextLine();
        scan.nextLine();
        System.out.printf("Enter your name: ");
        String personName = scan.nextLine();
        System.out.printf("Enter start reservation date (dd-MM-yyyy HH:mm): ");
        String startTime = scan.nextLine();
        System.out.printf("Enter end reservation date (dd-MM-yyyy HH:mm):  ");
        String endTime = scan.nextLine();
        System.out.printf("Enter your remark:  ");
        String remark = scan.nextLine();

        try {
            Reservation newReservation = new Reservation(roomNumber, personName, startTime, endTime, remark);
            reservations.add(newReservation);
            System.out.println("New reservation added!");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
