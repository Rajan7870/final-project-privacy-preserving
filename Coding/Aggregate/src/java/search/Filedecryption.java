/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package search;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Statement;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author Sq1
 */
public class Filedecryption {
    public static String path1,fname1;;
              
    
 public void fun2(String decrypt_input,String decrypt_output)
         
    {
        Statement   st;
       try {   
			String key = "squirrel123"; // needs to be at least 8 characters for DES
         	FileInputStream fis2 = new FileInputStream(decrypt_input);
			FileOutputStream fos2 = new FileOutputStream(decrypt_output);
                        decrypt(key, fis2, fos2);
                        
                 
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }
public void fun2(String decrypt_input,String decrypt_output,String key1)
         
    {
        Statement   st;
       try {
                       
          
                        String key = key1;
        
                        FileInputStream fis2 = new FileInputStream(decrypt_input);
			FileOutputStream fos2 = new FileOutputStream(decrypt_output);
                        decrypt(key, fis2, fos2);
                        
              
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }
	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	} 
 
    
}
