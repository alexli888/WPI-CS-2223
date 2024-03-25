import java.util.Scanner;

public class Li_A_B23_palindromecheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sequence of strings: ");
        String input = scanner.nextLine();
        scanner.close();

        if (isPalindrome(input)) {
            System.out.println("This is a palindrome!");
        } else {
            System.out.println("This is not a palindrome.");
        }
    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) { // base case
            return true;
        }
        // set pointers at start and end of the input
        int i = 0;
        int j = s.length() - 1;

        while (i <= j) {
            char currFirst = s.charAt(i);
            char currLast = s.charAt(j);

            if (!Character.isLetterOrDigit(currFirst)) {
                i++;
            } else if (!Character.isLetterOrDigit(currLast)) {
                j--;
            } else {
                if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
                    return false;
                }
                i++;
                j--;
            }
        }

        return true;
    }
}