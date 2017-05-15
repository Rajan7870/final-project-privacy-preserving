/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package store;

/**
 *
 * @author Sq1
 */
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.exception.DropboxServerException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.RequestTokenPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Arun Kumar
 */
public class Cloudmanupulation {

    //private static final String APP_KEY = "sgoyhvwn16g7qhd"; 
    //private static final String APP_SECRET = "xdtf8yxaz5vyy0y"; 
    private static final String APP_KEY = "exm9b21pm455n9t"; 
    private static final String APP_SECRET = "kq0wq7xdy5e17ez"; 
    private static final AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
    private static DropboxAPI mDBApi;

    public static void main() {
        try {
            AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
            WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
            WebAuthInfo authInfo = session.getAuthInfo();
            RequestTokenPair pair = authInfo.requestTokenPair;
            String url = authInfo.url;
 // Mahesh areaaaaaaaaaaaaaaaaaaaaaaaaaaaa
            System.out.println("URL Value        "+authInfo.url);
// Mahesh areaaaaaaaaaaaaaaaaaaaaaaaaaaaaa            
            try {
                try {
                    Desktop.getDesktop().browse(new URL(url).toURI());
                } catch (URISyntaxException ex) {
                    Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Press  ok to continue once you have authenticated.");
            session.retrieveWebAccessToken(pair);
            AccessTokenPair tokens = session.getAccessTokenPair();
            System.out.println("Access granted");
            mDBApi = new DropboxAPI(session);


        } catch (DropboxException ex) {
            Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
            
        }

    }

    public static void upload(File f,String location) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
           mDBApi.putFile("/"+location+"/"+f.getName(), fis, f.length(), null, null);
         //  mDBApi.delete("/"+location+"/"+f.getName());
           
        }
        catch (DropboxException ex) {
            System.out.println("The exception is"+ex);
            Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void delete(File f,String location){
         FileInputStream fis = null;
        try {
           
           mDBApi.delete("/"+location+"/"+f.getName());
           
        }
        catch (Exception ex) {
            Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static File download(String location,String fileName) {

        File file = null;
        FileOutputStream outputStream = null;
        try {
             file = new File("c:\\Temp",fileName);
             
            outputStream = new FileOutputStream(file);
            mDBApi.getFile("/"+location+"/"+fileName, null, outputStream, null);
        }catch(DropboxServerException dse){
            int status=dse.error;
            System.out.println(status);
            if(status==404){
                
             //   download("XOR",fileName);
            }
        }
        catch (DropboxException ex) {
            Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Localized message");
            ex.getLocalizedMessage();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Cloudmanupulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return file;
    }
}
