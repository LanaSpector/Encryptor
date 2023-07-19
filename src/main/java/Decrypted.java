import java.io.*;
import java.util.Scanner;

public class Decrypted {
    private final CaesarCipher caesar = new CaesarCipher();

    public void decrypted() {
        System.out.println("Введите путь к файлу для его расшифровки");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        System.out.println("Теперь введите ключ");
        int key = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите путь куда записать результат расшифровки");
        String path2 = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(path));
             BufferedWriter writer = new BufferedWriter(new FileWriter(path2))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String decrypted = caesar.decrypt(string, key);
                writer.write(decrypted+ "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

