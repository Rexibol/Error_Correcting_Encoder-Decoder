import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        int percent = scanner.nextInt();
        int targetMoney = scanner.nextInt();

        int year = 0;
        double currentMoney = money;
        while (currentMoney < targetMoney) {
            currentMoney += currentMoney * percent / 100;
            year++;
        }
        System.out.println(year);
    }
}