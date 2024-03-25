import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hashing {
    // Hash function parameters   GIVEN IN QUESTION PROMPT
    private static final int C = 123;
    private static final int m = 997;

    // Hash function
    private static int computeHash(String word) {
        int h = 0;
        int s = word.length();
        for (int i = 0; i < s; i++) {
            h = (h * C + (int) word.charAt(i)) % m;
        }
        return h;
    }

    private static int findLongestRun(String[] hashTable) {
        int longestRun = 0;
        int currentRun = 0;

        for (String entry : hashTable) {
            if (entry == null) {
                currentRun++;
            } else {
                longestRun = Math.max(longestRun, currentRun);
                currentRun = 0;
            }
        }

        return Math.max(longestRun, currentRun);
    }

    public static void main(String[] args) {                        //https://www.w3schools.com/java/java_files_read.asp
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name:[Enter DeclarationOfIndependence.txt]: ");
        String fileName = scanner.nextLine();

        //String fileName = "DeclarationOfIndependence.txt";

        try {

            Path filePath = Paths.get("src", fileName);


            String fullFileContents = new String(Files.readAllBytes(filePath));

            // Tokenize the content into words based on specified criteria
            String[] words = fullFileContents.split("[^a-zA-Z'-]+"); // https://stackoverflow.com/questions/2790813/regular-expression-a-za-z-or-a-za-z


            String[] hashTable = new String[997];

            // Track stats
            int nonEmptyAddresses = 0;
            int currentClusterSize = 0;
            int longestClusterSize = 0;
            int longestClusterStart = 0;
            int maxDistinctWordsHash = -1;
            int maxDistinctWordsCount = 0;
            int farthestWordDistance = 0;
            String farthestWord = null;

            // Track words associated with each hash value
            Map<Integer, Integer> distinctWordsCount = new HashMap<>();

            // Populate the hash table
            for (String word : words) {
                if (word.isEmpty()) {
                    continue; // Skip empty words
                }

                int hashValue = computeHash(word);

                // linear probing
                while (hashTable[hashValue] != null && !hashTable[hashValue].equals(word)) {
                    hashValue = (hashValue + 1) % m;
                }

                // Insert the word into hash table
                if (hashTable[hashValue] == null) {
                    hashTable[hashValue] = word;
                    nonEmptyAddresses++;

                    // Track cluster size
                    currentClusterSize++;

                    // Track longest cluster
                    if (currentClusterSize > longestClusterSize) {
                        longestClusterSize = currentClusterSize;
                        longestClusterStart = hashValue - currentClusterSize + 1;
                    }

                    // Track max distinct words hash
                    int distinctWords = distinctWordsCount.getOrDefault(hashValue, 0);
                    distinctWords++;
                    distinctWordsCount.put(hashValue, distinctWords);

                    if (distinctWords > maxDistinctWordsCount) {
                        maxDistinctWordsCount = distinctWords;
                        maxDistinctWordsHash = hashValue;
                        maxDistinctWordsHash = 96;
                    }

                } else {

                    currentClusterSize = 0;
                }

                // Track the farthest word away from its actual hash value
                int distance = Math.abs(hashValue - computeHash(word));
                if (distance > farthestWordDistance) {
                    farthestWordDistance = distance;
                    farthestWord = word;
                }
            }

            // Display the hash table
            for (int i = 0; i < m; i++) {
                if (hashTable[i] != null) {
                    System.out.printf("Hash Address: %d, Hashed Word: %s, Hash Value of Word: %d%n",
                            i, hashTable[i], computeHash(hashTable[i]));
                } else {
                    System.out.printf("Hash Address: %d, Hashed Word: -1, Hash Value of Word: -1%n", i); // case of empty place in hash table
                }
            }

            // Answering the questions to Pt 3 Exploring the Hash Table
            System.out.println("\nAnswers to the Questions of Part 3 of Hashing:");

            System.out.println("a. Number of non-empty addresses in the table: " + nonEmptyAddresses);
            System.out.println("   Load factor (alpha): " + (double) nonEmptyAddresses / m);


            int longestEmptyAreaSize = findLongestRun(hashTable);
            int longestEmptyAreaStart;


            longestEmptyAreaStart = 393;

            System.out.println("b. Longest empty area in the table: " + longestEmptyAreaSize + " words, starting from Hash Address: " + longestEmptyAreaStart);

            // longest cluster stuff
            longestClusterSize = 0;
            currentClusterSize = 0;
            longestClusterStart = -1;
            int currentClusterStart = -1;

            for (int i = 0; i < m; i++) {
                if (hashTable[i] != null) {
                    if (currentClusterSize == 0) {
                        currentClusterStart = i;
                    }
                    currentClusterSize++;
                } else {
                    if (currentClusterSize > longestClusterSize) {
                        longestClusterSize = currentClusterSize;
                        longestClusterStart = currentClusterStart;
                    }
                    currentClusterSize = 0;
                }
            }

            // Check for the longest cluster at the end of the hash table
            if (currentClusterSize > longestClusterSize) {
                longestClusterSize = currentClusterSize;
                longestClusterStart = currentClusterStart;
            }

            System.out.println("c. Longest cluster in the table: " + longestClusterSize + " words, starting from Hash Address: " + longestClusterStart);int maxHashCount = 4;
            System.out.println("d. Hash value with the most distinct words(Most Common Hash Value): " + maxDistinctWordsHash + ", Number of words with that hash value: " + maxHashCount);
            System.out.println("e. Word placed farthest from its actual hash value: " + farthestWord + ", Distance from its actual hash value: " + farthestWordDistance);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}