// This class exists since in order to add to the program, we needed to change the code such that it affected this
// result of 3A, which is why I have a separate class for that specific question.
public class Li_A_B23_Q3partAHelpFunction {
    public static int countFourElementCombinations(int[][] matrix) {
        // Calculate the sum of the main diagonal
        int mainDiagonalSum = 0;
        for (int i = 0; i < 4; i++) {
            mainDiagonalSum += matrix[i][i];
        }

        // Calculate the sum of the other diagonal
        int otherDiagonalSum = 0;
        for (int i = 0; i < 4; i++) {
            otherDiagonalSum += matrix[i][3 - i];
        }

        // Count the number of four-element combinations with the same sum as rows, columns, and diagonals
        int count = 0;

        // Rows
        for (int i = 0; i < 4; i++) {
            if (matrix[i][0] + matrix[i][1] + matrix[i][2] + matrix[i][3] == mainDiagonalSum) {
                count++;
            }
        }

        // Columns
        for (int i = 0; i < 4; i++) {
            if (matrix[0][i] + matrix[1][i] + matrix[2][i] + matrix[3][i] == mainDiagonalSum) {
                count++;
            }
        }

        // Diagonals
        if (mainDiagonalSum == otherDiagonalSum) {
            count++;
        }

        return count;
    }
}
