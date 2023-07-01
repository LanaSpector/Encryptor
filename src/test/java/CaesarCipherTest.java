import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherTest {
    private CaesarCipher caesar = new CaesarCipher();

    @Test
    void encrypt() {
        assertEquals(caesar.encrypt("abc", 3),"def");
    }
    @Test
    void encryptTest2() {
        assertEquals(caesar.encrypt("abc", 29),"def");
    }
    @Test
    void encryptTest3() {
        assertEquals(caesar.encrypt("abc", -3),"xyz");
    }
    @Test
    void encryptTest4() {assertEquals(caesar.encrypt("€", 5), ""); }
}