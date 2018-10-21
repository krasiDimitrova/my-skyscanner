package password;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PasswordEncryption {

    private static byte[] charArrayToByteArray(char[] c_array) {
        byte[] b_array = new byte[c_array.length];
        for (int i = 0; i < c_array.length; i++) {
            b_array[i] = (byte) (0xFF & c_array[i]);
        }
        return b_array;
    }

    public static char[] encryptPassword(char[] password) {
        char[] generatedPassword = null;
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(charArrayToByteArray(password));
            bytes = md.digest();
            String hashedPass = new BigInteger(1, md.digest()).toString(16);
            generatedPassword = hashedPass.toCharArray();
        } catch (NoSuchAlgorithmException e) {
        } finally {
            Arrays.fill(password, '\u0000');
            Arrays.fill(bytes, (byte) 0);
        }
        return generatedPassword;
    }

}
