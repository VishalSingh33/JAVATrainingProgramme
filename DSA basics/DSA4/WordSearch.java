// Given a 2D board and a word, find if the word exists on the board.

// The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells
// are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.isEmpty()) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (search(board, word, i, j, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true; // Found the entire word
        }

        int m = board.length;
        int n = board[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || board[i][j] != word.charAt(index)) {
            return false; // Out of bounds or already visited or mismatched character
        }

        visited[i][j] = true; // Mark current cell as visited

        // Explore adjacent cells recursively
        boolean found = search(board, word, i + 1, j, index + 1, visited) ||
                search(board, word, i - 1, j, index + 1, visited) ||
                search(board, word, i, j + 1, index + 1, visited) ||
                search(board, word, i, j - 1, index + 1, visited);

        visited[i][j] = false; // Backtrack (mark current cell as unvisited)

        return found;
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        char[][] board = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";

        System.out.println("Word 'ABCCED' exists: " + wordSearch.exist(board, word1));
        System.out.println("Word 'SEE' exists: " + wordSearch.exist(board, word2));
        System.out.println("Word 'ABCB' exists: " + wordSearch.exist(board, word3));
    }
}
