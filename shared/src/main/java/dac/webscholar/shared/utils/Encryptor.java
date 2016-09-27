package dac.webscholar.shared.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by marcusviniv on 20/09/2016.
 */
public class Encryptor {


    public static String encrypt(String password){
        try{
            String plaintext = password;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
            return hashtext;
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateSalt(){

        try {
            SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");
            byte[] randomBytes = new byte[22];
            secureRandomGenerator.nextBytes(randomBytes);
            return new String(randomBytes);

        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

}
