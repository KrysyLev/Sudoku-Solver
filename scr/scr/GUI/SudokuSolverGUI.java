package GUI;

import Sudoku.SudokuBoard;
import Sudoku.SudokuGenerator;
import Sudoku.SudokuSolver;
import Utilities.SudokuUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SudokuSolverGUI extends JFrame {
    private static final int GRID_SIZE = 9;
    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];
    private SudokuBoard board;
    private SudokuBoard solutionBoard;

    // Color map for different numbers
    private Map<Integer, Color> colorMap = new HashMap<>();

    public SudokuSolverGUI() {
        // Initialize the color map
        initializeColorMap();

        setTitle("Sudoku Game & Solver");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the Sudoku grid
        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cells[i][j] = createTextField();
                gridPanel.add(cells[i][j]);
            }
        }

        // Set up difficulty selection combo box
        String[] difficulties = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficulties);

        // Set up buttons
        JButton solveButton = createButton("Solve", e -> solveSudoku());
        JButton generateButton = createButton("Generate Puzzle", e -> generatePuzzle((String) difficultyComboBox.getSelectedItem()));
        JButton checkButton = createButton("Check Solution", e -> checkSolution());

        // Set up button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Difficulty:"));
        buttonPanel.add(difficultyComboBox);
        buttonPanel.add(generateButton);
        buttonPanel.add(solveButton);
        buttonPanel.add(checkButton);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeColorMap() {
        colorMap.put(1, Color.RED);
        colorMap.put(2, Color.BLUE);
        colorMap.put(3, Color.GREEN);
        colorMap.put(4, Color.MAGENTA);
        colorMap.put(5, Color.ORANGE);
        colorMap.put(6, Color.CYAN);
        colorMap.put(7, Color.PINK);
        colorMap.put(8, Color.YELLOW);
        colorMap.put(9, Color.LIGHT_GRAY);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.BOLD, 18));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return textField;
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.addActionListener(actionListener);
        return button;
    }

    private void solveSudoku() {
        board = new SudokuBoard();
        board.readInput(cells);
        SudokuSolver solver = new SudokuSolver(board);
        if (solver.solve()) {
            displayResult(board);
        } else {
            JOptionPane.showMessageDialog(this, "Unsolvable board!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generatePuzzle(String difficulty) {
        int cellsToRemove = SudokuUtils.getCellsToRemoveByDifficulty(difficulty);
        SudokuGenerator generator = new SudokuGenerator();
        board = generator.generateRandomSudokuPuzzle(cellsToRemove);
        solutionBoard = new SudokuBoard(board);
        SudokuSolver solver = new SudokuSolver(solutionBoard);
        solver.solve();
        displayResult(board);
    }

    private void checkSolution() {
        board.readInput(cells);
        if (board.equals(solutionBoard)) {
            JOptionPane.showMessageDialog(this, "Correct solution!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect solution. Keep trying!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayResult(SudokuBoard board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int value = board.getCell(i, j);
                cells[i][j].setText(value == 0 ? "" : String.valueOf(value));
                if (value != 0) {
                    cells[i][j].setForeground(colorMap.get(value));
                }
            }
        }
    }

}


