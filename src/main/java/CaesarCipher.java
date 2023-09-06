public class CaesarCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            ".,\":!? +-*/\\@#$%^&(){}[];'|`~=_©«»—" + "0123456789";

    public int alphabetLength() {
        return ALPHABET.length();
    } // используется в Bruteforce

    public String encrypt(String message, int key) {
        StringBuilder builder = new StringBuilder();
        for (char aChar : message.toCharArray()) {
            int index = ALPHABET.indexOf(aChar);
            if (index >= 0) { // проверка, в случае, если indexOf вернет (-1).
                int newIndex = (index + key) % ALPHABET.length();
                char charAt = ALPHABET.charAt(newIndex < 0 ? newIndex + ALPHABET.length() : newIndex); // почему новый индекс может быть меньше нуля? из-за ограниченности значений int
                builder.append(charAt);
            }
        }
        return builder.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, key * -1);
    }
}
