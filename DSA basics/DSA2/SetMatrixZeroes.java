public class SetMatrixZeroes {
    public static void main(String[] args) {

        int[][] matrix = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
        
        for(int i = 0 ; i < matrix.length ; ++i) {
            for(int j = 0 ; j < matrix[i].length ; ++j) {
                System.out.print(matrix[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
