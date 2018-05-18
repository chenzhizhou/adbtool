package mainbody;

import mainbody.FileChooser;
import mainbody.FtpUtil;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.sound.sampled.Line;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;

import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;



import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;






public class tool {

	final static String AppVersion = "v.1.3.6";
	private JFrame frame;
	JLabel ConTip;
	JLabel PrtScTip;
	JLabel crashLogUpdateTip;
	JButton btnCrashlog;
	Process SaveLogProcess;
	boolean SaveLogTag;
	File[] Choosedapps;
	ArrayList<String> ChoosedappsStr = new ArrayList<String>();
	File[] ChoosedConfigs;
	ArrayList<String> ChoosedConfigsstr = new ArrayList<String>();
	boolean mDataConnectionState = false;
	boolean mWifiConnectionState = false;
	static JComboBox<String> orgName;
	public static JComboBox<String> serverAddress;
	ComboBoxEditor orgNameeditor;
	ComboBoxEditor serverAddresseditor;
	JComboBox<String> serialPort;
	ComboBoxEditor serialPorteditor;
	boolean DataConnectionFlag = false;
	boolean WifiConnectionFlag = false;
	JComboBox<String> nowVendor;
	ComboBoxEditor nowVendoreditor;
	private static JLabel updateFlagIcon;
	private static String updateHost = "10.5.16.200";
	private static String filepath = "C:/inhandTool/";
	//private static String workPath = "C:\\inhandTool\\";
	private int now;
	private JProgressBar installprogressBar;
	Map<String, String>vendormap = new HashMap<String, String>();
	private JComboBox<String> insatlled3app;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			

			public void run() {
				try {
					
					tool window = new tool();
					window.frame.setVisible(true);
					//filepath = getNowPath();
					newfolder(filepath);
					deleteFile(filepath+"ver.txt");
					creatNewFile(filepath+"ver.txt");
					writeFile(filepath+"ver.txt",AppVersion);
//					InputStream inStream = new FileInputStream(new File(filepath+"config.properties"));
//					Properties prop = new Properties();  
//					prop.load(inStream);
//					updateHost = prop.getProperty("updateHost");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tool() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		newfolder(filepath);
		deleteFile(filepath+"ver.txt");
		try {
			creatNewFile(filepath+"ver.txt");
			writeFile(filepath+"ver.txt",AppVersion);
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		customize.initCFGxml();
		frame = new JFrame();
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/toolIcon/logo.png")));
		frame.setBounds(100, 100, 816, 610);
		frame.getContentPane().setLayout(null);
		String Title = AppVersion+"——By.chenzhiz@inhand.com.cn";
		frame.setTitle("adb工具"+AppVersion);
		JLabel versionBottom = new JLabel(Title);
		versionBottom.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 10));
		versionBottom.setBounds(590, 556, 194, 15);
		frame.getContentPane().add(versionBottom);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextArea adbdevicesArea = new JTextArea();
		adbdevicesArea.setLineWrap(true);
		adbdevicesArea.setEditable(false);
		adbdevicesArea.setBounds(10, 10, 132, 63);
		frame.getContentPane().add(adbdevicesArea);
		JScrollPane adbdevicesAreascrollPane = new JScrollPane(adbdevicesArea);
		adbdevicesAreascrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//滚动条总是出现
		adbdevicesAreascrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		adbdevicesAreascrollPane.setBounds(10, 10, 171, 63);
		frame.getContentPane().add(adbdevicesAreascrollPane);
		JPanel panel_1ogcat = new JPanel();
		panel_1ogcat.setBackground(Color.WHITE);
		panel_1ogcat.setBorder(BorderFactory.createTitledBorder("打印日志"));
		panel_1ogcat.setBounds(10, 116, 280, 169);
		frame.getContentPane().add(panel_1ogcat);
		panel_1ogcat.setLayout(null);
		JButton logcatvtime = new JButton("<html>打印<br>所有日志</html>");
		logcatvtime.setBounds(177, 10, 93, 39);
		panel_1ogcat.add(logcatvtime);
		JButton logcatvtimetags = new JButton("<html>打印<br>过滤日志</html>");
		logcatvtimetags.setBounds(177, 59, 93, 39);
		panel_1ogcat.add(logcatvtimetags);
		
		Label label = new Label("adb logcat -v time ");
		label.setBounds(10, 27, 118, 23);
		panel_1ogcat.add(label);
		
		Label label_1 = new Label("adb logcat -v time -s ");
		label_1.setBounds(10, 56, 118, 23);
		panel_1ogcat.add(label_1);
		
		JTextField tagsField = new JTextField();
		tagsField.setBounds(49, 85, 118, 21);
		panel_1ogcat.add(tagsField);
		tagsField.setColumns(10);
		
		Label label_2 = new Label("Tags:");
		label_2.setBounds(10, 85, 33, 23);
		panel_1ogcat.add(label_2);
		
		JButton clearbeforeLog = new JButton("<html>清空<br>日志缓存</html>");
		clearbeforeLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String command = "cmd.exe /c adb logcat -c";
				try {
					Process p = Runtime.getRuntime().exec(command);
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		clearbeforeLog.setBounds(177, 108, 93, 39);
		panel_1ogcat.add(clearbeforeLog);
		
		JComboBox<String> commonTagscomboBox = new JComboBox<String>();
		commonTagscomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch (commonTagscomboBox.getSelectedItem().toString()) {
				case "广告":
					tagsField.setText("Player PlayerPolicy PlayerController VideoPlayer AdService");
					break;
				case "出货":
					tagsField.setText("WSDeliverGoods ObtainQRIndentTask");
					break;
				case "网络连接":
					tagsField.setText("WSConnectTask Steper ObtainTokenTask");
					break;
				case "远程升级":
					tagsField.setText("ReportAdsTask DeviceManagerService");
					break;
				default:
					tagsField.setText("");
					break;
				}
			}
		});
		commonTagscomboBox.setBounds(49, 111, 70, 22);
		commonTagscomboBox.addItem("");
		commonTagscomboBox.addItem("广告");
		commonTagscomboBox.addItem("出货");
		commonTagscomboBox.addItem("网络连接");
		commonTagscomboBox.addItem("远程升级");
		panel_1ogcat.add(commonTagscomboBox);
		
		JLabel lblNewLabel = new JLabel("常用：");
		lblNewLabel.setBounds(10, 114, 54, 15);
		panel_1ogcat.add(lblNewLabel);
		
		JButton addLogTag = new JButton("+");
		addLogTag.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component tip = null;
				JOptionPane.showMessageDialog(tip, "暂不可用", "暂不可用",JOptionPane.CANCEL_OPTION);
			}
		});
		addLogTag.setFont(new Font("宋体", Font.PLAIN, 12));
		addLogTag.setBounds(127, 111, 40, 22);
		panel_1ogcat.add(addLogTag);
		
		JPanel panel_save1ogcat = new JPanel();
		panel_save1ogcat.setBackground(Color.WHITE);
		panel_save1ogcat.setBorder(BorderFactory.createTitledBorder("实时保存日志"));
		panel_save1ogcat.setBounds(10, 295, 280, 223);
		frame.getContentPane().add(panel_save1ogcat);
		panel_save1ogcat.setLayout(null);
		
		JTextField saveLogTagField = new JTextField();
		saveLogTagField.setEditable(false);
		saveLogTagField.setBounds(49, 49, 221, 21);
		panel_save1ogcat.add(saveLogTagField);
		saveLogTagField.setColumns(10);
		
		JTextField logSavePathField = new JTextField();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		logSavePathField.setColumns(10);
		logSavePathField.setBounds(10, 129, 260, 30);
		logSavePathField.setText(fsv.getHomeDirectory().toString());
		panel_save1ogcat.add(logSavePathField);
		
		JButton pullHistoryLogbtn = new JButton("导出主日志");
		pullHistoryLogbtn.addMouseListener(new MouseAdapter() {
			private Process p;
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame2 = new JFrame("导出主日志");
			    frame2.getContentPane().setLayout(null);
			    frame2.setBounds(300, 300, 300, 200);
			    frame2.setVisible(true);
			    JLabel progress = new JLabel("准备导出logcat_main至");
			    progress.setBounds(50, 10, 300, 30);
				frame2.getContentPane().add(progress);
				JLabel progress2 = new JLabel(logSavePathField.getText());
				File f = new File(logSavePathField.getText());
				String savepath = f.getAbsolutePath();
				if (!f.exists()){
					newfolder(savepath);
				}
			    progress2.setBounds(50, 20, 300, 50);
				frame2.getContentPane().add(progress2);
				JButton begin = new JButton("开始导出");
				begin.setBounds(80, 80, 120, 30);
				frame2.getContentPane().add(begin);
				class pullHistoryLogThread implements Runnable{

					@Override
					public void run() {
						progress.setText("正在导出……");
						String command = "cmd.exe /c adb pull sdcard/inbox/log/logcat_main.log "+savepath;
					    String command1 = "cmd.exe /c adb pull sdcard/inbox/log/logcat_main.log.1 "+savepath;
					    String command2 = "cmd.exe /c adb pull sdcard/inbox/log/logcat_main.log.2 "+savepath;
					    String command3 = "cmd.exe /c adb pull sdcard/inbox/log/logcat_main.log.3 "+savepath;
					    String command4 = "cmd.exe /c adb pull sdcard/inbox/log/logcat_main.log.4 "+savepath;
					    String command5 = "cmd.exe /c adb pull sdcard/inbox/log/crash_log.txt "+savepath;
						try {
							progress2.setText("进度1/6,logcat_main.log");
							p = Runtime.getRuntime().exec(command);
							p.waitFor();
							p.destroy();
							progress2.setText("进度2/6,logcat_main.log.1");
							p = Runtime.getRuntime().exec(command1);
							p.waitFor();
							p.destroy();
							progress2.setText("进度3/6,logcat_main.log.2");
							p = Runtime.getRuntime().exec(command2);
							p.waitFor();
							p.destroy();
							progress2.setText("进度4/6,logcat_main.log.3");
							p = Runtime.getRuntime().exec(command3);
							p.waitFor();
							p.destroy();
							progress2.setText("进度5/6,logcat_main.log.4");
							p = Runtime.getRuntime().exec(command4);
							p.waitFor();
							p.destroy();
							progress2.setText("进度6/6,crash_log.txt");
							p = Runtime.getRuntime().exec(command5);
							p.waitFor();
							p.destroy();
							progress.setText("完成");
							progress2.setText("");
							begin.setEnabled(true);
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}
						
					}
					
				}
				pullHistoryLogThread pHLT = new pullHistoryLogThread();
				Thread t1 = new Thread(pHLT);
				begin.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						t1.start();
						begin.setEnabled(false);
					}
				});
			}
		});
		pullHistoryLogbtn.setBounds(143, 173, 114, 23);
		panel_save1ogcat.add(pullHistoryLogbtn);
		
		JButton chooseSavelogPathbtn = new JButton("<html>选择日志<br>保存路径</html>");
		
		chooseSavelogPathbtn.setBounds(10, 80, 93, 39);
		panel_save1ogcat.add(chooseSavelogPathbtn);
		
		
		JCheckBox checkboxLogTag = new JCheckBox("tag过滤");
		checkboxLogTag.setBackground(Color.WHITE);
		checkboxLogTag.setBounds(10, 20, 103, 23);
		panel_save1ogcat.add(checkboxLogTag);
		

		
		Label label_3 = new Label("Tags:");
		label_3.setBounds(10, 49, 33, 23);
		panel_save1ogcat.add(label_3);
		
		//停止保存日志
		JButton btnStopSaveLog = new JButton("Stop");
		btnStopSaveLog.setFont(new Font("宋体", Font.PLAIN, 10));
		//btnStopSaveLog.setIcon(new ImageIcon("./toolIcon/stop.png"));
		//btnStopSaveLog.setIcon(new ImageIcon(getClass().getResource("/toolIcon/stop.png")));
		btnStopSaveLog.setBounds(201, 80, 69, 39);
		panel_save1ogcat.add(btnStopSaveLog);
		btnStopSaveLog.setEnabled(false);
		
		//开始保存日志
		JButton btnStartSaveLog = new JButton();
		btnStartSaveLog.setFont(new Font("宋体", Font.PLAIN, 10));
		btnStartSaveLog.setText("Start");
		//btnStartSaveLog.setIcon(new ImageIcon(getClass().getResource("/toolIcon/start.png")));
		btnStartSaveLog.setBounds(113, 80, 78, 39);
		panel_save1ogcat.add(btnStartSaveLog);
		
		//重启按钮
		JButton btnRestart = new JButton("重启");
		btnRestart.setBounds(191, 11, 93, 36);
		frame.getContentPane().add(btnRestart);
		
		//设备截图
		JButton btnScreenshot = new JButton("设备截图");
		btnScreenshot.setBounds(191, 57, 93, 49);
		frame.getContentPane().add(btnScreenshot);
		PrtScTip = new JLabel("");
		PrtScTip.setBounds(20, 83, 114, 15);
		frame.getContentPane().add(PrtScTip);
		PrtScTip.setForeground(Color.ORANGE);
		
		btnCrashlog = new JButton("查看崩溃日志");
		btnCrashlog.setBounds(10, 173, 114, 23);
		panel_save1ogcat.add(btnCrashlog);
		
		crashLogUpdateTip = new JLabel("CrashLog.txt有更新！");
		crashLogUpdateTip.setBounds(10, 198, 157, 15);
		panel_save1ogcat.add(crashLogUpdateTip);
		crashLogUpdateTip.setForeground(Color.RED);
		crashLogUpdateTip.setVisible(false);
		
		//推送配置文件框
		JTextArea pushConfigArea = new JTextArea("请将需要下发的配置文件拖拽至此\n文件路径不能包含中文和空格\n");
		pushConfigArea.setLineWrap(true);
		pushConfigArea.setEditable(false);
		pushConfigArea.setTransferHandler(new TransferHandler(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
            public boolean importData(JComponent comp, Transferable t) {
                try {
                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);

                    String filepath = o.toString();
                    if (filepath.startsWith("[")) {
                        filepath = filepath.substring(1);
                    }
                    if (filepath.endsWith("]")) {
                        filepath = filepath.substring(0, filepath.length() - 1);
                    }
                    String[] strs = filepath.split(", ");
            		String regexstr = "[\u4e00-\u9fa5\u0020]";
            		Pattern p = Pattern.compile(regexstr);
            		for (String configstring : strs) {
            			Matcher m = p.matcher(configstring);
            			if(m.find()){
            				//System.out.print("\n包含中文");
            				Component tip = null;
							JOptionPane.showMessageDialog(tip, configstring+"\n文件路径不能包含中文和空格", "添加失败",JOptionPane.WARNING_MESSAGE);
							pushConfigArea.setText("");
            			}
            			else{
            				ChoosedConfigsstr.add(configstring);
            				pushConfigArea.setText("");
            			}
            		}
            		for (String ChoosedConfigstring : ChoosedConfigsstr) {
            			pushConfigArea.append(ChoosedConfigstring+"\n");
            		}
                    return true;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
            @Override
            public boolean canImport(JComponent comp, DataFlavor[] flavors) {
                for (int i = 0; i < flavors.length; i++) {
                    if (DataFlavor.javaFileListFlavor.equals(flavors[i])) {
                        return true;
                    }
                }
                return false;
            }
        });

		
		JScrollPane pushConfigscrollPane = new JScrollPane(pushConfigArea);
		pushConfigscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pushConfigscrollPane.setBounds(10, 47, 126, 87);
		pushConfigscrollPane.setViewportView(pushConfigArea);
		
		JPanel configPanel = new JPanel();
		configPanel.setLayout(null);
		configPanel.setBorder(BorderFactory.createTitledBorder("下发运行配置、修改Android时间"));
		configPanel.setBackground(Color.WHITE);
		configPanel.setBounds(300, 15, 280, 169);
		frame.getContentPane().add(configPanel);
		configPanel.add(pushConfigscrollPane);
		
//		JButton btnPushConfig = new JButton("选择配置文件");
//		btnPushConfig.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				ChoosedConfigs = null;
//				ChoosedConfigs = FileChooser.multipathConfigChooser();
//				for(int i = 0; i < ChoosedConfigs.length; i++){
////					System.out.print(ChoosedConfigs[i].getAbsolutePath()+"\n");
////					System.out.print(ChoosedConfigs[i].getName()+"\n");
//					ChoosedappsStr.add(ChoosedConfigs[i].getAbsolutePath());
//					pushConfigArea.append(ChoosedConfigs[i].getAbsolutePath()+"\n");
//				}
//			}
//		});
//		btnPushConfig.setBounds(10, 27, 126, 23);
//		configPanel.add(btnPushConfig);
		
		//下发config
		JButton btnPushingConfig = new JButton("下发");
		btnPushingConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				try {
//					String batName = "./bat/pushConfig.bat";
//					deleteFile(batName);
//					creatNewFile(batName);
////					for(int i = 0; i < ChoosedConfigs.length; i++){
////						writeFile(batName, "adb push " + ChoosedConfigs[i].getAbsolutePath() + " sdcard/inbox/config\n");
////					}
//					for(String tmp:ChoosedConfigsstr){
//			            //System.out.println(tmp);
//			            writeFile(batName, "adb push " + tmp + " sdcard/inbox/config\n");
//			        }
//					writeFile(batName, "adb shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP");
//					readFile(batName);
//					Runtime.getRuntime().exec(batName);
//					//deleteFile(batName);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
				class pushConfig implements Runnable{
					@Override
					public void run() {
						now = 0;
						int all = 1;
						all = all + ChoosedConfigsstr.size();
						installprogressBar.setValue(0);
						installprogressBar.setValue(0);
						try{
							for(String tmp:ChoosedConfigsstr){
					            Process p1 = Runtime.getRuntime().exec("adb push " + tmp + " sdcard/inbox/config");
					            p1.waitFor();
					            p1.destroy();
					            InstallProgress(all, now+=1, installprogressBar);
							}
							RestartAPP();
							//Runtime.getRuntime().exec("cmd.exe /c adb shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP");
							installprogressBar.setValue(100);
						}
						catch(IOException | InterruptedException e1){
							e1.printStackTrace();
						}
					}
				}
				pushConfig pConfig = new pushConfig();
				Thread t1 = new Thread(pConfig);
				t1.start();
			}
		});
		btnPushingConfig.setBounds(10, 136, 72, 23);
		configPanel.add(btnPushingConfig);
		
		//清空config
		JButton clearconfig = new JButton();
		clearconfig.setToolTipText("\u6E05\u7A7A");
		clearconfig.setFont(new Font("宋体", Font.PLAIN, 10));
		clearconfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChoosedConfigs = null;
				ChoosedConfigsstr.clear();
				pushConfigArea.setText("选择配置文件或拖拽配置文件至文本框");
			}
		});
		clearconfig.setText("...");
		//clearconfig.setIcon(new ImageIcon(getClass().getResource("/toolIcon/searchreset.png")));
		clearconfig.setBounds(92, 136, 44, 23);
		configPanel.add(clearconfig);
		
		//时间选择器
		SpinnerDateModel datemodel = new SpinnerDateModel();
		JSpinner datespinner = new JSpinner(datemodel);
		datespinner.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		datespinner.setValue(new Date());
		JSpinner.DateEditor editor = new JSpinner.DateEditor(datespinner,"yyyy-MM-dd HH:mm:ss");
		datespinner.setEditor(editor);
		datespinner.setBounds(146, 20, 124, 77);
		configPanel.add(datespinner);
		
		JButton btnSetTime = new JButton("设置时间");
		btnSetTime.setBounds(146, 107, 124, 23);
		configPanel.add(btnSetTime);
		
		JButton btnRevertTime = new JButton("还原当前时间");
		
		btnRevertTime.setBounds(146, 136, 124, 23);
		configPanel.add(btnRevertTime);
		
		JButton btnPullConfig = new JButton("获取配置文件");
		btnPullConfig.setBounds(10, 20, 126, 23);
		configPanel.add(btnPullConfig);
		//获取运行配置
		btnPullConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					getConfigs();
				}
		});
		
		JPanel inhandApp = new JPanel();
		inhandApp.setLayout(null);
		inhandApp.setBorder(BorderFactory.createTitledBorder("Inhand应用"));
		inhandApp.setBackground(Color.WHITE);
		inhandApp.setBounds(300, 194, 280, 178);
		frame.getContentPane().add(inhandApp);
		
