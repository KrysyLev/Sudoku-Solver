import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class SudokuSolverGUI extends JFrame {
    private int gridSize;
    private int[][] board;
    private JTextField[][] cells;
    private SudokuSolver solver;
    private DifficultyAnalyzer analyzer;
    private PuzzleGenerator generator;
    private UserProfile userProfile;
    private Timer timer;
    private long startTime;

    public SudokuSolverGUI(int gridSize, String username) {
        this.gridSize = gridSize;
        this.board = new int[gridSize][gridSize];
        this.cells = new JTextField[gridSize][gridSize];
        this.solver = new SudokuSolver(gridSize);
        this.analyzer = new DifficultyAnalyzer(gridSize);
        this.generator = new PuzzleGenerator(gridSize);
        this.userProfile = new UserProfile(username);

        setTitle("Sudoku Solver");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(gridSize + 1, gridSize));

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                add(cells[i][j]);
            }
        }

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> solvePuzzle());
        add(solveButton);

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> generatePuzzle());
        add(generateButton);

        JButton hintButton = new JButton("Hint");
        hintButton.addActionListener(e -> provideHint());
        add(hintButton);

        JButton difficultyButton = new JButton("Difficulty");
        difficultyButton.addActionListener(e -> analyzeDifficulty());
        add(difficultyButton);

        JButton multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.addActionListener(e -> startMultiplayer());
        add(multiplayerButton);

        JButton profileButton = new JButton("Profile");
        profileButton.addActionListener(e -> viewProfile());
        add(profileButton);
    }

    private void readInput() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                String text = cells[i][j].getText();
                if (text.isEmpty()) {
                    board[i][j] = 0;
                } else {
                    try {
                        board[i][j] = Integer.parseInt(text);
                    } catch (NumberFormatException e) {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    private void displayBoard(int[][] board) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                cells[i][j].setText(board[i][j] == 0 ? "" : String.valueOf(board[i][j]));
            }
        }
    }

    private void solvePuzzle() {
        readInput();
        long startTime = System.currentTimeMillis();
        if (solver.solveSudoku(board)) {
            long endTime = System.currentTimeMillis();
            displayBoard(board);
            userProfile.saveStatistics(gridSize, endTime - startTime, 100.0);
            JOptionPane.showMessageDialog(this, "Puzzle solved!");
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists!");
        }
    }

    private void generatePuzzle() {
        board = generator.generatePuzzle();
        displayBoard(board);
    }

    private void provideHint() {
        readInput();
        int[][] copyBoard = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            System.arraycopy(board[i], 0, copyBoard[i], 0, gridSize);
        }

        solver.solveSudoku(copyBoard);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (board[i][j] == 0 && copyBoard[i][j] != 0) {
                    cells[i][j].setBackground(Color.YELLOW);
                    cells[i][j].setText(String.valueOf(copyBoard[i][j]));
                    return;
                }
            }
        }
    }

    private void analyzeDifficulty() {
        readInput();
        String difficulty = analyzer.analyzeDifficulty(board);
        JOptionPane.showMessageDialog(this, "Puzzle difficulty: " + difficulty);
    }

    private void startMultiplayer() {
        // Implement multiplayer logic
    }

    private void viewProfile() {
        List<Map<String, Object>> stats = userProfile.getStatistics();
        StringBuilder statsMessage = new StringBuilder("User Profile Statistics:\n");
        for (Map<String, Object> stat : stats) {
            statsMessage.append("Puzzle Size: ").append(stat.get("puzzleSize"))
                    .append(", Solving Time: ").append(stat.get("solvingTime"))
                    .append(" ms, Accuracy: ").append(stat.get("accuracy"))
                    .append("%\n");
        }
        JOptionPane.showMessageDialog(this, statsMessage.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuSolverGUI(9, "defaultUser").setVisible(true));
    }
}
