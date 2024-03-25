import java.util.Scanner;

public class Li_A_B23_LucasNumbers {

    private static long[] memorizePrev; // declare type long array to put prev Lucas Nums

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter A value of n (number of Lucas numbers you want to generate): ");
        int n = scanner.nextInt();

        System.out.println("Lucas Numbers are below!!");
        memorizePrev = new long[n + 1];

        for (int i = 0; i <= n; i++) {
            long startTime = System.nanoTime();
            long lucasNumber = calculateLucas(i);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime; // teh elapsed time in nanoseconds

            double ratio = (i == 0) ? 0.0 : (double) lucasNumber / memorizePrev[i - 1];
            double successiveTime = (i == 0) ? 0.0 : (double) elapsedTime / memorizePrev[i - 1];

            System.out.println("L(" + i + ") = " + lucasNumber + ", Time: " + elapsedTime + " ns, Ratio of successive calculations: " + ratio + ", Successive calculation time: " + successiveTime);

            memorizePrev[i] = lucasNumber;
        }


        scanner.close();
    }

    // method to calculate Lucas numbers
    public static long calculateLucas(int n) {            // O(n) runtime
        if (n == 0) {
            return 2;
        } else if (n == 1) {
            return 1;
        } else if (memorizePrev[n] != 0) {
            return memorizePrev[n];
        } else {
            memorizePrev[n] = calculateLucas(n - 1) + calculateLucas(n - 2);

            return memorizePrev[n];
        }
    }
}
