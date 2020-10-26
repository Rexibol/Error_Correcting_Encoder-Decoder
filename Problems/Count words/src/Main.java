import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine().trim();
            System.out.println(input.isEmpty() ? 0 : input.split("\\s+").length);
        }
    }
}