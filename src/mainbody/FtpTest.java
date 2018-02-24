package mainbody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.jcraft.jsch.JSchException;

public class FtpTest {

	public static void main(String[] args) throws JSchException {
		String ftpHost = "10.5.16.200";
		String ftpUserName = "ubuntu";
		String ftpPassword = "inhand";
		int ftpPort = 22;
		String ftpPath = "/home/liwei/czz";
		String localPath = "D:/temp/";
		String fileName = "ver.txt";
		FtpUtil.downloadSftpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, localPath, fileName);
		String netVerStr = getNowVer(localPath+"/ver.txt");
		System.out.print(netVerStr);
	}
	public static String getNowVer(String LocalVerFilePath){
		//本地版本文件  
        File verFile = new File(LocalVerFilePath);  

        FileReader is = null;  
        BufferedReader br = null;  

        //读取本地版本  
        try {  
            is = new FileReader(verFile);  

            br = new BufferedReader(is);  
            String ver = br.readLine();  

            return ver;  
        } catch (FileNotFoundException ex) {  
            System.out.print(ex);
        } catch (IOException ex) {  
        	System.out.print(ex); 
        } finally {  
            //释放资源  
            try {  
                br.close();  
                is.close();  
            } catch (IOException ex1) {  
            }  
        }  
        return "";  
    }
}
