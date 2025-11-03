import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hack_Difficult_Encrypted_Msg{

    public static void main(String[] args) {
        // Encrypted message and target message
        String encryptedMessage = "msax_ersetapalni_fch_lAl";
        String targetMessage = "All_chapters_final_exams";
        
        // Matrix dimensions we can change it till we find the right combination 
        int rows = 6;
        int columns = 4;
        
        // Generate all possible swaps for rows and columns
        List<List<Integer>> rowOrders = generateSwaps(rows);
        List<List<Integer>> colOrders = generateSwaps(columns);

        System.out.println("Starting decryption...");
        int attemptCount = 0;

        // Try all combinations of row and column swaps
        for (List<Integer> rowOrder : rowOrders) {
            for (List<Integer> colOrder : colOrders) {
                attemptCount++;

                // Print attempt count every 100,000 attempts
                if (attemptCount % 100000 == 0) {
                    System.out.println("Attempts: " + attemptCount);
                }

                // Decrypt the message with the current row and column swaps
                String decryptedMessage = decrypt(encryptedMessage, rows, columns, rowOrder, colOrder);

                // Check if the decrypted message matches the target message
                if (decryptedMessage.equals(targetMessage)) {
                    System.out.println("Decrypted successfully!");
                    System.out.println("Row order: " + rowOrder);
                    System.out.println("Column order: " + colOrder);
                    System.out.println("Decrypted Message: " + decryptedMessage);
                    return;
                }
            }
        }

        // Print failure message if decryption was not successful
        System.out.println("Failed to decrypt the message after " + attemptCount + " attempts.");
    }

    // Method to decrypt the message using given row and column swaps
    public static String decrypt(String message, int rows, int columns, List<Integer> rowOrder, List<Integer> colOrder) {
        char[][] grid = new char[rows][columns];
        int index = 0;

        // Fill the grid with characters from the message according to row and column swaps
        for (int r : rowOrder) {
            for (int c : colOrder) {
                if (index < message.length()) {
                    grid[r][c] = message.charAt(index++);
                }
            }
        }

        // Read the grid row by row to form the decrypted message
        StringBuilder decrypted = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                decrypted.append(grid[r][c]);
            }
        }

        return decrypted.toString();
    }

    // Method to generate all swaps of a given size
    public static List<List<Integer>> generateSwaps(int n) {
        List<List<Integer>> swaps = new ArrayList<>();
        List<Integer> initial = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            initial.add(i);
        }
        generateSwapsHelper(initial, 0, swaps);
        return swaps;
    }

    // Helper method to generate swaps recursively
    private static void generateSwapsHelper(List<Integer> current, int index, List<List<Integer>> result) {
        if (index == current.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < current.size(); i++) {
            Collections.swap(current, i, index);
            generateSwapsHelper(current, index + 1, result);
            Collections.swap(current, i, index);
        }
    }
}
