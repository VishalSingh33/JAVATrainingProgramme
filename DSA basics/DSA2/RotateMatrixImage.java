public class RotateMatrixImage {
    public static void main(String[] args) {

        int[][] matrix = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
        
        int newMatrix[][] = new int[matrix.length][matrix.length];

        for(int i=0; i < matrix.length; i++){
              for(int j = 0; j < matrix[i].length; j++) {
                newMatrix[i][j] = matrix[matrix.length - j - 1][i];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }
}
