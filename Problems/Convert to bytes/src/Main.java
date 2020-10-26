import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;

        while (inputStream.readAllBytes())

        StringBuilder sb = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(inputStream);
        int tmp = reader.read();
        while (tmp != -1) {
            sb.append(tmp);
            tmp = reader.read();
        }

        System.out.println(sb.toString());
    }
}