//		JButton btnChooseApps = new JButton("选择Apk");
//		btnChooseApps.setIcon(new ImageIcon("./toolIcon/open.png"));
//
//		btnChooseApps.setBounds(10, 136, 109, 23);
//		inhandApp.add(btnChooseApps);
		
		
//如下代码使用Jmeter，现去掉这些功能
/*
		JPanel channelSetup = new JPanel();
		channelSetup.setLayout(null);
		channelSetup.setBorder(BorderFactory.createTitledBorder("货道快速配置、退测试款"));
		channelSetup.setBackground(Color.WHITE);
		channelSetup.setBounds(583, 382, 280, 179);
		frame.getContentPane().add(channelSetup);
		channelSetup.setVisible(false);
		
		
		JLabel label_ServerIP = new JLabel("服务器：");
		label_ServerIP.setBounds(10, 27, 54, 15);
		channelSetup.add(label_ServerIP);
		JTextField passwordField = new JTextField();
		passwordField.setBounds(56, 84, 110, 21);
		passwordField.setText("a123456");
		channelSetup.add(passwordField);
		JComboBox<String> ServerIPcomboBox = new JComboBox<String>();
		ServerIPcomboBox.setEditable(true);
		ServerIPcomboBox.setBounds(56, 24, 110, 21);
		ServerIPcomboBox.addItem("121.42.28.70");
		ServerIPcomboBox.addItem("115.28.180.246");
		channelSetup.add(ServerIPcomboBox);
		JComboBox<String> userNamecomboBox = new JComboBox<String>();
		userNamecomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch (userNamecomboBox.getSelectedItem().toString()) {
				case "lizy@inhand.com.cn":
					passwordField.setText("123456");
					break;
				case "940740274@qq.com":
					passwordField.setText("a123456");
					break;
				case "czztest@123.com":
					passwordField.setText("a123456");
					break;
				default:
					passwordField.setText("a123456");
					break;
				}
				
			}
		});
		userNamecomboBox.setBounds(56, 53, 110, 21);
		userNamecomboBox.addItem("940740274@qq.com");
		userNamecomboBox.addItem("lizy@inhand.com.cn");
		userNamecomboBox.addItem("czztest@123.com");
		userNamecomboBox.setEditable(true);
		channelSetup.add(userNamecomboBox);
		
		JLabel label_userName = new JLabel("用户名：");
		label_userName.setBounds(10, 56, 54, 15);
		channelSetup.add(label_userName);
		
		JLabel label_password = new JLabel("密  码：");
		label_password.setBounds(10, 87, 54, 15);
		channelSetup.add(label_password);
		JLabel label_VMCassetid = new JLabel("VMC：");
		label_VMCassetid.setBounds(10, 118, 54, 15);
		channelSetup.add(label_VMCassetid);
		JComboBox<String> VMCassetidcomboBox = new JComboBox<String>();
		VMCassetidcomboBox.setBounds(56, 115, 110, 21);
		VMCassetidcomboBox.addItem("czz_test");
		VMCassetidcomboBox.addItem("Inhand_cd");
		VMCassetidcomboBox.addItem("fujismart");
		VMCassetidcomboBox.setEditable(true);
		channelSetup.add(VMCassetidcomboBox);
		
		JComboBox<String> modelcomboBox = new JComboBox<String>();
		modelcomboBox.setBounds(56, 146, 110, 21);
		modelcomboBox.addItem("SimulatorInit20");
		modelcomboBox.addItem("aucma_Drink&Complex");
		modelcomboBox.addItem("fuji_Drink&Grid");
		channelSetup.add(modelcomboBox);
		
		JTextField refundStartField = new JTextField();
		Date nowtime = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(nowtime);
		rightNow.add(Calendar.DAY_OF_YEAR, -7);
		Date beforetime = rightNow.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String time2 = formatter.format(nowtime);
		String time3 = formatter.format(beforetime);
		refundStartField.setBounds(176, 39, 93, 21);
		channelSetup.add(refundStartField);
		JTextField refundEndField = new JTextField();
		refundEndField.setBounds(176, 84, 93, 21);
		channelSetup.add(refundEndField);
		refundEndField.setText(time2);
		refundStartField.setText(time3);
		
		JButton btnSetupChannel = new JButton("配置货道");
		btnSetupChannel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				class setupChannelThread implements Runnable{
					@Override
					public void run() {
						try {
							//System.out.print(ServerIPcomboBox.getSelectedItem());
							String SetupConfigFile = "./jmeter/channelSetup.csv";
							deleteFile(SetupConfigFile);
							creatNewFile(SetupConfigFile); 
					        String refundStartTime = date2TimeStamp(refundStartField.getText()+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
					        String refundEndTime = date2TimeStamp(refundEndField.getText()+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
					        writeFile(SetupConfigFile, ServerIPcomboBox.getSelectedItem()+","+userNamecomboBox.getSelectedItem()+
					        		","+passwordField.getText()+","+VMCassetidcomboBox.getSelectedItem()+","+modelcomboBox.getSelectedItem()+","+refundStartTime+","+refundEndTime);
					        readFile(SetupConfigFile);
					        JmeterTest.runScript("./jmeter/SimulatorInit20_userDefine.jmx");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				}
				setupChannelThread channelT = new setupChannelThread();
	    		Thread t1 = new Thread(channelT);
	    		t1.start();
			}
		});
		btnSetupChannel.setBounds(176, 146, 93, 21);
		channelSetup.add(btnSetupChannel);
		
		JLabel label_modelName = new JLabel("机型：");
		label_modelName.setBounds(10, 149, 54, 15);
		channelSetup.add(label_modelName);
		
		
		JButton btnRefund = new JButton("退款");
		btnRefund.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	        	class refundThread implements Runnable{
					@Override
					public void run() {
						try {
							String SetupConfigFile = "./jmeter/channelSetup.csv";
							//String SetupConfigFile = "./jmeter/channelSetup.csv";
							deleteFile(SetupConfigFile);
							creatNewFile(SetupConfigFile);
					        String refundStartTime = date2TimeStamp(refundStartField.getText()+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
					        String refundEndTime = date2TimeStamp(refundEndField.getText()+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
					        writeFile(SetupConfigFile, ServerIPcomboBox.getSelectedItem()+","+userNamecomboBox.getSelectedItem()+
					        		","+passwordField.getText()+","+VMCassetidcomboBox.getSelectedItem()+","+modelcomboBox.getSelectedItem()+","+refundStartTime+","+refundEndTime);
					        readFile(SetupConfigFile);
					        JmeterTest.runScript("./jmeter/refund_userDefine.jmx");
//							JmeterTest.runScript("./jmeter/Refund_userDefine3.jmx");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
	        	}
	        	refundThread refundT = new refundThread();
	    		Thread t1 = new Thread(refundT);
	    		t1.start();
				
			}
		});
		btnRefund.setBounds(176, 114, 93, 23);
		channelSetup.add(btnRefund);
		
		JLabel label_refundStartTime = new JLabel("交易起始时间：");
		label_refundStartTime.setBounds(176, 24, 93, 15);
		channelSetup.add(label_refundStartTime);
		
		JLabel label_refundEndTime = new JLabel("交易截止时间：");
		label_refundEndTime.setBounds(176, 70, 93, 15);
		channelSetup.add(label_refundEndTime);
*/	
		JPanel CloudSetup = new JPanel();
		CloudSetup.setLayout(null);
		CloudSetup.setBorder(BorderFactory.createTitledBorder("连接平台配置"));
		CloudSetup.setBackground(Color.WHITE);
		CloudSetup.setBounds(300, 382, 280, 179);
		frame.getContentPane().add(CloudSetup);

		
		JButton btnUpdateCloudConfig = new JButton("更新配置");
		btnUpdateCloudConfig.setBounds(184, 146, 86, 23);
		CloudSetup.add(btnUpdateCloudConfig);
		
		orgName = new JComboBox<String>();
		orgName.setBounds(74, 24, 118, 21);
		orgName.addItem("");
