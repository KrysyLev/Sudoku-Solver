
import java.util.Random;

public class SudokuSolverRandom {
    //INITIALIZE THE BOARD SIZE 9X9
    private static final int GRID_SIZE = 9;

    // Generate a full sudoku board
    private static int[][] generateFullSudoku() {
        int[][] sudokuArray = new int[9][9];
        solveSudokuBoard(sudokuArray);
        return sudokuArray;
    }

    //Randomize the sudoku board my remove some random spot in the board
    public static int[][] generateRandomSudokuPuzzle(int numberOfCellsToRemove) {
        int[][] sudokuArray = generateFullSudoku();
        Random random = new Random();

        while(numberOfCellsToRemove > 0) {
            int row = random.nextInt(9);
            int column = random.nextInt(9);
            if (sudokuArray[row][column] != 0) {
                sudokuArray[row][column] = 0;
                --numberOfCellsToRemove;
            }
        }

        return sudokuArray;
    }
    //Display the sudoku board method

    private static void printSudokuArray(int[][] sudokuArray) {
        for(int i = 0; i < 9; ++i) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-------------------");
            }

            for(int j = 0; j < 9; ++j) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }

                System.out.print(sudokuArray[i][j] + " ");
            }

            System.out.println();
        }

    }
    // Method calling to check whether there is a number exist in a row or not ?

    private static boolean isNumberInRow(int[][] sudokuArray, int number, int row) {
        for(int i = 0; i < 9; ++i) {
            if (sudokuArray[row][i] == number) {
                return true;
            }
        }

        return false;
    }
    // Method calling to check whether there is a number exist in a column or not ?

    private static boolean isNumberInColumn(int[][] sudokuArray, int number, int column) {
        for(int i = 0; i < 9; ++i) {
            if (sudokuArray[i][column] == number) {
                return true;
            }
        }

        return false;
    }
    // Method calling to check whether there is a number exist in a box or not ?

    private static boolean isNumberInBox(int[][] sudokuArray, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for(int i = localBoxRow; i < localBoxRow + 3; ++i) {
            for(int j = localBoxColumn; j < localBoxColumn + 3; ++j) {
                if (sudokuArray[i][j] == number) {
                    return true;
                }
            }
        }

        return false;
    }
    // Calling 3 above method in 1 for convenience

    private static boolean isValidPlacement(int[][] sudokuArray, int number, int row, int column) {
        return !isNumberInRow(sudokuArray, number, row) && !isNumberInColumn(sudokuArray, number, column) && !isNumberInBox(sudokuArray, number, row, column);
    }
    //Main method backtracking solving the sudoku board

    public static boolean solveSudokuBoard(int[][] sudokuArray) {
        for(int row = 0; row < 9; ++row) {
            for(int column = 0; column < 9; ++column) {
                if (sudokuArray[row][column] == 0) {
                    for(int numberToTry = 1; numberToTry <= 9; ++numberToTry) {
                        if (isValidPlacement(sudokuArray, numberToTry, row, column)) {
                            sudokuArray[row][column] = numberToTry;
                            // Recursion step

                            // if solve successful return true then continue to try new numberToTry
                            if (solveSudokuBoard(sudokuArray)) {
                                return true;
                            }
                            //clear out the previous value back to "0"

                            sudokuArray[row][column] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }
}
