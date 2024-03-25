import java.util.ArrayList;

public class MostPreciousPath {
    static int startingVaultColumn = 0;
    static int maxGemsCollectedColumn = 0;
    static ArrayList<Integer> gemsPath = new ArrayList<>();

    static int findMaxGemsPath(int[][] gemsMatrix) {
        int numRows = gemsMatrix.length;
        int numCols = gemsMatrix[0].length;
        int maxGemsResult = -1;

        // Find the starting column and initialize the result
        for (int i = 0; i < numCols; i++) {
            if (gemsMatrix[numRows - 1][i] > maxGemsResult) {
                startingVaultColumn = i;
                maxGemsResult = gemsMatrix[numRows - 1][i];
            }
        }

        // Find the maximum gems
        for (int i = numRows - 2; i >= 0; i--) {
            maxGemsResult = -1;
            for (int j = 0; j < numCols; j++) {

                // Update the matrix with the maximum gems
                if (j > 0 && j < numCols - 1)
                    gemsMatrix[i][j] += Math.max(gemsMatrix[i + 1][j], Math.max(gemsMatrix[i + 1][j - 1], gemsMatrix[i + 1][j + 1]));
                else if (j > 0)
                    gemsMatrix[i][j] += Math.max(gemsMatrix[i + 1][j], gemsMatrix[i + 1][j - 1]);
                else if (j < numCols - 1)
                    gemsMatrix[i][j] += Math.max(gemsMatrix[i + 1][j], gemsMatrix[i + 1][j + 1]);

                // Update result and the column where max gems are collected
                if (gemsMatrix[i][j] > maxGemsResult) {
                    maxGemsResult = Math.max(gemsMatrix[i][j], maxGemsResult);
                    maxGemsCollectedColumn = j;
                }
            }
        }
        return maxGemsResult;
    }

    public static void buildGemsPath(int[][] gemsMatrix, int index) {
        int numRows = gemsMatrix.length;
        gemsPath.add(gemsMatrix[0][index]);   // Add the gem in the bottom row

        for (int i = 0; i < numRows - 1; i++) {
            int leftIndex = Math.max(0, index - 1);

            int rightIndex = Math.min(gemsMatrix[0].length - 1, index + 1);

            int maxElement = Math.max(gemsMatrix[i + 1][leftIndex], Math.max(gemsMatrix[i + 1][index], gemsMatrix[i + 1][rightIndex]));

            if (gemsMatrix[i + 1][leftIndex] == maxElement){
                index = leftIndex;
            }
            else if (gemsMatrix[i + 1][rightIndex] == maxElement){
                index = rightIndex;
            }

            gemsPath.add(0, gemsMatrix[i + 1][index]);
        }
    }

    public static void main(String[] args) {
        int[][] gemsMatrix = {                                // given matrix
                {35, 89, 52, 66, 82, 20, 95, 21},
                {79, 5, 14, 23, 78, 37, 40, 74},
                {32, 59, 17, 25, 31, 4, 16, 63},
                {91, 11, 77, 48, 13, 71, 92, 15},
                {56, 70, 47, 64, 22, 88, 67, 12},
                {83, 97, 94, 27, 65, 51, 30, 7},
                {10, 41, 1, 86, 46, 24, 53, 93},
                {96, 33, 44, 98, 75, 68, 99, 84}
        };

        int[][] gemsMatrixCopy = {
                {35, 89, 52, 66, 82, 20, 95, 21}, // a copy of the given matrix for path building
                {79, 5, 14, 23, 78, 37, 40, 74},
                {32, 59, 17, 25, 31, 4, 16, 63},
                {91, 11, 77, 48, 13, 71, 92, 15},
                {56, 70, 47, 64, 22, 88, 67, 12},
                {83, 97, 94, 27, 65, 51, 30, 7},
                {10, 41, 1, 86, 46, 24, 53, 93},
                {96, 33, 44, 98, 75, 68, 99, 84}
        };

        int numRows = gemsMatrix.length;
        int numCols = gemsMatrix[0].length;

        // Find the maximum gems and build the path
        int maxGemsCollected = findMaxGemsPath(gemsMatrix);
        buildGemsPath(gemsMatrixCopy, maxGemsCollectedColumn);

        int firstGem = gemsPath.get(0);
        int vaultIndex = -1;
        for (int i = 0; i < numCols; i++) {
            if (gemsMatrixCopy[numRows - 1][i] == firstGem)
                vaultIndex = i;
        }

        System.out.println("Bilbo's starting square is Row 1 Vault " + (vaultIndex + 1));   //A
        System.out.println("Bilbo's path that he followed was " + gemsPath); //B
        System.out.println("The Total number of gems collected on the way are " + maxGemsCollected + " gems." ); //C
        System.out.println("The number of the vault where the king has secreted the Arkenstone is Vault " + (maxGemsCollectedColumn + 1)); //D
    }

}








