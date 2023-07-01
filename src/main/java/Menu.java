import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите действие введя его номер:\n" +
                    "1. Зашифровать текст в файле с помощью ключа\n" +
                    "2. Расшифровать\n" +
                    "3. Подобрать ключ\n" +
                    "4. Синтаксический анализ\n" +
                    "5. Выйти из программы\n ");
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextLine()) {
                case "1" -> System.out.println("зашифровать");
                case "2" -> System.out.println("расшифровать");
                case "3" -> System.out.println("подобрать ключ");
                case "4" -> System.out.println("cинтаксический анализ");
                case "5" -> {
                    return;
                }
            }
        }
    }
}
