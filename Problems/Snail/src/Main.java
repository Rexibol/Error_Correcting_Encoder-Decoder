import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int targetHeight = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int currentHeight = 0;
        int day = 1;
        while (currentHeight < targetHeight) {
            currentHeight += A;
            if (currentHeight >= targetHeight) {
                System.out.println(day);
                return;
            }
            currentHeight -= B;
            day++;
        }
        System.out.println(day);
    }
}