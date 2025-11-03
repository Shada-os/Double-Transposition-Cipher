import java.util.Arrays;

public class Hack_Encrypted_Msg {
    public static void main(String[] args) {
        String encryptedMessage = "_rinftomoa.r31o_2sueua_tIasm__cct_SiyuernnfIdo_te_";
        String expectedDecryptedMessage = "I_am_a_student_of_Information_Security_course_321.";
        int rows = 5;
        int cols = 10;

        
        int[] rowOrder = new int[rows];
        int[] colOrder = new int[cols];
        for (int i = 0; i < rows; i++) rowOrder[i] = i;
        for (int i = 0; i < cols; i++) colOrder[i] = i;

        
        permute(rowOrder, 0, rowOrder.length - 1, rowsPerm -> {
            permute(colOrder, 0, colOrder.length - 1, colsPerm -> {
                String decrypted = decrypt(encryptedMessage, rows, cols, rowsPerm, colsPerm);
                if (decrypted.equals(expectedDecryptedMessage)) {
                    System.out.println("Decrypted message: " + decrypted);
                    System.out.println("Row Order: " + Arrays.toString(rowsPerm));
                    System.out.println("Column Order: " + Arrays.toString(colsPerm));
                }
            });
        });
    }

    private static String decrypt(String message, int rows, int cols, int[] rowOrder, int[] colOrder) {
        char[][] matrix = new char[rows][cols];
        int index = 0;

        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = message.charAt(index++);
            }
        }

       
        char[][] colPermuted = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                colPermuted[i][j] = matrix[i][colOrder[j]];
            }
        }

        
        char[][] rowPermuted = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            rowPermuted[i] = colPermuted[rowOrder[i]];
        }

        
        StringBuilder decrypted = new StringBuilder();
        for (char[] row : rowPermuted) {
            for (char c : row) {
                decrypted.append(c);
            }
        }

        return decrypted.toString();
    }

    private static void permute(int[] array, int l, int r, PermutationConsumer consumer) {
        if (l == r) {
            consumer.consume(array.clone());
        } else {
            for (int i = l; i <= r; i++) {
                swap(array, l, i);
                permute(array, l + 1, r, consumer);
                swap(array, l, i);
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @FunctionalInterface
    interface PermutationConsumer {
        void consume(int[] permutation);
    }
}
