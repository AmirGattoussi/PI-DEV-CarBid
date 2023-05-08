package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import org.mindrot.jbcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHasher {

    public static String hash(String password) {
        String hashedPassword = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(6, password.toCharArray());
        return hashedPassword;
    }
    
//    public static String hash(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hash = md.digest(password.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hash) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
