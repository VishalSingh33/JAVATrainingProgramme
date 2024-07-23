import java.util.ArrayList;
import java.util.List;

// Print all ways of arranging n queens on an n x n chess board so that none of them share the same row, 
// column or diagonal. In this case, "diagonal" means all diagonals, not just the two that bisects the 
// board or passes through the center.

public class NQueens {
    public String nQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        solveNQueens(result, board, 0, n);
        if (result.isEmpty()) {
            return "No Solution Exists";
        }
        StringBuilder sb = new StringBuilder();
        for (List<String> solution : result) {
            for (String row : solution) {
                sb.append(row).append('\n');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private void solveNQueens(List<List<String>> result, char[][] board, int row, int n) {
        if (row == n) {
            result.add(constructSolution(board, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q'; // Place queen
                solveNQueens(result, board, row + 1, n); // Recursive call
                board[row][col] = '.'; // Backtrack (remove queen)
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col, int n) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private List<String> constructSolution(char[][] board, int n) {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j] == 'Q' ? '1' : '0');
            }
            solution.add(sb.toString());
        }
        return solution;
    }

    public static void main(String[] args) {
        NQueens nQueensSolver = new NQueens();
        int n = 4; // Example: 2 queens on 2x2 chessboard
        String solutions = nQueensSolver.nQueens(n);
        System.out.println(solutions);
    }
}
