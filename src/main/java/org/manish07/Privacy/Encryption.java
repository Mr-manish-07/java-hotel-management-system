package org.manish07.Privacy;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryption {

    private static final String ALGO = "AES";

    private static final String SECRET = System.getenv("ENCRYPTION_SECRET");
    // TO SETTING THIS GO TO YOUR INTELLIJ AND CHANGE SOME CONFIGURATION
    // GO TO THREE DOT (...) NEAR RUN BUTTON --> CLICK ON EDIT BUTTON --> UNDER APPLICATION SELECT YOUR CLASS
    //     --> FIND ENVIRONMENTAL_VARIABLE --> AT END THERE IS $ SIGN CLICK ON IT YOU WILL GET USE ENVIRONMENTAL VARIABLE
    //     --> CLICK ON + ICON --> AND PLACE "ENCRYPTION_SECRET" IN THE NAME BOX --> AND PUT ANY STRING IN VALUE
    //     --> SUCH AS "MySuperSecretKey123"(THIS KEY SHOULD BE 16 DIGIT)

//    ------------------------------------- FOR PASSWORD HASHING ---------------------------------------



//    -------------------------------------- FOR CONFIDENTIAL DOCUMENTS ENCRYPTION-----------------------

    public static String encrypt(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), ALGO);
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encVal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), ALGO);
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            byte[] decVal = cipher.doFinal(decoded);
            return new String(decVal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        System.out.println(encrypt("manish"));
        System.out.println(decrypt("repUsa6YKMKmMaQAuJ0+6w=="));
    }

}
