import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class Util {
    private static final BufferedReader CONSOLE = new BufferedReader(new InputStreamReader(System.in));

    private Util() {
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    @SneakyThrows
    public static String readString() {
        return CONSOLE.readLine();
    }

    public static int readInt() {
        return Integer.parseInt(readString());
    }

    public static Path buildFileName(String path, String suffix) {
        String fileName;
        if (path.contains(".")) {
            int index = path.lastIndexOf(".");
            fileName = path.substring(0, index) + suffix + path.substring(index);
        } else {
            fileName = path + suffix;
        }
        return Path.of(fileName);
    }
}
