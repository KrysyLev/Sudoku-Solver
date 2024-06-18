# Sudoku Solver and Puzzle Generator

This project implements a Sudoku solver and a random Sudoku puzzle generator in Java. The solver uses a backtracking algorithm to fill in the Sudoku grid, while the generator creates a valid Sudoku puzzle by removing a specified number of cells from a complete Sudoku solution.

## Features

- **Sudoku Solver**: Solves a given Sudoku puzzle using backtracking.
- **Sudoku Puzzle Generator**: Generates a random Sudoku puzzle by removing cells from a complete solution.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later

### Running the Code

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/sudoku-solver.git
   cd sudoku-solver
Compile the Java file:

bash
javac SudokuSolverRandom.java
Run the program:

bash
java SudokuSolverRandom
Code Overview
Main Class: SudokuSolverRandom
Methods
main(String[] args): Entry point of the program. Generates a random Sudoku puzzle, prints it, solves it, and prints the solution.
generateFullSudoku(): Generates a full, solved Sudoku board.
generateRandomSudokuPuzzle(): Generates a random Sudoku puzzle by removing a specified number of cells from a full Sudoku solution.
printSudokuArray(int[][] sudokuArray): Prints the Sudoku board to the console.
isNumberInRow(int[][] sudokuArray, int number, int row): Checks if a number is present in the specified row.
isNumberInColumn(int[][] sudokuArray, int number, int column): Checks if a number is present in the specified column.
isNumberInBox(int[][] sudokuArray, int number, int row, int column): Checks if a number is present in the specified 3x3 sub-grid (box).
isValidPlacement(int[][] sudokuArray, int number, int row, int column): Validates if placing a number at the specified cell is valid.
solveSudokuBoard(int[][] sudokuArray): Solves the Sudoku board using a backtracking algorithm.
Algorithm Explanation
The Sudoku solver uses a backtracking approach:

It iterates through each cell in the grid.
For each empty cell, it tries all possible numbers (1-9).
It places a number and recursively attempts to solve the rest of the board.
If a conflict is found, it backtracks by resetting the cell and trying the next number.
The process continues until the board is either solved or all possibilities are exhausted.
The random puzzle generator:

Generates a fully solved Sudoku board.
Randomly removes a specified number of cells to create a puzzle.
Example Output
markdown
Before solving:
7 0 2 |0 5 0 |6 0 0
0 0 0 |0 0 3 |0 0 0
1 0 0 |0 0 9 |5 0 0
-------------------
8 0 0 |0 0 0 |0 9 0
0 4 3 |0 0 0 |7 5 0
0 9 0 |0 0 0 |0 0 8
-------------------
0 0 9 |7 0 0 |0 0 5
0 0 0 |2 0 0 |0 0 0
0 0 7 |0 4 0 |2 0 3

Solved successfully !

7 3 2 |1 5 4 |6 8 9
9 5 4 |8 2 3 |1 7 6
1 8 6 |6 7 9 |5 3 4
-------------------
8 2 1 |3 6 7 |4 9 5
6 4 3 |9 8 5 |7 5 2
5 9 7 |4 1 2 |3 6 8
-------------------
3 6 9 |7 8 1 |8 4 5
4 7 5 |2 3 6 |9 1 7
2 1 8 |5 4 8 |2 9 3
License
This project is licensed under the MIT License - see the LICENSE file for details.

Acknowledgments
This project is inspired by the common algorithms used to solve and generate Sudoku puzzles.
Special thanks to the open-source community for providing resources and inspiration for the implementation.
