package Sudoku;

import java.util.Random;

public class SudokuGenerator {
    private static final int GRID_SIZE = 9;

    public SudokuBoard generateRandomSudokuPuzzle(int cellsToRemove) {
        SudokuBoard board = generateFullSudoku();
        Random random = new Random();
        while (cellsToRemove > 0) {
            int row = random.nextInt(GRID_SIZE);
            int col = random.nextInt(GRID_SIZE);
            if (board.getCell(row, col) != 0) {
                board.setCell(row, col, 0);
                cellsToRemove--;
            }
        }
        return board;
    }

    private SudokuBoard generateFullSudoku() {
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new SudokuSolver(board);
        solver.solve();
        return board;
    }
}

