import java.util.ArrayList;
import java.util.List;

public class AlgoRhythmics {

    public static void main(String[] args) {
        int order = 5;

        List<String> grayCode = brgc(order);
        List<List<String>> playerSequences = generatePlayerSequences(grayCode);

        // Print the table
        System.out.println("Index\tGray Code\tPlayers\t\tAction");

        for (int i = 0; i < grayCode.size(); i++) {
            System.out.println(i + "\t" + grayCode.get(i) + "\t\t" + playerSequences.get(i) + "\t\t"
                    + getActionString(playerSequences, i));
        }
    }

    public static List<String> brgc(int n) {
        List<String> result = new ArrayList<>();
        if (n == 1) {
            result.add("0");
            result.add("1");
        } else {
            List<String> l1 = brgc(n - 1);
            List<String> l2 = new ArrayList<>(l1);
            reverseList(l2);

            for (int i = 0; i < l1.size(); i++) {
                l1.set(i, "0" + l1.get(i));
                l2.set(i, "1" + l2.get(i));
            }

            l1.addAll(l2);
            result.addAll(l1);
        }
        return result;
    }

    private static void reverseList(List<String> list) {
        int start = 0;
        int end = list.size() - 1;

        while (start < end) {
            String temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }

    public static List<List<String>> generatePlayerSequences(List<String> grayCode) {
        List<List<String>> playerSequences = new ArrayList<>();

        for (int i = 0; i < grayCode.size(); i++) {
            List<String> sequence = new ArrayList<>();
            for (int j = 0; j < grayCode.get(i).length(); j++) {
                if (grayCode.get(i).charAt(j) == '1') {
                    sequence.add(getMusician(j));
                }
            }
            playerSequences.add(sequence);
        }

        return playerSequences;
    }

    private static String getActionString(List<List<String>> playerSequences, int currentIndex) {
        if (currentIndex == 0) {
            return "Silent Stage";
        }

        List<String> currentMusicians = playerSequences.get(currentIndex);
        List<String> previousMusicians = playerSequences.get(currentIndex - 1);

        StringBuilder actionString = new StringBuilder();

        for (String musician : previousMusicians) {
            if (!currentMusicians.contains(musician)) {
                actionString.append(musician).append(" Fades, ");
            }
        }

        for (String musician : currentMusicians) {
            if (!previousMusicians.contains(musician)) {
                actionString.append(musician).append(" Joins, ");
            }
        }

        if (actionString.length() > 0) {
            return actionString.substring(0, actionString.length() - 2);
        } else {
            return "No Action";
        }
    }

    private static String getMusician(int index) {
        String[] musicians = { "Alfie", "Berty", "Crizz", "Dietz", "Elmer" };
        return musicians[index];
    }
}
