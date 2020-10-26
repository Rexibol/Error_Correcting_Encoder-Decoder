import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = m;
        int counter = 1;

        {
            int i, k = 0, l = 0;

        /*  k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        i - iterator
        */

            while (k < m && l < n) {
                // Print the first row from the remaining rows
                for (i = l; i < n; ++i) {
                    System.out.print(counter++ + " ");
                }
                k++;

                // Print the last column from the remaining
                // columns
                for (i = k; i < m; ++i) {
                    System.out.print(counter++ + " ");
                }
                n--;

                // Print the last row from the remaining rows */
                if (k < m) {
                    for (i = n - 1; i >= l; --i) {
                        System.out.print(counter++ + " ");

                    }
                    m--;
                }

                // Print the first column from the remaining
                // columns */
                if (l < n) {
                    for (i = m - 1; i >= k; --i) {
                        System.out.print(counter++ + " ");
                    }
                    l++;
                }
            }
        }

        // put your code here
    }
}