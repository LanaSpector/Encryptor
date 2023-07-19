import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Bruteforce {
    private final CaesarCipher caesar = new CaesarCipher();
    Scanner scanner = new Scanner(System.in);

    @SneakyThrows
    public void bruteforce() {
        System.out.println("Введите путь к файлу для расшифровки");
        String path = scanner.nextLine();
        System.out.println("Введите путь для записи расшифровки");
        String path2 = scanner.nextLine();
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(path2))) {
            while (reader.ready()) {
                String line = reader.readLine();
                builder.append(line);
            }
            for (int i = 0; i < caesar.alphabetLength(); i++) {
                String decrypt = caesar.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    writer.write(decrypt);
                    System.out.println("Файл расшифрован. Ключ расшифровки = " + i);
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
            System.out.println(text);
            System.out.println("Понятен ли этот текст? Y(yes)/N(no)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                isValidate = false;
            } else {
                System.out.println("Вероятно, Вы нажали не ту букву. Повторите попытку");
            }
        }
        return false;

    }
}

