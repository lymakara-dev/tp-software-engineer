import java.util.Scanner;
import java.util.Random;

public class TP01_05 {
    static int correctCount = 0;
    static int incorrectCount = 0;
    static int questionCount = 0;
    static int maxQuestions = 10;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (questionCount < maxQuestions) {
            int randomNumber1 = random.nextInt(9) + 1; // Random number between 1 and 9
            int randomNumber2 = random.nextInt(9) + 1;

            System.out.print(randomNumber1 + " + " + randomNumber2 + " = ");
            boolean[] inputReceived = { false };
            int[] userAnswer = { 0 };

            // Thread for handle user input
            Thread inputThread = new Thread(() -> {
                if (scanner.hasNextInt()) {
                    userAnswer[0] = scanner.nextInt();
                    inputReceived[0] = true;
                } else {
                    scanner.next(); // Clear invalid input
                    inputReceived[0] = false;
                }
            });

            inputThread.start();
            inputThread.join(2000);

            // Check if the user answered in time
            if (!inputReceived[0]) {
                System.out.println("\nTime is up! You missed the answer.");
                break; // Exit the loop if time expired
            }

            if (userAnswer[0] == randomNumber1 + randomNumber2) {
                correctCount++;
            } else {
                incorrectCount++;
            }
            questionCount++;
        }

        // Print results1
        System.out.println("\nResult: correct = " + correctCount + ", incorrect = " + incorrectCount);
        if (correctCount >= 5) {
            System.out.println("Good job!");
        } else if (correctCount >= 3) {
            System.out.println("Not bad!");
        } else {
            System.out.println("You might want to practice more!");
        }

        scanner.close();
    }
}
