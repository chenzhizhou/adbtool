package mainbody;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileChooser {

	public static String pathChooser() {
		String path="";
		JFileChooser jfc=new JFileChooser();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		jfc.setCurrentDirectory(fsv.getHomeDirectory());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = jfc.showOpenDialog(jfc);
        if(returnVal == JFileChooser.APPROVE_OPTION){ 
        	path= jfc.getSelectedFile().getAbsolutePath();
        }
		return path;
	}
	public static File[] multipathApkChooser(){
//		String[] path = null;
//		int fileslength = 0;
		File[] files = null;
		JFileChooser jfc=new JFileChooser();
		jfc.setMultiSelectionEnabled(true);
		FileSystemView fsv = FileSystemView.getFileSystemView();
		jfc.setCurrentDirectory(fsv.getHomeDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "AndroidPackage", "apk", "APK");
		jfc.setFileFilter(filter);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = jfc.showOpenDialog(jfc);
        if(returnVal == JFileChooser.APPROVE_OPTION){ 
        	files = jfc.getSelectedFiles();
        	//fileslength = jfc.getSelectedFiles().length;
        }
//        for(int i = 0; i < fileslength; i++){
//        	System.out.print(files[i].getAbsolutePath()+"\n");
//        	System.out.print(files[i].getName()+"\n");
//        }
		return files;
	}
	public static File[] multipathConfigChooser(){
//		String[] path = null;
//		int fileslength = 0;
		File[] files = null;
		JFileChooser jfc=new JFileChooser();
		jfc.setMultiSelectionEnabled(true);
		FileSystemView fsv = FileSystemView.getFileSystemView();
		jfc.setCurrentDirectory(fsv.getHomeDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "configFile", "xml", "txt");
		jfc.setFileFilter(filter);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = jfc.showOpenDialog(jfc);
        if(returnVal == JFileChooser.APPROVE_OPTION){ 
        	files = jfc.getSelectedFiles();
        }
		return files;
	}
}
