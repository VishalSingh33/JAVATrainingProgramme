import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> triangle = new ArrayList<>();
        if(numRows <= 0){
        return triangle;
        }

        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);
        for (int i = 1; i < numRows; i++){

        List<Integer> prevRow = triangle.get(i-1);
        List<Integer> currRow = new ArrayList<>();

        currRow.add(1);
        for(int j = 1; j < i; j++){
        System.out.println(prevRow.get(j - 1) );
        System.out.println(prevRow.get(j));
        currRow.add(prevRow.get(j - 1) + prevRow.get(j));
        }
        currRow.add(1);
        triangle.add(currRow);

        }
        return triangle;
    }
    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> triangle = generate(numRows);

        // Print the generated Pascal's Triangle
        for (List<Integer> row : triangle) {
            System.out.println(row);
        }
    }
}
