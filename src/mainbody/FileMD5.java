package mainbody;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileMD5 {

    private static FileInputStream fis;

	public static String MD5encode(String Path) {
    	BigInteger bigInt = null;
        try {
        	
            File file = new File(Path);
            if (!file.exists()){
                return null;
    	        } 
            fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            bigInt = new BigInteger(1, md.digest());
            //System.out.println(tool.getFormatTime()+"-ÎÄ¼þmd5Öµ£º" + bigInt.toString(16));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return bigInt.toString(16);
    }

}