//		orgName.addItem("inhand-chenzhiz");
//		orgName.addItem("chenzhiz");
//		orgName.addItem("fengorg");
//		orgName.addItem("lizy_inhand");
//		orgName.addItem("czztest");
		orgName.setEditable(true);
		orgNameeditor = orgName.getEditor();
		CloudSetup.add(orgName);
		customize.addressRead(orgName,"orgname");

		
		serverAddress = new JComboBox<String>();
		serverAddress.setBounds(74, 55, 118, 21);
		serverAddress.addItem("");
//		serverAddress.addItem("121.42.28.70");
//		serverAddress.addItem("182.150.21.232:10081");
//		serverAddress.addItem("115.28.180.246");
//		serverAddress.addItem("mall.inhand.com.cn");
		customize.addressRead(serverAddress,"serveraddress");
		serverAddress.setEditable(true);
		serverAddresseditor = serverAddress.getEditor();
		CloudSetup.add(serverAddress);
		
		serialPort = new JComboBox<String>();
		serialPort.setBounds(74, 88, 118, 21);
		serialPort.setEditable(true);
		serialPort.addItem("");
		serialPort.addItem("ttyO1");
		serialPort.addItem("ttyO2");
		serialPort.addItem("ttyO3");
		serialPort.addItem("ttyO4");
		serialPort.addItem("ttyO5");
		serialPort.addItem("ttyO6");
		serialPort.addItem("ttyO7");
		serialPort.addItem("ttyO8");
		serialPorteditor = serialPort.getEditor();
		CloudSetup.add(serialPort);
		
		
		JLabel labelorgName = new JLabel("机构名：");
		labelorgName.setBounds(10, 27, 54, 15);
		CloudSetup.add(labelorgName);
		
		JLabel labelServerAddress = new JLabel("平台地址：");
		labelServerAddress.setBounds(10, 58, 68, 15);
		CloudSetup.add(labelServerAddress);
		
		JLabel labelVMCport = new JLabel("VMC串口：");
		labelVMCport.setBounds(10, 91, 68, 15);
		CloudSetup.add(labelVMCport);
		
		JButton btnGetCloudConfig = new JButton("刷新");
		btnGetCloudConfig.addMouseListener(new MouseAdapter() {
			private Process p;
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					newfolder("C:\\inhandTool\\config\\temp");
					deleteFile("C:\\inhandTool\\config\\temp\\config.xml");
					deleteFile("C:\\inhandTool\\config\\temp\\smartvm_cfg.xml");
					String command1 = "cmd.exe /c adb pull sdcard/inbox/config/config.xml C:\\inhandTool\\config\\temp";
					String command2 = "cmd.exe /c adb pull sdcard/inbox/config/smartvm_cfg.xml C:\\inhandTool\\config\\temp";
					p = Runtime.getRuntime().exec(command1);
					p.waitFor();
					p.destroy();
					p = Runtime.getRuntime().exec(command2);
					p.waitFor();
					p.destroy();
					//Thread.sleep(1000);
					String serveraddressPath = "C:\\inhandTool\\config\\temp\\config.xml";
					String orgNamePath = "C:\\inhandTool\\config\\temp\\smartvm_cfg.xml";
					DocumentBuilderFactory serveraddressdbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilderFactory orgNamedbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder serveraddressdBuilder, orgNamedBuilder,vendordBuilder;
					serveraddressdBuilder = serveraddressdbFactory.newDocumentBuilder();
					Document serveraddressdoc = serveraddressdBuilder.parse(serveraddressPath);
					NodeList serveraddresslist = serveraddressdoc.getElementsByTagName("server-address");
					Element serveraddressele = (Element) serveraddresslist.item(0);
					orgNamedBuilder = orgNamedbFactory.newDocumentBuilder();
					Document orgNamedoc = orgNamedBuilder.parse(orgNamePath);
					NodeList orgNamelist = orgNamedoc.getElementsByTagName("org-name");
					NodeList seriallist = orgNamedoc.getElementsByTagName("cabinet");
					Element orgNameele = (Element) orgNamelist.item(0);
					Element serialele = (Element) seriallist.item(0);
					DocumentBuilderFactory vendordbFactory = DocumentBuilderFactory.newInstance();
					vendordBuilder = vendordbFactory.newDocumentBuilder();
					Document vendordoc = vendordBuilder.parse(orgNamePath);
					NodeList vendorlist = vendordoc.getElementsByTagName("current-vendor");
					Element vendorele = (Element) vendorlist.item(0);
					String vendornum = vendorele.getTextContent();
					NodeList vendorNamelist = vendordoc.getElementsByTagName("vendor");
					Element vendorNameele = (Element) vendorNamelist.item(Integer.parseInt(vendornum)-1);
					//serverAddress.setText(serveraddressele.getTextContent());
					//orgNameeditor.setText(orgNameele.getTextContent());
					//Map<String, String>vendormap = new HashMap<String, String>();
					nowVendor.removeAllItems();
					for(int i=0 ;i<vendorNamelist.getLength();i++){
						vendormap.put(Integer.toString(i), vendorNamelist.item(i).getTextContent());
					}
					Iterator<Map.Entry<String, String>> it = vendormap.entrySet().iterator();
					while (it.hasNext()) {  
					    Map.Entry<String, String> entry = it.next();
					    //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
					    nowVendor.addItem(entry.getValue());
					}
					nowVendoreditor.setItem(vendorNameele.getTextContent());
					nowVendor.setSelectedItem(vendorNameele.getTextContent());
					orgNameeditor.setItem(orgNameele.getTextContent());
					orgName.setSelectedItem(orgNameele.getTextContent());
					serverAddresseditor.setItem(serveraddressele.getTextContent());
					serverAddress.setSelectedItem(serveraddressele.getTextContent());
					serialPorteditor.setItem(serialele.getAttribute("serial"));
					serialPort.setSelectedItem(serialele.getAttribute("serial"));
				} catch (Exception e2) {
				}
				
			}
		});

		btnGetCloudConfig.setBounds(10, 146, 86, 23);
		CloudSetup.add(btnGetCloudConfig);
		
		JLabel lblNewLabel_1 = new JLabel("当前厂家：");
		lblNewLabel_1.setBounds(10, 119, 68, 15);
		CloudSetup.add(lblNewLabel_1);
		
		nowVendor = new JComboBox<String>();
		nowVendoreditor = nowVendor.getEditor();
		nowVendor.setBounds(74, 119, 118, 21);
		CloudSetup.add(nowVendor);
		
		JButton addOrg = new JButton("ADD");
		addOrg.setToolTipText("新增机构");
		addOrg.setFont(new Font("宋体", Font.PLAIN, 12));
		addOrg.setBounds(202, 23, 54, 23);
		CloudSetup.add(addOrg);
		addOrg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Frame frame = new customize("orgname");
				frame.setVisible(true);
			}
		});
		
		JButton addAddress = new JButton("ADD");
		addAddress.setToolTipText("新增平台地址");
		addAddress.setFont(new Font("宋体", Font.PLAIN, 12));
		addAddress.setBounds(202, 54, 54, 23);
		CloudSetup.add(addAddress);
		addAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Frame frame = new customize("serveraddress");
				frame.setVisible(true);
			}
		});
		
		//更新并下发基本配置，修改VMC串口、机构名称和服务器地址
		btnUpdateCloudConfig.addMouseListener(new MouseAdapter() {
			private Process p;
			@Override
			public void mouseClicked(MouseEvent e) {
				class CloudCofigThread implements Runnable{
					@Override
					public void run() {
						try{
							now = 0;
							int all = 10;
							installprogressBar.setValue(0);
							
							newfolder("C:\\inhandTool\\config\\temp");
							deleteFile("C:\\inhandTool\\config\\temp\\config.xml");
							deleteFile("C:\\inhandTool\\config\\temp\\smartvm_cfg.xml");
							InstallProgress(all, now+=1, installprogressBar);
							String command1 = "cmd.exe /c adb pull sdcard/inbox/config/config.xml C:\\inhandTool\\config\\temp";
							String command2 = "cmd.exe /c adb pull sdcard/inbox/config/smartvm_cfg.xml C:\\inhandTool\\config\\temp";
							String command3 = "cmd.exe /c adb push C:\\inhandTool\\config\\temp\\smartvm_cfg.xml sdcard/inbox/config";
							String command4 = "cmd.exe /c adb push C:\\inhandTool\\config\\temp\\config.xml sdcard/inbox/config";
							//String command5 = "cmd.exe /c adb shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
							p = Runtime.getRuntime().exec(command1);
							p.waitFor();
							p.destroy();
							InstallProgress(all, now+=1, installprogressBar);
							p = Runtime.getRuntime().exec(command2);
							p.waitFor();
							p.destroy();
							InstallProgress(all, now+=1, installprogressBar);
							//Thread.sleep(1000);
							InstallProgress(all, now+=1, installprogressBar);
				        	//读取xml
							String serveraddressPath = "C:\\inhandTool\\config\\temp\\config.xml";
							String orgNamePath = "C:\\inhandTool\\config\\temp\\smartvm_cfg.xml";
							DocumentBuilderFactory serveraddressdbFactory = DocumentBuilderFactory.newInstance();
							DocumentBuilderFactory orgNamedbFactory = DocumentBuilderFactory.newInstance();
							DocumentBuilder serveraddressdBuilder, orgNamedBuilder;
							serveraddressdBuilder = serveraddressdbFactory.newDocumentBuilder();
							Document serveraddressdoc = serveraddressdBuilder.parse(serveraddressPath);
							NodeList serveraddresslist = serveraddressdoc.getElementsByTagName("server-address");
							Element serveraddressele = (Element) serveraddresslist.item(0);
							orgNamedBuilder = orgNamedbFactory.newDocumentBuilder();
							Document orgNamedoc = orgNamedBuilder.parse(orgNamePath);
							NodeList orgNamelist = orgNamedoc.getElementsByTagName("org-name");
							NodeList seriallist = orgNamedoc.getElementsByTagName("cabinet");
							Element orgNameele = (Element) orgNamelist.item(0);
							
							NodeList vendorlist = orgNamedoc.getElementsByTagName("current-vendor");
							Element vendorele = (Element) vendorlist.item(0);
							String key = getKey(vendormap, (String) nowVendor.getSelectedItem());
				            int keyint = Integer.parseInt(key)+1;
				            key = Integer.toString(keyint);
				            InstallProgress(all, now+=1, installprogressBar);
				            //修改xml
							//serialele.setAttribute("serial", serialPort.getSelectedItem().toString());
				            serveraddressele.setTextContent((String) serverAddress.getSelectedItem());
				            orgNameele.setTextContent((String) orgName.getSelectedItem());
				            
				            for(int i=0;i<seriallist.getLength();i++){
				            	Element serialele = (Element) seriallist.item(i);
				            	serialele.setAttribute("vendor", (String) key);
				            	serialele.setAttribute("serial", (String) serialPort.getSelectedItem());
				            }
				            
				            vendorele.setTextContent((String) key);
				            InstallProgress(all, now+=1, installprogressBar);
				            //保存xml
				            TransformerFactory tf = TransformerFactory.newInstance();
				            Transformer transformer = tf.newTransformer();
				            DOMSource AddressdomSource = new DOMSource(serveraddressdoc);
				            DOMSource orgNamedomSource = new DOMSource(orgNamedoc);
				            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				            FileOutputStream addressfos = new FileOutputStream(serveraddressPath);
				            FileOutputStream orgNamefos = new FileOutputStream(orgNamePath);
				            StreamResult Addressresult = new StreamResult(addressfos);
				            StreamResult orgNameresult = new StreamResult(orgNamefos);
				            transformer.transform(AddressdomSource, Addressresult);
				            transformer.transform(orgNamedomSource, orgNameresult);
				            addressfos.close();
				            orgNamefos.close();
				            InstallProgress(all, now+=1, installprogressBar);
				            p = Runtime.getRuntime().exec(command3);
				            p.waitFor();
							p.destroy();
				            InstallProgress(all, now+=1, installprogressBar);
							p = Runtime.getRuntime().exec(command4);
							p.waitFor();
							p.destroy();
							InstallProgress(all, now+=1, installprogressBar);
							//Thread.sleep(1000);
							InstallProgress(all, now+=1, installprogressBar);
							RestartAPP();
							//Runtime.getRuntime().exec(command5);
							installprogressBar.setValue(100);
						}
				        catch(Exception e1){
				        	e1.printStackTrace();
				        }
					}
				}
				CloudCofigThread CloudCofigT = new CloudCofigThread();
	    		Thread t1 = new Thread(CloudCofigT);
	    		t1.start();
			}
		});
		
		
		//蜂窝网络状态显示
