import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class Li_A_B23_Subirach {

    public static void main(String[] args) { // Initialize Subrichs Square as Matrix
        int[][] matrix = {
                {1, 14, 14, 4},
                {11, 7, 6, 9},
                {8, 10, 10, 5},
                {13, 2, 3, 15}
        };

        List<Integer> allElements = new ArrayList<>();
        for (int[] row : matrix) {
            for (int element : row) {
                allElements.add(element);
            }
        }

        // Initialize lists to store combinations with the same sum as rows, columns, and diagonals
        List<List<Integer>> rowCombinations = new ArrayList<>();
        List<List<Integer>> columnCombinations = new ArrayList<>();
        List<List<Integer>> diagonalCombinations = new ArrayList<>();

        Li_A_B23_CombosMthds comboMthds = new Li_A_B23_CombosMthds();// setting instance of Li_A_B23_CombosMthds class

        for (int i = 0; i < 4; i++) {
            // Rows
            List<Integer> rowElements = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                rowElements.add(matrix[i][j]);
            }
            rowCombinations.addAll(Li_A_B23_CombosMthds.findCombinationsWithSum(rowElements.get(0), allElements, 4));
            // Columns
            List<Integer> columnElements = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                columnElements.add(matrix[j][i]);
            }
            columnCombinations.addAll(Li_A_B23_CombosMthds.findCombinationsWithSum(columnElements.get(0), allElements, 4));
        }

        // Diagonals
        List<Integer> diagonal1Elements = new ArrayList<>();
        List<Integer> diagonal2Elements = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            diagonal1Elements.add(matrix[i][i]);
            diagonal2Elements.add(matrix[i][3 - i]);
        }
        diagonalCombinations.addAll(Li_A_B23_CombosMthds.findCombinationsWithSum(diagonal1Elements.get(0), allElements, 4));
        diagonalCombinations.addAll(Li_A_B23_CombosMthds.findCombinationsWithSum(diagonal2Elements.get(0), allElements, 4));

        int totalCombinations = rowCombinations.size() + columnCombinations.size() + diagonalCombinations.size();

        // Number of ways each possible sum can be formed
        Map<Integer, Integer> sumCombinationsCount = new HashMap<>();
        sumCombinationsCount.put(0, 1); // Initialize 0 as a possible sum
        for (int element : allElements) {
            for (int i = 1; i <= allElements.size(); i++) {
                List<List<Integer>> combinations = comboMthds.findCombinationsWithSum(element, allElements, i);
                for (List<Integer> combination : combinations) {
                    int sum = combination.stream().mapToInt(Integer::intValue).sum();
                    sumCombinationsCount.put(sum, sumCombinationsCount.getOrDefault(sum, 0) + 1);
                }
            }
        }

        // Calculate the largest possible sum created by summing every cell of the square
        int largestPossibleSum = matrix[0][0] + matrix[0][1] + matrix[0][2] + matrix[0][3] +
                matrix[1][0] + matrix[1][1] + matrix[1][2] + matrix[1][3] +
                matrix[2][0] + matrix[2][1] + matrix[2][2] + matrix[2][3] +
                matrix[3][0] + matrix[3][1] + matrix[3][2] + matrix[3][3];


        // most common Subrich Sum mthd
        int mostCommonSum = sumCombinationsCount.entrySet().stream()
                .max(Entry.comparingByValue())
                .map(Entry::getKey)
                .orElse(0);

        // Count combinations with the largest sum
        List<List<Integer>> largestSumCombinations = Li_A_B23_CombosMthds.findCombinationsWithSum(largestPossibleSum, allElements, allElements.size());
        int count1 = Li_A_B23_Q3partAHelpFunction.countFourElementCombinations(matrix); // reference other class for first print statement

        System.out.println("Number of four-element combinations with the same sum as rows, columns, and diagonals: " + count1); //3A

        System.out.println("Total number of combinations(not exactly 4) with the same sum as rows, columns, and diagonals: " + totalCombinations); //3B

        System.out.println("Number of ways each possible sum can be formed: " + sumCombinationsCount); //3C

        System.out.println("Largest possible sum created by summing every cell of the square: " + largestPossibleSum);//3D

        System.out.println("Number of combinations with the largest sum: " + largestSumCombinations.size());//3D Cont

        System.out.println("Most common sum: " + mostCommonSum); // Was in Rubric, but not in Assignment Instructions, but put anyways

    }
}