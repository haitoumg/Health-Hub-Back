package com.healthHub.healthHub.classes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtils {
    private static final String SECRET_KEY = "TI92MPL9TI92MPL9"; 
    private static final String INIT_VECTOR = "RIPI82HF70MP6YH5";

    public static String encrypt(String email) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

            byte[] encrypted = cipher.doFinal(email.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static String decrypt(String encryptedEmail) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedEmail));
        return new String(original);
    }


}
