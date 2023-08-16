import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptedDecrypted {

    private final CaesarCipher caesar = new CaesarCipher();

    @SneakyThrows
    public void encryptedDecrypted(boolean flag) {
        Util.writeMessage(flag ? "Введите путь к файлу для его зашифровки" : "Введите путь к файлу для его расшифровки");
        String path = Util.readString();
        Util.writeMessage("Теперь введите ключ");
        int key = Util.readInt();
        Path newPath = Util.buildFileName(path, flag ? "_encrypted" : "_decrypted");
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encryptedDecrypted = flag ? caesar.encrypt(string, key) : caesar.decrypt(string, key);
                writer.write(encryptedDecrypted + "\n");
            }
        }
    }
}


