import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KthSmallestElementInMatrix {
    public static void main(String[] args) {

        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 5, 9));
        matrix.add(Arrays.asList(10, 11, 13));
        matrix.add(Arrays.asList(12, 13, 15));
        int k = 8;

        ArrayList<Integer> flatMatrix = new ArrayList<>();
        // Flatten the matrix into a single list
        for (List<Integer> row : matrix) {
            flatMatrix.addAll(row);
        }
        Collections.sort(flatMatrix);
        System.out.println(flatMatrix);
        // Return the kth smallest element
        // return flatMatrix.get(k - 1);
        System.out.println(flatMatrix.get(k - 1));
        System.out.println(flatMatrix.get(flatMatrix.size()-k));

    }
}
