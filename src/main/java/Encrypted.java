import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Encrypted {
    private final CaesarCipher caesar = new CaesarCipher();
    public void encrypted(){
        System.out.println("Введите путь к файлу для его зашифровки");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        System.out.println("Теперь введите ключ");
        int key = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите путь куда записать результат шифровки");
        String path2 = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(path));
        BufferedWriter writer = new BufferedWriter(new FileWriter(path2))) {
            while(reader.ready()) {
                String string = reader.readLine();
                String encrypted = caesar.encrypt(string, key);
                writer.write(encrypted + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
