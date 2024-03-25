import java.util.ArrayList;
import java.util.List;
public class Li_A_B23_CombosMthds {
    public static List<List<Integer>> findCombinationsWithSum(int targetSum, List<Integer> elements, int n) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinationsWithSumHelper(targetSum, 0, new ArrayList<>(), result, elements, n);
        return result;
    }

    private static void findCombinationsWithSumHelper(int targetSum, int start, List<Integer> current, List<List<Integer>> result, List<Integer> elements, int n) {
        if (targetSum == 0) {
            if (current.size() <= n) {
                result.add(new ArrayList<>(current));
            }
        }
        if (targetSum < 0 || current.size() >= n || start >= elements.size()) {
            return;
        }

        for (int i = start; i < elements.size(); i++) {
            current.add(elements.get(i));
            findCombinationsWithSumHelper(targetSum - elements.get(i), i + 1, current, result, elements, n);
            current.remove(current.size() - 1);
        }
    }


}
