import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bruteforce {
    private final CaesarCipher caesar = new CaesarCipher();

    @SneakyThrows
    public void bruteforce() {
        Util.writeMessage("Введите путь к файлу для расшифровки");
        String path = Util.readString();
        Path bruteforce = Util.buildFileName(path, "_bruteforce");
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(bruteforce)) {
            List<String> list = new ArrayList<>();
            while (reader.ready()) {
                String line = reader.readLine();
                list.add(line);
                builder.append(line);
            }
            for (int i = 0; i < caesar.alphabetLength(); i++) {
                String decrypt = caesar.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    for (String str : list) {
                        writer.write(caesar.decrypt(str, i));
                        writer.newLine();
                    }
                    Util.writeMessage("Файл расшифрован. Ключ расшифровки = " + i);
                    break;
                }
            }
        }
    }

    private boolean isValidateText(String text) {
        boolean isValidate = false;
        String[] words = text.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 28) {
                return false;
            }
        }

        if (text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")) {
            isValidate = true;
        }
        while (isValidate) {
            Util.writeMessage(text);
            Util.writeMessage("Понятен ли этот текст? Y(yes)/N(no)");
            String answer = Util.readString();
            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                isValidate = false;
            } else {
                Util.writeMessage("Вероятно, введена не та буква. Повторите попытку");
            }
        }
        return false;
    }
}

