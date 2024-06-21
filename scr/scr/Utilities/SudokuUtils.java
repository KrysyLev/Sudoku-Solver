package Utilities;

public class SudokuUtils {
    public static int getCellsToRemoveByDifficulty(String difficulty) {
        switch (difficulty) {
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
}

