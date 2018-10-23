package password;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class PasswordEncryptionTest {

    @Test
    public void encryptPassword_euqalPasswordsWithOnlyLettersAndDigits_true() {
        char[] pass1 = { 't', 'e', 's', 't', '1', '2' };
        char[] pass2 = { 't', 'e', 's', 't', '1', '2' };
        assertArrayEquals(PasswordEncryption.encryptPassword(pass1),
                PasswordEncryption.encryptPassword(pass2));
    }

    @Test
    public void encryptPassword_differentPasswordsWithOnlyLettersAndDigits_true() {
        char[] pass1 = { 't', 'e', 's', 't', '1', '2' };
        char[] pass2 = { 't', 'e', '1', '2' };
        assertFalse(PasswordEncryption.encryptPassword(pass1)
                .equals(PasswordEncryption.encryptPassword(pass2)));
    }

    @Test
    public void encryptPassword_euqalPasswordsWithSymbolsLettersAndDigits_true() {
        char[] pass1 = { 't', '#', '>', 't', '1', '+' };
        char[] pass2 = { 't', '#', '>', 't', '1', '+' };
        assertArrayEquals(PasswordEncryption.encryptPassword(pass1),
                PasswordEncryption.encryptPassword(pass2));
    }

    @Test
    public void encryptPassword_differentPasswordsWithSymbolsLettersAndDigits_true() {
        char[] pass1 = { 't', '#', '>', 't', '1', '+' };
        char[] pass2 = { 't', '#', '<', 't', '1', '-' };
        assertFalse(PasswordEncryption.encryptPassword(pass1)
                .equals(PasswordEncryption.encryptPassword(pass2)));
    }

}
