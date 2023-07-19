import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Util {
    private static final BufferedReader CONSOLE = new BufferedReader(new InputStreamReader(System.in));

    private Util() {

    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }
    @SneakyThrows
    public static String readString(){
        return CONSOLE.readLine();
    }

    public static int readInt(){
        return Integer.parseInt(readString());
    }
}
