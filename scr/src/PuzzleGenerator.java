import java.util.*;

public class PuzzleGenerator {
    private int gridSize;
    private Random random;

    public PuzzleGenerator(int gridSize) {
        this.gridSize = gridSize;
        this.random = new Random();
    }

    public int[][] generatePuzzle() {
        int[][] board = new int[gridSize][gridSize];
        SudokuSolver solver = new SudokuSolver(gridSize);
        solver.solveSudoku(board);
        removeNumbers(board);
        return board;
    }

    private void removeNumbers(int[][] board) {
        int cellsToRemove = (int) (gridSize * gridSize * 0.5); // Remove 50% of cells
        while (cellsToRemove > 0) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                cellsToRemove--;
            }
        }
    }

    public boolean hasUniqueSolution(int[][] board) {
        int[][] copy = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, gridSize);
        }
        return new SudokuSolver(gridSize).solveSudoku(copy);
    }
}
