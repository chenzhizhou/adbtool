package mainbody;

import javax.swing.*;

import com.jcraft.jsch.JSchException;

import java.awt.*;  
import java.net.*;
import java.util.Properties;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpgradeTool extends JFrame {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String updateHost;
	JFrame c = this;
	private String filepath;
	private String localpath = getNowPath();
  
    public UpgradeTool(String path,String host) throws IOException {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	this.filepath=path;
    	this.updateHost=host;
    	
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			File needDelete = new File(filepath+"temp");
    			deleteFile(needDelete);
    			
    		}
    	});
//        filepath = getNowPath();
//		InputStream inStream = new FileInputStream(new File(filepath+"config.properties"));
//		Properties prop = new Properties();  
//		prop.load(inStream);
//		updateHost = prop.getProperty("updateHost");
    	//setIconImage(Toolkit.getDefaultToolkit().getImage(".png"));  
        //���ô�������  
    	System.out.println(localpath);
        setAttb();  
        getContentPane().setLayout(null);
        JLabel title = new JLabel("���ڼ���Ƿ��и��¡���");  
        getContentPane().add(title);  
        title.setBounds(10,68,264,70);
        JLabel msg = new JLabel();
        msg.setBounds(10,108,264,30);
        getContentPane().add(msg);  
        JLabel process = new JLabel();  
        process.setBounds(0,141, 284, 20);
        getContentPane().add(process);   
        JLabel isUpdate_label = new JLabel("");
        isUpdate_label.setBounds(10, 96, 264, 30);
        getContentPane().add(isUpdate_label);
        JButton btnUpdate = new JButton("ѡ������·��������");
        btnUpdate.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String path = FileChooser.pathChooser();
        		title.setText("���ظ����ļ��С���");
        		new DownloadUpdate(isUpdate_label,msg,title,btnUpdate,path).start();
        		btnUpdate.setEnabled(false);
        	}
        });
        btnUpdate.setBounds(10, 10, 156, 48);
        getContentPane().add(btnUpdate);
        //���������߳�  
        new Check(isUpdate_label,msg,title).start();
    }    
