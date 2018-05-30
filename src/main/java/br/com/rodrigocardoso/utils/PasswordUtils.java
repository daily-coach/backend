package br.com.rodrigocardoso.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by rodri on 28/05/2018.
 */
public class PasswordUtils {

    private static final Random RANDOM = new SecureRandom();
    private static final int INTERACTIONS = 10000;
    private static final int KEY_LENGTH = 250;

    private PasswordUtils(){ }

    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, INTERACTIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Cant create Hash: " + e.getMessage());
        } finally {
            spec.clearPassword();
        }
    }

    public static boolean validatePassword(char[] password, byte[] salt, byte[] compareHash) {
        byte[] pwdHash = hash(password, salt);
        Arrays.fill(password, Character.MIN_VALUE);
        if (pwdHash.length != compareHash.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != compareHash[i]) return false;
        }
        return true;
    }
}
