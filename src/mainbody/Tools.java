package mainbody;

import mainbody.CommonCommands;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.ComboBoxEditor;

public class Tools {

	String OS_type = "";
	static String adb_path = "";
	//目录
	public String root_path;
	public String screenshot_path;
	
	private JFrame frame;
	Execute_command ec;
	Action_handler ach;
	Timing_task tt;
	CommonCommands cc;
	
	//UI公用
	JComboBox<String> device_selection_box;
	JTextArea device_display_area;
	JLabel screenshot_label;
	private JProgressBar install_progress_bar;
	private JTextArea info_area;
	private JComboBox<String> insatlled_app_box;
//	private JButton select_adb_path_button;
	
	//公有变量
	ArrayList<String> choosed_appsArrayListString = new ArrayList<String>();
	private int progress_bar_value;
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tools window = new Tools();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Tools() throws IOException {
		ec = new Execute_command();
		ach = new Action_handler();
		cc = new CommonCommands();
		tt = new Timing_task();
		initialize();
	}

	private void initialize() throws IOException {
		create_main_frame();
		init_workspace();
		init_UI();
	}
	private void create_main_frame() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 50, 950, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 3, 10, 10));
	}
	
	private String get_os_type() {
		String os_nameString = System.getProperty("os.name").toLowerCase();
		if (os_nameString.indexOf("mac")>=0) {
			return "mac";
		}
		else if (os_nameString.indexOf("windows")>=0) {
			return "windows";
		}
		else {
			return null;
		}	
	}
	
	private void init_workspace() throws IOException {
		OS_type = get_os_type();
		root_path = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + File.separator + "inhand-adbtool" + File.separator;
		screenshot_path = root_path + "screenshot" + File.separator;
		String adb_path_file_path = root_path + "adb_path";
		CommonOperations.mkdir(root_path);
		CommonOperations.mkdir(screenshot_path);
		if (!new File(adb_path_file_path).exists()) {
			if (OS_type.equals("mac")) {
				adb_path = FileChooser.file_chooser() + " ";
			}
			else if (OS_type.equals("windows")) {
				adb_path = "adb" + " ";
			}
			CommonOperations.create_new_file(adb_path_file_path);
			CommonOperations.write_file(adb_path_file_path, adb_path);
		}
		else {
			adb_path = CommonOperations.read_file(adb_path_file_path);
		}
	}


	private void init_UI() {
		devices_info_module();
		device_config_module();	
		device_simulate_and_timeset_module();
		show_log_module();
		app_install_module();
		app_manager_module();
		save_log_module();
		base_config_module();
		module9();
	}
	private void devices_info_module() {
		//设备信息模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("设备信息"));
		frame.getContentPane().add(panel);
		//售货机编号显示区
		device_display_area = new JTextArea();
		device_display_area.setLineWrap(true);
		device_display_area.setEditable(false);
		device_display_area.setBounds(20, 36, 152, 36);
		panel.add(device_display_area);
		JScrollPane device_display_scroll = new JScrollPane(device_display_area);
		device_display_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//滚动条总是出现
		device_display_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		device_display_scroll.setBounds(10, 20, 170, 70);
		panel.add(device_display_scroll);
		//重启应用
		JButton restart_application_button = new JButton("重启应用");
	    restart_application_button.setBounds(193, 20, 100, 50);
	    panel.add(restart_application_button);
	    restart_application_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.restart_APP();//点击重启应用
			}
		});
		//设备截图
	    JButton devices_screenshot_button = new JButton("设备截图");
		devices_screenshot_button.setBounds(193, 79, 100, 50);
		panel.add(devices_screenshot_button);
		devices_screenshot_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.devices_screenshot();//点击设备截图
			}
		});
		//设备选择下拉框
		JLabel label_1 = new JLabel("选择连接的设备：");
		label_1.setBounds(10, 103, 161, 15);
//		panel.add(label_1);
		device_selection_box = new JComboBox<String>();
		device_selection_box.setBounds(6, 130, 177, 30);
