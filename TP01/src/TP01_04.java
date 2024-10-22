import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PrimeInThread extends Thread {
    int start;
    int end;
    String threadName;
    List<Integer> primes;

    public PrimeInThread(int start, int end, String threadName) {
        this.start = start;
        this.end = end;
        this.threadName = threadName;
        this.primes = new ArrayList<>();
    }

    // Method to add primes to the list
    public void run() {
        for (int number = start; number <= end; number++) {
            if (checkPrime(number)) {
                primes.add(number);
                System.out.println(threadName + number);
            }
        }
    }

    // Method to check if a number is prime
    boolean checkPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Getter for the list of primes
    List<Integer> getPrimes() {
        return primes;
    }
}

public class TP01_04 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Get user input range
        System.out.print("Input start: ");
        int start = scan.nextInt();
        System.out.print("Input end: ");
        int end = scan.nextInt();
        scan.close();

        // Find the number of thread needed
        int range = end - start + 1;
        int maxNumbersPerThread = 100;
        int numberOfThreads = (int) Math.ceil((double) range / maxNumbersPerThread); // Use ceil to round the number up

        // Create array of threads with the number of threads
        PrimeInThread[] threads = new PrimeInThread[numberOfThreads];
        int threadIndex = 0;

        // Interate throw each threads and assign task to each threads
        for (int i = 0; i < numberOfThreads; i++) {
            int threadStart = start + (i * maxNumbersPerThread);
            int threadEnd = Math.min(threadStart + maxNumbersPerThread - 1, end);
            threads[i] = new PrimeInThread(threadStart, threadEnd, "t" + threadIndex + "-");
            threads[i].start();
            threadIndex++;
        }

        // Join each threads in the array
        for (PrimeInThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the prime numbers found
        int countPrimeNumber = 0;
        for (PrimeInThread thread : threads) {
            for (@SuppressWarnings("unused")
            int prime : thread.getPrimes()) {
                countPrimeNumber++;
            }
        }

        System.out.println("Number of prime numbers in the range: " + countPrimeNumber);

    }
}
