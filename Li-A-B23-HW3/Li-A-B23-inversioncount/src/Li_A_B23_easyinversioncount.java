import java.util.Scanner;

public class Li_A_B23_easyinversioncount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the length of the array(default is 8): ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Please enter the elements of the array(add a space after each element): ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        int inversionCount = countInversions(arr);
        System.out.println("The number of inversions: " + inversionCount);
    }

    public static int countInversions(int[] arr) {
        int inversionCount = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    inversionCount++;
                }
            }
        }

        return inversionCount;
    }

}