package Sudoku;

import GUI.SudokuSolverGUI;

import javax.swing.*;

public class SudokuSolverApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuSolverGUI().setVisible(true));
    }
}

