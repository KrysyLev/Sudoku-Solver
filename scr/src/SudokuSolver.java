import java.util.concurrent.*;
import java.util.*;

public class SudokuSolver {
    private int gridSize;
    private int boxSize;

    public SudokuSolver(int gridSize) {
        this.gridSize = gridSize;
        this.boxSize = (int) Math.sqrt(gridSize);
    }

    public boolean solveSudoku(int[][] board) {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        return solve(board, executor);
    }

    private boolean solve(int[][] board, ExecutorService executor) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        if (isEmpty) {
            executor.shutdown();
            return true;
        }

        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (int num = 1; num <= gridSize; num++) {
            int finalRow = row;
            int finalCol = col;
            int finalNum = num;
            tasks.add(() -> {
                if (isSafe(board, finalRow, finalCol, finalNum)) {
                    board[finalRow][finalCol] = finalNum;
                    if (solve(board, executor)) {
                        return true;
                    }
                    board[finalRow][finalCol] = 0;
                }
                return false;
            });
        }

        try {
            List<Future<Boolean>> results = executor.invokeAll(tasks);
            for (Future<Boolean> result : results) {
                if (result.get()) {
                    executor.shutdown();
                    return true;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        return false;
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        return !isInRow(board, row, num) && !isInCol(board, col, num) && !isInBox(board, row - row % boxSize, col - col % boxSize, num);
    }

    private boolean isInRow(int[][] board, int row, int num) {
        for (int col = 0; col < gridSize; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInCol(int[][] board, int col, int num) {
        for (int row = 0; row < gridSize; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBox(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < boxSize; row++) {
            for (int col = 0; col < boxSize; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }
}
