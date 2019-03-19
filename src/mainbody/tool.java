package mainbody;

import mainbody.FileChooser;
import mainbody.FtpUtil;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.text.ParseException;
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
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
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
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;






public class tool {

	final static String AppVersion = "v.1.4.11";
	private JFrame frame;
	Timer devicesinfotimer = new Timer();
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
	JComboBox<String> master_serialPort;
	ComboBoxEditor master_serialPorteditor;
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
	private String log_savepath;
	protected String save_log_formatTime;
	private JLabel versionBottom;
	private JButton activity_cfg_Button;
	private JPanel panel_1ogcat;
	private JButton logcatvtime;
	private JButton logcatvtimetags;
	private Label label;
	private Label label_1;
	private JTextField tagsField;
	private Label label_2;
	private JButton clearbeforeLog;
	private JButton addLogTag;
	private JLabel lblNewLabel_3;
	private JPanel panel_save1ogcat;
	private JTextField saveLogTagField;
	private JTextField logSavePathField;
	private FileSystemView fsv;
	private JButton pullHistoryLogbtn;
	private JButton chooseSavelogPathbtn;
	private JCheckBox checkboxLogTag;
	private Label label_3;
	private JButton btnStopSaveLog;
	private JButton btnStartSaveLog;
	private JButton btnScreenshot;
	private JTextArea pushConfigArea;
	private JScrollPane pushConfigscrollPane;
	private JPanel configPanel;
	private JButton btnPushingConfig;
	private JButton clearconfig;
	private SpinnerDateModel datemodel;
	private JButton btnPullConfig;
	private JButton btnPullGameCfg;
	private JButton btnchannelcfg;
	private JPanel inhandApp;
	private JPanel CloudSetup;
	private JButton btnUpdateCloudConfig;
	private JLabel labelorgName;
	private JLabel labelServerAddress;
	private JLabel labelVMCport;
	private JButton btnGetCloudConfig;
	private JLabel lblNewLabel_1;
	private JButton addOrg;
	private JButton addAddress;
	private JCheckBox only_matser_serial_chckbxNewCheckBox;
	private JButton btn_mDataConnectionState;
	private JButton btn_mWifiConnectionState;
	private JTextArea choosedappsArea;
	private JScrollPane choosedappsScrollPane;
	private JButton btnclearApps;
	private JButton btnInstallAll;
	private JButton btnOnlyInstall;
	private JButton btnUninstallAll;
	private JButton changeMachineId;
	private JPanel simKeyEventPanel;
	private JButton btnSimKeyHome;
	private JButton btnSimKeyBack;
	private JButton btnSimKeyMenu;
	private JButton btnSimKeyMode;
	private JSpinner datespinner;
	private JButton btnSetTime;
	private JButton btnRevertTime;
	private JButton btnToolUpdate;
	private JPanel panel;
	private JButton uninstallapp;
	private JButton btnInstalled;
	private JLabel lblNewLabel_2;
	private crashLogUpdateThread clut;
	private CloudConfigThread cct;
	private Thread clutT;
	private Thread cctT;
	private JTextArea adbdevicesArea;
	private JScrollPane adbdevicesAreascrollPane;
	private static JTextArea operation_log_Area;
	private static JScrollPane operation_log_AreascrollPane;
	private JCheckBox crash_log_monitor_CheckBox;
	private JCheckBox set_time_and_restart_checkbox;
	private static JComboBox<String> devices_comboBox;
	static JComboBox<String> commonTagscomboBox;
	private int version_check_count = 0;
	
	
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
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(tool.class.getResource("/com/sun/javafx/scene/web/skin/IncreaseIndent_16x16_JFX.png")));
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/toolIcon/logo.png")));
		frame.setBounds(100, 100, 832, 610);
		frame.getContentPane().setLayout(null);
		String Title = AppVersion+"——By.chenzhiz@inhand.com.cn";
		frame.setTitle("adb工具"+AppVersion);
		versionBottom = new JLabel(Title);
		versionBottom.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 10));
		versionBottom.setBounds(622, 556, 194, 15);
		frame.getContentPane().add(versionBottom);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adbdevicesArea = new JTextArea();
		adbdevicesArea.setLineWrap(true);
		adbdevicesArea.setEditable(false);
		adbdevicesArea.setBounds(10, 47, 152, 36);
		frame.getContentPane().add(adbdevicesArea);
		adbdevicesAreascrollPane = new JScrollPane(adbdevicesArea);
		adbdevicesAreascrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//滚动条总是出现
		adbdevicesAreascrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		adbdevicesAreascrollPane.setBounds(10, 10, 171, 36);
		frame.getContentPane().add(adbdevicesAreascrollPane);
		
		panel_1ogcat = new JPanel();
		panel_1ogcat.setBackground(Color.WHITE);
		panel_1ogcat.setBorder(BorderFactory.createTitledBorder("打印日志"));
		panel_1ogcat.setBounds(10, 116, 280, 169);
		frame.getContentPane().add(panel_1ogcat);
		panel_1ogcat.setLayout(null);
		logcatvtime = new JButton("<html>打印<br>所有日志</html>");
		logcatvtime.setBounds(177, 10, 93, 39);
		panel_1ogcat.add(logcatvtime);
		logcatvtimetags = new JButton("<html>打印<br>过滤日志</html>");
		logcatvtimetags.setBounds(177, 59, 93, 39);
		panel_1ogcat.add(logcatvtimetags);
		
		label = new Label("adb logcat -v time ");
		label.setBounds(10, 27, 118, 23);
		panel_1ogcat.add(label);
		
		label_1 = new Label("adb logcat -v time -s ");
		label_1.setBounds(10, 56, 118, 23);
		panel_1ogcat.add(label_1);
		
		tagsField = new JTextField();
		tagsField.setBounds(49, 111, 118, 21);
		panel_1ogcat.add(tagsField);
		tagsField.setColumns(10);
		
		label_2 = new Label("Tags:");
		label_2.setBounds(10, 97, 33, 23);
		panel_1ogcat.add(label_2);
		
		clearbeforeLog = new JButton("<html>清空<br>日志缓存</html>");
		clearbeforeLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String command = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " logcat -c";
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
		
		commonTagscomboBox = new JComboBox<String>();
		commonTagscomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String s = commonTagscomboBox.getSelectedItem().toString();
					String tags = s.split(":")[1];
					tagsField.setText(tags);
				} catch (Exception e2) {
					
				}
			}
		});
		commonTagscomboBox.setBounds(49, 85, 118, 22);
		customize.addressRead(commonTagscomboBox,"log_tag");
		panel_1ogcat.add(commonTagscomboBox);
		
		addLogTag = new JButton("+");
		addLogTag.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Frame frame = new customize("log_tag");
				frame.setVisible(true);
			}
		});
		addLogTag.setFont(new Font("宋体", Font.PLAIN, 12));
		addLogTag.setBounds(93, 139, 40, 22);
		panel_1ogcat.add(addLogTag);
		
		lblNewLabel_3 = new JLabel("添加常用Tag：");
		lblNewLabel_3.setBounds(10, 143, 93, 15);
		panel_1ogcat.add(lblNewLabel_3);
		
		panel_save1ogcat = new JPanel();
		panel_save1ogcat.setBackground(Color.WHITE);
		panel_save1ogcat.setBorder(BorderFactory.createTitledBorder("实时保存日志"));
		panel_save1ogcat.setBounds(10, 295, 280, 223);
		frame.getContentPane().add(panel_save1ogcat);
		panel_save1ogcat.setLayout(null);
		
		saveLogTagField = new JTextField();
		saveLogTagField.setEditable(false);
		saveLogTagField.setBounds(49, 49, 221, 21);
		panel_save1ogcat.add(saveLogTagField);
		saveLogTagField.setColumns(10);
		
		logSavePathField = new JTextField();
		fsv = FileSystemView.getFileSystemView();
		logSavePathField.setColumns(10);
		logSavePathField.setBounds(10, 129, 260, 30);
		logSavePathField.setText(fsv.getHomeDirectory().toString());
		panel_save1ogcat.add(logSavePathField);
		
		pullHistoryLogbtn = new JButton("导出主日志");
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
						String command = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/log/logcat_main.log "+savepath;
					    String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/log/logcat_main.log.1 "+savepath;
					    String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/log/logcat_main.log.2 "+savepath;
					    String command3 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/log/logcat_main.log.3 "+savepath;
					    String command4 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/log/logcat_main.log.4 "+savepath;
					    String command5 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/log/crash_log.txt "+savepath;
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
		
		chooseSavelogPathbtn = new JButton("<html>选择日志<br>保存路径</html>");
		
		chooseSavelogPathbtn.setBounds(10, 80, 93, 39);
		panel_save1ogcat.add(chooseSavelogPathbtn);
		
		
		checkboxLogTag = new JCheckBox("tag过滤");
		checkboxLogTag.setBackground(Color.WHITE);
		checkboxLogTag.setBounds(10, 20, 103, 23);
		panel_save1ogcat.add(checkboxLogTag);
		

		
		label_3 = new Label("Tags:");
		label_3.setBounds(10, 49, 33, 23);
		panel_save1ogcat.add(label_3);
		
		//停止保存日志
		btnStopSaveLog = new JButton("Stop");
		btnStopSaveLog.setFont(new Font("宋体", Font.PLAIN, 10));
		//btnStopSaveLog.setIcon(new ImageIcon("./toolIcon/stop.png"));
		//btnStopSaveLog.setIcon(new ImageIcon(getClass().getResource("/toolIcon/stop.png")));
		btnStopSaveLog.setBounds(201, 80, 69, 39);
		panel_save1ogcat.add(btnStopSaveLog);
		btnStopSaveLog.setEnabled(false);
		
		//开始保存日志
		btnStartSaveLog = new JButton();
		btnStartSaveLog.setFont(new Font("宋体", Font.PLAIN, 10));
		btnStartSaveLog.setText("Start");
		//btnStartSaveLog.setIcon(new ImageIcon(getClass().getResource("/toolIcon/start.png")));
		btnStartSaveLog.setBounds(113, 80, 78, 39);
		panel_save1ogcat.add(btnStartSaveLog);
		
		//重启按钮
		JButton btnrestartapp = new JButton("重启应用");
	    btnrestartapp.setBounds(191, 11, 93, 36);
		frame.getContentPane().add(btnrestartapp);
		btnrestartapp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//String command = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
				RestartAPP();
				JOptionPane.showMessageDialog(null, "已重启应用", "重启应用",JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		//设备截图
		btnScreenshot = new JButton("设备截图");
		btnScreenshot.setBounds(191, 57, 93, 49);
		frame.getContentPane().add(btnScreenshot);
		PrtScTip = new JLabel("");
		PrtScTip.setBounds(10, 91, 114, 15);
		frame.getContentPane().add(PrtScTip);
		PrtScTip.setForeground(Color.ORANGE);
		
		btnCrashlog = new JButton("查看崩溃日志");
		btnCrashlog.setBounds(10, 173, 114, 23);
		panel_save1ogcat.add(btnCrashlog);
		
		crashLogUpdateTip = new JLabel("CrashLog.txt有更新！");
		crashLogUpdateTip.setBounds(123, 200, 157, 15);
		panel_save1ogcat.add(crashLogUpdateTip);
		crashLogUpdateTip.setForeground(Color.RED);
		
		crash_log_monitor_CheckBox = new JCheckBox("崩溃监控开关");
		crash_log_monitor_CheckBox.setBounds(10, 196, 103, 23);
		panel_save1ogcat.add(crash_log_monitor_CheckBox);
		crashLogUpdateTip.setVisible(false);
		
		//推送配置文件框
		pushConfigArea = new JTextArea("请将需要下发的配置文件拖拽至此\n文件路径不能包含中文和空格\n");
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
            		ArrayList<String> illegal_configs = new ArrayList<String>();
            		for (String configstring : strs) {
            			Matcher m = p.matcher(configstring);
            			if(m.find()){
            				//System.out.print("\n包含中文");
//							JOptionPane.showMessageDialog(null, configstring+"\n文件路径不能包含中文和空格", "添加失败",JOptionPane.WARNING_MESSAGE);
							pushConfigArea.setText("");
							illegal_configs.add(configstring);
            			}
            			else{
            				ChoosedConfigsstr.add(configstring);
            				pushConfigArea.setText("");
            			}
            		}
            		if (illegal_configs.size() != 0) {
            			String dialog_str = ""; 
            			for (String config : illegal_configs){
            				dialog_str += config + "\n"; 
            			}
            			JOptionPane.showMessageDialog(null, dialog_str+"\n包含中文和空格", "添加失败",JOptionPane.WARNING_MESSAGE);
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

		
		pushConfigscrollPane = new JScrollPane(pushConfigArea);
		pushConfigscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pushConfigscrollPane.setBounds(126, 20, 144, 106);
		pushConfigscrollPane.setViewportView(pushConfigArea);
		
		configPanel = new JPanel();
		configPanel.setLayout(null);
		configPanel.setBorder(BorderFactory.createTitledBorder("运行配置"));
		configPanel.setBackground(Color.WHITE);
		configPanel.setBounds(300, 15, 280, 169);
		frame.getContentPane().add(configPanel);
		configPanel.add(pushConfigscrollPane);
		
		
		//下发config
		btnPushingConfig = new JButton("下发");
		btnPushingConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				class pushConfig implements Runnable{
					@Override
					public void run() {
						now = 0;
						int all = 1;
						all = all + ChoosedConfigsstr.size();
						installprogressBar.setValue(0);
						installprogressBar.setValue(0);
						String dialogStr = "";
						try{
							for(String tmp:ChoosedConfigsstr){
								if (tmp.contains("game") || tmp.contains("promotion")) {
									String cmd = "adb -s " + devices_comboBox.getSelectedItem().toString() + " push " + tmp + " sdcard/inbox/game";
									log(cmd);
									Process p1 = Runtime.getRuntime().exec(cmd);
						            p1.waitFor();
						            p1.destroy();
						            dialogStr += tmp + "\n";
								}
								else {
									String cmd = "adb -s " + devices_comboBox.getSelectedItem().toString() + " push " + tmp + " sdcard/inbox/config";
									Process p1 = Runtime.getRuntime().exec(cmd);
						            p1.waitFor();
						            p1.destroy();
						            InstallProgress(all, now+=1, installprogressBar);
						            dialogStr += tmp + "\n";
								}
							}
							RestartAPP();
							//Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP");
							installprogressBar.setValue(100);
							JOptionPane.showMessageDialog(null, "下发\n"+dialogStr+"完成！", "下发配置成功",JOptionPane.PLAIN_MESSAGE);
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
		btnPushingConfig.setBounds(201, 136, 72, 23);
		configPanel.add(btnPushingConfig);
		
		//清空config
		clearconfig = new JButton();
		clearconfig.setToolTipText("\u6E05\u7A7A");
		clearconfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChoosedConfigs = null;
				ChoosedConfigsstr.clear();
				pushConfigArea.setText("选择配置文件或拖拽配置文件至文本框");
			}
		});
		clearconfig.setText("\u6E05\u7A7A");
		//clearconfig.setIcon(new ImageIcon(getClass().getResource("/toolIcon/searchreset.png")));
		clearconfig.setBounds(126, 136, 72, 23);
		configPanel.add(clearconfig);
		
		//时间选择器
		datemodel = new SpinnerDateModel();
		
		btnPullConfig = new JButton("获取运行配置");
		btnPullConfig.setBounds(10, 20, 112, 34);
		configPanel.add(btnPullConfig);
		//获取运行配置
		btnPullConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					getConfigs();
				}
		});
		
		btnPullGameCfg = new JButton("获取活动配置");
		btnPullGameCfg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/game C:\\inhandTool\\config";
				String command3 = "cmd.exe /c start C:\\inhandTool\\config\\";
				String response = getMachineId();
				String MachineID = response;
					if(response == null){
						ConTip.setVisible(true);
					}
					else{
						ConTip.setVisible(false);
						command2 = command2 + "\\" + MachineID + "\\game";
						newfolder("C:\\inhandTool\\config\\"+MachineID+"\\game");
						try {
							Process p;
							p = Runtime.getRuntime().exec(command3+MachineID+"\\game");
							p.waitFor();
							p.destroy();
							log(command2);
							p = Runtime.getRuntime().exec(command2);
							p.waitFor();
							p.destroy();
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}
					}
			}
		});
		btnPullGameCfg.setBounds(10, 57, 112, 23);
		configPanel.add(btnPullGameCfg);
		
		btnchannelcfg = new JButton("货道快速配置");
		btnchannelcfg.setBounds(10, 125, 112, 34);
		configPanel.add(btnchannelcfg);
		
		activity_cfg_Button = new JButton("（旧）优惠打折");
		activity_cfg_Button.setBounds(10, 90, 112, 23);
		configPanel.add(activity_cfg_Button);
		activity_cfg_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					newfolder("C:\\inhandTool\\temp\\activity");
					deleteFile("C:\\inhandTool\\temp\\activity\\channel_cfg.xml");
					String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/config/channel_cfg.xml C:\\inhandTool\\temp\\activity";
					Process p = Runtime.getRuntime().exec(command1);
					p.waitFor();
					p.destroy();
					activityFrame frame = new activityFrame(devices_comboBox);
					frame.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "初始化失败", "初始化失败",JOptionPane.CANCEL_OPTION);
					e1.printStackTrace();
				}
			}
		});
		btnchannelcfg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				channelFrame c = new channelFrame(devices_comboBox);
				c.setVisible(true);
			}
		});

		
		inhandApp = new JPanel();
		inhandApp.setLayout(null);
		inhandApp.setBorder(BorderFactory.createTitledBorder("Inhand应用"));
		inhandApp.setBackground(Color.WHITE);
		inhandApp.setBounds(300, 194, 280, 178);
		frame.getContentPane().add(inhandApp);
		
		
		
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
		CloudSetup = new JPanel();
		CloudSetup.setLayout(null);
		CloudSetup.setBorder(BorderFactory.createTitledBorder("连接平台配置"));
		CloudSetup.setBackground(Color.WHITE);
		CloudSetup.setBounds(300, 382, 280, 179);
		frame.getContentPane().add(CloudSetup);

		
		btnUpdateCloudConfig = new JButton("更新配置");
		btnUpdateCloudConfig.setBounds(184, 146, 86, 23);
		CloudSetup.add(btnUpdateCloudConfig);
		
		orgName = new JComboBox<String>();
		orgName.setBounds(74, 24, 118, 21);
		orgName.addItem("");
		orgName.setEditable(true);
		orgNameeditor = orgName.getEditor();
		CloudSetup.add(orgName);
		customize.addressRead(orgName,"orgname");

		
		serverAddress = new JComboBox<String>();
		serverAddress.setBounds(74, 55, 118, 21);
		serverAddress.addItem("");
		customize.addressRead(serverAddress,"serveraddress");
		serverAddress.setEditable(true);
		serverAddresseditor = serverAddress.getEditor();
		CloudSetup.add(serverAddress);
		
		master_serialPort = new JComboBox<String>();
		master_serialPort.setBounds(74, 88, 54, 21);
		master_serialPort.setEditable(true);
		master_serialPort.addItem("");
		master_serialPort.addItem("ttyO1");
		master_serialPort.addItem("ttyO2");
		master_serialPort.addItem("ttyO3");
		master_serialPort.addItem("ttyO4");
		master_serialPort.addItem("ttyO5");
		master_serialPort.addItem("ttyO6");
		master_serialPort.addItem("ttyO7");
		master_serialPort.addItem("ttyO8");
		master_serialPorteditor = master_serialPort.getEditor();
		CloudSetup.add(master_serialPort);
		
		
		labelorgName = new JLabel("机构名：");
		labelorgName.setBounds(10, 27, 54, 15);
		CloudSetup.add(labelorgName);
		
		labelServerAddress = new JLabel("平台地址：");
		labelServerAddress.setBounds(10, 58, 68, 15);
		CloudSetup.add(labelServerAddress);
		
		labelVMCport = new JLabel("VMC串口：");
		labelVMCport.setBounds(10, 91, 68, 15);
		CloudSetup.add(labelVMCport);
		
		btnGetCloudConfig = new JButton("刷新");
		btnGetCloudConfig.addMouseListener(new MouseAdapter() {
			private Process p;
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					newfolder("C:\\inhandTool\\config\\temp");
					deleteFile("C:\\inhandTool\\config\\temp\\config.xml");
					deleteFile("C:\\inhandTool\\config\\temp\\smartvm_cfg.xml");
					String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/config/config.xml C:\\inhandTool\\config\\temp";
					String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/config/smartvm_cfg.xml C:\\inhandTool\\config\\temp";
					p = Runtime.getRuntime().exec(command1);
					p.waitFor();
					p.destroy();
					p = Runtime.getRuntime().exec(command2);
					p.waitFor();
					p.destroy();
					//Thread.sleep(1000);
					String serveraddressPath = "C:\\inhandTool\\config\\temp\\config.xml";
					String orgNamePath = "C:\\inhandTool\\config\\temp\\smartvm_cfg.xml";
					org.dom4j.Document smartvmdocument=load(orgNamePath);
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
					Element orgNameele = (Element) orgNamelist.item(0);
					//dom4j:
					List<Node>smartvmlist=smartvmdocument.selectNodes("//cabinet[@id='master']");
					String master_serial = ((org.dom4j.Element)smartvmlist.get(0)).attributeValue("serial").toString();
					
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
					master_serialPorteditor.setItem(master_serial);
					master_serialPort.setSelectedItem(master_serial);
					JOptionPane.showMessageDialog(null, "刷新完成！", "刷新成功",JOptionPane.PLAIN_MESSAGE);
				} 
				catch (Exception e2) {
				}
				
			}
		});

		btnGetCloudConfig.setBounds(10, 146, 86, 23);
		CloudSetup.add(btnGetCloudConfig);
		
		lblNewLabel_1 = new JLabel("当前厂家：");
		lblNewLabel_1.setBounds(10, 119, 68, 15);
		CloudSetup.add(lblNewLabel_1);
		
		nowVendor = new JComboBox<String>();
		nowVendoreditor = nowVendor.getEditor();
		nowVendor.setBounds(74, 119, 118, 21);
		CloudSetup.add(nowVendor);
		
		addOrg = new JButton("ADD");
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
		
		addAddress = new JButton("ADD");
		addAddress.setToolTipText("新增平台地址");
		addAddress.setFont(new Font("宋体", Font.PLAIN, 12));
		addAddress.setBounds(202, 54, 54, 23);
		CloudSetup.add(addAddress);
		
		only_matser_serial_chckbxNewCheckBox = new JCheckBox("只修改主柜串口");
		only_matser_serial_chckbxNewCheckBox.setBounds(134, 87, 122, 23);
		CloudSetup.add(only_matser_serial_chckbxNewCheckBox);
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
							int all = 11;
							installprogressBar.setValue(0);
							
							newfolder("C:\\inhandTool\\config\\temp");
							deleteFile("C:\\inhandTool\\config\\temp\\config.xml");
							deleteFile("C:\\inhandTool\\config\\temp\\smartvm_cfg.xml");
							InstallProgress(all, now+=1, installprogressBar);
							String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/config/config.xml C:\\inhandTool\\config\\temp";
							String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/config/smartvm_cfg.xml C:\\inhandTool\\config\\temp";
							String command3 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " push C:\\inhandTool\\config\\temp\\smartvm_cfg.xml sdcard/inbox/config";
							String command4 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " push C:\\inhandTool\\config\\temp\\config.xml sdcard/inbox/config";
							//String command5 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
							log(command1);
							log(command2);
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
							String key = getKey(vendormap, (String) nowVendor.getSelectedItem());
				            int keyint = Integer.parseInt(key)+1;
				            key = Integer.toString(keyint);
							if (only_matser_serial_chckbxNewCheckBox.isSelected()) {
								Element serialele = (Element) seriallist.item(0);
								serialele.setAttribute("serial", master_serialPort.getSelectedItem().toString());
								try {
									NodeList master_tag_serial = orgNamedoc.getElementsByTagName("master");
									Element master_tag_serialele = (Element) master_tag_serial.item(0);
									master_tag_serialele.setAttribute("serial", master_serialPort.getSelectedItem().toString());
								} catch (Exception e2) {
								}
							}
							else{
					            for(int i=0;i<seriallist.getLength();i++){
					            	Element serialele = (Element) seriallist.item(i);
					            	serialele.setAttribute("vendor", (String) key);
					            	serialele.setAttribute("serial", (String) master_serialPort.getSelectedItem());
					            }
							}
							InstallProgress(all, now+=1, installprogressBar);
							NodeList vendorlist = orgNamedoc.getElementsByTagName("current-vendor");
							Element vendorele = (Element) vendorlist.item(0);
							try {
								NodeList master_tag_serial = orgNamedoc.getElementsByTagName("master");
								Element master_tag_serialele = (Element) master_tag_serial.item(0);
								master_tag_serialele.setAttribute("serial", master_serialPort.getSelectedItem().toString());
							} catch (Exception e2) {
							}
				            InstallProgress(all, now+=1, installprogressBar);
				            //修改xml
				            serveraddressele.setTextContent((String) serverAddress.getSelectedItem());
				            orgNameele.setTextContent((String) orgName.getSelectedItem());

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
				            log(command3);
				            log(command4);
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
							JOptionPane.showMessageDialog(null, "更新运行配置完成！", "更新成功",JOptionPane.PLAIN_MESSAGE);
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
		btn_mDataConnectionState = new JButton("<html>蜂窝网络<br>状态:UNKNOWN</html>");
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
						String command_off = "adb -s " + devices_comboBox.getSelectedItem().toString() + " shell su 0 svc data disable";
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
		    		JOptionPane.showMessageDialog(null, "已关闭蜂窝网络", "关闭蜂窝网络",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					DataConnectionFlag = false;
					t1.interrupt();
					String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell su 0 svc data enable";
					try {
						Process p = Runtime.getRuntime().exec(command2);
						p.waitFor();
						p.destroy();
						JOptionPane.showMessageDialog(null, "已开启蜂窝网络", "开启蜂窝网络",JOptionPane.PLAIN_MESSAGE);
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
		
		btn_mWifiConnectionState = new JButton("<html>WIFI网络<br>状态:UNKNOWN</html>");
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
						String command_off = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell su 0 svc wifi disable";
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
		    		JOptionPane.showMessageDialog(null, "已关闭Wi-Fi", "关闭Wi-Fi",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					WifiConnectionFlag = false;
					t1.interrupt();
					String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell su 0 svc wifi enable";
					try {
						Process p = Runtime.getRuntime().exec(command2);
						p.waitFor();
						p.destroy();
						JOptionPane.showMessageDialog(null, "已打开Wi-Fi", "打开Wi-Fi",JOptionPane.PLAIN_MESSAGE);
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
					btn_mWifiConnectionState.setBackground(new Color(240, 230, 140));
					btn_mWifiConnectionState.setText("<html>WIFI网络<br>状态:OFF</html>");
					btn_mWifiConnectionState.setEnabled(true);
				}
			}
		});
		btn_mWifiConnectionState.setBounds(724, 503, 92, 43);
		frame.getContentPane().add(btn_mWifiConnectionState);
		
		
		//已选APK框
		choosedappsArea = new JTextArea("将需要安装的APK文件拖拽至此\n文件路径不能包含中文和空格\n");
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
            		ArrayList<String> illegal_configs = new ArrayList<String>();
            		for (String configstring : strs) {
            			Matcher m = p.matcher(configstring);
            			if(m.find()){
            				//System.out.print("\n包含中文");
//							JOptionPane.showMessageDialog(null, configstring+"\n文件路径不能包含中文和空格", "添加失败",JOptionPane.WARNING_MESSAGE);
            				choosedappsArea.setText("");
							illegal_configs.add(configstring);
            			}
            			else{
            				ChoosedappsStr.add(configstring);
            				choosedappsArea.setText("");
            			}
            		}
            		if (illegal_configs.size() != 0) {
            			String dialog_str = ""; 
            			for (String config : illegal_configs){
            				dialog_str += config + "\n"; 
            			}
            			JOptionPane.showMessageDialog(null, dialog_str+"\n包含中文和空格", "添加失败",JOptionPane.WARNING_MESSAGE);
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
		
		choosedappsScrollPane = new JScrollPane(choosedappsArea);
		choosedappsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		choosedappsScrollPane.setBounds(10, 21, 177, 105);
		inhandApp.add(choosedappsScrollPane);
		
		//清空apps
		btnclearApps = new JButton("清空");
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
		
		btnInstallAll = new JButton("<html>卸载<br>全部<br>并安装</html>");
		btnInstallAll.setBounds(197, 21, 73, 59);
		inhandApp.add(btnInstallAll);
		
		btnOnlyInstall = new JButton("仅安装");
		btnOnlyInstall.setBounds(197, 116, 73, 35);
		inhandApp.add(btnOnlyInstall);
		
		installprogressBar = new JProgressBar();
		installprogressBar.setStringPainted(true);
		installprogressBar.setBounds(10, 154, 260, 14);
		inhandApp.add(installprogressBar);
	
		btnUninstallAll = new JButton("全部卸载");
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
						installprogressBar.setValue(0);
						Process p;
						try {
							String list3packagecommand = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell pm list package -3";
							Process plist3inhandpackage = null;
							try {
								plist3inhandpackage = Runtime.getRuntime().exec(list3packagecommand);
								plist3inhandpackage.waitFor();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							InputStream isplist3package = plist3inhandpackage.getInputStream();
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
							packageList.add("com.inhand.inboxcore");
							System.out.println(packageList);
							log("即将卸载\n"+packageList);
							int all = packageList.size();
							for(int i = 0;i<packageList.size();i++){
								p = Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " uninstall "+packageList.get(i));
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
							}
							installprogressBar.setValue(100);
							JOptionPane.showMessageDialog(null, "卸载完成！", "卸载全部应用：\n"+packageList,JOptionPane.PLAIN_MESSAGE);
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
						
					}
					
				}
				int n = JOptionPane.showConfirmDialog(null, "是否要将系统第三方 APP全部卸载","全部卸载",JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					uninstall uIt = new uninstall();
					Thread t1 = new Thread(uIt);
					t1.start();
				}
			}
		});
		//仅安装
		btnOnlyInstall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				class onlyinstall implements Runnable{
					@Override
					public void run() {
						now = 0;
						int all = 1;
						all = all + ChoosedappsStr.size();
						installprogressBar.setValue(0);
						installprogressBar.setValue(0);
						String dialogStr = "";
						try{
							for(String tmp:ChoosedappsStr){
								String cmd = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " install -r " + tmp;
					            log(cmd);
								Process p1 = Runtime.getRuntime().exec(cmd);
					            p1.waitFor();
					            p1.destroy();
					            InstallProgress(all, now+=1, installprogressBar);
					            dialogStr += tmp + "\n";
							}
							RestartAPP();
							//Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP");
							installprogressBar.setValue(100);
							JOptionPane.showMessageDialog(null, "安装\n"+dialogStr+"完成！", "安装成功",JOptionPane.PLAIN_MESSAGE);
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
								int all = 0;
								all = all + ChoosedappsStr.size() + 1;
								installprogressBar.setValue(0);
								Process p;
								try {
									String list3packagecommand = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell pm list package -3";
									Process plist3inhandpackage = null;
									try {
										plist3inhandpackage = Runtime.getRuntime().exec(list3packagecommand);
										plist3inhandpackage.waitFor();
									} catch (Exception e1) {
										e1.printStackTrace();
									}
									InputStream isplist3package = plist3inhandpackage.getInputStream();
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
									packageList.add("com.inhand.inboxcore");
									System.out.println(packageList);
									log("即将卸载\n"+packageList);
									all = all + packageList.size();
									for(int i = 0;i<packageList.size();i++){
										p = Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " uninstall "+packageList.get(i));
										p.waitFor();
										p.destroy();
										InstallProgress(all, now+=1, installprogressBar);
									}
								} catch (IOException | InterruptedException e) {
									e.printStackTrace();
								}
								InstallProgress(all, now+=1, installprogressBar);
								String rmapps = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell rm -rf sdcard/inbox/apps";
								log(rmapps);
								p = Runtime.getRuntime().exec(rmapps);
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								String mkdirapps = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell mkdir sdcard/inbox/apps";
								log(mkdirapps);
								p = Runtime.getRuntime().exec(mkdirapps);
								p.waitFor();
								p.destroy();
								InstallProgress(all, now+=1, installprogressBar);
								String dialogStr = "";
								for(String tmp:ChoosedappsStr){
									String command = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " push \"" + tmp + "\" sdcard/inbox/apps\n";
						            Process p1 = Runtime.getRuntime().exec(command);
						            log(command);
						            p1.waitFor();
						            p1.destroy();
						            InstallProgress(all, now+=1, installprogressBar);
						            dialogStr += tmp + "\n";
								}
								
								p = Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell am start com.inhand.install/.InstallActivity");
								p.waitFor();
								p.destroy();
								installprogressBar.setValue(100);
								JOptionPane.showMessageDialog(null, "推送\n"+dialogStr+"完成！并执行Install安装", "卸载并安装",JOptionPane.PLAIN_MESSAGE);
							}
							catch(IOException | InterruptedException e1){
								e1.printStackTrace();
							}
							
						}
					
					}
					if (ChoosedappsStr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "请添加需要安装的安装包", "提示",JOptionPane.CANCEL_OPTION);
					}
					else {
						int n = JOptionPane.showConfirmDialog(null, "是否要全部卸载，并安装APP","卸载并安装",JOptionPane.OK_CANCEL_OPTION);
						if (n == 0) {
							Install It = new Install();
							Thread t1 = new Thread(It);
							t1.start();
						}
					}
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
				save_log_formatTime = getFormatTime();
				String command0 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " logcat -c";
				String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " logcat -v time ";
				String command2 = "cmd.exe /c start ";
				//String machineid = getMachineId();
				log_savepath = logSavePathField.getText();
				File file =new File(log_savepath);
				String tag = saveLogTagField.getText();
				if(!file.exists()){
					newfolder(log_savepath);
				}
				if (SaveLogTag) {
					command1 = command1 + "-s " + tag + " >" + log_savepath + "\\log" +save_log_formatTime + ".log";
				}
				else{
					command1 = command1 + ">" + log_savepath + "\\log" +save_log_formatTime + ".log";	
				}
				System.out.print(command1);
				try {
					SaveLogProcess = Runtime.getRuntime().exec(command0);
					//Thread.sleep(200);
					SaveLogProcess = Runtime.getRuntime().exec(command1);
					command2 = command2 + log_savepath;
					//SaveLogProcess = Runtime.getRuntime().exec(command2);
					JOptionPane.showMessageDialog(null, "正在保存日志……\n日志保存目录：\n"+log_savepath+"\n日志名称：log" +save_log_formatTime + ".log", "保存日志",JOptionPane.PLAIN_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "保存成功！\n"+log_savepath+"\n日志名称：log" +save_log_formatTime + ".log", "停止保存日志",JOptionPane.PLAIN_MESSAGE);
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		//轮询已连接设备
		adbdevicesTimerDemo(adbdevicesArea,btn_mDataConnectionState,btn_mWifiConnectionState);
		
		//连接断开提示
		ConTip = new JLabel("Device Disconnect");
		ConTip.setBounds(10, 91, 114, 15);
		frame.getContentPane().add(ConTip);
		ConTip.setForeground(Color.RED);
		
		
		changeMachineId = new JButton("更改售货机编号");
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
						String commandpush = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " push "+machine_id_path+" sdcard/inbox/config";
						FileOutputStream fos = null;
						try {
							fos = new FileOutputStream(machine_id_path);
							String s = MachineId.getText();
				            fos.write(s.getBytes());
				            fos.close();
				            p = Runtime.getRuntime().exec(commandpush);
				            p.waitFor();
				            RestartAPP();
				            JOptionPane.showMessageDialog(null, "已更改售货机编号为"+MachineId.getText(), "更改售货机编号",JOptionPane.PLAIN_MESSAGE);
				            deleteFile(machine_id_path);
				            frame2.dispose();
				            
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}
			            
					}
				});
			}
		});
		
		simKeyEventPanel = new JPanel();
		simKeyEventPanel.setBackground(Color.WHITE);
		simKeyEventPanel.setLayout(null);
		simKeyEventPanel.setBorder(BorderFactory.createTitledBorder("模拟按键、时间"));
		simKeyEventPanel.setBounds(590, 15, 226, 169);
		frame.getContentPane().add(simKeyEventPanel);
		
		btnSimKeyHome = new JButton("HOME");
		btnSimKeyHome.setBounds(10, 24, 68, 23);
		simKeyEventPanel.add(btnSimKeyHome);
		
		btnSimKeyBack = new JButton("BACK");
		btnSimKeyBack.setBounds(10, 56, 68, 23);
		simKeyEventPanel.add(btnSimKeyBack);
		
		btnSimKeyMenu = new JButton("MENU");
		btnSimKeyMenu.setBounds(10, 89, 68, 23);
		simKeyEventPanel.add(btnSimKeyMenu);
		
		btnSimKeyMode = new JButton("MODE");
		btnSimKeyMode.setBounds(10, 122, 68, 37);
		simKeyEventPanel.add(btnSimKeyMode);
		datespinner = new JSpinner(datemodel);
		datespinner.setBounds(88, 17, 132, 77);
		simKeyEventPanel.add(datespinner);
		datespinner.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		datespinner.setValue(new Date());
		JSpinner.DateEditor editor = new JSpinner.DateEditor(datespinner,"yyyy-MM-dd HH:mm:ss");
		datespinner.setEditor(editor);
		
		btnSetTime = new JButton("设置时间");
		btnSetTime.setBounds(88, 103, 88, 23);
		simKeyEventPanel.add(btnSetTime);
		
		btnRevertTime = new JButton("还原时间");
		btnRevertTime.setBounds(88, 136, 88, 23);
		simKeyEventPanel.add(btnRevertTime);
		
		set_time_and_restart_checkbox = new JCheckBox("<html><br>重启</html>");
		set_time_and_restart_checkbox.setBounds(177, 100, 43, 59);
		simKeyEventPanel.add(set_time_and_restart_checkbox);
		//还原Android时间
		btnRevertTime.addMouseListener(new MouseAdapter() {
			private Process p;
			@Override
			public void mouseClicked(MouseEvent e) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd.HHmmss");
				String nowtime = formatter.format(new Date());
				System.out.print(nowtime);
				try {
					p = Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell date -s\"yymmdd.hhmmss\"");
					if(p.waitFor()==0)
						p = Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell date -s " + nowtime);
						p.waitFor();
						p.destroy();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
				datespinner.setValue(new Date());
				if(set_time_and_restart_checkbox.isSelected()){
					RestartAPP();
				}
				JOptionPane.showMessageDialog(null, "Andriod时间已设置为当前时间", "设置时间",JOptionPane.PLAIN_MESSAGE);
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
					p = Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell date -s\"yymmdd.hhmmss\"");
					if(p.waitFor()==0)
						p = Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell date -s " + time2);
						p.waitFor();
						p.destroy();
						if(set_time_and_restart_checkbox.isSelected()){
							RestartAPP();
						}
						JOptionPane.showMessageDialog(null, "Andriod时间修改成功", "设置时间",JOptionPane.PLAIN_MESSAGE);
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnToolUpdate = new JButton("工具更新");
		btnToolUpdate.setBounds(191, 528, 99, 23);
		frame.getContentPane().add(btnToolUpdate);
		btnToolUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UpgradeTool u = new UpgradeTool(filepath, updateHost);
					u.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		updateFlagIcon = new JLabel("...");
		updateFlagIcon.setFont(new Font("宋体", Font.PLAIN, 10));
		updateFlagIcon.setHorizontalAlignment(SwingConstants.CENTER);
		updateFlagIcon.setBounds(560, 552, 82, 23);
		frame.getContentPane().add(updateFlagIcon);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder("卸载应用"));
		panel.setBackground(Color.WHITE);
		panel.setBounds(590, 194, 226, 178);
		frame.getContentPane().add(panel);
		
		insatlled3app = new JComboBox<String>();
		insatlled3app.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				insatlled3app.setEnabled(false);
				String list3packagecommand = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell pm list package -3";
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
				insatlled3app.addItem("com.inhand.inboxcore");
				insatlled3app.setEnabled(true);
			}
		});
		insatlled3app.setBounds(10, 82, 196, 32);
		panel.add(insatlled3app);
		insatlled3app.addItem("选择要删除的应用");
		
		//卸载已安装的第三方应用
		uninstallapp = new JButton("卸载");
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
							Process p1 = Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " uninstall "+uninstallPackageName);
				            p1.waitFor();
				            p1.destroy();
				            InstallProgress(all, now+=1, installprogressBar);
							installprogressBar.setValue(100);
							//刷新
							String list3packagecommand = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell pm list package -3";
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
							insatlled3app.addItem("com.inhand.inboxcore");
							JOptionPane.showMessageDialog(null, uninstallPackageName+"\n已卸载", "卸载完成",JOptionPane.OK_CANCEL_OPTION);
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
					JOptionPane.showMessageDialog(null, "请选择需要卸载的包", "提示",JOptionPane.CANCEL_OPTION);
				}
			}
		});
		uninstallapp.setBounds(113, 136, 93, 32);
		panel.add(uninstallapp);
		
		btnInstalled = new JButton("<html>当前<br>版本号</html>");
		btnInstalled.setBounds(10, 21, 100, 36);
		panel.add(btnInstalled);
		
		lblNewLabel_2 = new JLabel("选择需要删除的应用：");
		lblNewLabel_2.setBounds(10, 67, 196, 15);
		panel.add(lblNewLabel_2);
		
		devices_comboBox = new JComboBox<String>();
		devices_comboBox.setBounds(10, 71, 171, 21);
		frame.getContentPane().add(devices_comboBox);
		
		JLabel lblNewLabel = new JLabel("选择设备：");
		lblNewLabel.setBounds(10, 53, 93, 15);
		frame.getContentPane().add(lblNewLabel);
		
		operation_log_Area = new JTextArea();
		operation_log_Area.setLineWrap(true);
		operation_log_Area.setEditable(false);
		operation_log_Area.setBounds(590, 377, 226, 120);
		frame.getContentPane().add(operation_log_Area);
		operation_log_AreascrollPane = new JScrollPane(operation_log_Area);
		operation_log_AreascrollPane.setBounds(590, 377, 226, 120);
		frame.getContentPane().add(operation_log_AreascrollPane);
		operation_log_AreascrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		operation_log_AreascrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		btnInstalled.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Process plist3package = null;
				String list3packagecommand = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell pm list package -3";
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
				StringBuffer sb = new StringBuffer();
				Iterator<String> iterator = packageList.iterator();
				while(iterator.hasNext()){
				    String packagename = iterator.next();
				    //System.out.println(packagename);
				    String getVersioncmd = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell dumpsys package "+packagename+" | findstr versionName";
				    //System.out.println(getVersioncmd);
				    try {
						Process p = Runtime.getRuntime().exec(getVersioncmd);
						InputStream is = p.getInputStream();
						InputStreamReader bi = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(bi);
						String versionstr = null;
						versionstr = br.readLine();
						if (versionstr != null) {
							versionstr = versionstr.replace("versionName=", "");
							sb.append(packagename+versionstr+"\n");
							//System.out.println(packagename+versionstr);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, sb, "APP版本",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		//模拟按键
		btnSimKeyHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String cmd = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell input keyevent 3";
					log(cmd);
					Process p = Runtime.getRuntime().exec(cmd);
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
					String cmd = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell input keyevent 4";
					log(cmd);
					Process p = Runtime.getRuntime().exec(cmd);
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
					String cmd = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell input keyevent 82";
					log(cmd);
					Process p = Runtime.getRuntime().exec(cmd);
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
					String cmd = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell am broadcast -a android.intent.action.ENG_MODE_SWITCH";
					log(cmd);
					Process p = Runtime.getRuntime().exec(cmd);
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		ConTip.setVisible(false);
		
		
		clut = new crashLogUpdateThread();
		clutT = new Thread(clut);
		clutT.start();
		cct = new CloudConfigThread();
		cctT = new Thread(cct);
		cctT.start();
		

	
//		//重启按钮
//		btnRestart.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				JFrame frame2 = new JFrame("重启");
//			    frame2.getContentPane().setLayout(null);
//			    frame2.setBounds(300, 300, 300, 200);
//			    frame2.setVisible(true);
//			    JButton btnrestartapp = new JButton("重启应用");
//			    btnrestartapp.setBounds(80, 10, 120, 30);
//				frame2.getContentPane().add(btnrestartapp);
//				btnrestartapp.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						//String command = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
//						RestartAPP();
//						JOptionPane.showMessageDialog(null, "已重启应用", "重启应用",JOptionPane.PLAIN_MESSAGE);
//						frame2.dispose();
//					}
//				});
//				JButton btnreboot = new JButton("重启设备");
//				btnreboot.setBounds(80, 80, 120, 30);
//				frame2.getContentPane().add(btnreboot);
//				btnreboot.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						String command = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " reboot";
//						try {
//							Process p = Runtime.getRuntime().exec(command);
//							p.waitFor();
//							p.destroy();
//							JOptionPane.showMessageDialog(null, "已重启系统", "重启系统",JOptionPane.PLAIN_MESSAGE);
//							frame2.dispose();
//						} catch (IOException | InterruptedException e1) {
//							e1.printStackTrace();
//						}
//					}
//				});
//			}
//		});
		//设备截图
		btnScreenshot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				class PrtscThread implements Runnable{
					@Override
					public void run() {
						String formatTime = getFormatTime();
						String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell /system/bin/screencap -p /sdcard/sc123456.png";
						String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull /sdcard/sc123456.png c:\\inhandTool\\Screenshots\\sc"+formatTime+".png";
						String command3 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell rm -rf /sdcard/sc123456.png";
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
								Process p1 = Runtime.getRuntime().exec("cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell du -k sdcard/sc123456.png");
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
							newfolder("C:\\inhandTool\\Screenshots");
							Thread.sleep(100);
							Process p1 = Runtime.getRuntime().exec(command2);
							if(p1.waitFor()==0)
								System.out.print("截图完成");
							Process p2 = Runtime.getRuntime().exec(command3);
							PrtScTip.setText("完成！");
							PrtScTip.setForeground(Color.green);
							Process p3 = null;
							int n = JOptionPane.showConfirmDialog(null, "是否立即查看截图？","截图完成",JOptionPane.OK_CANCEL_OPTION);
							if (n == 0) {
								p3 = Runtime.getRuntime().exec(command4);
							}
							Thread.sleep(1000);
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
			        clipboard.setContents(new StringSelection("adb -s " + devices_comboBox.getSelectedItem().toString() + " logcat -v time"), null);
					try {
				        Robot robot;
						robot = new Robot();
						try {
							Thread.currentThread();
							Thread.sleep(300);
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
			        clipboard.setContents(new StringSelection("adb -s " + devices_comboBox.getSelectedItem().toString() + " logcat -v time -s "+tag), null);
					try {
				        Robot robot;
						robot = new Robot();
						try {
							Thread.currentThread();
							Thread.sleep(300);
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
	public static org.dom4j.Document load(String filename) {
		org.dom4j.Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename));  //读取XML文件,获得document对象
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}
	private void getCrashLog(){
		boolean IsExsit = crashLogIsExsit();
		if (!IsExsit) {
			crashLogUpdateTip.setText("crash_log.txt不存在");
			crashLogUpdateTip.setForeground(Color.GREEN);
			btnCrashlog.setBackground(new Color(240, 240, 240));
		}
		else{
			String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/log/crash_log.txt C:\\inhandTool\\crash_log";
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
		String response = "";
		String response1 = null;
		String response2 = null;
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			p.waitFor();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		InputStream is = p.getInputStream();
		InputStreamReader bi = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(bi);
		String message = null;
		try {
			message = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(message != null && !"".equals(message)){
//			adbdevicesArea.setText(message);
			response += message;
			try {
				message = br.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
//		System.out.println("response:"+response);
		response = response.replace(" ", "");
		response = response.replace("Listofdevicesattached", "");
		String comobox_content = "";
		if(response.indexOf("offline") != -1){
			response = response.replace("offline", "device");
		}
		for(int i = 0; i<devices_comboBox.getItemCount(); i++){
			comobox_content += devices_comboBox.getItemAt(i);
		}
//		System.out.println("comobox_content:"+comobox_content);
		if(!"".equals(response)){
			int now_count = 0;
			try {
				now_count = response.split("device").length;
			} catch (Exception e) {
				// TODO: handle exception
			}
			for (String retval: response.split("device")) {
		        retval = retval.replace("	", " ");
//		        System.out.println(retval);
				if(comobox_content.indexOf(retval) == -1){
					devices_comboBox.addItem(retval);
				}
				if(devices_comboBox.getItemCount() != now_count){
					devices_comboBox.removeAllItems();
					devices_comboBox.addItem(retval);
				}
		      }
		}
		else{
			devices_comboBox.removeAllItems();
		}
		if("".equals(response) && devices_comboBox.getItemCount() == 0){
			ConTip.setVisible(true);
			adbdevicesArea.setText("error: device not found\n- waiting for device -");
		}
		else{
			ConTip.setVisible(false);
			adbdevicesArea.setText("\nMachineID:"+getMachineId());
			String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell dumpsys telephony.registry | findstr mDataConnectionState";
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
			
			String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell dumpsys wifi | findstr Wi-Fi";
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
		}
//		adbdevicesArea.setText("\nMachineID:"+getMachineId());
//		String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell dumpsys telephony.registry | findstr mDataConnectionState";
//		Process p1 = Runtime.getRuntime().exec(command1);
//		try {
//			p1.waitFor();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		InputStream is1 = p1.getInputStream();
//		InputStreamReader bi1 = new InputStreamReader(is1);
//		BufferedReader br1 = new BufferedReader(bi1);
//		String message1 = br1.readLine();
//		while(message1 != null && !"".equals(message1)){
//			//System.out.print(message1);
//			response1 = message1;
//			message1 = br1.readLine();
//		}
//		if (response1 != null){
//			response1 = response1.trim();
//		}
//		if("mDataConnectionState=2".equals(response1) && response1 != null){
//			btn_mDataConnectionState.setBackground(new Color(0, 250, 154));
//			btn_mDataConnectionState.setText("<html>蜂窝网络<br>状态:ON</html>");
//			btn_mDataConnectionState.setEnabled(true);
//			mDataConnectionState = true;
//		}
//		else if("mDataConnectionState=0".equals(response1) && response1 != null){
//			btn_mDataConnectionState.setBackground(new Color(255, 160, 122));
//			btn_mDataConnectionState.setText("<html>蜂窝网络<br>状态:OFF</html>");
//			btn_mDataConnectionState.setEnabled(true);
//			mDataConnectionState = false;
//		}
//		else {
//			btn_mDataConnectionState.setBackground(new Color(192, 192, 192));
//			btn_mDataConnectionState.setText("<html>蜂窝网络<br>状态:UNKNOWN</html>");
//			btn_mDataConnectionState.setEnabled(false);
//		}
//		
//		String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell dumpsys wifi | findstr Wi-Fi";
//		Process p2 = Runtime.getRuntime().exec(command2);
//		try {
//			p2.waitFor();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		InputStream is2 = p2.getInputStream();
//		InputStreamReader bi2 = new InputStreamReader(is2);
//		BufferedReader br2 = new BufferedReader(bi2);
//		String message2 = br2.readLine();
//		while(message2 != null && !"".equals(message2)){
//			response2 = message2;
//			message2 = br2.readLine();
//		}
//		if (response2 != null){
//			response2 = response2.trim();
//		}
//		if("Wi-Fi is enabled".equals(response2) && response2 != null){
//			btn_mWifiConnectionState.setBackground(new Color(0, 250, 154));
//			btn_mWifiConnectionState.setText("<html>WIFI网络<br>状态:ON</html>");
//			btn_mWifiConnectionState.setEnabled(true);
//			mWifiConnectionState = true;
//		}
//		else{
//			btn_mWifiConnectionState.setBackground(new Color(255, 160, 122));
//			btn_mWifiConnectionState.setText("<html>WIFI网络<br>状态:OFF</html>");
//			btn_mWifiConnectionState.setEnabled(true);
//			mWifiConnectionState = false;
//		}
//		p1.destroy();
//		p2.destroy();
//		p.destroy();
	}
	public String getConfigs(){
		String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/config C:\\inhandTool\\config\\";
		String command3 = "cmd.exe /c start C:\\inhandTool\\config\\";
		String response = getMachineId();
		String MachineID = response;
			if(response == null){
				ConTip.setVisible(true);
			}
			else{
				ConTip.setVisible(false);
				newfolder("C:\\inhandTool\\config\\"+response);
				try {
					Process p;
					p = Runtime.getRuntime().exec(command3+response);
					p.waitFor();
					p.destroy();
					log(command2+response);
					p = Runtime.getRuntime().exec(command2+response);
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
//	Timer timer = new Timer();
	int delay = 0;//ms
	int period = 2000;//ms
	devicesinfotimer.schedule(new TimerTask() {    
	       public void run(){
	    	   try{
	    		   getdevices(adbdevicesArea,mDataConnectionState,mWifiConnectionState);
	    	   } 
	    	   catch (Exception e){
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
					String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/config/config.xml C:\\inhandTool\\config\\temp";
					String command2 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " pull sdcard/inbox/config/smartvm_cfg.xml C:\\inhandTool\\config\\temp";
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
					master_serialPorteditor.setItem(serialele.getAttribute("serial"));
					master_serialPort.setSelectedItem(serialele.getAttribute("serial"));
					p.destroy();
			} catch (Exception e) {
			}
	       }  
	   }, delay, period); 
	}
	public void CrashLogUpdateTimerDemo(JLabel crashLogUpdateTip, JButton btnCrashLog2, JCheckBox crash_log_monitor_CheckBox)
	{
		Timer timer = new Timer();
		int delay = 2000;//ms
		int period = 8000;//ms
		timer.schedule(new TimerTask() {    
	       public void run(){
	    	   boolean IsExsit = false;
	    	   if (devices_comboBox.getItemCount() != 0 && crash_log_monitor_CheckBox.isSelected()) {
	    		   IsExsit = crashLogIsExsit();
			}
	    	   if (!IsExsit){
	    		   if (!crash_log_monitor_CheckBox.isSelected()){
	    			   crashLogUpdateTip.setVisible(false);
	    		   }
	    		   else {
	    			   crashLogUpdateTip.setVisible(true);
		    		   crashLogUpdateTip.setText("crash_log.txt不存在");
		    		   crashLogUpdateTip.setForeground(Color.GREEN);
	    		   }
	    		   
	    	   }
	    	   else{
	    		   newfolder("C:\\inhandTool\\crash_log\\temp");
		    	   deleteFile("C:\\inhandTool\\crash_log\\temp\\crash_log.txt");
		    	   String command = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell md5 /sdcard/inbox/log/crash_log.txt";
		    	   try {
						Process p = Runtime.getRuntime().exec(command);
						p.waitFor();
						InputStream is = p.getInputStream();
						InputStreamReader bi = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(bi);
						String message = br.readLine();
						message = message.split("  ")[0];
//						System.out.println(message);
						p.destroy();
						String tempCrashMd5 = message;
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
	    	    if(version_check_count<=3) {
	    	    	Check checkup = new Check(updateFlagIcon,updateHost);
					Thread tcheck = new Thread(checkup);
					tcheck.start();
				}
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
		String command = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell ls sdcard/inbox/log | findstr crash_log.txt";
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
		String command1 = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell cat sdcard/inbox/config/machine_id.txt";
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
			CrashLogUpdateTimerDemo(crashLogUpdateTip,btnCrashlog,crash_log_monitor_CheckBox);
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
    		String cmd = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			p.destroy();
			log(cmd);
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
                	FlagIcon.setText("已是最新");
                	FlagIcon.setForeground(Color.GRAY);
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
				version_check_count += 1;
			}

        }
	}
    public static void log(Object x){
    	String s = String.valueOf(x);
    	Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	operation_log_Area.append("\n"+sdf.format(d)+" "+s);
    }
}