package mainbody;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonOperations {
	//新建文件夹
	public static void mkdir(String path) {
		File file =new File(path);
		if  (!file.exists() && !file.isDirectory()){   
		    file.mkdirs();    
		}
	}
	//格式化时间
	public static String get_format_time(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatTime = sdf.format(d);
		return formatTime;
		
	}
	//新建文件
	public static boolean create_new_file(String file_path){
		File file = new File(file_path);
        if (!file.exists()){
            try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
            return true;
	        } 
        else 
        	return false;
	}
	// 读取文件
	public static String read_file(String path) throws IOException {
		File file = new File(path);    
        BufferedReader bufread;  
        String readString = "";
        String line; 
        bufread = new BufferedReader(new FileReader(file));  
        while ((line = bufread.readLine()) != null) {  
        	readString = readString + line + "\n"; 
        }  
        bufread.close(); 
        return readString;
	}
	public static void write_file(String path,String content) throws IOException {
        File file = new File(path);   
        if (!file.exists()) {  
            file.createNewFile();  
        }  
        FileWriter fw = new FileWriter(file, true);  
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);  
        bw.flush();  
        bw.close(); 
	}
}
