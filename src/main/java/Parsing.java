import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parsing {

    @SneakyThrows
    public void parsing() {
        Util.writeMessage("Введите путь к файлу для его расшифровки");
        String pathEncrypted = Util.readString();

        Util.writeMessage("Введите путь для набора статистики");
        String pathStatistics = Util.readString();

        Path newPath = Util.buildFileName(pathEncrypted, "_parsing");

        Map<Character, Integer> mapEncrypted = new HashMap<>();
        Map<Character, Integer> mapStatistics = new HashMap<>();
        fillMapWithValues(mapEncrypted, pathEncrypted);
        fillMapWithValues(mapStatistics, pathStatistics); // откуда здесь данные?
        List<Map.Entry<Character, Integer>> listEncrypted = mapToList(mapEncrypted);
        List<Map.Entry<Character, Integer>> listStatistics = mapToList(mapStatistics);

        Map<Character, Character> mapDecrypted = new HashMap<>();
        if (listEncrypted.size() <= listStatistics.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                mapDecrypted.put(listEncrypted.get(i).getKey(), listStatistics.get(i).getKey());
            }
        } else {
            Util.writeMessage("Размер файла со статистикой меньше зашифрованного. Нужен файл большего размера"); // как сделать файл большего размера?
        }
        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathEncrypted));
             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
            while (reader.ready()) {
                String readLine = reader.readLine();
                char[] charArray = readLine.toCharArray();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < charArray.length; i++) {
                    char encryptedChar = charArray[i];
                    Character decryptedChar = mapDecrypted.get(encryptedChar);
                    builder.append(decryptedChar);
                }
                writer.write(builder.toString());
                writer.newLine();
            }
        }
        Util.writeMessage("Файл успешно расшифрован методом статического анализа"); // может, "статистического"?
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) { // помещаем мапу в список, чтобы отсортировать его методом sort
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        return list;
    }

    @SneakyThrows
    private void fillMapWithValues(Map<Character, Integer> map, String path) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            while (reader.ready()) {
                String string = reader.readLine();
                for (int i = 0; i < string.length(); i++) {
                    char charAt = string.charAt(i);
                    if (!map.containsKey(charAt)) {
                        map.put(charAt, 1);
                    } else {
                        map.put(charAt, map.get(charAt) + 1);
                    }
                }
            }
        }
    }
}