//    private class Check extends Thread {
//        //���ذ汾�ļ���  
//  
//        //��ʾ��Ϣ  
//        //private JLabel isUpdate_label; 
//        private JLabel msg;
//		private String netVerStr;
//		private JLabel title; 
//  
//        public Check(JLabel isUpdate_label, JLabel msg, JLabel title) {  
//        	//this.isUpdate_label = isUpdate_label; 
//        	this.msg = msg; 
//        	this.title = title;
//        }  
//  
//        public void run() {  
//            //�����ļ��汾��ʶURL
//        	URL url = null;  
//            InputStream is = null;  
//            InputStreamReader isr = null;  
//            BufferedReader netVer = null;
//            String versionUrl = updateHost + "/myapps/AutoUpdate/ver/ver.txt";
//            try {
//				url = new URL(versionUrl);
//	            is = url.openStream();  
//	            isr = new InputStreamReader(is);  
//
//	            netVer = new BufferedReader(isr);  
//	            netVerStr = netVer.readLine();  
//	            
//			} catch (Exception e) {
//				title.setText("�����³��ִ���");
//				title.setForeground(Color.RED);
//				e.printStackTrace();
//			}
//            finally {
//            	String nowVersion = getNowVer(getNowPath()+"ver.txt");
//            	msg.setText("���°汾��"+netVerStr+"   ���ذ汾��"+nowVersion);
//                if(netVerStr.equals(nowVersion)){
//                	title.setText("������£�");
//                	title.setForeground(Color.GREEN);
//                }
//                else{
//                	title.setText("���ذ汾�����粻һ�£����Խ��и��£�");
//                	title.setForeground(Color.ORANGE);
//                }
//                }
//			}
//
//        }
//private class DownloadUpdate extends Thread {
//    	
//
//		
//        //private JLabel isUpdate_label;
//		private JLabel msg;
//		private JLabel process;
//		private JLabel title;
//		public DownloadUpdate(JLabel isUpdate_label, JLabel msg,JLabel process,JLabel title) {  
//        	//this.isUpdate_label = isUpdate_label;
//        	this.msg = msg;
//        	this.process = process;
//        	this.title = title;
//        }
//		public void run() {
//			InputStream is = null;
//			URL url;
//			String nowPath = getNowPath();
//	    	//������Ҫ�����µ��ļ�  
//	        File oldFile = new File(nowPath+"tool.jar");  
//	        //�������������ص��ļ�  
//	        File newFile = new File(nowPath+"UpgradeTemp.jar");
//	        //�����ϵ��ļ�λ��  
//	        String updateUrl = updateHost+"/myapps/AutoUpdate/ver/tool.jar";  
//	        HttpURLConnection httpUrl = null;  
//	        BufferedInputStream bis = null;  
//	        FileOutputStream fos = null;
//	        
//	        try {
//				url = new URL(updateUrl);
//	            httpUrl = (HttpURLConnection) url.openConnection();  
//	            httpUrl.connect();  
//
//	            byte[] buffer = new byte[1024];  
//	            int size = 0;  
//	            is = httpUrl.getInputStream();  
//	            bis = new BufferedInputStream(is);  
//	            fos = new FileOutputStream(newFile);
//	            msg.setText("���ڴ������������µĸ����ļ�\n");;
//	          //�����ļ�  
//                try {  
//                    int flag = 0;  
//                    int flag2 = 0;  
//                    while ((size = bis.read(buffer)) != -1) {  
//                        //��ȡ��ˢ����ʱ�����ļ�  
//                        fos.write(buffer, 0, size);  
//                        fos.flush();  
//
//                        //ģ��һ���򵥵Ľ�����  
//                        if (flag2 == 99) {  
//                            flag2 = 0;  
//                            process.setText(process.getText() + ".");  
//                        }  
//                        flag2++;  
//                        flag++;  
//                        if (flag > 99 * 50) {  
//                            flag = 0;  
//                            process.setText("");  
//                        }  
//                    }  
//                } catch (Exception ex4) {  
//                    System.out.println(ex4.getMessage());  
//                }
//                msg.setText("\n�ļ��������\n");
//                CopyFile(oldFile,newFile);
//                title.setText("������ɣ�");
//                title.setForeground(Color.PINK);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	        finally {  
//                try {  
//                    fos.close();  
//                    bis.close();  
//                    is.close();  
//                    httpUrl.disconnect();  
//                } catch (IOException ex3) {  
//                } 
//	        
//		}
//		}
//
//    }
    private class Check extends Thread {
        private JLabel msg;
		private JLabel title;
		private String netVerStr; 
  
        public Check(JLabel isUpdate_label, JLabel msg, JLabel title) {  
        	this.msg = msg; 
        	this.title = title;
        }  
  
        public void run() {
        	
        	String ftpHost = updateHost;
    		String ftpUserName = "ubuntu";
    		String ftpPassword = "inhand";
    		int ftpPort = 22;
    		String ftpPath = "/home/liwei/czz";
    		newfolder(filepath+"temp/");
    		String localPath = filepath + "temp/";
    		String fileName = "ver.txt";
            try {
            	FtpUtil.downloadSftpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, localPath, fileName); 
            	netVerStr = getNowVer(filepath+"temp/ver.txt");
			} catch (Exception e) {
				title.setText("�����³��ִ���");
				title.setForeground(Color.RED);
				JOptionPane.showMessageDialog(null, "�޷�������16���εķ���������\n����ϵ����","���߸���",JOptionPane.PLAIN_MESSAGE);
				e.printStackTrace();
			}
            finally {
            	String nowVersion = getNowVer(filepath+"ver.txt");
            	msg.setText("���°汾��"+netVerStr+"   ���ذ汾��"+nowVersion);
                if(netVerStr.equals(nowVersion)){
                	title.setText("������£�");
                	title.setForeground(Color.GREEN);
                }
                else{
                	title.setText("���ذ汾�����粻һ�£����Խ��и��£�");
                	title.setForeground(Color.ORANGE);
                }
                }
			}

        }
    private class DownloadUpdate extends Thread {
    	

		
        //private JLabel isUpdate_label;
		private JLabel msg;
		private JLabel title;
		private JButton btnUpdate;
		private String downloadpath;
		public DownloadUpdate(JLabel isUpdate_label, JLabel msg,JLabel title,JButton btnUpdate,String DownloadPath) {  
        	//this.isUpdate_label = isUpdate_label;
        	this.msg = msg;
        	this.title = title;
        	this.btnUpdate = btnUpdate;
        	this.downloadpath = DownloadPath;
        }
		public void run() {
        	String ftpHost = updateHost;
    		String ftpUserName = "ubuntu";
    		String ftpPassword = "inhand";
    		int ftpPort = 22;
    		String ftpPath = "/home/liwei/czz";
    		newfolder(filepath+"temp/");
    		//String localPath = filepath + "temp/";
    		String localPath = downloadpath+"\\";
    		String fileName = "adbTools.exe";
	    	//������Ҫ�����µ��ļ�  
	        File oldFile = new File(filepath+"adbTools.exe");  
	        //�������������ص��ļ�  
	        File newFile = new File(filepath+"temp/adbTools.exe");
            try {
				FtpUtil.downloadSftpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, localPath, fileName);
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            msg.setText("\n�ļ��������\n");
            //CopyFile(oldFile,newFile);
            title.setText("������ɣ�");
            title.setForeground(Color.PINK);
            btnUpdate.setEnabled(true);
            
		}

    }
    //�����ļ�  
    private void CopyFile(File oldFile, File newFile) {  
        FileInputStream in = null;  
        FileOutputStream out = null;  
          
        try {  
            if(oldFile.exists()){  
                oldFile.delete();  
            }  
            in = new FileInputStream(newFile);  
            out = new FileOutputStream(oldFile);  

            byte[] buffer = new byte[1024 * 5];  
            int size;  
            while ((size = in.read(buffer)) != -1) {  
                out.write(buffer, 0, size);  
                out.flush();  
            }  
        } catch (FileNotFoundException ex) {  
        } catch (IOException ex) {  
        } finally {  
            try {  
                out.close();  
                in.close();  
            } catch (IOException ex1) {  
            }  
        }  

    }
    private void setAttb() {  
        //��������  
        this.setTitle("Auto Update");  
        this.setSize(300, 200);  
        getContentPane().setLayout(null);  
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
  
        // �������  
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
        Dimension frameSize = this.getSize();  
        if (frameSize.height > screenSize.height) {  
            frameSize.height = screenSize.height;  
        }  
        if (frameSize.width > screenSize.width) {  
            frameSize.width = screenSize.width;  
        }  
        this.setLocation((screenSize.width - frameSize.width) / 2,  
                         (screenSize.height - frameSize.height) / 2);  
    }  
  
    public static String getNowPath(){
    	URL url = UpgradeTool.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;  
        try {  
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// ת��Ϊutf-8����
            File file = new File(filePath);
            filePath = file.getAbsolutePath();
            filePath = filePath.substring(0, filePath.lastIndexOf("\\") + 1);
        }
        catch(IOException ex1){
        }
        return filePath;
    }
	public static String getNowVer(String LocalVerFilePath){
		//���ذ汾�ļ�  
        File verFile = new File(LocalVerFilePath);  

        FileReader is = null;  
        BufferedReader br = null;  

        //��ȡ���ذ汾  
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
            //�ͷ���Դ  
            try {  
                br.close();  
                is.close();  
            } catch (IOException ex1) {  
            }  
        }  
        return "";  
    }
	public static boolean deleteFile(File file) {
        if (file.isDirectory()) {
            String[] children = file.list();
            //�ݹ�ɾ��Ŀ¼�е���Ŀ¼��
            for (int i=0; i<children.length; i++) {
                boolean success = deleteFile(new File(file, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
		return file.delete();

	}
	public void newfolder(String path){
		//System.out.print(path);
		File file =new File(path);    
		//����ļ��в������򴴽�    
		if  (!file.exists() && !file.isDirectory()){   
		    file.mkdirs();    
		}
	}
    public static void main(String[] args) throws IOException {
//    	String filepath = getNowPath();
//        String nowVersion = getNowVer(filepath+"ver.txt");
//        System.out.print("\nNowVersion="+nowVersion);
        UpgradeTool checkupdate = new UpgradeTool("C:/inhandTool/","10.5.16.200");  
        checkupdate.setVisible(true);
        //System.out.print(args[0]);
    }  
}