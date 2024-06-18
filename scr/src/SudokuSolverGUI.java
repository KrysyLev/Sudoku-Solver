import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverGUI extends JFrame{
    //Initializing the variables and the GUI elements
    private static final int GRID_SIZE = 9;
    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];
    private int[][] sudokuArray = new int[GRID_SIZE][GRID_SIZE];
    private int [][] solutionArray = new int[GRID_SIZE][GRID_SIZE];

    public SudokuSolverGUI() {
        //SETTING TITLE AND FRAME
        setTitle("Sudoku Game & Solver");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //SETTING THE DEFAULT BOARD
        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                gridPanel.add(cells[i][j]);
            }
        }

        // Setting the difficulties scroll down box
        String[] difficulties = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyCombox = new JComboBox<>(difficulties);

        //Create a new Solve button
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readInput();
                if (SudokuSolverRandom.solveSudokuBoard(sudokuArray)){
                    displayResult(sudokuArray);
                }
                else JOptionPane.showMessageDialog(null, "Unsolvable board!");
            }
        });

        //Create a new Generate Puzzle button
        JButton generateButton = new JButton("Generate Puzzle");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDiff = (String) difficultyCombox.getSelectedItem();
                int cellsToRemove = getCellsToRemoveByificulty(selectedDiff);
                sudokuArray = SudokuSolverRandom.generateRandomSudokuPuzzle(cellsToRemove);
                copyArray(sudokuArray, solutionArray);
                SudokuSolverRandom.solveSudokuBoard(solutionArray);
                displayResult(sudokuArray);
            }
        });

        //Create a new Check button

        JButton checkButton = new JButton("Check Solution");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readInput();
                if (isSolutionCorrect()) {
                    JOptionPane.showMessageDialog(null, "Correct solution!!!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect solution. Keep trying !!!");
                }
            }
        });

        //Adding the elements into JPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Difficulty:"));
        buttonPanel.add(difficultyCombox);
        buttonPanel.add(generateButton);
        buttonPanel.add(solveButton);
        buttonPanel.add(checkButton);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    //Choosing level method
    private int getCellsToRemoveByificulty(String difficulty){
        switch (difficulty){
            case "Easy":
                return 30;
            case "Medium":
                return 40;
            case "Hard":
                return 50;
            default:
                return 35;
        }
    }

    //Read input from user method
    private void readInput(){
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                String text = cells[i][j].getText();
                if (text.isEmpty()){
                    sudokuArray[i][j] = 0;
                }
                else {
                    try {
                        sudokuArray[i][j] = Integer.parseInt(text);
                    } catch (NumberFormatException e){
                        sudokuArray[i][j] = 0;
                    }
                }
            }
        }
    }

    //Display Result in the GUI
    private void displayResult(int[][] array){
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                if (array[i][j] == 0){
                    cells[i][j].setText("");
                }
                else {
                    cells[i][j].setText(String.valueOf(array[i][j]));
                }
            }
        }
    }

    //Checking if the solution is correct or not ?
    private boolean isSolutionCorrect(){
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                if (cells[i][j].getText().isEmpty() || Integer.parseInt(cells[i][j].getText()) != solutionArray[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    //Copy array method
    private void copyArray(int [][] source, int [][] destination){
        for (int i = 0; i < GRID_SIZE; i++){
            System.arraycopy(source[i], 0, destination[i], 0, GRID_SIZE);
        }
    }

    //Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuSolverGUI().setVisible(true);
            }
        });
    }
}