//		JLabel label_Switch_mdata = new JLabel("New label");
//		label_Switch_mdata.setBounds(590, 65, 54, 15);
//		frame.getContentPane().add(label_Switch_mdata);
		JButton btn_mDataConnectionState = new JButton("<html>蜂窝网络<br>状态:UNKNOWN</html>");
		btn_mDataConnectionState.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				class breaknet implements Runnable{
					@Override
					public void run() {
						btn_mDataConnectionState.setBackground(new Color(255, 160, 122));
						btn_mDataConnectionState.setText("<html>蜂窝网络<br>状态:OFF</html>");
						btn_mDataConnectionState.setEnabled(true);
						mDataConnectionState = false;
						DataConnectionFlag = true;
						String command_off = "adb shell su -c svc data disable";
						try {
							while(DataConnectionFlag){
								Process p = Runtime.getRuntime().exec(command_off);
								p.waitFor();
								p.destroy();
								Thread.sleep(3000);
							}
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					
				}
				breaknet breaknetT = new breaknet();
	    		Thread t1 = new Thread(breaknetT);
				if (mDataConnectionState) {
		    		t1.start();
				}
				else {
					DataConnectionFlag = false;
					t1.interrupt();
					String command2 = "cmd.exe /c adb shell su -c svc data enable";
					try {
						Process p = Runtime.getRuntime().exec(command2);
						p.waitFor();
						p.destroy();
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
					btn_mDataConnectionState.setBackground(new Color(240, 230, 140));
					btn_mDataConnectionState.setText("<html>蜂窝网络<br>状态:OFF</html>");
					btn_mDataConnectionState.setEnabled(true);
				}
			}
		});
		btn_mDataConnectionState.setBounds(590, 507, 92, 43);
		frame.getContentPane().add(btn_mDataConnectionState);
		
		JButton btn_mWifiConnectionState = new JButton("<html>WIFI网络<br>状态:UNKNOWN</html>");
		btn_mWifiConnectionState.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				class breaknet implements Runnable{
					@Override
					public void run() {
						btn_mWifiConnectionState.setBackground(new Color(255, 160, 122));
						btn_mWifiConnectionState.setText("<html>WIFI网络<br>状态:OFF</html>");
						btn_mWifiConnectionState.setEnabled(true);
						mWifiConnectionState = false;
						WifiConnectionFlag = true;
						String command_off = "cmd.exe /c adb shell su -c svc wifi disable";
						try {
							while(WifiConnectionFlag){
								Process p = Runtime.getRuntime().exec(command_off);
								p.waitFor();
								p.destroy();
								Thread.sleep(3000);
							}
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					
				}
				breaknet breaknetT = new breaknet();
	    		Thread t1 = new Thread(breaknetT);
				if (mWifiConnectionState) {
		    		t1.start();
				}
				else {
					WifiConnectionFlag = false;
					t1.interrupt();
					String command2 = "cmd.exe /c adb shell su -c svc wifi enable";
					try {
						Process p = Runtime.getRuntime().exec(command2);
						p.waitFor();
						p.destroy();
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
					btn_mWifiConnectionState.setBackground(new Color(240, 230, 140));
					btn_mWifiConnectionState.setText("<html>WIFI网络<br>状态:OFF</html>");
					btn_mWifiConnectionState.setEnabled(true);
				}
			}
		});
		btn_mWifiConnectionState.setBounds(682, 507, 92, 43);
		frame.getContentPane().add(btn_mWifiConnectionState);
		
		
		//已选APK框
		JTextArea choosedappsArea = new JTextArea("将需要安装的APK文件拖拽至此\n文件路径不能包含中文和空格\n");
		choosedappsArea.setLineWrap(true);
		choosedappsArea.setEditable(false);
		choosedappsArea.setBounds(10, 21, 171, 63);
		choosedappsArea.setTransferHandler(new TransferHandler(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
            public boolean importData(JComponent comp, Transferable t) {
                try {
                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);

                    String filepath = o.toString();
                    if (filepath.startsWith("[")) {
                        filepath = filepath.substring(1);
                    }
                    if (filepath.endsWith("]")) {
                        filepath = filepath.substring(0, filepath.length() - 1);
                    }
                    String[] strs = filepath.split(", ");
                    String regexstr = "[\u4e00-\u9fa5\u0020]";
            		Pattern p = Pattern.compile(regexstr);
            		for (String configstring : strs) {
            			//System.out.println(configstring);
            	        Matcher m = p.matcher(configstring);
            			if(m.find()){
            				//System.out.print("\n包含中文");
            				Component tip = null;
							JOptionPane.showMessageDialog(tip, configstring+"\n文件路径不能包含中文和空格", "添加失败",JOptionPane.WARNING_MESSAGE);
							choosedappsArea.setText("");
                    }
            			else{
            				ChoosedappsStr.add(configstring);
            				choosedappsArea.setText("");
            			}
            		}
            		for (String ChoosedConfigstring : ChoosedappsStr) {
            			choosedappsArea.append(ChoosedConfigstring+"\n");
            		}
                    return true;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                
                return false;
            }
            @Override
            public boolean canImport(JComponent comp, DataFlavor[] flavors) {
                for (int i = 0; i < flavors.length; i++) {
                    if (DataFlavor.javaFileListFlavor.equals(flavors[i])) {
                        return true;
                    }
                }
                return false;
            }
        });
		
		JScrollPane choosedappsScrollPane = new JScrollPane(choosedappsArea);
		choosedappsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		choosedappsScrollPane.setBounds(10, 21, 177, 105);
		inhandApp.add(choosedappsScrollPane);
		
		//清空apps
		JButton btnclearApps = new JButton("清空");
		btnclearApps.setToolTipText("\u6E05\u7A7A");
		btnclearApps.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Choosedapps = null;
				ChoosedappsStr.clear();
				choosedappsArea.setText("选择APK文件或拖拽APK文件至文本框\n");
			}
		});
		//btnclearApps.setIcon(new ImageIcon(getClass().getResource("/toolIcon/searchreset.png")));
		btnclearApps.setBounds(112, 128, 75, 23);
		inhandApp.add(btnclearApps);
		
		JButton btnInstallAll = new JButton("<html>卸载并安装</html>");
		btnInstallAll.setBounds(197, 68, 73, 45);
		inhandApp.add(btnInstallAll);
		
		JButton btnOnlyInstall = new JButton("仅安装");
		btnOnlyInstall.setBounds(197, 128, 73, 23);
		inhandApp.add(btnOnlyInstall);
		
		installprogressBar = new JProgressBar();
		installprogressBar.setStringPainted(true);
		installprogressBar.setBounds(10, 154, 260, 14);
		inhandApp.add(installprogressBar);
		
		JButton btnInstalled = new JButton("当前版本");
		btnInstalled.setBounds(198, 20, 72, 38);
		inhandApp.add(btnInstalled);
		
			JButton btnUninstallAll = new JButton("全部卸载");
			btnUninstallAll.setBounds(10, 127, 92, 23);
			inhandApp.add(btnUninstallAll);
			
			//卸载ALLApk
			btnUninstallAll.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					class uninstall implements Runnable{
						@Override
						public void run() {
							now = 0;
							int all = 16;
							installprogressBar.setValue(0);
							Process p;
							try {
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.videoplayer");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.smartvm");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.smartvm.theme");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.vmcsettings");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.vcs");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.ads");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.dms");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.game");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.inboxcore");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.payservice");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.slavevmcservice");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.vmcservice");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inbox.vm.vmcplugservice");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.member.greenfortune");
								p.waitFor();
								p.destroy();
								installprogressBar.setValue(100);
							} catch (IOException | InterruptedException e) {
								e.printStackTrace();
							}
							
						}
						
					}
					uninstall uIt = new uninstall();
					Thread t1 = new Thread(uIt);
					t1.start();
				}
			});
		btnInstalled.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Process p = Runtime.getRuntime().exec("adb shell am start com.inhand.inboxcore/.fragment.AppVersionInfo");
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		//仅安装
		btnOnlyInstall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				try {
//					String batName = "./bat/OnlyInstall.bat";
//					deleteFile(batName);
//					creatNewFile(batName);
////					for(int i = 0; i < Choosedapps.length; i++){
////						//System.out.print(Choosedapps[i].getName()+"\n");
////						writeFile(batName, "adb install " + Choosedapps[i].getAbsolutePath() + "\n");
////					}
//					for(String tmp:ChoosedappsStr){
//			            //System.out.println(tmp);
//			            writeFile(batName, "adb install -r " + tmp + "\n");
//			        }
//					readFile(batName);
//					Process p = Runtime.getRuntime().exec(batName);
//					if(p.waitFor()==0){
//						Runtime.getRuntime().exec("cmd.exe /k adb shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP");
//					}
//					
//					//deleteFile(batName);
//				} catch (IOException | InterruptedException e1) {
//					e1.printStackTrace();
//				}
				class onlyinstall implements Runnable{
					@Override
					public void run() {
						now = 0;
						int all = 1;
						all = all + ChoosedappsStr.size();
						installprogressBar.setValue(0);
						installprogressBar.setValue(0);
						try{
							for(String tmp:ChoosedappsStr){
					            Process p1 = Runtime.getRuntime().exec("cmd.exe /c adb install -r " + tmp);
					            p1.waitFor();
					            p1.destroy();
					            InstallProgress(all, now+=1, installprogressBar);
							}
							RestartAPP();
							//Runtime.getRuntime().exec("cmd.exe /c adb shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP");
							installprogressBar.setValue(100);
						}
						catch(IOException | InterruptedException e1){
							e1.printStackTrace();
						}
					}
				}
				onlyinstall oIt = new onlyinstall();
				Thread t1 = new Thread(oIt);
				t1.start();
			}
		});
		//卸载并安装所选APK
		btnInstallAll.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
					class Install implements Runnable{
						@Override
						public void run() {
							try{
								now = 0;
								int all = 16;
								all = all + ChoosedappsStr.size() + 1;
								installprogressBar.setValue(0);
								Process p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.videoplayer");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.smartvm");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.smartvm.theme");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.vmcsettings");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.vcs");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.ads");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.dms");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.game");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.inboxcore");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.payservice");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.slavevmcservice");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.vmcservice");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inbox.vm.vmcplugservice");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb uninstall com.inhand.member.greenfortune");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb shell rm -rf sdcard/inbox/apps");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								p = Runtime.getRuntime().exec("cmd.exe /c adb shell mkdir sdcard/inbox/apps");
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								for(String tmp:ChoosedappsStr){
						            Process p1 = Runtime.getRuntime().exec("cmd.exe /c adb push \"" + tmp + "\" sdcard/inbox/apps\n");
						            p1.waitFor();
						            p1.destroy();
						            InstallProgress(all, now+=1, installprogressBar);
								}
								
								p = Runtime.getRuntime().exec("cmd.exe /c adb shell am start com.inhand.install/.InstallActivity");
								p.waitFor();
								p.destroy();
								installprogressBar.setValue(100);
							}
							catch(IOException | InterruptedException e1){
								e1.printStackTrace();
							}
							
						}
					
					}
					Install It = new Install();
					Thread t1 = new Thread(It);
					t1.start();
			}
		});
		//设置Android时间
			btnSetTime.addMouseListener(new MouseAdapter() {
				private Process p;
				@Override
				public void mouseClicked(MouseEvent e) {
					Object time = datespinner.getValue();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd.HHmmss");
					String time2 = formatter.format(time);
					System.out.print(time2);
					try {
						p = Runtime.getRuntime().exec("cmd.exe /c adb shell date -s\"yymmdd.hhmmss\"");
						if(p.waitFor()==0)
							p = Runtime.getRuntime().exec("cmd.exe /c adb shell date -s " + time2);
							p.waitFor();
							p.destroy();
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			});
			//还原Android时间
			btnRevertTime.addMouseListener(new MouseAdapter() {
				private Process p;
				@Override
				public void mouseClicked(MouseEvent e) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd.HHmmss");
					String nowtime = formatter.format(new Date());
					System.out.print(nowtime);
					try {
						p = Runtime.getRuntime().exec("cmd.exe /c adb shell date -s\"yymmdd.hhmmss\"");
						if(p.waitFor()==0)
							p = Runtime.getRuntime().exec("cmd.exe /c adb shell date -s " + nowtime);
							p.waitFor();
							p.destroy();
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
					datespinner.setValue(new Date());
				}
			});
	//保存崩溃日志
			btnCrashlog.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					getCrashLog();
				}
			});
		//保存日志
			btnStartSaveLog.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnStartSaveLog.setEnabled(false);
					btnStopSaveLog.setEnabled(true);
					String formatTime = getFormatTime();
					String command0 = "cmd.exe /c adb logcat -c";
					String command1 = "cmd.exe /c adb logcat -v time ";
					String command2 = "cmd.exe /c start ";
					//String machineid = getMachineId();
					String savepath = logSavePathField.getText();
					File file =new File(savepath);
					String tag = saveLogTagField.getText();
					if(!file.exists()){
						newfolder(savepath);
					}
					if (SaveLogTag) {
						command1 = command1 + "-s " + tag + " >" + savepath + "\\log" +formatTime + ".log";
					}
					else{
						command1 = command1 + ">" + savepath + "\\log" +formatTime + ".log";	
					}
					System.out.print(command1);
					try {
						SaveLogProcess = Runtime.getRuntime().exec(command0);
						//Thread.sleep(200);
						SaveLogProcess = Runtime.getRuntime().exec(command1);
						command2 = command2 + savepath;
						SaveLogProcess = Runtime.getRuntime().exec(command2);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			//停止保存日志
			btnStopSaveLog.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnStartSaveLog.setEnabled(true);
					btnStopSaveLog.setEnabled(false);
					SaveLogProcess.destroy();
					String command1 = "cmd.exe /c TASKKILL /F /IM adb.exe";
					try {
						Process p = Runtime.getRuntime().exec(command1);
						p.waitFor();
						p.destroy();
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			});
		//轮询已连接设备
		adbdevicesTimerDemo(adbdevicesArea,btn_mDataConnectionState,btn_mWifiConnectionState);
		
		//连接断开提示
		ConTip = new JLabel("Device Disconnect");
		ConTip.setBounds(20, 83, 114, 15);
		frame.getContentPane().add(ConTip);
		ConTip.setForeground(Color.RED);
		
		
		JButton changeMachineId = new JButton("更改售货机编号");
		changeMachineId.setBounds(10, 528, 142, 23);
		frame.getContentPane().add(changeMachineId);
		//更改售货机编号
		changeMachineId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame2 = new JFrame("更改售货机编号");
			    frame2.getContentPane().setLayout(null);
			    frame2.setBounds(300, 300, 300, 200);
			    frame2.setVisible(true);
			    JTextField MachineId = new JTextField(getMachineId());
			    MachineId.setBounds(80, 10, 120, 30);
				frame2.getContentPane().add(MachineId);

				
				JButton changeId = new JButton("确认修改");
				changeId.setBounds(80, 80, 120, 30);
				frame2.getContentPane().add(changeId);
				changeId.addMouseListener(new MouseAdapter() {
					private Process p;

					@Override
					public void mouseClicked(MouseEvent e) {
						String machine_id_path = getNowPath()+"/machine_id.txt";
						String commandpush = "cmd.exe /c adb push "+machine_id_path+" sdcard/inbox/config";
						//String commandrestart = "cmd.exe /c adb shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
						FileOutputStream fos = null;
						try {
							fos = new FileOutputStream(machine_id_path);
							String s = MachineId.getText();
				            fos.write(s.getBytes());
				            fos.close();
				            p = Runtime.getRuntime().exec(commandpush);
				            p.waitFor();
//				            p = Runtime.getRuntime().exec(commandrestart);
//				            p.waitFor();
//				            p.destroy();
				            RestartAPP();
				            deleteFile(machine_id_path);
				            frame2.dispose();
				            
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}
			            
					}
				});
			}
		});
		
		JPanel simKeyEventPanel = new JPanel();
		simKeyEventPanel.setBackground(Color.WHITE);
		simKeyEventPanel.setLayout(null);
		simKeyEventPanel.setBorder(BorderFactory.createTitledBorder("模拟按键"));
		simKeyEventPanel.setBounds(590, 15, 194, 169);
		frame.getContentPane().add(simKeyEventPanel);
		
		JButton btnSimKeyHome = new JButton("HOME");
		btnSimKeyHome.setBounds(10, 24, 93, 23);
		simKeyEventPanel.add(btnSimKeyHome);
		
		JButton btnSimKeyBack = new JButton("BACK");
		btnSimKeyBack.setBounds(10, 56, 93, 23);
		simKeyEventPanel.add(btnSimKeyBack);
		
		JButton btnSimKeyMenu = new JButton("MENU");
		btnSimKeyMenu.setBounds(10, 89, 93, 23);
		simKeyEventPanel.add(btnSimKeyMenu);
		
		JButton btnSimKeyMode = new JButton("MODE");
		btnSimKeyMode.setBounds(81, 136, 93, 23);
		simKeyEventPanel.add(btnSimKeyMode);
		
		JButton btnToolUpdate = new JButton("工具更新");
		btnToolUpdate.setBounds(191, 528, 99, 23);
		frame.getContentPane().add(btnToolUpdate);
		btnToolUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UpgradeTool u = new UpgradeTool(filepath, updateHost);
					u.setVisible(true);
