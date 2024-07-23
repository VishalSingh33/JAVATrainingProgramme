// Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
// Each row must contain the digits 1-9 without repetition.
// Each column must contain the digits 1-9 without repetition.
// Each of the nine 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
// The Sudoku board could be partially filled, where empty cells are filled with the character '.'

public class ValidSudoku {
    public boolean validSudoku(char[][] board) {
        // Check rows and columns
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(board, i) || !isValidColumn(board, i)) {
                return false;
            }
        }

        // Check 3x3 sub-boxes
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!isValidSubBox(board, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidRow(char[][] board, int row) {
        boolean[] visited = new boolean[9];
        for (int i = 0; i < 9; i++) {
            char digit = board[row][i];
            if (digit != '.' && visited[digit - '1']) {
                return false; // Duplicate digit found
            }
            if (digit != '.') {
                visited[digit - '1'] = true;
            }
        }
        return true;
    }

    private boolean isValidColumn(char[][] board, int col) {
        boolean[] visited = new boolean[9];
        for (int i = 0; i < 9; i++) {
            char digit = board[i][col];
            if (digit != '.' && visited[digit - '1']) {
                return false; // Duplicate digit found
            }
            if (digit != '.') {
                visited[digit - '1'] = true;
            }
        }
        return true;
    }

    private boolean isValidSubBox(char[][] board, int startRow, int startCol) {
        boolean[] visited = new boolean[9];
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                char digit = board[i][j];
                if (digit != '.' && visited[digit - '1']) {
                    return false; // Duplicate digit found
                }
                if (digit != '.') {
                    visited[digit - '1'] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidSudoku sudoku = new ValidSudoku();
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("Is the Sudoku board valid? " + sudoku.validSudoku(board));
    }
}
