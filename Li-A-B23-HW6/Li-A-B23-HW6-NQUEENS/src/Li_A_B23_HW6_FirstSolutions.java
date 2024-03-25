import java.util.ArrayList;
import java.util.Scanner;

public class Li_A_B23_HW6_FirstSolutions {

    private static void printSolution(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.print((solution[i] + 1) + " ");  // indices to start from 1
        }
        System.out.println();
    }

    private static boolean isSafeToPlaceQueen(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == row - i) {
                return false;
            }
        }
        return true;
    }

    private static boolean solveNQueens(int[] board, int row, int n, ArrayList<int[]> solutions) {
        if (row == n) {
            // Found a solution, store a copy of the board
            int[] solution = new int[n];
            System.arraycopy(board, 0, solution, 0, n);
            solutions.add(solution);
            return true;
        }

        boolean res = false;
        for (int i = 0; i < n; i++) {
            if (isSafeToPlaceQueen(board, row, i)) {
                board[row] = i;
                res = solveNQueens(board, row + 1, n, solutions) || res;
                if (res) {
                    // once a solution is found, break loop to print the first lexicographical solution
                    break;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("By the Way, I am going for the Alternate Rubric. Thought i'll tell you to make grading easier. ");
        System.out.println("This class Prints the first Lexicographical solution for each n along the way ");
        System.out.println(" ");
        System.out.println("Enter the value of n (n>=4), Put '20' for Question Answer ");
        System.out.print("Also, Please try (verify) the Bonus points n values of n = 32 and n>= 36 as well if you want: ");

        int n = scanner.nextInt();

        if (n < 4) {
            System.out.println("Invalid Input!! Rerun and put a valid n value for the n-Queens.");
        } else {
            for (int i = 4; i <= n; i++) {
                ArrayList<int[]> solutions = new ArrayList<>();
                solveNQueens(new int[i], 0, i, solutions);

                if (solutions.isEmpty()) {
                    System.out.println("No solutions for " + i + "-Queens.");
                } else {
                    System.out.println("First Lexicographical Solution for " + i + "-Queens:");
                    printSolution(solutions.get(0));
                    System.out.println();
                }
            }
        }
        System.out.println("Please Continue to the Next Class(Li_A_B23_HW6_AllSolutions) for Part 2 of the Alternate Rubric.");
        scanner.close();
    }
}
