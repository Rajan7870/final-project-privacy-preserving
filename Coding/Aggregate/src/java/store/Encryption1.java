/*@author Arun Kumar
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

/**
 *
 * @author Arun Kumar
 */
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

/*  Mode = CipherMode.CBC,-( Cipher-block chaining)
Padding = PaddingMode.PKCS7 or PKCS5,
KeySize = 128,
BlockSize = 128,
Key = keyBytes - password,
IV = keyBytes  - password*/

public class Encryption1 {

    Cipher cipher;
    static String output=null;
    // Input encrypted String
    
    // password for encryption

    public static String main(byte[] getBytes) throws Exception {
        
        byte[] input = getBytes;

        String strPassword = "password12345678";
        // put this as key in AES
        SecretKeySpec key = new SecretKeySpec(strPassword.getBytes(), "AES");

        // Parameter specific algorithm
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(strPassword.getBytes());
        //Whatever you want to encrypt/decrypt
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // You can use ENCRYPT_MODE (ENCRYPTunderscoreMODE)  or DECRYPT_MODE (DECRYPT underscore MODE)
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

        // encrypt data
        byte[] encrypted = cipher.doFinal(input);

        // encode data using standard encoder
        output = new BASE64Encoder().encode(encrypted);

        System.out.println("Orginal string: " + input);
        System.out.println("Encrypted string: " + output);

        return output;

     }

}
