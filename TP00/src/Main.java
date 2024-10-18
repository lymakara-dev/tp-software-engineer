import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static final List<Reservation> reservations = new ArrayList<>();
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            int chooseFunction = 0;
            
            
            while(chooseFunction != 6){
                try {
                    listAllFunctions();
                    
                    // Get the input directly from the user
                    if (scan.hasNextInt()) {
                        chooseFunction = scan.nextInt();
                    } else {
                        scan.next();
                        throw new InputMismatchException("Invalid input: Please enter a valid number.");
                    }
                    
                    switch (chooseFunction) {
                        case 1 -> listAllReservations();
                        case 2 -> addNewReservation(scan);
                        case 3 -> cancelReservation(scan);
                        case 4 -> updateReservtion(scan);
                        case 5 -> swapRooms(scan);
                        case 6 -> System.out.println("System exiting...");
                        default -> throw new IllegalArgumentException("Invalid input: Must be a number between 1 to 6.");
                    }
                } catch (IllegalArgumentException | InputMismatchException e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
        }
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
        System.out.printf("Please choos a function: " + RESET);
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
        System.out.println("\nEnter reservaton details");
        System.out.printf("Enter room number (Format: A-301): ");
        scan.nextLine();
        String roomNumber = scan.nextLine();
        System.out.printf("Enter your name (Example: Ly Makara or Makara): ");
        String personName = scan.nextLine();
        System.out.printf("Enter start reservation date (dd-MM-yyyy HH:mm -> 10-11-2024 07:30): ");
        String startTime = scan.nextLine();
        System.out.printf("Enter end reservation date (dd-MM-yyyy HH:mm -> 10-11-2024 09:30):  ");
        String endTime = scan.nextLine();
        System.out.printf("Enter your remark (Optional):  ");
        String remark = scan.nextLine();

        try {
            Reservation newReservation = new Reservation(roomNumber, personName, startTime, endTime, remark);
            reservations.add(newReservation);
            System.out.println("New reservation added!");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private static void cancelReservation(Scanner scan){
        System.out.printf("\nEnter the room number of the reservation to cancel: ");
        scan.nextLine();
        String roomToCancel = scan.nextLine();
        Reservation toCancel = findReservationByRoom(roomToCancel);

        if(toCancel != null && toCancel.getStartTime().isAfter(LocalDateTime.now())){
            reservations.remove(toCancel);
            System.out.println("Reservaton canceled: " + toCancel);
        }else if(toCancel == null){
            System.out.println("Resernation not found!");
        }else{
            System.out.println("Cannot cancel reservation that has already started.");
        }
    }

    private static void updateReservtion(Scanner scan){
        System.out.printf("\nEnter the room number of reservation to update: ");
        scan.nextLine();
        String roomToUpdate = scan.nextLine();
        Reservation toUpdate = findReservationByRoom(roomToUpdate);

        if(toUpdate != null && toUpdate.getStartTime().isAfter(LocalDateTime.now())){
            System.out.printf("Enter your new name (Example: Ly Makara or Makara): ");
            String NewPersonName = scan.nextLine();
            System.out.printf("Enter new start reservation date (dd-MM-yyyy HH:mm -> 10-11-2024 07:30): ");
            String NewStartTime = scan.nextLine();
            System.out.printf("Enter new end reservation date (dd-MM-yyyy HH:mm -> 10-11-2024 09:30):  ");
            String NewEndTime = scan.nextLine();
            System.out.printf("Enter your new remark (Optional):  ");
            String NewRemark = scan.nextLine();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime NewStartTimeFormatted = LocalDateTime.parse(NewStartTime, formatter);
                LocalDateTime NewEndTimeFormatted = LocalDateTime.parse(NewEndTime, formatter);

                toUpdate.setPersonName(NewPersonName);
                toUpdate.setStartTime(NewStartTimeFormatted);
                toUpdate.setEndTime(NewEndTimeFormatted);
                toUpdate.setRemark(NewRemark);

                System.out.println("Reservation updated: " + toUpdate);
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }else if(toUpdate == null){
            System.out.println("Resernation not found!");
        }else{
            System.out.println("Cannot update reservation that has already started.");
        }
    }

    private static void swapRooms(Scanner scan){
        System.out.printf("\nEnter room number of reservation to swap (Format: A-301 B-301): ");
        scan.nextLine();
        String swapInput = scan.nextLine();
        String[] roomToSwap = swapInput.split(" ");

        System.out.println(swapInput);

        if(swapInput.length() == 2){
            Reservation reservation1 = findReservationByRoom(roomToSwap[0]);
            Reservation reservation2 = findReservationByRoom(roomToSwap[1]);
            
            if(reservation1 != null && reservation2 != null && reservation1.getStartTime().equals(reservation2.getStartTime())){
                String temp = reservation1.getroomNumber();
                reservation1.setRoomNumber(reservation2.getroomNumber());
                reservation2.setRoomNumber(temp);
                System.out.println("Swapped rooms between: " + reservation1 + " and " + reservation2);
            } else {
                System.out.println("Reservations not found or do not have the same start time.");
            }
        } else {
            System.out.println("Enter exactly two room number.");
        }
    }

    private static Reservation findReservationByRoom(String roomNumber){
        for(Reservation reservation : reservations){
            if(reservation.getroomNumber().equalsIgnoreCase(roomNumber)){
                return reservation;
            }
        }
        return null;
    }
}
