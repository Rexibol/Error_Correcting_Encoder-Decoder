import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = scanner.nextInt();
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            // sum = array[i];

            for (int j = 0; j < num; j++) {

                if (i != j) {
                    sum = array[j] + array[i];
                    if (sum % 6 == 0) {
                        System.out.println(sum);
                        return;
                    } else {
                        if (i != j) {
                            sum -= array[j];
                        }
                    }
                }
            }
        }
        // put your code here
    }
}