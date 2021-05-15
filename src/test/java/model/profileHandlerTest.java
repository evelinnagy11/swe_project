package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class profileHandlerTest {

    profileHandler handler = new profileHandler();

    private String Encryption(String password, int encryptionNumber){
        String encrypted = handler.Encryption(password, encryptionNumber);
        String decrypted = handler.Encryption(encrypted, -encryptionNumber);
        return decrypted;
    }

    @Test
    void encryption() {
        assertEquals("teszt", Encryption("teszt", 11));
        assertEquals("Teszt123", Encryption("Teszt123", 20));
        assertEquals("teszt_123", Encryption("teszt_123", 7));
    }
}