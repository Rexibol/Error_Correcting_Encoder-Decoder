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
        int maxSum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = array[i];
            if ((sum % 4 == 0) && sum > maxSum) {
                maxSum = sum;
            }
        }
        System.out.println(maxSum);
    }
}