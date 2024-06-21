package Sudoku;

import java.util.HashSet;

public class SudokuSolver {
    private SudokuBoard board;
    private HashSet<Integer>[] rows;
    private HashSet<Integer>[] columns;
    private HashSet<Integer>[][] boxes;

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
        this.rows = new HashSet[9];
        this.columns = new HashSet[9];
        this.boxes = new HashSet[3][3];
        initializeSets();
    }

    private void initializeSets() {
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            columns[i] = new HashSet<>();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boxes[i][j] = new HashSet<>();
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = board.getCell(i, j);
                if (num != 0) {
                    rows[i].add(num);
                    columns[j].add(num);
                    boxes[i / 3][j / 3].add(num);
                }
            }
        }
    }

    public boolean solve() {
        return solveBoard(0, 0);
    }

    private boolean solveBoard(int row, int col) {
        if (row == 9) {
            return true;
        }
        if (col == 9) {
            return solveBoard(row + 1, 0);
        }
        if (board.getCell(row, col) != 0) {
            return solveBoard(row, col + 1);
        }
        for (int num = 1; num <= 9; num++) {
            if (isValidPlacement(row, col, num)) {
                board.setCell(row, col, num);
                rows[row].add(num);
                columns[col].add(num);
                boxes[row / 3][col / 3].add(num);

                if (solveBoard(row, col + 1)) {
                    return true;
                }

                board.setCell(row, col, 0);
                rows[row].remove(num);
                columns[col].remove(num);
                boxes[row / 3][col / 3].remove(num);
            }
        }
        return false;
    }

    private boolean isValidPlacement(int row, int col, int num) {
        return !rows[row].contains(num) && !columns[col].contains(num) && !boxes[row / 3][col / 3].contains(num);
    }
}