//					Thread.sleep(2000);
//					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		updateFlagIcon = new JLabel("...");
		updateFlagIcon.setHorizontalAlignment(SwingConstants.CENTER);
		updateFlagIcon.setBounds(191, 551, 99, 23);
		frame.getContentPane().add(updateFlagIcon);
		
		JButton btnchannelcfg = new JButton("货道快速配置");
		btnchannelcfg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				channelFrame c = new channelFrame();
				c.setVisible(true);
			}
		});
		btnchannelcfg.setBounds(590, 382, 124, 49);
		frame.getContentPane().add(btnchannelcfg);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder("卸载应用"));
		panel.setBackground(Color.WHITE);
		panel.setBounds(590, 194, 194, 178);
		frame.getContentPane().add(panel);
		
		insatlled3app = new JComboBox<String>();
		insatlled3app.setBounds(10, 54, 174, 32);
		panel.add(insatlled3app);
		insatlled3app.addItem("刷新获取已安装应用");
		
		//已安装的第三方应用
		JButton button = new JButton("刷新");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String list3packagecommand = "cmd.exe /c adb shell pm list package -3";
				insatlled3app.removeAllItems();
				Process plist3package = null;
				try {
					plist3package = Runtime.getRuntime().exec(list3packagecommand);
					plist3package.waitFor();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				InputStream isplist3package = plist3package.getInputStream();
				InputStreamReader biplist3package = new InputStreamReader(isplist3package);
				BufferedReader brlist3package = new BufferedReader(biplist3package);
				List<String> packageList=new ArrayList<String>();
				String line;
				try {
					while((line = brlist3package.readLine())!= null){
						if (!line.equals("")) {
							line.trim();
							line = line.replace("package:", "");
							packageList.add(line);
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				insatlled3app.addItem("选择要删除的应用");
				Iterator<String> iterator = packageList.iterator();
				while(iterator.hasNext()){
				    String i = iterator.next();
				    System.out.println(i);
				    insatlled3app.addItem(i);
				}
			}
		});
		button.setBounds(10, 21, 80, 23);
		panel.add(button);
		
		JButton uninstallapp = new JButton("卸载");
		uninstallapp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String uninstallPackageName = insatlled3app.getSelectedItem().toString();
				class uninstall implements Runnable{
					@Override
					public void run() {
						now = 0;
						int all = 1;
						installprogressBar.setValue(0);
						try{
							Process p1 = Runtime.getRuntime().exec("cmd.exe /c adb uninstall "+uninstallPackageName);
				            p1.waitFor();
				            p1.destroy();
				            InstallProgress(all, now+=1, installprogressBar);
							installprogressBar.setValue(100);
							//刷新
							String list3packagecommand = "cmd.exe /c adb shell pm list package -3";
							insatlled3app.removeAllItems();
							Process plist3package = null;
							try {
								plist3package = Runtime.getRuntime().exec(list3packagecommand);
								plist3package.waitFor();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							InputStream isplist3package = plist3package.getInputStream();
							InputStreamReader biplist3package = new InputStreamReader(isplist3package);
							BufferedReader brlist3package = new BufferedReader(biplist3package);
							List<String> packageList=new ArrayList<String>();
							String line;
							try {
								while((line = brlist3package.readLine())!= null){
									if (!line.equals("")) {
										line.trim();
										line = line.replace("package:", "");
										packageList.add(line);
									}
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							insatlled3app.addItem("选择要删除的应用");
							Iterator<String> iterator = packageList.iterator();
							while(iterator.hasNext()){
							    String i = iterator.next();
							    System.out.println(i);
							    insatlled3app.addItem(i);
							}
							Component tip = null;
							JOptionPane.showMessageDialog(tip, uninstallPackageName+"\n已卸载", "卸载完成",JOptionPane.OK_CANCEL_OPTION);
						}
						catch(IOException | InterruptedException e1){
							e1.printStackTrace();
						}
					}
				}
				if (uninstallPackageName.contains("com")) {
					uninstall unins = new uninstall();
					Thread t1 = new Thread(unins);
					t1.start();
				}
				else {
					Component tip = null;
					JOptionPane.showMessageDialog(tip, "请选择需要卸载的包", "提示",JOptionPane.CANCEL_OPTION);
				}
			}
		});
		uninstallapp.setBounds(91, 136, 93, 32);
		panel.add(uninstallapp);
		
		
		//模拟按键
		btnSimKeyHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Process p = Runtime.getRuntime().exec("cmd.exe /c adb shell input keyevent 3");
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSimKeyBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Process p = Runtime.getRuntime().exec("cmd.exe /c adb shell input keyevent 4");
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSimKeyMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Process p = Runtime.getRuntime().exec("cmd.exe /c adb shell input keyevent 82");
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSimKeyMode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Process p = Runtime.getRuntime().exec("cmd.exe /c adb shell am broadcast -a android.intent.action.ENG_MODE_SWITCH");
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		ConTip.setVisible(false);
		
		
		crashLogUpdateThread clut = new crashLogUpdateThread();
		Thread t1 = new Thread(clut);
		t1.start();
		CloudConfigThread cct = new CloudConfigThread();
		Thread t2 = new Thread(cct);
		t2.start();
		

	
		//重启按钮
		btnRestart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame2 = new JFrame("重启");
			    frame2.getContentPane().setLayout(null);
			    frame2.setBounds(300, 300, 300, 200);
			    frame2.setVisible(true);
			    JButton btnrestartapp = new JButton("重启应用");
			    btnrestartapp.setBounds(80, 10, 120, 30);
				frame2.getContentPane().add(btnrestartapp);
				btnrestartapp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//String command = "cmd.exe /c adb shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
						RestartAPP();
						frame2.dispose();
					}
				});
				JButton btnreboot = new JButton("重启设备");
				btnreboot.setBounds(80, 80, 120, 30);
				frame2.getContentPane().add(btnreboot);
				btnreboot.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String command = "cmd.exe /c adb reboot";
						try {
							Process p = Runtime.getRuntime().exec(command);
							p.waitFor();
							p.destroy();
							frame2.dispose();
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		//设备截图
		btnScreenshot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				class PrtscThread implements Runnable{
					@Override
					public void run() {
						String formatTime = getFormatTime();
						String command1 = "cmd.exe /c adb shell /system/bin/screencap -p /sdcard/sc123456.png";
						String command2 = "cmd.exe /c adb pull /sdcard/sc123456.png c:\\inhandTool\\Screenshots\\sc"+formatTime+".png";
						String command3 = "cmd.exe /c adb shell rm -rf /sdcard/sc123456.png";
						String command4 = "cmd.exe /c start C:\\inhandTool\\Screenshots";
						try {
							PrtScTip.setVisible(true);
							PrtScTip.setForeground(Color.ORANGE);
							PrtScTip.setText("截图中……");
							boolean flag = false;
							Process p = Runtime.getRuntime().exec(command1);
							p.waitFor();
							p.destroy();
							while(!flag){
								Thread.sleep(1000);
								Process p1 = Runtime.getRuntime().exec("cmd.exe /c adb shell du -k sdcard/sc123456.png");
								p1.waitFor();
								p1.destroy();
								InputStream is = p.getInputStream();
								InputStreamReader bi = new InputStreamReader(is);
								BufferedReader br = new BufferedReader(bi);
								String message = br.readLine();
								String response = "";
								while(message != null && !"".equals(message)){
									response = message;
									message = br.readLine();	
								}
								//System.out.print("\n"+response);
								String[] a = response.split("sdcard");
								//System.out.print("\n"+a[0].trim());
								String picLength = a[0].trim();
								if (!picLength.equals("0")) {
									flag = true;
								}
							}
//							if(p1.waitFor()==0)
//								System.out.print("截图完成");;
							//Thread.sleep(3000);
							newfolder("C:\\inhandTool\\Screenshots");
							Thread.sleep(100);
							Process p1 = Runtime.getRuntime().exec(command2);
							if(p1.waitFor()==0)
								System.out.print("截图完成");
							Process p2 = Runtime.getRuntime().exec(command3);
							PrtScTip.setText("完成！");
							PrtScTip.setForeground(Color.green);
							Process p3 = Runtime.getRuntime().exec(command4);
							Thread.sleep(5000);
							p1.destroy();
							p2.destroy();
							p3.destroy();
							PrtScTip.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				PrtscThread prtscT = new PrtscThread();
	    		Thread pt = new Thread(prtscT);
	    		pt.start();
			}
		});

		//查看日志
		logcatvtime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String command = "cmd.exe /c start C:\\Windows\\System32\\cmd.exe";
				try {
					Process p = Runtime.getRuntime().exec(command);
					p.waitFor();
					p.destroy();
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			        clipboard.setContents(new StringSelection("adb logcat -v time"), null);
					try {
				        Robot robot;
						robot = new Robot();
						try {
							Thread.currentThread();
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
				        robot.keyPress(KeyEvent.VK_CONTROL);
				        robot.keyPress(KeyEvent.VK_V);
				        robot.keyRelease(KeyEvent.VK_V);
				        robot.keyRelease(KeyEvent.VK_CONTROL);
				        robot.keyPress(KeyEvent.VK_ENTER);
				        robot.keyRelease(KeyEvent.VK_ENTER);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		//查看过滤标签日志
		logcatvtimetags.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String command = "cmd.exe /c start C:\\Windows\\System32\\cmd.exe";
				String tag = tagsField.getText();
				try {
					Process p = Runtime.getRuntime().exec(command);
					p.waitFor();
					p.destroy();
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			        clipboard.setContents(new StringSelection("adb logcat -v time -s "+tag), null);
					try {
				        Robot robot;
						robot = new Robot();
						try {
							Thread.currentThread();
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
				        robot.keyPress(KeyEvent.VK_CONTROL);
				        robot.keyPress(KeyEvent.VK_V);
				        robot.keyRelease(KeyEvent.VK_V);
				        robot.keyRelease(KeyEvent.VK_CONTROL);
				        robot.keyPress(KeyEvent.VK_ENTER);
				        robot.keyRelease(KeyEvent.VK_ENTER);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		//选择apks
//		btnChooseApps.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				Choosedapps = null;
//				Choosedapps = FileChooser.multipathApkChooser();
//				choosedappsArea.setText("");
//				for(int i = 0; i < Choosedapps.length; i++){
//					//System.out.print(Choosedapps[i].getAbsolutePath()+"\n");
//					//System.out.print(Choosedapps[i].getName()+"\n");
//					ChoosedappsStr.add(Choosedapps[i].getAbsolutePath());
//					choosedappsArea.append(Choosedapps[i].getAbsolutePath()+"\n");
//				}
//				//System.out.print(path);
//			}
//		});
		//目录选择器
		chooseSavelogPathbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String path = FileChooser.pathChooser();
				System.out.print(path);
				logSavePathField.setText(path);
			}
		});
		//tag过滤checkbox事件
		checkboxLogTag.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(checkboxLogTag.isSelected()){
					saveLogTagField.setEditable(true);
					SaveLogTag = true;
				}
				else{
					saveLogTagField.setEditable(false);
					saveLogTagField.setText("");
					SaveLogTag = false;
				}
			}
		});
	}
	private void getCrashLog(){
		boolean IsExsit = crashLogIsExsit();
		if (!IsExsit) {
			crashLogUpdateTip.setText("crash_log.txt不存在");
			crashLogUpdateTip.setForeground(Color.GREEN);
			btnCrashlog.setBackground(new Color(240, 240, 240));
		}
		else{
			String command2 = "cmd.exe /c adb pull sdcard/inbox/log/crash_log.txt C:\\inhandTool\\crash_log";
			String command3 = "cmd.exe /c start C:\\inhandTool\\crash_log\\";
			String response = getMachineId();
				if(response == null){
					ConTip.setVisible(true);
				}
				else{
					ConTip.setVisible(false);
					command2 = command2 + "\\" + response;
					newfolder("C:\\inhandTool\\crash_log\\"+response);
					try {
						Process p;
						p = Runtime.getRuntime().exec(command3+response);
						p.waitFor();
						p.destroy();
						//System.out.println(command2);
						p = Runtime.getRuntime().exec(command2);
						p.waitFor();
						p.destroy();
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
				}
		}
	}
	private void getdevices(JTextArea adbdevicesArea,JButton btn_mDataConnectionState,JButton btn_mWifiConnectionState) throws IOException {
		String command = "cmd.exe /c adb devices";
		String response = null;
		String response1 = null;
		String response2 = null;
		Process p = Runtime.getRuntime().exec(command);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		InputStream is = p.getInputStream();
		InputStreamReader bi = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(bi);
		String message = br.readLine();
		while(message != null && !"".equals(message)){
			adbdevicesArea.setText(message);
			response = message;
			message = br.readLine();
		}
		if("List of devices attached ".equals(response)){
			ConTip.setVisible(true);
			adbdevicesArea.setText("error: device not found\n- waiting for device -");
		}
		else{
			ConTip.setVisible(false);
		}
		adbdevicesArea.append("\nMachineID:"+getMachineId());
		String command1 = "cmd.exe /c adb shell dumpsys telephony.registry | grep mDataConnectionState";
		Process p1 = Runtime.getRuntime().exec(command1);
		try {
			p1.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		InputStream is1 = p1.getInputStream();
		InputStreamReader bi1 = new InputStreamReader(is1);
		BufferedReader br1 = new BufferedReader(bi1);
		String message1 = br1.readLine();
		while(message1 != null && !"".equals(message1)){
			//System.out.print(message1);
			response1 = message1;
			message1 = br1.readLine();
		}
		if (response1 != null){
			response1 = response1.trim();
		}
		if("mDataConnectionState=2".equals(response1) && response1 != null){
			btn_mDataConnectionState.setBackground(new Color(0, 250, 154));
			btn_mDataConnectionState.setText("<html>蜂窝网络<br>状态:ON</html>");
			btn_mDataConnectionState.setEnabled(true);
			mDataConnectionState = true;
		}
		else if("mDataConnectionState=0".equals(response1) && response1 != null){
			btn_mDataConnectionState.setBackground(new Color(255, 160, 122));
			btn_mDataConnectionState.setText("<html>蜂窝网络<br>状态:OFF</html>");
			btn_mDataConnectionState.setEnabled(true);
			mDataConnectionState = false;
		}
		else {
			btn_mDataConnectionState.setBackground(new Color(192, 192, 192));
			btn_mDataConnectionState.setText("<html>蜂窝网络<br>状态:UNKNOWN</html>");
			btn_mDataConnectionState.setEnabled(false);
		}
		
		String command2 = "cmd.exe /c adb shell dumpsys wifi | grep \"Wi-Fi is\"";
		Process p2 = Runtime.getRuntime().exec(command2);
		try {
			p2.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		InputStream is2 = p2.getInputStream();
		InputStreamReader bi2 = new InputStreamReader(is2);
		BufferedReader br2 = new BufferedReader(bi2);
		String message2 = br2.readLine();
		while(message2 != null && !"".equals(message2)){
			//System.out.print(message1);
			response2 = message2;
			message2 = br2.readLine();
		}
		if (response2 != null){
			response2 = response2.trim();
		}
		if("Wi-Fi is enabled".equals(response2) && response2 != null){
			btn_mWifiConnectionState.setBackground(new Color(0, 250, 154));
			btn_mWifiConnectionState.setText("<html>WIFI网络<br>状态:ON</html>");
			btn_mWifiConnectionState.setEnabled(true);
			mWifiConnectionState = true;
		}
		else{
			btn_mWifiConnectionState.setBackground(new Color(255, 160, 122));
			btn_mWifiConnectionState.setText("<html>WIFI网络<br>状态:OFF</html>");
			btn_mWifiConnectionState.setEnabled(true);
			mWifiConnectionState = false;
		}
		p1.destroy();
		p2.destroy();
		p.destroy();
		
//		String list3packagecommand = "cmd.exe /c adb shell pm list package -3";
//		Process plist3package = Runtime.getRuntime().exec(list3packagecommand);
//		try {
//			plist3package.waitFor();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		InputStream isplist3package = plist3package.getInputStream();
//		InputStreamReader biplist3package = new InputStreamReader(isplist3package);
//		BufferedReader brlist3package = new BufferedReader(biplist3package);
//		List<String> packageList=new ArrayList<String>();
//		String line;
//		while((line = brlist3package.readLine())!= null){
//			line = line.replace("package:", "");
//			//System.out.println(line);
//			packageList.add(line);
//		}
//		Iterator<String> iterator = packageList.iterator();
//		while(iterator.hasNext()){
//		    String i = iterator.next();
//		    System.out.println(i);
//		}
		
	}
	public String getConfigs(){
		String command2 = "cmd.exe /c adb pull sdcard/inbox/config C:\\inhandTool\\config";
		String command3 = "cmd.exe /c start C:\\inhandTool\\config\\";
		String response = getMachineId();
		String MachineID = response;
			if(response == null){
				ConTip.setVisible(true);
			}
			else{
				ConTip.setVisible(false);
				command2 = command2 + "\\" + response;
				newfolder("C:\\inhandTool\\config\\"+response);
				try {
					Process p;
					p = Runtime.getRuntime().exec(command3+response);
					p.waitFor();
					p.destroy();
					//System.out.println(command2);
					p = Runtime.getRuntime().exec(command2);
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			return MachineID;
	}

	public void adbdevicesTimerDemo(JTextArea adbdevicesArea,JButton mDataConnectionState,JButton mWifiConnectionState)
	{
	Timer timer = new Timer();
	int delay = 0;//ms
	int period = 5000;//ms
	   timer.schedule(new TimerTask() {    
	       public void run() {  
	    	   try{
	    		   getdevices(adbdevicesArea,mDataConnectionState,mWifiConnectionState);
			} catch (IOException e) {
				e.printStackTrace();
			}
	           
	       }  
	   }, delay, period);  
	}
	public void CloudConfigTimerDemo() {
		Timer timer = new Timer();
		int delay = 1000;//ms
		int period = 60000;//ms
		timer.schedule(new TimerTask() {    
	       public void run(){
	    	   try {
					newfolder("C:\\inhandTool\\config\\temp");
					deleteFile("C:\\inhandTool\\config\\temp\\config.xml");
					deleteFile("C:\\inhandTool\\config\\temp\\smartvm_cfg.xml");
					String command1 = "cmd.exe /c adb pull sdcard/inbox/config/config.xml C:\\inhandTool\\config\\temp";
					String command2 = "cmd.exe /c adb pull sdcard/inbox/config/smartvm_cfg.xml C:\\inhandTool\\config\\temp";
					Process p = Runtime.getRuntime().exec(command1);
					p.waitFor();
					p.destroy();
					p = Runtime.getRuntime().exec(command2);
					p.waitFor();
					p.destroy();
					//Thread.sleep(1000);
					String serveraddressPath = "C:\\inhandTool\\config\\temp\\config.xml";
					String orgNamePath = "C:\\inhandTool\\config\\temp\\smartvm_cfg.xml";
					DocumentBuilderFactory serveraddressdbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilderFactory orgNamedbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder serveraddressdBuilder, orgNamedBuilder,vendordBuilder;
					serveraddressdBuilder = serveraddressdbFactory.newDocumentBuilder();
					Document serveraddressdoc = serveraddressdBuilder.parse(serveraddressPath);
					NodeList serveraddresslist = serveraddressdoc.getElementsByTagName("server-address");
					Element serveraddressele = (Element) serveraddresslist.item(0);
					orgNamedBuilder = orgNamedbFactory.newDocumentBuilder();
					Document orgNamedoc = orgNamedBuilder.parse(orgNamePath);
					NodeList orgNamelist = orgNamedoc.getElementsByTagName("org-name");
					NodeList seriallist = orgNamedoc.getElementsByTagName("cabinet");
					Element orgNameele = (Element) orgNamelist.item(0);
					Element serialele = (Element) seriallist.item(0);

					DocumentBuilderFactory vendordbFactory = DocumentBuilderFactory.newInstance();
					vendordBuilder = vendordbFactory.newDocumentBuilder();
					Document vendordoc = vendordBuilder.parse(orgNamePath);
					NodeList vendorlist = vendordoc.getElementsByTagName("current-vendor");
					Element vendorele = (Element) vendorlist.item(0);
					String vendornum = vendorele.getTextContent();
					
					NodeList vendorNamelist = vendordoc.getElementsByTagName("vendor");
					//System.out.print(vendorNamelist.getLength());
					//Map<String, String>vendormap = new HashMap<String, String>();
					nowVendor.removeAllItems();
					for(int i=0 ;i<vendorNamelist.getLength();i++){
						vendormap.put(Integer.toString(i), vendorNamelist.item(i).getTextContent());
					}
					Iterator<Map.Entry<String, String>> it = vendormap.entrySet().iterator();
					while (it.hasNext()) {  
					    Map.Entry<String, String> entry = it.next();
					    //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
					    nowVendor.addItem(entry.getValue());
					}
					Element vendorNameele = (Element) vendorNamelist.item(Integer.parseInt(vendornum)-1);
					
					nowVendoreditor.setItem(vendorNameele.getTextContent());
					nowVendor.setSelectedItem(vendorNameele.getTextContent());
					orgNameeditor.setItem(orgNameele.getTextContent());
					orgName.setSelectedItem(orgNameele.getTextContent());
					serverAddresseditor.setItem(serveraddressele.getTextContent());
					serverAddress.setSelectedItem(serveraddressele.getTextContent());
					serialPorteditor.setItem(serialele.getAttribute("serial"));
					serialPort.setSelectedItem(serialele.getAttribute("serial"));
					p.destroy();
			} catch (Exception e) {
			}
	       }  
	   }, delay, period); 
	}
	public void CrashLogUpdateTimerDemo(JLabel crashLogUpdateTip, JButton btnCrashLog2)
	{
		Timer timer = new Timer();
		int delay = 2000;//ms
		int period = 60000;//ms
		timer.schedule(new TimerTask() {    
	       public void run(){
	    	   boolean IsExsit = crashLogIsExsit();
	    	   if (!IsExsit){
	    		   crashLogUpdateTip.setText("crash_log.txt不存在");
	    		   crashLogUpdateTip.setForeground(Color.GREEN);
	    	   }
	    	   else{
	    		   newfolder("C:\\inhandTool\\crash_log\\temp");
		    	   deleteFile("C:\\inhandTool\\crash_log\\temp\\crash_log.txt");
		    	   String command = "cmd.exe /c adb pull sdcard/inbox/log/crash_log.txt C:\\inhandTool\\crash_log\\temp";
		    	   try {
						Process p = Runtime.getRuntime().exec(command);
						p.waitFor();
						p.destroy();
						//Thread.sleep(3000);
						String tempCrashMd5 = FileMD5.MD5encode("C:\\inhandTool\\crash_log\\temp\\crash_log.txt");
						String machineid = getMachineId();
						String nowCrashMd5 = FileMD5.MD5encode("C:\\inhandTool\\crash_log\\"+machineid+"\\crash_log.txt");
					if(!tempCrashMd5.equals(nowCrashMd5) && nowCrashMd5 != null){
						crashLogUpdateTip.setVisible(true);
						crashLogUpdateTip.setText("CrashLog.txt有更新！");
						crashLogUpdateTip.setForeground(Color.RED);
						btnCrashLog2.setBackground(new Color(255, 160, 122));
					}
					else if (nowCrashMd5 == null && tempCrashMd5 != null) {
						crashLogUpdateTip.setVisible(true);
						crashLogUpdateTip.setText("CrashLog.txt有更新！");
						crashLogUpdateTip.setForeground(Color.RED);
						btnCrashLog2.setBackground(new Color(255, 160, 122));
					}
					else{
						crashLogUpdateTip.setText("CrashLog正常");
						crashLogUpdateTip.setForeground(Color.GREEN);
						crashLogUpdateTip.setVisible(true);
						btnCrashLog2.setBackground(new Color(240, 240, 240));
					}
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
	    	   }
		   		Check checkup = new Check(updateFlagIcon,updateHost);
				Thread tcheck = new Thread(checkup);
				tcheck.start();
	       }  
	   }, delay, period);  
	}
	
	public static String getFormatTime(){
		//Calendar now = Calendar.getInstance();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatTime = sdf.format(d);
        //System.out.println("当前时间：" + formatTime);
		return formatTime;
		
	}
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }
    private static String getKey(Map<String,String> map,String value){  
        String key="";  
        for (Map.Entry<String, String> entry : map.entrySet()) {  
            if(value.equals(entry.getValue())){  
                key=entry.getKey();  
            }  
        }  
        return key;  
    }  
	public static void newfolder(String path){
		//System.out.print(path);
		File file =new File(path);    
		//如果文件夹不存在则创建    
		if  (!file.exists() && !file.isDirectory()){   
		    file.mkdirs();    
		}
	}
	public boolean crashLogIsExsit(){
		String command = "cmd.exe /c adb shell ls sdcard/inbox/log | grep crash_log.txt";
		Process p = null;
		String response = null;
		boolean crashLogEmpty = true;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			InputStream is = p.getInputStream();
			InputStreamReader bi = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(bi);
			String message = br.readLine();
			while(message != null && !"".equals(message)){
				//System.out.println(message);
				response = message;
				message = br.readLine();	
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//System.out.print("RESPONSE:"+response);
		if(response == null){
			crashLogEmpty = false;
		}
		else{
			crashLogEmpty = true;
		}
		p.destroy();
		return crashLogEmpty;
	}
	public String getMachineId(){
		String command1 = "cmd.exe /c adb shell cat sdcard/inbox/config/machine_id.txt";
		Process p = null;
		String response = null;
		try {
			p = Runtime.getRuntime().exec(command1);
			p.waitFor();
			InputStream is = p.getInputStream();
			InputStreamReader bi = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(bi);
			String message = br.readLine();
			while(message != null && !"".equals(message)){
				//System.out.println("MachineID:"+message);
				response = message;
				message = br.readLine();	
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		p.destroy();
		return response;
	}
	public void readFile(String path) throws IOException {
		File file = new File(path);  
        // 读取文件，并且以utf-8的形式写出去  
        BufferedReader bufread;  
        String read;  
        bufread = new BufferedReader(new FileReader(file));  
        while ((read = bufread.readLine()) != null) {  
            System.out.println(read);  
        }  
        bufread.close(); 
	}
	public static void writeFile(String path,String content) throws IOException {
        File file = new File(path);  
        // if file doesnt exists, then create it  
        if (!file.exists()) {  
            file.createNewFile();  
        }  
        FileWriter fw = new FileWriter(file, true);  
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);  
        bw.flush();  
        bw.close(); 
	}
	public static void writeFileEncoding(String path,String content) throws IOException {
		File file = new File(path);
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file,true),"ASCII");   
        BufferedWriter writer=new BufferedWriter(write); 
        writer.write(content);   
        writer.close();
	}
	public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()){
            //System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
	        } 
        else{
        	if (file.isFile())
        	{
        		file.delete();
        		return true;
        	}
        	else
        		return false;
        }
	}
	public static boolean creatNewFile(String fileName) throws IOException {
		File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
            return true;
	        } 
        else 
        	return false;
	}
	public class crashLogUpdateThread implements Runnable{

		@Override
		public void run() {
			CrashLogUpdateTimerDemo(crashLogUpdateTip,btnCrashlog);
		}
	}
	public class CloudConfigThread implements Runnable{

		@Override
		public void run() {
			CloudConfigTimerDemo();
		}
		
	}
	public static void InstallProgress(int all, int now,JProgressBar progressBar){
		double prog = (double)now / (double)all;
//		DecimalFormat    df   = new DecimalFormat("######0.0");
//		lbl.setText(df.format(prog*100)+"%");
		int progbarValue = (int)(prog*100);
		progressBar.setValue(progbarValue);
	}
    public static String getNowPath(){
    	URL url = tool.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;  
        try {  
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码
            File file = new File(filePath);
            filePath = file.getAbsolutePath();
            filePath = filePath.substring(0, filePath.lastIndexOf("\\") + 1);
        }
        catch(IOException ex1){
        }
        return filePath;
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
    public static void RestartAPP(){
    	Process p;
    	try {
			p = Runtime.getRuntime().exec("cmd.exe /c adb shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP");
			p.waitFor();
			p.destroy();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
    }
	class Check implements Runnable {
        //本地版本文件名  
  
        //显示信息  
        //private JLabel isUpdate_label; 
        private JLabel FlagIcon;
		private String host;
		private String netVerStr;
		
        public Check(JLabel FlagIcon, String host) {  
        	//this.isUpdate_label = isUpdate_label; 
        	this.FlagIcon = FlagIcon; 
        	this.host = host;
        }  
		
  
        public void run() {  
        	String ftpHost = host;
    		String ftpUserName = "ubuntu";
    		String ftpPassword = "inhand";
    		int ftpPort = 22;
    		String ftpPath = "/home/liwei/czz";
    		String localPath = filepath + "temp/";
    		newfolder(localPath);
    		String fileName = "ver.txt";
    		
            //更新文件版本标识URL
            try {
            	FtpUtil.downloadSftpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, localPath, fileName); 
            	netVerStr = getNowVer(localPath+"/ver.txt");
                if(netVerStr.equals(AppVersion)){
                	//System.out.print("no need update");
                	FlagIcon.setText("...");
                	FlagIcon.setForeground(Color.BLACK);
                	//FlagIcon.setIcon(new ImageIcon(getClass().getResource("/toolIcon/success.png")));
                }
                else{
                	System.out.print("need update");
                	FlagIcon.setText("可更新");
                	FlagIcon.setForeground(Color.ORANGE);
                	//FlagIcon.setIcon(new ImageIcon(getClass().getResource("/toolIcon/warning.png")));
                }
	            
			} catch (Exception e) {
				FlagIcon.setIcon(new ImageIcon());
				e.printStackTrace();
			}

    }
}
}