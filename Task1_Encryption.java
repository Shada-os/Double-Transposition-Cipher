

import java.util.Scanner;
public class DoubleTransposition{
    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the plaintext (letters and spaces allowed): ");
        String plaintext = scanner.nextLine().replace(" ", ""); // Remove spaces

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter the secret password (order of columns): ");
        String password = scanner.nextLine();

        char[][] matrix = new char[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < plaintext.length()) {
                    matrix[i][j] = plaintext.charAt(index++);
                } else {
                    matrix[i][j] = ' '; 
                }
            }
        }

        StringBuilder encryptedText = new StringBuilder();
        for (char c : password.toCharArray()) {
            int colIndex = Character.getNumericValue(c) - 1;
            for (int i = 0; i < rows; i++) {
                encryptedText.append(matrix[i][colIndex]);
            }
        }
       

        System.out.println("Encrypted Text: " + encryptedText.toString());

        scanner.close();
    }
}