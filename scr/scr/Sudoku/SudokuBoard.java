package Sudoku;

import javax.swing.*;

public class SudokuBoard {
    private static final int SIZE = 9;
    private int[][] board;

    public SudokuBoard() {
        board = new int[SIZE][SIZE];
    }

    public SudokuBoard(SudokuBoard other) {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(other.board[i], 0, this.board[i], 0, SIZE);
        }
    }

    public int getCell(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, int value) {
        board[row][col] = value;
    }

    public void readInput(JTextField[][] cells) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String text = cells[i][j].getText();
                board[i][j] = text.isEmpty() ? 0 : Integer.parseInt(text);
            }
        }
    }

    public boolean equals(SudokuBoard other) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.board[i][j] != other.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
