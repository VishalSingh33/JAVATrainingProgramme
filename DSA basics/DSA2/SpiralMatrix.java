public class SpiralMatrix {
    public static void main(String[] args) {

        int[][] result = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
        int n = 3;
        int top = 0, right = n - 1, left = 0, bottom = n - 1, count = 1;
        int resultMatrix[][] = new int[n][n];
       
        while (count <= n * n) {
            for (int i = left; i <= right; i++) {
                resultMatrix[top][i] = count;
                count++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {

                resultMatrix[i][right] = count;
                count++;
            }
            right--;
            for (int i = right; i >= left; i--) {

                resultMatrix[bottom][i] = count;
                count++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {

                resultMatrix[i][left] = count;
                count++;
            }
            left++;
        }
        // return resultMatrix;
        for(int i = 0; i < resultMatrix.length; i++){
            for(int j = 0; j < resultMatrix[i].length; j++ ){

                System.out.print(resultMatrix[i][j] + " ");

            }
        }
        
    }
}