//		panel.add(device_selection_box);
		//截图信息提示
		screenshot_label = new JLabel("此处显示截图信息");
		screenshot_label.setBounds(193, 141, 111, 15);
		panel.add(screenshot_label);
		//轮询devices_info
		tt.adbdevicesTimerDemo();
		
	}
	private void device_config_module() {
		//配置文件模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("配置文件"));
		frame.getContentPane().add(panel);
		//获取运行配置
		JButton get_running_configure_button = new JButton("获取运行配置");
		get_running_configure_button.setBounds(6, 20, 112, 34);
		panel.add(get_running_configure_button);
		//获取活动配置
		JButton get_active_configuration_button = new JButton("获取活动配置");
		get_active_configuration_button.setBounds(6, 55, 112, 34);
		panel.add(get_active_configuration_button);
		//货道快速配置
		JButton channel_rapid_configuration_button = new JButton("货道快速配置");
		channel_rapid_configuration_button.setBounds(6, 140, 112, 34);
		panel.add(channel_rapid_configuration_button);
		//推送配置文件
		JTextArea push_configuration_file_area = new JTextArea("请将需要下发的配置文件拖拽至此\n文件路径不能包含中文和空格\n");
		push_configuration_file_area.setLineWrap(true);
		push_configuration_file_area.setEditable(false);
		panel.add(push_configuration_file_area);
		JScrollPane push_configuration_file_area_scroll = new JScrollPane(push_configuration_file_area);
		push_configuration_file_area_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		push_configuration_file_area_scroll.setBounds(126, 20, 178, 90);
		push_configuration_file_area_scroll.setViewportView(push_configuration_file_area);
		panel.add(push_configuration_file_area_scroll);
		//清空推送配置文件内容
		JButton clear_push_configuration_file_area_button = new JButton();
		clear_push_configuration_file_area_button.setToolTipText("清空待下发配置");
		clear_push_configuration_file_area_button.setText("清空待下发配置");
		//clearconfig.setIcon(new ImageIcon(getClass().getResource("/toolIcon/searchreset.png")));
		clear_push_configuration_file_area_button.setBounds(126, 145, 175, 30);
		panel.add(clear_push_configuration_file_area_button);
		//推送下发配置文件
		JButton push_configuration_file_button = new JButton("推送下发配置文件");
		push_configuration_file_button.setBounds(124, 110, 180, 34);
		panel.add(push_configuration_file_button);
		//进度条
		JProgressBar config_progress_bar = new JProgressBar();
		config_progress_bar.setStringPainted(true);
		config_progress_bar.setBounds(6, 176, 298, 20);
		panel.add(config_progress_bar);
		
	}
	private void device_simulate_and_timeset_module() {
		//按键模拟、时间修改
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("按键模拟、时间修改"));
		frame.getContentPane().add(panel);
		//修改Android系统时间
		//时间选择器
		SpinnerDateModel date_model = new SpinnerDateModel();
		JSpinner time_selector = new JSpinner(date_model);
		time_selector.setBounds(115, 16, 189, 75);
		panel.add(time_selector);
		time_selector.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		time_selector.setValue(new Date());
		JSpinner.DateEditor date_editor = new JSpinner.DateEditor(time_selector,"yyyy-MM-dd HH:mm:ss");
		time_selector.setEditor(date_editor);
		//设置时间、还原时间
		JButton set_time_button = new JButton("设置设备时间");
		set_time_button.setBounds(167, 91, 122, 35);
		panel.add(set_time_button);
		JButton reduction_time_button = new JButton("设备还原时间");
		reduction_time_button.setBounds(167, 125, 122, 35);
		panel.add(reduction_time_button);
		//是否同时重启按钮
		JCheckBox set_time_and_restart_checkbox = new JCheckBox("同时重启");
		set_time_and_restart_checkbox.setBounds(167, 161, 122, 35);
		panel.add(set_time_and_restart_checkbox);
		//模拟系统按键
		//HOME
		JButton home_simulating_keyboard_events = new JButton("HOME");
		home_simulating_keyboard_events.setBounds(10, 24, 82, 37);
		panel.add(home_simulating_keyboard_events);
		//BACK
		JButton back_simulating_keyboard_events = new JButton("BACK");
		back_simulating_keyboard_events.setBounds(10, 61, 82, 37);
		panel.add(back_simulating_keyboard_events);
		//MENU
		JButton menu_simulating_keyboard_events = new JButton("MENU");
		menu_simulating_keyboard_events.setBounds(10, 100, 82, 37);
		panel.add(menu_simulating_keyboard_events);
		//MODE
		JButton mode_simulating_keyboard_events = new JButton("MODE");
		mode_simulating_keyboard_events.setBounds(10, 149, 82, 47);
		panel.add(mode_simulating_keyboard_events);
	}
	private void show_log_module() {
		//打印日志模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("打印日志"));
		frame.getContentPane().add(panel);
		//命令标签
		JLabel label = new JLabel("adb logcat -v time ");
		label.setBounds(10, 27, 153, 23);
		panel.add(label);
		JLabel label_1 = new JLabel("adb logcat -v time -s ");
		label_1.setBounds(10, 56, 153, 23);
		panel.add(label_1);
		JLabel label_2 = new JLabel("Tags:");
		label_2.setBounds(10, 91, 41, 23);
		panel.add(label_2);
		//日志tag选择框
		JComboBox<String> common_tags_combobox = new JComboBox<String>();
		common_tags_combobox.setBounds(47, 92, 118, 22);
		panel.add(common_tags_combobox);
		//日志tag输入框
		JTextField tags_input_field = new JTextField();
		tags_input_field.setBounds(10, 117, 294, 54);
		panel.add(tags_input_field);
		tags_input_field.setColumns(10);
		//添加常用日志tag
		JLabel label_3 = new JLabel("添加常用Tag：");
		label_3.setBounds(10, 176, 93, 15);
		panel.add(label_3);
		JButton add_log_tag_button = new JButton("+");
		add_log_tag_button.setFont(new Font("宋体", Font.PLAIN, 12));
		add_log_tag_button.setBounds(94, 173, 40, 22);
		panel.add(add_log_tag_button);
		//打印日志按钮
		JButton show_log_button = new JButton("打印所有日志");
		show_log_button.setBounds(175, 20, 93, 39);
		panel.add(show_log_button);
		JButton show_log_with_filter_button = new JButton("打印过滤日志");
		show_log_with_filter_button.setBounds(175, 75, 93, 39);
		panel.add(show_log_with_filter_button);
		//清空日志缓存
		JButton clear_log_buffer_button = new JButton("清空日志缓存");
		clear_log_buffer_button.setBounds(186, 173, 118, 23);
		panel.add(clear_log_buffer_button);
	}
	private void app_install_module() {
		//Inhand-app安装模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("Inhand-app安装"));
		frame.getContentPane().add(panel);
		//待安装app框
		JTextArea push_apps_file_area = new JTextArea("将需要安装的APK文件拖拽至此\n文件路径不能包含中文和空格\n");
		push_apps_file_area.setLineWrap(true);
		push_apps_file_area.setEditable(false);
		panel.add(push_apps_file_area);
		JScrollPane push_apps_file_area_scroll = new JScrollPane(push_apps_file_area);
		push_apps_file_area_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		push_apps_file_area_scroll.setBounds(126, 20, 178, 104);
		push_apps_file_area_scroll.setViewportView(push_apps_file_area);
		panel.add(push_apps_file_area_scroll);
		push_apps_file_area.setTransferHandler(new TransferHandler(){
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
            				push_apps_file_area.setText("");
							illegal_configs.add(configstring);
            			}
            			else{
            				choosed_appsArrayListString.add(configstring);
            				push_apps_file_area.setText("");
            			}
            		}
            		if (illegal_configs.size() != 0) {
            			String dialog_str = ""; 
            			for (String config : illegal_configs){
            				dialog_str += config + "\n"; 
            			}
            			JOptionPane.showMessageDialog(null, dialog_str+"\n包含中文和空格", "添加失败",JOptionPane.WARNING_MESSAGE);
					}
            		for (String ChoosedConfigstring : choosed_appsArrayListString) {
            			push_apps_file_area.append(ChoosedConfigstring+"\n");
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
		//仅安装
		JButton install_and_restart_button = new JButton("单独安装app");
		install_and_restart_button.setBounds(6, 84, 104, 35);
		panel.add(install_and_restart_button);
		install_and_restart_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (choosed_appsArrayListString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请添加需要安装的安装包", "提示",JOptionPane.CANCEL_OPTION);
				}
				else {
					int n = JOptionPane.showConfirmDialog(null, "是否覆盖安装APP","安装",JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						Install_APP_Thread Iat = new Install_APP_Thread();
						Thread t1 = new Thread(Iat);
						t1.start();
					}
				}
			}
		});
		//卸载全套第三方app
		JButton uninstall_all_inhand_button = new JButton("卸载All第三方");
		uninstall_all_inhand_button.setBounds(6, 140, 114, 35);
		panel.add(uninstall_all_inhand_button);
		uninstall_all_inhand_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "是否要将系统第三方 APP全部卸载","全部卸载",JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					Uninstall_ALL_Thread uIt = new Uninstall_ALL_Thread();
					Thread t1 = new Thread(uIt);
					t1.start();
				}
			}
		});
		//卸载inhand全套app并安装app
		JButton cover_installation_button = new JButton("<html>卸载<br>All第三方<br>并安装</html>");
		cover_installation_button.setBounds(6, 21, 104, 59);
		panel.add(cover_installation_button);
		cover_installation_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (choosed_appsArrayListString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请添加需要安装的安装包", "提示",JOptionPane.CANCEL_OPTION);
				}
				else {
					int n = JOptionPane.showConfirmDialog(null, "是否要全部卸载，并安装APP","卸载并安装",JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						Uninstall_and_Install_Thread It = new Uninstall_and_Install_Thread();
						Thread t1 = new Thread(It);
						t1.start();
					}
				}
			}
		});
		//清空待安装app内容
		JButton clear_push_app_file_area_button = new JButton();
		clear_push_app_file_area_button.setToolTipText("清空待安装app");
		clear_push_app_file_area_button.setText("清空待安装app");
		clear_push_app_file_area_button.setBounds(126, 142, 175, 30);
		panel.add(clear_push_app_file_area_button);
		clear_push_app_file_area_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choosed_appsArrayListString.clear();
				push_apps_file_area.setText("选择APK文件或拖拽APK文件至文本框\n");
			}
		});
		//进度条
		install_progress_bar = new JProgressBar();
		install_progress_bar.setStringPainted(true);
		install_progress_bar.setBounds(6, 176, 298, 20);
		panel.add(install_progress_bar);
		
	}
	private void app_manager_module() {
		//应用管理模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("应用管理"));
		frame.getContentPane().add(panel);
		//已安装应用信息
		JButton installed_application_information_button = new JButton("已安装app信息");
		installed_application_information_button.setBounds(6, 21, 135, 36);
		panel.add(installed_application_information_button);
		installed_application_information_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Get_app_version_Thread gavt = new Get_app_version_Thread();
				Thread t1 = new Thread(gavt);
				t1.start();
			}
		});
		//卸载应用
		JLabel label = new JLabel("选择需要卸载的应用：");
		label.setBounds(10, 67, 196, 15);
		panel.add(label);
		//已安装应用选择框
		insatlled_app_box = new JComboBox<String>();
		insatlled_app_box.setBounds(6, 82, 196, 32);
		panel.add(insatlled_app_box);
		//卸载按钮
		JButton uninstall_app_button = new JButton("卸载");
		uninstall_app_button.setBounds(90, 120, 117, 29);
		panel.add(uninstall_app_button);
		uninstall_app_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String uninstallPackageName = insatlled_app_box.getSelectedItem().toString();
				if (uninstallPackageName.contains("com")) {
					int n = JOptionPane.showConfirmDialog(null, "卸载"+uninstallPackageName,"卸载",JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						Uninstall_app_Thread uat = new Uninstall_app_Thread();
						Thread t1 = new Thread(uat);
						t1.start();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "请选择需要卸载的包", "提示",JOptionPane.CANCEL_OPTION);
				}
			}
		});
	}
	private void save_log_module() {
		//日志保存模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("日志保存"));
		frame.getContentPane().add(panel);
		//tag过滤选项
		JCheckBox save_log_with_tag_checkbox = new JCheckBox("tag过滤");
		save_log_with_tag_checkbox.setBackground(Color.WHITE);
		save_log_with_tag_checkbox.setBounds(10, 20, 103, 23);
		panel.add(save_log_with_tag_checkbox);
		//日志tag选择框
		JLabel label = new JLabel("Tags:");
		label.setBounds(10, 46, 41, 23);
		panel.add(label);
		JComboBox<String> common_tags_combobox = new JComboBox<String>();
		common_tags_combobox.setBounds(47, 47, 118, 22);
		panel.add(common_tags_combobox);
		//停止保存日志
		JButton stop_save_log_button = new JButton("Stop");
		stop_save_log_button.setFont(new Font("宋体", Font.PLAIN, 10));
		stop_save_log_button.setBounds(223, 108, 69, 39);
		panel.add(stop_save_log_button);
		stop_save_log_button.setEnabled(false);
		//开始保存日志
		JButton start_save_log_button = new JButton();
		start_save_log_button.setFont(new Font("宋体", Font.PLAIN, 10));
		start_save_log_button.setText("Start");
		start_save_log_button.setBounds(135, 108, 78, 39);
		panel.add(start_save_log_button);
		//选择日志保存路径
		JButton select_log_save_path_button = new JButton("选择日志保存路径");
		select_log_save_path_button.setBounds(10, 107, 118, 39);
		panel.add(select_log_save_path_button);
		//日志保存路径
		JLabel label_1 = new JLabel("路径:");
		label_1.setBounds(10, 81, 41, 23);
		panel.add(label_1);
		JTextField log_saved_path_field = new JTextField();
		log_saved_path_field.setColumns(10);
		log_saved_path_field.setBounds(47, 75, 260, 30);
		panel.add(log_saved_path_field);
		String log_saved_path = root_path + "device-log";
		log_saved_path_field.setText(log_saved_path);
		//崩溃日志
		//保存崩溃日志
		JButton save_crash_log_button = new JButton("查看崩溃日志");
		save_crash_log_button.setBounds(10, 158, 114, 38);
		panel.add(save_crash_log_button);
		//崩溃日志更新提示
		JLabel crash_log_tip = new JLabel("CrashLog.txt有更新！");
		crash_log_tip.setBounds(135, 158, 157, 15);
		crash_log_tip.setForeground(Color.RED);
		crash_log_tip.setVisible(false);
		panel.add(crash_log_tip);
		//崩溃监控开关
		JCheckBox crash_log_monitor_checkbox = new JCheckBox("崩溃日志监控开关");
		crash_log_monitor_checkbox.setBounds(135, 173, 136, 23);
		panel.add(crash_log_monitor_checkbox);
	}
	private void base_config_module() {
		//基础配置模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("基础配置"));
		frame.getContentPane().add(panel);
		//配置显示与修改
		//标签显示
		JLabel labelorgName = new JLabel("机构名：");
		labelorgName.setBounds(10, 27, 54, 15);
		panel.add(labelorgName);		
		JLabel labelServerAddress = new JLabel("平台地址：");
		labelServerAddress.setBounds(10, 58, 68, 15);
		panel.add(labelServerAddress);		
		JLabel labelVMCport = new JLabel("VMC串口：");
		labelVMCport.setBounds(10, 91, 68, 15);
		panel.add(labelVMCport);		
		JLabel lblNewLabel_1 = new JLabel("当前厂家：");
		lblNewLabel_1.setBounds(10, 130, 68, 15);
		panel.add(lblNewLabel_1);
		//更新配置按钮
		JButton update_configuration_button = new JButton("更新配置");
		update_configuration_button.setBounds(72, 161, 86, 35);
		panel.add(update_configuration_button);
		//刷新配置按钮
		JButton refresh_configuration_button = new JButton("刷新");
		refresh_configuration_button.setBounds(6, 161, 54, 35);
		panel.add(refresh_configuration_button);
		//机构名称下拉框
		JComboBox<String> org_name_combobox = new JComboBox<String>();
		org_name_combobox.setBounds(74, 24, 174, 25);
		org_name_combobox.addItem("");
		org_name_combobox.setEditable(true);
		ComboBoxEditor org_name_editor = org_name_combobox.getEditor();
		panel.add(org_name_combobox);
		//服务器地址下拉框
		JComboBox<String> server_address_combobox = new JComboBox<String>();
		server_address_combobox.setBounds(74, 55, 174, 25);
		server_address_combobox.addItem("");
		server_address_combobox.setEditable(true);
		ComboBoxEditor server_addresseditor = server_address_combobox.getEditor();
		panel.add(server_address_combobox);
		//串口下拉框
		JComboBox<String> serial_port_combobox = new JComboBox<String>();
		serial_port_combobox.setBounds(74, 88, 105, 25);
		serial_port_combobox.setEditable(true);
		serial_port_combobox.addItem("");
		serial_port_combobox.addItem("ttyO1");
		serial_port_combobox.addItem("ttyO2");
		serial_port_combobox.addItem("ttyO3");
		serial_port_combobox.addItem("ttyO4");
		serial_port_combobox.addItem("ttyO5");
		serial_port_combobox.addItem("ttyO6");
		serial_port_combobox.addItem("ttyO7");
		serial_port_combobox.addItem("ttyO8");
		ComboBoxEditor serial_port_combobox_editor = serial_port_combobox.getEditor();
		panel.add(serial_port_combobox);
		//当前厂家下拉框
		JComboBox<String> current_manufacturer_combobox = new JComboBox<String>();
		ComboBoxEditor nowVendoreditor = current_manufacturer_combobox.getEditor();
		current_manufacturer_combobox.setBounds(74, 125, 164, 25);
		panel.add(current_manufacturer_combobox);
		//新增常用机构按钮
		JButton add_common_organization = new JButton("ADD");
		add_common_organization.setToolTipText("新增常用机构");
		add_common_organization.setFont(new Font("宋体", Font.PLAIN, 12));
		add_common_organization.setBounds(250, 23, 54, 25);
		panel.add(add_common_organization);
		//新增常用平台地址
		JButton add_common_address = new JButton("ADD");
		add_common_address.setToolTipText("新增常用平台地址");
		add_common_address.setFont(new Font("宋体", Font.PLAIN, 12));
		add_common_address.setBounds(250, 54, 54, 25);
		panel.add(add_common_address);
		//只修改主柜串口选择框
		JCheckBox only_matser_serial_chckbx = new JCheckBox("只修改主柜串口");
		only_matser_serial_chckbx.setBounds(181, 88, 123, 25);
		panel.add(only_matser_serial_chckbx);
		//修改售货机编号
		JButton change_machine_id_button = new JButton("更改售货机编号");
		change_machine_id_button.setBounds(162, 161, 142, 35);
		panel.add(change_machine_id_button);
	}
	private void module9() {
		//9模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("模块9"));
		frame.getContentPane().add(panel);
		info_area = new JTextArea("");
		info_area.setLineWrap(true);
		panel.add(info_area);
		JScrollPane info_area_scroll = new JScrollPane(info_area);
		info_area_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		info_area_scroll.setBounds(10, 20, 294, 162);
		info_area_scroll.setViewportView(info_area);
		panel.add(info_area_scroll);
	}
	 class Execute_command{
		public String adb_exec(String command) {
			String device_name = "";
			//取消设备选择的功能
//			if (command.equals("devices")) {
//				device_name = "";
//			}else {
//				device_name =  "-s "+ device_selection_box.getSelectedItem().toString() + " ";
//			}
			command = adb_path + device_name + command;
	        String returnString = "";
	        Process pro = null;
	        Runtime runTime = Runtime.getRuntime();
//	        System.out.print(command+"\n");
	        if (runTime == null) {
	            System.err.println("Create runtime false!");
	        }
	        try {
	            pro = runTime.exec(command);
	            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
	            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
	            String line;
	            while ((line = input.readLine()) != null) {
	                returnString = returnString + line + "\n";
	            }
	            input.close();
	            output.close();
	            pro.destroy();
	        } catch (IOException ex) {
	            //no
	        }
	        return returnString;
	    }
		public String shell_exec(String command) {
	        String returnString = "";
	        Process pro = null;
	        Runtime runTime = Runtime.getRuntime();
//	        System.out.print(command);
	        if (runTime == null) {
	            System.err.println("Create runtime false!");
	        }
	        try {
	            pro = runTime.exec(command);
	            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
	            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
	            String line;
	            while ((line = input.readLine()) != null) {
	                returnString = returnString + line + "\n";
	            }
	            input.close();
	            output.close();
	            pro.destroy();
	        } catch (IOException ex) {
	            //no
	        }
	        return returnString;
	    }
	}
	class Action_handler{
		//重启应用
	    public void restart_APP(){
//	    	String cmd = "cmd.exe /c adb -s " + devices_comboBox.getSelectedItem().toString() + " shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
			String cmd = cc.restart_app_command;
    		ec.adb_exec(cmd);
    		JOptionPane.showMessageDialog(null, "已重启应用", "重启应用",JOptionPane.PLAIN_MESSAGE);
	    }
	    //获取售货机编号
	    public String get_machine_id() {
	    	String cmd = cc.get_machine_id;
	    	String response = ec.adb_exec(cmd);
			return response;
		}
	    //获取adb devices
	    public void get_devices_info() {
	    	device_display_area.setText("\n售货机编号:\n"+ach.get_machine_id());
	    	//去掉选择设备功能
//	    	String response = "";
//	    	response = ec.adb_exec(cc.get_devices_info_command);
//			response = response.replace(" ", "");
//			response = response.replace("Listofdevicesattached", "");
//			String comobox_content = "";
//			if(response.indexOf("offline") != -1){
//				response = response.replace("offline", "device");
//			}
//			for(int i = 0; i<device_selection_box.getItemCount(); i++){
//				comobox_content += device_selection_box.getItemAt(i);
//			}
//			if (device_selection_box.getItemCount() == 1) {
//				response = response.replace("\n", "");
//			}
////			System.out.println("comobox_content:"+comobox_content);
//			if(!"".equals(response)){
//				int now_count = 0;
//				try {
//					now_count = response.split("device").length;
//				} catch (Exception e) {
//					
//				}
//				for (String retval: response.split("device")) {
//			        retval = retval.replace("	", " ");
////			        System.out.println(retval);
//					if(comobox_content.indexOf(retval) == -1){
//						device_selection_box.addItem(retval);
//					}
//					if(device_selection_box.getItemCount() != now_count){
//						device_selection_box.removeAllItems();
//						device_selection_box.addItem(retval);
//					}
//			      }
//			}else{
//				device_selection_box.removeAllItems();
//			}
//			if("".equals(response) && device_selection_box.getItemCount() == 0){
//				device_display_area.setText("");
//				device_display_area.setText("error: device not found\n- waiting for device -");
//			}else {
//				device_display_area.setText("\n售货机编号:\n"+ach.get_machine_id());
//			}
		}
	    //设备屏幕截图
	    public void devices_screenshot() {
	    	Screenshot_Thread prtscT = new Screenshot_Thread();
    		Thread pt = new Thread(prtscT);
    		pt.start();
		}
	    //进度条显示
		public void progress_show(int all, int now,JProgressBar progressBar){
			double prog = (double)now / (double)all;
			int progbarValue = (int)(prog*100);
			progressBar.setValue(progbarValue);
		}
		//获取第三方应用包名
		public List<String> get_3_package_list() {
			String package3String = ec.adb_exec(cc.list_package_3);
			String[] package3StringsLiStrings = package3String.split("package:");
			System.out.println(package3StringsLiStrings.length);
			List<String> package_list=new ArrayList<String>();
			for (int i = 1; i < package3StringsLiStrings.length; i++) {
				System.out.println(i +":"+ package3StringsLiStrings[i]);
				String s1 = package3StringsLiStrings[i].trim();
				package_list.add(s1);
				}
			package_list.add("com.inhand.inboxcore");
			return package_list;
		}
		//获取并显示已安装app版本信息
		public void get_app_version_String(List<String> package_list) {
			info_area.setText("版本信息：\n");
			String grep = "";
			if (OS_type.equals("mac")) {
				grep = cc.grepString;
			}
			else if (OS_type.equals("windows")) {
				grep = cc.findstrString;
			}
			insatlled_app_box.removeAllItems();
			insatlled_app_box.addItem("选择要删除的应用");
			for (String packagename:package_list) {
				String getVersioncmd = cc.dumpsys_packageString + packagename + cc.symbol_orString + grep + cc.versionNameString;
				String versionstr = ec.adb_exec(getVersioncmd);
				versionstr = versionstr.replace("versionName=", "");
				info_area.append(packagename+":"+versionstr);
				insatlled_app_box.addItem(packagename);
			}
		}
		
	}
	class Get_app_version_Thread implements Runnable{
		@Override
		public void run() {
			ach.get_app_version_String(ach.get_3_package_list());
		}
		
	}
	class Uninstall_app_Thread implements Runnable{
		@Override
		public void run() {
			String uninstallPackageName = insatlled_app_box.getSelectedItem().toString();
			String responseString = ec.adb_exec(cc.uninstallString+uninstallPackageName);
			ach.get_app_version_String(ach.get_3_package_list());
			info_area.append("卸载"+uninstallPackageName+" "+responseString);
		}
		
	}
	class Install_APP_Thread implements Runnable{
		@Override
		public void run() {
			try{
				progress_bar_value = 0;
				int all = 1;
				all = all + choosed_appsArrayListString.size();
				install_progress_bar.setValue(0);
				String dialogStr = "";
				for(String tmp:choosed_appsArrayListString){
					String commandString = cc.installString+tmp;
					ec.adb_exec(commandString);
		            ach.progress_show(all, progress_bar_value+=1, install_progress_bar);
		            dialogStr += tmp + "\n";
					}
					ach.restart_APP();
					install_progress_bar.setValue(100);
					JOptionPane.showMessageDialog(null, "安装\n"+dialogStr+"完成！", "安装成功",JOptionPane.PLAIN_MESSAGE);
				}
			catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}
	class Uninstall_ALL_Thread implements Runnable{
		@Override
		public void run() {
			try{
				progress_bar_value = 0;
				int all = 0;
				all = all + choosed_appsArrayListString.size() + 1;
				install_progress_bar.setValue(0);
//				String package3String = ec.adb_exec(cc.list_package_3);
//				String[] package3StringsLiStrings = package3String.split("package:");
//				System.out.println(package3StringsLiStrings.length);
//				List<String> package_list=new ArrayList<String>();
//				for (int i = 1; i < package3StringsLiStrings.length; i++) {
//					System.out.println(i +":"+ package3StringsLiStrings[i]);
//					String s1 = package3StringsLiStrings[i].trim();
//					package_list.add(s1);
//					}
//				package_list.add("com.inhand.inboxcore");
				List<String> package_list = ach.get_3_package_list();
				System.out.println(package_list);
				
				all = all + package_list.size();
				String dialogStr = "";
				for(String utemp:package_list){
					ec.adb_exec(cc.uninstallString+utemp);
					dialogStr += utemp + "\n";
					ach.progress_show(all, progress_bar_value+=1, install_progress_bar);
					}
				install_progress_bar.setValue(100);
				JOptionPane.showMessageDialog(null, "卸载\n"+dialogStr+"完成！", "卸载所有第三方app",JOptionPane.PLAIN_MESSAGE);
				}
			catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}
	class Uninstall_and_Install_Thread implements Runnable{
		@Override
		public void run() {
			try{
				progress_bar_value = 0;
				int all = 0;
				all = all + choosed_appsArrayListString.size() + 1;
				install_progress_bar.setValue(0);
//				String package3String = ec.adb_exec(cc.list_package_3);
//				String[] package3StringsLiStrings = package3String.split("package:");
//				System.out.println(package3StringsLiStrings.length);
//				List<String> package_list=new ArrayList<String>();
//				for (int i = 1; i < package3StringsLiStrings.length; i++) {
//					System.out.println(i +":"+ package3StringsLiStrings[i]);
//					String s1 = package3StringsLiStrings[i].trim();
//					package_list.add(s1);
//				}
//				package_list.add("com.inhand.inboxcore");
				List<String> package_list = ach.get_3_package_list();
				System.out.println(package_list);
				
				all = all + package_list.size();
				for(String utemp:package_list){
					ec.adb_exec(cc.uninstallString+utemp);
					ach.progress_show(all, progress_bar_value+=1, install_progress_bar);
					}
				ec.adb_exec(cc.remove_apps_dirString);
				ec.adb_exec(cc.mkdir_apps_dirString);
				ach.progress_show(all, progress_bar_value+=1, install_progress_bar);
				String dialogStr = "";
				for (String tmp:choosed_appsArrayListString) {
					String commandString = cc.pushString+tmp+cc.apps_dirString;
					ec.adb_exec(commandString);
					ach.progress_show(all, progress_bar_value+=1, install_progress_bar);
					dialogStr += tmp + "\n";
					}
				ec.adb_exec(cc.start_install_activityString);
				install_progress_bar.setValue(100);
				JOptionPane.showMessageDialog(null, "推送\n"+dialogStr+"完成！并执行Install安装", "卸载并安装",JOptionPane.PLAIN_MESSAGE);
				}
			catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}
	class Screenshot_Thread implements Runnable{
		@Override
		public void run() {
			String formatTime = CommonOperations.get_format_time();
			String command1 = "shell /system/bin/screencap -p /sdcard/sc123456.png";
			String command2 = "pull /sdcard/sc123456.png " + screenshot_path + "sc" + formatTime + ".png";
			String command3 = "shell rm -rf /sdcard/sc123456.png";
			String command4_windows = "cmd.exe /c start " + screenshot_path;
			String command4_macosx = "open " + screenshot_path;
			String command4 = "";
			String command5 = "shell du -k sdcard/sc123456.png";
			if (OS_type.equals("mac")) {
				command4 = command4_macosx;
			}
			else if (OS_type.equals("windows")) {
				command4 = command4_windows;
			}
			try {
				screenshot_label.setVisible(true);
				screenshot_label.setForeground(Color.ORANGE);
				screenshot_label.setText("截图中……");
				boolean flag = false;
				ec.adb_exec(command1);
				while(!flag){
					Thread.sleep(1000);
					String response = ec.adb_exec(command5);
					String[] a = response.split("sdcard");
					String picLength = a[0].trim();
					if (!picLength.equals("0")) {
						flag = true;
					}
				}
				Thread.sleep(100);
				ec.adb_exec(command2);
				ec.adb_exec(command3);
				screenshot_label.setText("完成！");
				screenshot_label.setForeground(Color.green);
				int n = JOptionPane.showConfirmDialog(null, "是否立即查看截图？","截图完成",JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					ec.shell_exec(command4);
				}
				Thread.sleep(1000);
				screenshot_label.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	class Timing_task{
		public void adbdevicesTimerDemo()
		{
		int delay = 0;//ms
		int period = 5000;//ms
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {    
		       public void run(){
		    	   try{
		    		   ach.get_devices_info();
		    	   } 
		    	   catch (Exception e){
		    		   e.printStackTrace();
		    	   }
		       }
		   }, delay, period);
		}
	}
}
