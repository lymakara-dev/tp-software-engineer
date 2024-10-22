import java.util.Scanner;

public class TP01_03 extends Thread {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter number of thread: ");
        int numberOfThread = scan.nextInt();

        for (int i = 0; i < numberOfThread; i++) {
            int index = i + 1;
            Thread thread = new Thread(() -> {
                System.out.println("Sub Task: " + index);
            });

            thread.start();
            thread.join();
        }

        scan.close();
    }
}
