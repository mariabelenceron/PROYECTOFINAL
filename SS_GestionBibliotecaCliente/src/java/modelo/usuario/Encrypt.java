package modelo.usuario;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt implements Serializable {

    // Asegúrate de añadir la clave de 32 dígitos para encriptar con AES 256
    private static final String secretKeyAES = "alexastudillo32digitosclaveaes25";
    private static final String saltAES = "alex1234";

    public Encrypt() {
    }

    public String getAES(String data) {
        Pattern pat = Pattern.compile("^[a-zA-Z0-9Ññ ]*$");
        Matcher mat = pat.matcher(data);
        try {
            if (mat.matches() && data != "") {
                byte[] iv = new byte[16];
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                KeySpec keySpec = new PBEKeySpec(secretKeyAES.toCharArray(), saltAES.getBytes(), 65536, 256);
                SecretKey secretKeyTemp = secretKeyFactory.generateSecret(keySpec);
                SecretKeySpec secretKey = new SecretKeySpec(secretKeyTemp.getEncoded(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
                return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
            } else {
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAESDecrypt(String data) {
        byte[] iv = new byte[16];
        try {
            if (data != "") {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                KeySpec keySpec = new PBEKeySpec(secretKeyAES.toCharArray(), saltAES.getBytes(), 65536, 256);
                SecretKey secretKeyTemp = secretKeyFactory.generateSecret(keySpec);
                SecretKeySpec secretKey = new SecretKeySpec(secretKeyTemp.getEncoded(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
                return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
            } else {
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
