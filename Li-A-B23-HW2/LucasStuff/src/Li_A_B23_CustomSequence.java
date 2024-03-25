import java.util.Scanner;

public class Li_A_B23_CustomSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of prime numbers in the custom sequence: ");
        int n = scanner.nextInt();

        System.out.println("Your Prime Number Sequence:");
        int currentNumber = 2;  // Start with 1st prime number
        int count = 0;
        double prevPrime = 2;  // Initialize prev prime number

        while (count < n) {
            if (isPrime(currentNumber)) {
                double ratio = (count == 0) ? 0.0 : (double) currentNumber / prevPrime;
                System.out.println("P(" + count + ") = " + currentNumber + ", Ratio of successive calculations: " + ratio);
                prevPrime = currentNumber;
                count++;
            }
            currentNumber++;
        }

        scanner.close();
    }

    // method to calculate prime numbers
    public static boolean isPrime(int num) {  // O(sqrtn) runtime?
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;

    }
}
