package mainbody;


import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class FtpUtil {



	
	/*
	 * ��SFTP�����������ļ�
	 * 
	 * @param ftpHost SFTP IP��ַ
	 * 
	 * @param ftpUserName SFTP �û���
	 * 
	 * @param ftpPassword SFTP�û�������
	 * 
	 * @param ftpPort SFTP�˿�
	 * 
	 * @param ftpPath SFTP���������ļ�����·�� ��ʽ�� ftptest/aa
	 * 
	 * @param localPath ���ص����ص�λ�� ��ʽ��H:/download
	 * 
	 * @param fileName �ļ�����
	 */
	public static void downloadSftpFile(String ftpHost, String ftpUserName,
			String ftpPassword, int ftpPort, String ftpPath, String localPath,
			String fileName) throws JSchException {
		Session session = null;
		Channel channel = null;

		JSch jsch = new JSch();
		session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
		session.setPassword(ftpPassword);
		session.setTimeout(100000);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();

		channel = session.openChannel("sftp");
		channel.connect();
		ChannelSftp chSftp = (ChannelSftp) channel;

		String ftpFilePath = ftpPath + "/" + fileName;
		//String localFilePath = localPath + File.separatorChar + fileName;

		try {
			chSftp.get(ftpFilePath, localPath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			chSftp.quit();
			channel.disconnect();
			session.disconnect();
		}

	}

}
