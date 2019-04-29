package mainbody;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class CommonOperations {
	//ȥ���ַ����еĿո񡢻س������з����Ʊ��
	public static String replace_trn(String s) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(s);
		s = m.replaceAll("");
		return s;
	}
	//�½��ļ���
	public static void mkdir(String path) {
		File file =new File(path);
		if  (!file.exists() && !file.isDirectory()){   
		    file.mkdirs();    
		}
	}
	//��ʽ��ʱ��
	public static String get_format_time(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatTime = sdf.format(d);
		return formatTime;
		
	}
	//�½��ļ�
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
	// ��ȡ�ļ�
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
	public static Document load_xml(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename));  //��ȡXML�ļ�,���document����
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}
	public static void save_xml(Document doc, String filename) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		//���ñ����ʽ
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)),format);
			//д������
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//��ȡmap�ж�Ӧ��key
    public static String getKey(Map<String,String> map,String value){  
        String key="";  
        for (Map.Entry<String, String> entry : map.entrySet()) {  
            if(value.equals(entry.getValue())){  
                key=entry.getKey();  
            }  
        }  
        return key;  
    }  
}
