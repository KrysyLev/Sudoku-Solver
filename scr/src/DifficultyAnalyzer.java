public class DifficultyAnalyzer {
    private int gridSize;

    public DifficultyAnalyzer(int gridSize) {
        this.gridSize = gridSize;
    }

    public String analyzeDifficulty(int[][] board) {
        int emptyCells = countEmptyCells(board);
        if (emptyCells < (gridSize * gridSize) * 0.25) {
            return "Easy";
        } else if (emptyCells < (gridSize * gridSize) * 0.5) {
            return "Medium";
        } else {
            return "Hard";
        }
    }

    private int countEmptyCells(int[][] board) {
        int count = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (board[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
