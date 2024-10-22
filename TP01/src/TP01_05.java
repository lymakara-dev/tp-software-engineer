import java.util.Scanner;

public class TP01_05 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int randomNumber1 = (int) (Math.random() * 10); // Generates a random number between 0 (inclusive) and 10
        int randomNumber2 = (int) (Math.random() * 10); // Generates a random number between 0 (inclusive) and 10

        System.out.printf(randomNumber1 + " + " + randomNumber2 + " = ");
        int userInputResult = scanner.nextInt();

        // Check result
        if (userInputResult - randomNumber2 == randomNumber1) {
            System.out.println("\nYou are correct!");
        } else {
            System.out.println("Grade one student is better than you!");
        }

        scanner.close();
    }
}
