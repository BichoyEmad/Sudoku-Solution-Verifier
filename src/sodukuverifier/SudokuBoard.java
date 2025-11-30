package sodukuverifier;
import java.io.*;
import java.util.*;

public class SudokuBoard {
    private int[][] board = new int[9][9];

    public SudokuBoard(String csvPath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csvPath));
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                for (int col = 0; col < 9; col++) {
                    board[row][col] = Integer.parseInt(parts[col].trim());
                }
                row++;
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in CSV: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.out.println("Error closing the file: " + e.getMessage());
            }
        }
    }

    public int get(int r, int c) {
        return board[r][c];
    }
}
