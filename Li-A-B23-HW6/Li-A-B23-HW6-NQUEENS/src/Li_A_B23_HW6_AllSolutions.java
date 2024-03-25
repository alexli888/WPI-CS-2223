import java.util.ArrayList;
import java.util.Scanner;

public class Li_A_B23_HW6_AllSolutions {

    private static boolean isSafeToPlaceQueen(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == row - i) {
                return false;
            }
        }
        return true;
    }

    private static int solveNQueens(int[] board, int row, int n, ArrayList<int[]> solutions) {
        if (row == n) {
            // Found a solution, store a copy of the board
            int[] solution = new int[n];
            System.arraycopy(board, 0, solution, 0, n);
            solutions.add(solution);
            return 1;
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (isSafeToPlaceQueen(board, row, i)) {
                board[row] = i;
                count += solveNQueens(board, row + 1, n, solutions);
            }
        }

        return count;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This is an continuation of the HW6. This class counts all solutions to the n-Queens problem with a user-inputted n");
        System.out.println(" ");
        System.out.print("Enter the value of n >= to 4. For answering the Question, put '14'\n(Please try(verify) the extra credit of n = 15 as well): ");
        int n = scanner.nextInt();

        if (n < 4) {
            System.out.println("Invalid Input!! Rerun and put a valid n value for the n-Queens.");
        } else {
            for (int i = 4; i <= n; i++) {
                ArrayList<int[]> solutions = new ArrayList<>();
                int count = solveNQueens(new int[i], 0, i, solutions);

                System.out.println("There are " + count + " solutions to the " + i + "-Queens Problem.");
            }
        }

        scanner.close();
    }
}
