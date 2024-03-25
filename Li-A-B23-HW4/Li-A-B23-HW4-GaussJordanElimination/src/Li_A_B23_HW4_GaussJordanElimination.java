public class Li_A_B23_HW4_GaussJordanElimination {

    private static double[] gaussJordanElimination(double[][] A, int n) {
        for (int i = 0; i < n; i++) {
            A[i][n] = A[i][n];
        }

        for (int i = 0; i < n; i++) {
            int pivotRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A[j][i]) > Math.abs(A[pivotRow][i])) {
                    pivotRow = j;
                }
            }

            swap2Rows(A, i, pivotRow);

            double pivotElement = A[i][i];

            // Divide the pivot row by the pivot element
            for (int k = i; k <= n; k++) {
                A[i][k] /= pivotElement;
            }

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = A[j][i];
                    for (int k = i; k <= n; k++) {
                        A[j][k] -= A[i][k] * factor;
                    }
                }
            }
        }

        System.out.println("\nFinal Augmented Matrix:  This Should be the Identity Matrix(With 1's on Diagonal)");
        printMatrix(A, n);

        double[] solution = new double[n];
        for (int i = 0; i < n; i++) {
            solution[i] = A[i][n];
        }

        return solution;
    }

    private static void swap2Rows(double[][] A, int row1, int row2) {
        double[] temp = A[row1];
        A[row1] = A[row2];
        A[row2] = temp;
    }

    private static void printMatrix(double[][] A, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%.0f\t", A[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] A = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 122},
                {1, 1, 1, 1, 1, -1, -1, -1, -1, -88},
                {1, -1, 1, -1, 1, -1, 1, -1, 1, 32},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 18},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 76},
                {1, 1, -1, 1, 1, -1, 1, 1, -1, 0},
                {9, -8, 7, -6, 5, -4, 3, -2, 1, 41}
        };

        int n = 9;
        System.out.println("Original Augmented Matrix:  Please Check if the Matrix is What is Intended to Be");
        printMatrix(A, n);
        double[] solution = gaussJordanElimination(A, n);

        if (solution != null) {
            System.out.println("\nSolution:");
            for (int i = 0; i < n; i++) {
                // Round up the solution to 2 decimal places
                int roundedSolution = (int) Math.round(solution[i]);
                System.out.println("x_" + (i + 1) + " = " + roundedSolution);

            }
        } else {
            System.out.println("\nOH NO...... no unique solution exists.");
        }
    }
}
