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
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
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
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxEditor;
import javax.swing.JRadioButton;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class Tools {

	String OS_type = "";
	static String adb_path = "";
	// 目录
	public String root_path;
	public String screenshot_path;
	public String temp_file_path;

	private JFrame frame;
	Execute_command ec;
	Action_handler ach;
	Device_info_Timing_task ditt;
	CommonCommands cc;

	// UI公用
	JComboBox<String> device_selection_box;
	JTextArea device_display_area;
	JLabel screenshot_label;
	private JProgressBar install_progress_bar;
	private JTextArea info_area;
	private JComboBox<String> insatlled_app_box;
	private JLabel data_status_label;
	private JLabel wifi_status_label;
	private JRadioButton wifi_off_radioButton;
	private JRadioButton data_off_radioButton;
	private JRadioButton wifi_open_radioButton;
	private JRadioButton data_open_radioButton;
	private ButtonGroup wifi_group;
	private ButtonGroup data_group;
//	private JButton select_adb_path_button;

	// 公有变量
	ArrayList<String> choosed_appsArrayListString = new ArrayList<String>();
	ArrayList<String> choosed_configsArrayListString = new ArrayList<String>();
	private int progress_bar_value;
	public String grep = "";
	private Map<String, String> vendor_map = new HashMap<String, String>();
	private JComboBox<String> server_address_combobox;
	public String last_machine_id = "";
	private String current_machine_id = "";
	private JComboBox<String> org_name_combobox;
	private JComboBox<String> serial_port_combobox;
	private JComboBox<String> current_manufacturer_combobox;
	private JCheckBox only_matser_serial_chckbx;
	private String running_config_file_path;
	private JTextField log_saved_path_field;
	private JSpinner time_selector;
	private JCheckBox set_time_and_restart_checkbox;
	private String device_log_path;
	private String crash_log_path;
	private String crash_log_xml_path;
	private JLabel crash_log_tip;
	private JCheckBox crash_log_monitor_checkbox;
	private JCheckBox log_advertisement_CheckBox;
	private JCheckBox log_UI_CheckBox;
	private JCheckBox log_vcs_CheckBox;
	private JCheckBox log_dms_CheckBox;
	private JCheckBox log_pay_CheckBox;
	private JCheckBox log_setting_CheckBox;
	private JCheckBox log_core_CheckBox;
	private JCheckBox log_vmcservice_CheckBox;
	private JTextField tags_input_field;

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
	 * 
	 * @throws IOException
	 */
	public Tools() throws IOException {
		ec = new Execute_command();
		ach = new Action_handler();
		cc = new CommonCommands();
		ditt = new Device_info_Timing_task();
		initialize();
	}

	private void initialize() throws IOException {
		create_main_frame();
		init_workspace();
		init_UI();
		init_time_task();
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
		if (os_nameString.indexOf("mac") >= 0) {
			grep = cc.grepString;
			return "mac";
		} else if (os_nameString.indexOf("windows") >= 0) {
			grep = cc.findstrString;
//			grep = cc.grepString;
			return "windows";
		} else {
			return null;
		}
	}

	private void init_workspace() throws IOException {
		OS_type = get_os_type();
		root_path = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + File.separator
				+ "inhand-adbtool" + File.separator;
		screenshot_path = root_path + "screenshot" + File.separator;
		temp_file_path = root_path + "temp_file" + File.separator;
		running_config_file_path = root_path + "running_config" + File.separator;
		device_log_path = root_path + "device-log" + File.separator;
		crash_log_path = root_path + "crash-log" + File.separator;
		crash_log_xml_path = crash_log_path + "crash_log_xml.xml";
		String adb_path_file_path = root_path + "adb_path";
		CommonOperations.mkdir(root_path);
		CommonOperations.mkdir(screenshot_path);
		CommonOperations.mkdir(temp_file_path);
		CommonOperations.mkdir(running_config_file_path);
		CommonOperations.mkdir(device_log_path);
		CommonOperations.mkdir(crash_log_path);
		CommonOperations.new_file_xml(crash_log_xml_path);
		if (!new File(adb_path_file_path).exists()) {
			if (OS_type.equals("mac")) {
				adb_path = FileChooser.file_chooser() + " ";
			} else if (OS_type.equals("windows")) {
				adb_path = "adb" + " ";
			}
			CommonOperations.create_new_file(adb_path_file_path);
			CommonOperations.write_file(adb_path_file_path, adb_path);
		} else {
			adb_path = CommonOperations.read_file(adb_path_file_path);
			adb_path = CommonOperations.replace_trn(adb_path);
			adb_path = adb_path + " ";
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
		info_module();
	}

	private void init_time_task() {
		// 轮询devices_info
		ditt.adbdevicesTimerDemo();
		// 轮询crash_log
		ditt.crash_logTimerDemo();
	}

	private void devices_info_module() {
		// 设备信息模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("设备信息"));
		frame.getContentPane().add(panel);
		// 售货机编号显示区
		device_display_area = new JTextArea();
		device_display_area.setLineWrap(true);
		device_display_area.setEditable(false);
		device_display_area.setBounds(20, 36, 152, 36);
		panel.add(device_display_area);
		JScrollPane device_display_scroll = new JScrollPane(device_display_area);
		device_display_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// 滚动条总是出现
		device_display_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		device_display_scroll.setBounds(10, 20, 170, 70);
		panel.add(device_display_scroll);
		// 重启应用
		JButton restart_application_button = new JButton("重启应用");
		restart_application_button.setBounds(193, 20, 100, 50);
		panel.add(restart_application_button);
		restart_application_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.restart_APP();// 点击重启应用
			}
		});
		// 设备截图
		JButton devices_screenshot_button = new JButton("设备截图");
		devices_screenshot_button.setBounds(193, 79, 100, 50);
		panel.add(devices_screenshot_button);
		devices_screenshot_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.devices_screenshot();// 点击设备截图
			}
		});
		// 设备选择下拉框
		JLabel label_1 = new JLabel("选择连接的设备：");
		label_1.setBounds(10, 103, 161, 15);
//		panel.add(label_1);
		device_selection_box = new JComboBox<String>();
		device_selection_box.setBounds(6, 130, 177, 30);
//		panel.add(device_selection_box);
		// 截图信息提示
		screenshot_label = new JLabel("");
		screenshot_label.setBounds(193, 141, 111, 15);
		panel.add(screenshot_label);
		// wifi网络开关
		JLabel wifi_label = new JLabel("WIFI开关：");
		wifi_label.setBounds(10, 102, 84, 16);
		panel.add(wifi_label);
		wifi_off_radioButton = new JRadioButton("关闭");
		wifi_off_radioButton.setBounds(65, 121, 58, 23);
		panel.add(wifi_off_radioButton);
		wifi_off_radioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (wifi_off_radioButton.isSelected()) {
					ach.info_append_to_text_area("关闭wifi");
					ec.adb_exec(cc.set_wifi_disableString);
				}
			}
		});
		wifi_open_radioButton = new JRadioButton("打开");
		wifi_open_radioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (wifi_open_radioButton.isSelected()) {
					ach.info_append_to_text_area("打开wifi");
					ec.adb_exec(cc.set_wifi_enableString);
				}
			}
		});
		wifi_open_radioButton.setBounds(10, 121, 58, 23);
		panel.add(wifi_open_radioButton);
		wifi_group = new ButtonGroup();
		wifi_group.add(wifi_off_radioButton);
		wifi_group.add(wifi_open_radioButton);
		wifi_status_label = new JLabel("●");
		wifi_status_label.setBounds(78, 102, 61, 16);
		panel.add(wifi_status_label);
		// data网络开关
		JLabel data_label = new JLabel("数据开关：");
		data_label.setBounds(10, 156, 84, 16);
		panel.add(data_label);
		data_off_radioButton = new JRadioButton("关闭");
		data_off_radioButton.setBounds(65, 173, 58, 23);
		panel.add(data_off_radioButton);
		data_off_radioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (data_off_radioButton.isSelected()) {
					ach.info_append_to_text_area("关闭数据网络");
					ec.adb_exec(cc.set_data_disableString);
				}
			}
		});
		data_open_radioButton = new JRadioButton("打开");
		data_open_radioButton.setBounds(10, 173, 58, 23);
		panel.add(data_open_radioButton);
		data_open_radioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (data_open_radioButton.isSelected()) {
					ach.info_append_to_text_area("打开数据网络");
					ec.adb_exec(cc.set_data_enableString);
				}
			}
		});
		data_group = new ButtonGroup();
		data_group.add(data_off_radioButton);
		data_group.add(data_open_radioButton);
		data_status_label = new JLabel("●");
		data_status_label.setBounds(78, 156, 61, 16);
		panel.add(data_status_label);

	}

	private void device_config_module() {
		// 配置文件模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("配置文件"));
		frame.getContentPane().add(panel);
		// 获取运行配置
		JButton get_running_configure_button = new JButton("获取运行配置");
		get_running_configure_button.setBounds(6, 20, 112, 34);
		panel.add(get_running_configure_button);
		get_running_configure_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pull_running_config_Thread prct = new Pull_running_config_Thread();
				Thread t1 = new Thread(prct);
				t1.start();
			}
		});
		// 获取活动配置
		JButton get_active_configuration_button = new JButton("获取活动配置");
		get_active_configuration_button.setBounds(6, 55, 112, 34);
		panel.add(get_active_configuration_button);
		get_active_configuration_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pull_game_config_Thread pgct = new Pull_game_config_Thread();
				Thread t1 = new Thread(pgct);
				t1.start();
			}
		});
		// 货道快速配置
		JButton channel_rapid_configuration_button = new JButton("货道快速配置");
		channel_rapid_configuration_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				channel_set_frame();
			}
		});
		channel_rapid_configuration_button.setBounds(6, 140, 112, 34);
		panel.add(channel_rapid_configuration_button);
		// 推送配置文件
		JTextArea push_configuration_file_area = new JTextArea("请将需要下发的配置文件拖拽至此\n");
		push_configuration_file_area.setLineWrap(true);
		push_configuration_file_area.setEditable(false);
		panel.add(push_configuration_file_area);
		JScrollPane push_configuration_file_area_scroll = new JScrollPane(push_configuration_file_area);
		push_configuration_file_area_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		push_configuration_file_area_scroll.setBounds(126, 20, 178, 90);
		push_configuration_file_area_scroll.setViewportView(push_configuration_file_area);
		panel.add(push_configuration_file_area_scroll);
		push_configuration_file_area.setTransferHandler(new TransferHandler() {
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
//					String regexstr = "[\u4e00-\u9fa5\u0020]";
//					Pattern p = Pattern.compile(regexstr);
//					ArrayList<String> illegal_configs = new ArrayList<String>();
					for (String configstring : strs) {
						choosed_configsArrayListString.add(configstring);
						push_configuration_file_area.setText("");
//						Matcher m = p.matcher(configstring);
//						if (m.find()) {
//							// System.out.print("\n包含中文");
////							JOptionPane.showMessageDialog(null, configstring+"\n文件路径不能包含中文和空格", "添加失败",JOptionPane.WARNING_MESSAGE);
//							push_configuration_file_area.setText("");
//							illegal_configs.add(configstring);
//						} else {
//							choosed_configsArrayListString.add(configstring);
//							push_configuration_file_area.setText("");
//						}
					}
//					if (illegal_configs.size() != 0) {
//						String dialog_str = "";
//						for (String config : illegal_configs) {
//							dialog_str += config + "\n";
//						}
//						JOptionPane.showMessageDialog(null, dialog_str + "\n包含中文和空格", "添加失败",
//								JOptionPane.WARNING_MESSAGE);
//					}
					for (String ChoosedConfigstring : choosed_configsArrayListString) {
						push_configuration_file_area.append(ChoosedConfigstring + "\n");
					}
					return true;
				} catch (Exception e) {
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
		// 清空推送配置文件内容
		JButton clear_push_configuration_file_area_button = new JButton();
		clear_push_configuration_file_area_button.setToolTipText("清空待下发配置");
		clear_push_configuration_file_area_button.setText("清空待下发配置");
		// clearconfig.setIcon(new
		// ImageIcon(getClass().getResource("/toolIcon/searchreset.png")));
		clear_push_configuration_file_area_button.setBounds(126, 145, 175, 30);
		panel.add(clear_push_configuration_file_area_button);
		clear_push_configuration_file_area_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choosed_configsArrayListString.clear();
				push_configuration_file_area.setText("请将需要下发的配置文件拖拽至此\n");
			}
		});
		// 推送下发配置文件
		JButton push_configuration_file_button = new JButton("推送下发配置文件");
		push_configuration_file_button.setBounds(124, 110, 180, 34);
		panel.add(push_configuration_file_button);
		push_configuration_file_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Push_config_Thread prt = new Push_config_Thread();
				Thread t1 = new Thread(prt);
				t1.start();
			}
		});
		// 进度条
		JProgressBar config_progress_bar = new JProgressBar();
		config_progress_bar.setStringPainted(true);
		config_progress_bar.setBounds(6, 176, 298, 20);
		panel.add(config_progress_bar);

	}

	private void device_simulate_and_timeset_module() {
		// 按键模拟、时间修改
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("按键模拟、时间修改"));
		frame.getContentPane().add(panel);
		// 修改Android系统时间
		// 时间选择器
		SpinnerDateModel date_model = new SpinnerDateModel();
		time_selector = new JSpinner(date_model);
		time_selector.setBounds(115, 16, 189, 75);
		panel.add(time_selector);
		time_selector.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		time_selector.setValue(new Date());
		JSpinner.DateEditor date_editor = new JSpinner.DateEditor(time_selector, "yyyy-MM-dd HH:mm:ss");
		time_selector.setEditor(date_editor);
		// 设置时间、还原时间
		JButton set_time_button = new JButton("设置设备时间");
		set_time_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.set_android_system_time();
			}
		});
		set_time_button.setBounds(167, 91, 122, 35);
		panel.add(set_time_button);
		JButton reduction_time_button = new JButton("设备还原时间");
		reduction_time_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.revert_android_system_time();
			}
		});
		reduction_time_button.setBounds(167, 125, 122, 35);
		panel.add(reduction_time_button);
		// 是否同时重启按钮
		set_time_and_restart_checkbox = new JCheckBox("同时重启");
		set_time_and_restart_checkbox.setBounds(167, 161, 122, 35);
		panel.add(set_time_and_restart_checkbox);
		// 模拟系统按键
		// HOME
		JButton home_simulating_keyboard_events = new JButton("HOME");
		home_simulating_keyboard_events.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.input_key("HOME");
			}
		});
		home_simulating_keyboard_events.setBounds(10, 24, 82, 37);
		panel.add(home_simulating_keyboard_events);
		// BACK
		JButton back_simulating_keyboard_events = new JButton("BACK");
		back_simulating_keyboard_events.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.input_key("BACK");
			}
		});
		back_simulating_keyboard_events.setBounds(10, 61, 82, 37);
		panel.add(back_simulating_keyboard_events);
		// MENU
		JButton menu_simulating_keyboard_events = new JButton("MENU");
		menu_simulating_keyboard_events.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.input_key("MENU");
			}
		});
		menu_simulating_keyboard_events.setBounds(10, 100, 82, 37);
		panel.add(menu_simulating_keyboard_events);
		// MODE
		JButton mode_simulating_keyboard_events = new JButton("MODE");
		mode_simulating_keyboard_events.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.input_key("MODE");
			}
		});
		mode_simulating_keyboard_events.setBounds(10, 149, 82, 47);
		panel.add(mode_simulating_keyboard_events);
		// 暂不可用
//		panel.setVisible(false);
	}

	private void show_log_module() {
		// 日志命令模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("打印日志命令获取"));
		frame.getContentPane().add(panel);
		// 日志tag输入框
		tags_input_field = new JTextField();
		tags_input_field.setBounds(10, 142, 294, 54);
		panel.add(tags_input_field);
		tags_input_field.setColumns(10);
		// 获取日志命令
		JButton get_log_cmd_button = new JButton("获取日志命令");
		get_log_cmd_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.get_log_cmd();
			}
		});
		get_log_cmd_button.setBounds(186, 103, 118, 37);
		panel.add(get_log_cmd_button);
		
		log_advertisement_CheckBox = new JCheckBox("Advertise");
		log_advertisement_CheckBox.setBounds(10, 79, 118, 23);
		panel.add(log_advertisement_CheckBox);
		
		log_UI_CheckBox = new JCheckBox("UI、Game");
		log_UI_CheckBox.setBounds(10, 49, 118, 23);
		panel.add(log_UI_CheckBox);
		
		log_vcs_CheckBox = new JCheckBox("VCS");
		log_vcs_CheckBox.setBounds(122, 19, 79, 23);
		panel.add(log_vcs_CheckBox);
		
		log_dms_CheckBox = new JCheckBox("DMS");
		log_dms_CheckBox.setBounds(122, 49, 79, 23);
		panel.add(log_dms_CheckBox);
		
		log_pay_CheckBox = new JCheckBox("PAY");
		log_pay_CheckBox.setBounds(122, 79, 79, 23);
		panel.add(log_pay_CheckBox);
		
		log_setting_CheckBox = new JCheckBox("VMCSetting");
		log_setting_CheckBox.setBounds(10, 109, 118, 23);
		panel.add(log_setting_CheckBox);
		
		log_core_CheckBox = new JCheckBox("InboxCore");
		log_core_CheckBox.setBounds(10, 19, 118, 23);
		panel.add(log_core_CheckBox);
		
		log_vmcservice_CheckBox = new JCheckBox("VMCService");
		log_vmcservice_CheckBox.setBounds(199, 19, 128, 23);
		panel.add(log_vmcservice_CheckBox);
		// 暂不可用
//		panel.setVisible(false);
	}

	private void app_install_module() {
		// Inhand-app安装模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("Inhand-app安装"));
		frame.getContentPane().add(panel);
		// 待安装app框
		JTextArea push_apps_file_area = new JTextArea("将需要安装的APK文件拖拽至此\n");
		push_apps_file_area.setLineWrap(true);
		push_apps_file_area.setEditable(false);
		panel.add(push_apps_file_area);
		JScrollPane push_apps_file_area_scroll = new JScrollPane(push_apps_file_area);
		push_apps_file_area_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		push_apps_file_area_scroll.setBounds(126, 20, 178, 104);
		push_apps_file_area_scroll.setViewportView(push_apps_file_area);
		panel.add(push_apps_file_area_scroll);
		push_apps_file_area.setTransferHandler(new TransferHandler() {
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
//					String regexstr = "[\u4e00-\u9fa5\u0020]";
//					Pattern p = Pattern.compile(regexstr);
//					ArrayList<String> illegal_configs = new ArrayList<String>();
					for (String configstring : strs) {
						choosed_appsArrayListString.add(configstring);
						push_apps_file_area.setText("");
//						Matcher m = p.matcher(configstring);
//						if (m.find()) {
//							// System.out.print("\n包含中文");
////							JOptionPane.showMessageDialog(null, configstring+"\n文件路径不能包含中文和空格", "添加失败",JOptionPane.WARNING_MESSAGE);
//							push_apps_file_area.setText("");
//							illegal_configs.add(configstring);	
//						} else {
//							choosed_appsArrayListString.add(configstring);
//							push_apps_file_area.setText("");
//						}
					}
//					if (illegal_configs.size() != 0) {
//						String dialog_str = "";
//						for (String config : illegal_configs) {
//							dialog_str += config + "\n";
//						}
//						JOptionPane.showMessageDialog(null, dialog_str + "\n包含中文和空格", "添加失败",
//								JOptionPane.WARNING_MESSAGE);
//					}
					for (String ChoosedConfigstring : choosed_appsArrayListString) {
						push_apps_file_area.append(ChoosedConfigstring + "\n");
					}
					return true;
				} catch (Exception e) {
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
		// 仅安装
		JButton install_and_restart_button = new JButton("安装app");
		install_and_restart_button.setBounds(6, 84, 114, 35);
		panel.add(install_and_restart_button);
		install_and_restart_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (choosed_appsArrayListString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请添加需要安装的安装包", "提示", JOptionPane.CANCEL_OPTION);
				} else {
					int n = JOptionPane.showConfirmDialog(null, "是否安装APP", "安装", JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						Install_APP_Thread Iat = new Install_APP_Thread();
						Thread t1 = new Thread(Iat);
						t1.start();
					}
				}
			}
		});
		// 卸载全套第三方app
		JButton uninstall_all_inhand_button = new JButton("卸载All第三方");
		uninstall_all_inhand_button.setBounds(6, 140, 114, 35);
		panel.add(uninstall_all_inhand_button);
		uninstall_all_inhand_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "是否要将系统第三方 APP全部卸载", "全部卸载", JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					Uninstall_ALL_Thread uIt = new Uninstall_ALL_Thread();
					Thread t1 = new Thread(uIt);
					t1.start();
				}
			}
		});
		// 卸载inhand全套app并安装app
		JButton cover_installation_button = new JButton("<html>卸载<br>All第三方<br>并安装</html>");
		cover_installation_button.setBounds(6, 21, 114, 59);
		panel.add(cover_installation_button);
		cover_installation_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (choosed_appsArrayListString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请添加需要安装的安装包", "提示", JOptionPane.CANCEL_OPTION);
				} else {
					int n = JOptionPane.showConfirmDialog(null, "是否要全部卸载，并安装APP", "卸载并安装",
							JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						Uninstall_and_Install_Thread It = new Uninstall_and_Install_Thread();
						Thread t1 = new Thread(It);
						t1.start();
					}
				}
			}
		});
		// 清空待安装app内容
		JButton clear_push_app_file_area_button = new JButton();
		clear_push_app_file_area_button.setToolTipText("清空待安装app");
		clear_push_app_file_area_button.setText("清空待安装app");
		clear_push_app_file_area_button.setBounds(126, 142, 175, 30);
		panel.add(clear_push_app_file_area_button);
		clear_push_app_file_area_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choosed_appsArrayListString.clear();
				push_apps_file_area.setText("将需要安装的APK文件拖拽至此\n");
			}
		});
		// 进度条
		install_progress_bar = new JProgressBar();
		install_progress_bar.setStringPainted(true);
		install_progress_bar.setBounds(6, 176, 298, 20);
		panel.add(install_progress_bar);

	}

	private void app_manager_module() {
		// 应用管理模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("应用管理"));
		frame.getContentPane().add(panel);
		// 已安装应用信息
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
		// 卸载应用
		JLabel label = new JLabel("选择需要卸载的应用：");
		label.setBounds(10, 67, 196, 15);
		panel.add(label);
		// 已安装应用选择框
		insatlled_app_box = new JComboBox<String>();
		insatlled_app_box.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if (insatlled_app_box.getItemCount() == 0 | insatlled_app_box.getItemCount() == 1) {
					ach.add_3package_to_installed_app_box(ach.get_3_package_list());
				}
			}
		});
		insatlled_app_box.setBounds(6, 82, 196, 32);
		panel.add(insatlled_app_box);
		// 卸载按钮
		JButton uninstall_app_button = new JButton("卸载");
		uninstall_app_button.setBounds(89, 113, 117, 29);
		panel.add(uninstall_app_button);
		uninstall_app_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String uninstallPackageName = insatlled_app_box.getSelectedItem().toString();
				if (uninstallPackageName.contains("com")) {
					int n = JOptionPane.showConfirmDialog(null, "卸载" + uninstallPackageName, "卸载",
							JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						Uninstall_app_Thread uat = new Uninstall_app_Thread();
						Thread t1 = new Thread(uat);
						t1.start();
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择需要卸载的包", "提示", JOptionPane.CANCEL_OPTION);
				}
			}
		});
		// 刷新按钮
		JButton refresh_app_button = new JButton("刷新");
		refresh_app_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.add_3package_to_installed_app_box(ach.get_3_package_list());
			}
		});
		refresh_app_button.setBounds(206, 84, 63, 29);
		panel.add(refresh_app_button);
		// 点我提示语
		JLabel lblNewLabel = new JLabel("←看版本号点我(￣.￣)");
		lblNewLabel.setBounds(145, 30, 146, 16);
		panel.add(lblNewLabel);

//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				String newString = "";
//				String cmdString = "shell du /sdcard/inbox/log/crash_log.txt | busybox awk '{print $1}'";
//				System.out.print(ec.adb_exec(cmdString));
//			}
//		});
//		btnNewButton.setBounds(6, 151, 117, 29);
//		panel.add(btnNewButton);

	}

	private void save_log_module() {
		// 日志保存模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("日志保存"));
		frame.getContentPane().add(panel);
//		// tag过滤选项
//		JCheckBox save_log_with_tag_checkbox = new JCheckBox("tag过滤");
//		save_log_with_tag_checkbox.setBackground(Color.WHITE);
//		save_log_with_tag_checkbox.setBounds(10, 20, 103, 23);
//		panel.add(save_log_with_tag_checkbox);
//		// 日志tag选择框
//		JLabel label = new JLabel("Tags:");
//		label.setBounds(10, 46, 41, 23);
//		panel.add(label);
//		JComboBox<String> common_tags_combobox = new JComboBox<String>();
//		common_tags_combobox.setBounds(47, 47, 118, 22);
//		panel.add(common_tags_combobox);
//		// 停止保存日志
//		JButton stop_save_log_button = new JButton("Stop");
//		stop_save_log_button.setFont(new Font("宋体", Font.PLAIN, 10));
//		stop_save_log_button.setBounds(223, 108, 69, 39);
//		panel.add(stop_save_log_button);
//		stop_save_log_button.setEnabled(false);
//		// 开始保存日志
//		JButton start_save_log_button = new JButton();
//		start_save_log_button.setFont(new Font("宋体", Font.PLAIN, 10));
//		start_save_log_button.setText("Start");
//		start_save_log_button.setBounds(135, 108, 78, 39);
//		panel.add(start_save_log_button);
		// 选择日志保存路径
		JButton select_log_save_path_button = new JButton("选择日志保存路径");
		select_log_save_path_button.setBounds(10, 107, 118, 39);
		panel.add(select_log_save_path_button);
		select_log_save_path_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String path = FileChooser.pathChooser() + File.separator;
				log_saved_path_field.setText(path);
			}
		});
		// 打包取log
		JButton zip_and_pull_log_button = new JButton("Pull main日志");
		zip_and_pull_log_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确认pull main log？", "确认？", JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					Pull_main_log_Thread pmlt = new Pull_main_log_Thread();
					Thread t1 = new Thread(pmlt);
					t1.start();
				}
			}
		});
		zip_and_pull_log_button.setBounds(6, 24, 118, 39);
		panel.add(zip_and_pull_log_button);
		// 日志保存路径
		JLabel label_1 = new JLabel("路径:");
		label_1.setBounds(10, 81, 41, 23);
		panel.add(label_1);
		log_saved_path_field = new JTextField();
		log_saved_path_field.setColumns(10);
		log_saved_path_field.setBounds(47, 75, 260, 30);
		panel.add(log_saved_path_field);
		String log_saved_path = root_path + "device-log" + File.separator;
		log_saved_path_field.setText(log_saved_path);
		// 崩溃日志
		// 保存崩溃日志
		JButton save_crash_log_button = new JButton("获取崩溃日志");
		save_crash_log_button.setBounds(10, 158, 114, 38);
		panel.add(save_crash_log_button);
		save_crash_log_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "是否获取crash_log.txt？", "确认？", JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					Pull_crash_log_Thread pclt = new Pull_crash_log_Thread();
					Thread t1 = new Thread(pclt);
					t1.start();
				}
			}
		});
		// 崩溃日志更新提示
		crash_log_tip = new JLabel("CrashLog.txt有更新！");
		crash_log_tip.setBounds(135, 158, 157, 15);
		crash_log_tip.setForeground(Color.RED);
		crash_log_tip.setVisible(false);
		panel.add(crash_log_tip);
		// 崩溃监控开关
		crash_log_monitor_checkbox = new JCheckBox("崩溃日志监控开关");
		crash_log_monitor_checkbox.setSelected(true);
		crash_log_monitor_checkbox.setBounds(135, 173, 136, 23);
		panel.add(crash_log_monitor_checkbox);
		// 暂不可用
//		panel.setVisible(false);
	}

	private void base_config_module() {
		// 基础配置模块
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("基础配置"));
		frame.getContentPane().add(panel);
		// 配置显示与修改
		// 标签显示
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
		// 更新配置按钮
		JButton update_configuration_button = new JButton("更新配置");
		update_configuration_button.setBounds(84, 161, 86, 35);
		panel.add(update_configuration_button);
		update_configuration_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Modify_running_config_Thread mrct = new Modify_running_config_Thread();
				Thread t1 = new Thread(mrct);
				t1.start();
			}
		});
		// 刷新配置按钮
		JButton refresh_configuration_button = new JButton("刷新");
		refresh_configuration_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Display_running_config_Thread drct = new Display_running_config_Thread();
				Thread t1 = new Thread(drct);
				t1.start();
			}
		});
		refresh_configuration_button.setBounds(6, 161, 72, 35);
		panel.add(refresh_configuration_button);
		// 机构名称下拉框
		org_name_combobox = new JComboBox<String>();
		org_name_combobox.setBounds(74, 24, 174, 25);
		org_name_combobox.addItem("");
		org_name_combobox.setEditable(true);
		panel.add(org_name_combobox);
		// 服务器地址下拉框
		server_address_combobox = new JComboBox<String>();
		server_address_combobox.setBounds(74, 55, 174, 25);
		server_address_combobox.addItem("");
		server_address_combobox.setEditable(true);
		panel.add(server_address_combobox);
		// 串口下拉框
		serial_port_combobox = new JComboBox<String>();
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
		panel.add(serial_port_combobox);
		// 当前厂家下拉框
		current_manufacturer_combobox = new JComboBox<String>();
		current_manufacturer_combobox.addItem("----");
		current_manufacturer_combobox.setBounds(74, 125, 164, 25);
		panel.add(current_manufacturer_combobox);
		// 只修改主柜串口选择框
		only_matser_serial_chckbx = new JCheckBox("只修改主柜串口");
		only_matser_serial_chckbx.setBounds(181, 88, 123, 25);
		panel.add(only_matser_serial_chckbx);
		// 修改售货机编号
		JButton change_machine_id_button = new JButton("更改售货机编号");
		change_machine_id_button.setBounds(170, 161, 134, 35);
		panel.add(change_machine_id_button);
		change_machine_id_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.modify_machine_id();
			}
		});
	}

	private void info_module() {
		// 信息
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("信息"));
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
	private void channel_set_frame() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(300, 300, 200, 300);
		JPanel contentPane = new JPanel();
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tip_1 = new JLabel("一键配置前请同步平台商品");
		tip_1.setBounds(10, 150, 164, 15);
		tip_1.setForeground(Color.ORANGE);
		contentPane.add(tip_1);
		
		JButton set_channel_goods_button = new JButton("一键配置货道商品");
		set_channel_goods_button.setSize(164, 80);
		set_channel_goods_button.setLocation(10, 10);
		contentPane.add(set_channel_goods_button);
		set_channel_goods_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Set_channel_goods_Thread scgt = new Set_channel_goods_Thread();
				Thread t1 = new Thread(scgt);
				t1.start();
			}
		});
		
//		JButton set_all_channel_price_button = new JButton("一键改所有货道价格");
//		set_all_channel_price_button.setSize(164, 40);
//		set_all_channel_price_button.setLocation(10, 211);
//		contentPane.add(set_all_channel_price_button);
//		
//		JTextField price_input_filed = new JTextField();
//		price_input_filed.setBounds(57, 180, 73, 21);
//		contentPane.add(price_input_filed);
//		price_input_filed.setColumns(10);
//		
//		JLabel tip_2 = new JLabel("价格：");
//		tip_2.setBounds(10, 183, 54, 15);
//		contentPane.add(tip_2);
//		
//		JLabel tip_3 = new JLabel("分");
//		tip_3.setBounds(130, 183, 54, 15);
//		contentPane.add(tip_3);
		
		JButton clear_all_channel_goods_button = new JButton("一键清空货道配置");
		clear_all_channel_goods_button.setBounds(10, 100, 164, 40);
		contentPane.add(clear_all_channel_goods_button);
		clear_all_channel_goods_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clear_channel_goods_Thread ccgt = new Clear_channel_goods_Thread();
				Thread t1 = new Thread(ccgt);
				t1.start();
			}
		});
	}

	class Execute_command {
		public String adb_exec(String command) {
			String device_name = "";
			// 取消设备选择的功能
//			if (command.equals("devices")) {
//				device_name = "";
//			}else {
//				device_name =  "-s "+ device_selection_box.getSelectedItem().toString() + " ";
//			}
			command = adb_path + device_name + command;
			String returnString = "";
			Process pro = null;
			Runtime runTime = Runtime.getRuntime();
			System.out.print(command + "\n");
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
				returnString = "";
			}
//			returnString = CommonOperations.replace_n(returnString);
			System.out.print(returnString + "\n");
			return returnString;
		}

		public String shell_exec(String command) {
			String returnString = "";
			Process pro = null;
			Runtime runTime = Runtime.getRuntime();
			System.out.print(command);
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
				returnString = "";
			}
			return returnString;
		}
	}

	class Action_handler {

		// 信息显示
		public void info_append_to_text_area(Object text) {
			String s = String.valueOf(text);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			info_area.append("\n" + sdf.format(d) + " " + s);
			info_area.setCaretPosition(info_area.getText().length());
		}

		// 设备断开连接UI变化
		public void disconnect_ui_change() {
			server_address_combobox.setSelectedItem("----");
			serial_port_combobox.setSelectedItem("----");
			org_name_combobox.setSelectedItem("----");
			current_manufacturer_combobox.setSelectedItem("----");
			insatlled_app_box.removeAllItems();
			crash_log_tip.setVisible(false);
		}

		public void modify_machine_id() {
			JFrame frame2 = new JFrame("更改售货机编号");
			frame2.getContentPane().setLayout(null);
			frame2.setBounds(300, 300, 300, 200);
			frame2.setVisible(true);
			JTextField MachineId = new JTextField(last_machine_id);
			MachineId.setBounds(80, 10, 120, 30);
			frame2.getContentPane().add(MachineId);
			JButton changeId = new JButton("确认修改");
			changeId.setBounds(80, 80, 120, 30);
			frame2.getContentPane().add(changeId);
			changeId.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String fileNameString = temp_file_path + "machine_id.txt";
					String cmdString = cc.pushString + fileNameString + cc.running_config_dirString;
					FileOutputStream fos = null;
					try {
						fos = new FileOutputStream(fileNameString);
						String s = MachineId.getText().toString();
						fos.write(s.getBytes());
						fos.close();
						info_append_to_text_area(cmdString);
						String reString = ec.adb_exec(cmdString);
						info_append_to_text_area(reString);
						info_append_to_text_area("修改售货机编号为：" + s);
						restart_APP();
						JOptionPane.showMessageDialog(null, "已更改售货机编号为" + s, "更改售货机编号", JOptionPane.PLAIN_MESSAGE);
						frame2.dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}

		// 重启应用
		public void restart_APP() {
			String cmd = cc.restart_app_command;
			info_append_to_text_area(cmd);
			ec.adb_exec(cmd);
//    		JOptionPane.showMessageDialog(null, "已重启应用", "重启应用",JOptionPane.PLAIN_MESSAGE);
		}

		// 获取售货机编号
		public String get_machine_id() {
			String cmd = cc.get_machine_id;
			String response = ec.adb_exec(cmd);
			response = CommonOperations.replace_trn(response);
			device_display_area.setText("\n售货机编号:\n" + response);
			return response;
		}

		// 设置wifi和数据状态为off，因为可能会被自动开启
		public void set_wifi_or_data_off() {
			if (wifi_off_radioButton.isSelected()) {
				ec.adb_exec(cc.set_wifi_disableString);
			}
			if (data_off_radioButton.isSelected()) {
				ec.adb_exec(cc.set_data_disableString);
			}
		}

		// 获取adb devices
		public void get_devices_info() {
			String inquire_wifi_command = cc.dumpsys_wifi_String + cc.symbol_orString + grep + cc.WiFi_String;
			;
			String wifi_status = ec.adb_exec(inquire_wifi_command);
//	    	System.out.print(wifi_status);
			if (wifi_status.indexOf("enabled") != -1) {
				wifi_status_label.setText("on");
			} else {
				wifi_status_label.setText("off");
			}

			String inquire_data_command = cc.dumpsys_telephony_String + cc.symbol_orString + grep
					+ cc.mDataConnectionState_String;
			;
			String data_status = ec.adb_exec(inquire_data_command);
//	    	System.out.print(data_status);
			if (data_status.indexOf("=2") != -1) {
				data_status_label.setText("on");
			} else {
				data_status_label.setText("off");
			}
			current_machine_id = ach.get_machine_id();
			if (!current_machine_id.equals(last_machine_id)) {
				if (!current_machine_id.equals("") && !current_machine_id.equals(null)) {
					// 还原网络开关按钮
					wifi_group.clearSelection();
					data_group.clearSelection();
					// 重新获取基础配置
					info_append_to_text_area(String.format("检测到%s连接", current_machine_id));
					info_append_to_text_area("刷新基础配置……");
					Display_running_config_Thread drct = new Display_running_config_Thread();
					Thread t = new Thread(drct);
					t.start();
					// 重新获取已安装应用并添加到已安装应用选择框中
					add_3package_to_installed_app_box(get_3_package_list());
					info_append_to_text_area(String.format("%s已连接", current_machine_id));
				} else {
					info_append_to_text_area(String.format("%s断开连接", last_machine_id));
					disconnect_ui_change();
				}
			}
			last_machine_id = current_machine_id;
//	    	System.out.print("last:"+last_machine_id);
//	    	System.out.print("now:"+current_machine_id);
			// 去掉选择设备功能
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

		public void push_config(String fileNameString) {
			String cmdString = "";
			if (fileNameString.contains("game") || fileNameString.contains("promotion")) {
				cmdString = cc.pushString + fileNameString + cc.game_config_dirString;
			} else {
				cmdString = cc.pushString + fileNameString + cc.running_config_dirString;
			}
			info_append_to_text_area(cmdString);
			String reString = ec.adb_exec(cmdString);
			info_append_to_text_area(reString);
		}

		// 设备屏幕截图
		public void devices_screenshot() {
			Screenshot_Thread prtscT = new Screenshot_Thread();
			Thread pt = new Thread(prtscT);
			pt.start();
		}

		// 进度条显示
		public void progress_show(int all, int now, JProgressBar progressBar) {
			double prog = (double) now / (double) all;
			int progbarValue = (int) (prog * 100);
			progressBar.setValue(progbarValue);
		}

		// 获取第三方应用包名
		public List<String> get_3_package_list() {
			info_append_to_text_area("获取第三方应用包名");
			String package3String = ec.adb_exec(cc.list_package_3);
			String[] package3StringsLiStrings = package3String.split("package:");
			System.out.println(package3StringsLiStrings.length);
			List<String> package_list = new ArrayList<String>();
			for (int i = 1; i < package3StringsLiStrings.length; i++) {
				System.out.println(i + ":" + package3StringsLiStrings[i]);
				String s1 = package3StringsLiStrings[i].trim();
				package_list.add(s1);
			}
			package_list.add("com.inhand.inboxcore");
			return package_list;
		}

		// 将第三方包名添加至应用选择框
		public void add_3package_to_installed_app_box(List<String> package_list) {
			insatlled_app_box.removeAllItems();
			insatlled_app_box.addItem("选择要删除的应用");
			for (String packagename : package_list) {
				insatlled_app_box.addItem(packagename);
			}
		}

		// 获取并显示已安装app版本信息
		public void get_app_version_String(List<String> package_list) {
			info_append_to_text_area("版本信息：\n");
			String dialogString = "";
			for (String packagename : package_list) {
				String getVersioncmd = cc.dumpsys_packageString + packagename + cc.symbol_orString + grep
						+ cc.versionNameString;
				String versionstr = ec.adb_exec(getVersioncmd);
				versionstr = versionstr.replace("versionName=", "");
				versionstr = CommonOperations.replace_trn(versionstr);
				dialogString = dialogString + packagename + ":" + versionstr + "\n";
				info_area.append(packagename + ":" + versionstr + "\n");
				info_area.setCaretPosition(info_area.getText().length());
				add_3package_to_installed_app_box(package_list);
			}
			JOptionPane.showMessageDialog(null, dialogString, "APP版本", JOptionPane.INFORMATION_MESSAGE);
		}

		public void pull_smartvm_cfg_xml() {
			String cmdString = cc.pull_smartvm_cfg_xmlString + temp_file_path;
			info_append_to_text_area(cmdString);
			String reString = ec.adb_exec(cmdString);
			info_append_to_text_area(reString);
		}

		public void pull_config_xml() {
			String cmdString = cc.pull_config_xmlString + temp_file_path;
			info_append_to_text_area(cmdString);
			String reString = ec.adb_exec(cmdString);
			info_append_to_text_area(reString);
		}

		public void pull_goods_info_xml() {
			String cmdString = cc.pull_goods_info_xmlString + temp_file_path;
			info_append_to_text_area(cmdString);
			String reString = ec.adb_exec(cmdString);
			info_append_to_text_area(reString);
		}

		public void parse_smartvm_cfg_and_display() {
			// 机构名称
			Document smartvm_cfgxml_doc = CommonOperations.load_xml(temp_file_path + "smartvm_cfg.xml");
			List<Node> org_name_list = smartvm_cfgxml_doc.selectNodes("//org-name");
			String org_nameString = org_name_list.get(0).getText().toString();
			org_name_combobox.setSelectedItem(org_nameString);
			// 串口号
			List<Node> smartvmlist = smartvm_cfgxml_doc.selectNodes("//cabinet[@id='master']");
			String master_serial = ((Element) smartvmlist.get(0)).attributeValue("serial").toString();
			serial_port_combobox.setSelectedItem(master_serial);
			// 厂家
			vendor_map = new HashMap<String, String>();
			List<Node> vendor_list = smartvm_cfgxml_doc.selectNodes("//vendor");
			current_manufacturer_combobox.removeAllItems();
			current_manufacturer_combobox.addItem("----");
			for (int i = 0; i < vendor_list.size(); i++) {
				vendor_map.put(((Element) vendor_list.get(i)).attributeValue("id").toString(),
						vendor_list.get(i).getText());
			}
			Iterator<Map.Entry<String, String>> it = vendor_map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				current_manufacturer_combobox.addItem(entry.getValue());
			}
			// 当前厂家
			List<Node> current_vendor_numlist = smartvm_cfgxml_doc.selectNodes("//current-vendor");
			String current_vendor_numString = current_vendor_numlist.get(0).getText().toString();
			String vendor_xpathString = String.format("//vendor[@id='%s']", current_vendor_numString);
			List<Node> current_vendor_namelist = smartvm_cfgxml_doc.selectNodes(vendor_xpathString);
			String current_vendor_nameString = current_vendor_namelist.get(0).getText().toString();
			current_manufacturer_combobox.setSelectedItem(current_vendor_nameString);
		}

		public void parse_config_and_display() {
			String fileNameString = temp_file_path + "config.xml";
			// 服务器地址
			Document configxml_doc = CommonOperations.load_xml(fileNameString);
			List<Node> server_address_list = configxml_doc.selectNodes("//server-address");
			String server_addressString = server_address_list.get(0).getText().toString();
			server_address_combobox.setSelectedItem(server_addressString);
//			System.out.print(server_addressString);
		}

		public void modify_smartvm_cfg_xml() {
			String fileNameString = temp_file_path + "smartvm_cfg.xml";
			Document smartvm_cfgxml_doc = CommonOperations.load_xml(fileNameString);
			// 机构名称
			List<Node> org_name_list = smartvm_cfgxml_doc.selectNodes("//org-name");
			org_name_list.get(0).setText(org_name_combobox.getSelectedItem().toString());
			// 串口号
			List<Node> masterlist = smartvm_cfgxml_doc.selectNodes("//master");
			((Element) masterlist.get(0)).addAttribute("serial", serial_port_combobox.getSelectedItem().toString());
			if (only_matser_serial_chckbx.isSelected()) {
				List<Node> smartvmlist = smartvm_cfgxml_doc.selectNodes("//cabinet[@id='master']");
				((Element) smartvmlist.get(0)).attribute("serial")
						.setValue(serial_port_combobox.getSelectedItem().toString());
			} else {
				List<Node> smartvmlist = smartvm_cfgxml_doc.selectNodes("//cabinet");
				for (int i = 0; i < smartvmlist.size(); i++) {
					((Element) smartvmlist.get(i)).addAttribute("serial",
							serial_port_combobox.getSelectedItem().toString());
				}
			}
			// 当前厂家
			List<Node> current_vendor_numlist = smartvm_cfgxml_doc.selectNodes("//current-vendor");
			current_vendor_numlist.get(0).setText(
					CommonOperations.getKey(vendor_map, current_manufacturer_combobox.getSelectedItem().toString()));
			List<Node> smartvmlist = smartvm_cfgxml_doc.selectNodes("//cabinet");
			for (int i = 0; i < smartvmlist.size(); i++) {
				((Element) smartvmlist.get(i)).addAttribute("vendor", CommonOperations.getKey(vendor_map,
						current_manufacturer_combobox.getSelectedItem().toString()));
			}
			CommonOperations.save_xml(smartvm_cfgxml_doc, fileNameString);
		}

		public void modify_config_xml() {
			String fileNameString = temp_file_path + "config.xml";
			Document configxml_doc = CommonOperations.load_xml(fileNameString);
			// 服务器地址
			List<Node> server_address_list = configxml_doc.selectNodes("//server-address");
			server_address_list.get(0).setText(server_address_combobox.getSelectedItem().toString());
			CommonOperations.save_xml(configxml_doc, fileNameString);
		}

		public void push_smartvm_cfg_xml() {
			String fileNameString = temp_file_path + "smartvm_cfg.xml";
			String cmdString = cc.pushString + fileNameString + cc.running_config_dirString;
			info_append_to_text_area(cmdString);
			String reString = ec.adb_exec(cmdString);
			info_append_to_text_area(reString);
		}

		public void push_config_xml() {
			String fileNameString = temp_file_path + "config.xml";
			String cmdString = cc.pushString + fileNameString + cc.running_config_dirString;
			info_append_to_text_area(cmdString);
			String reString = ec.adb_exec(cmdString);
			info_append_to_text_area(reString);
		}

		public void download_machine_id_txt() {
			String cmdString = cc.pull_machine_id_txtString + temp_file_path;
			info_append_to_text_area(cmdString);
			String reString = ec.adb_exec(cmdString);
			info_append_to_text_area(reString);

		}

		public String mkdir_by_machineid() {
			String machine_idString = get_machine_id();
			String dir_pathsString = running_config_file_path + machine_idString + File.separator;
			CommonOperations.mkdir(dir_pathsString);
			return dir_pathsString;
		}

		public void pull_running_config(String config_dirString) {
			String cmdString = cc.pull_running_config_dirString + config_dirString;
			info_append_to_text_area(cmdString);
			String reString = ec.adb_exec(cmdString);
			info_append_to_text_area(reString);
		}

		public void open_in_system_explorer(String config_dirString) {
			String cmdString = "";
			if (OS_type.equals("mac")) {
				cmdString = cc.open_in_macosx + config_dirString;
			} else if (OS_type.equals("windows")) {
				cmdString = cc.open_in_windows + config_dirString;
			}
			ec.shell_exec(cmdString);
		}

		public void pull_game_config(String config_dirString) {
			String cmdString = cc.pull_game_config_dirString + config_dirString;
			info_append_to_text_area(cmdString);
			String reString = ec.adb_exec(cmdString);
			info_append_to_text_area(reString);
		}

		public void tar_main_log() {
			String cmdString = cc.tar_logcat_main_logString;
			info_append_to_text_area("打包main日志文件……please wait");
			info_append_to_text_area(ec.adb_exec(cmdString));
		}

		public void gzip_main_log() {
			String cmdString = cc.gzip_logcat_main_logString;
			info_append_to_text_area("压缩main日志文件……please wait");
			info_append_to_text_area(ec.adb_exec(cmdString));
		}

		public void pull_main_log_gz(String save_pathString) {
			String cmdString = cc.pull_logcat_main_logString + save_pathString;
			CommonOperations.mkdir(save_pathString);
			info_append_to_text_area("pull main日志文件……please wait");
			info_append_to_text_area(ec.adb_exec(cmdString));
		}

		public void rm_main_log_gz() {
			String cmdString = cc.rm_logcat_main_log_targzString;
			ec.adb_exec(cmdString);
		}

		public String pull_crash_log() {
			String save_pathString = crash_log_path + last_machine_id + File.separator;
			String cmdString = cc.pull_crash_logString + save_pathString;
			CommonOperations.mkdir(save_pathString);
			info_append_to_text_area("pull crash_log.txt……please wait");
			info_append_to_text_area(ec.adb_exec(cmdString));
			return save_pathString;
		}

		public void set_android_system_time() {
			Object time = time_selector.getValue();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd.HHmmss");
			String time_by_format = formatter.format(time);
			String format_cmdString = cc.date_set_formatString;
			String set_time_cmdString = cc.set_android_system_date_timeString + time_by_format;
			ec.adb_exec(format_cmdString);
			ec.adb_exec(set_time_cmdString);
			info_append_to_text_area("将系统时间更改为：\n" + time.toString());
			if (set_time_and_restart_checkbox.isSelected()) {
				restart_APP();
			}
		}

		public void revert_android_system_time() {
			Date now_time_date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd.HHmmss");
			String now_time = formatter.format(now_time_date);
			String format_cmdString = cc.date_set_formatString;
			String set_time_cmdString = cc.set_android_system_date_timeString + now_time;
			ec.adb_exec(format_cmdString);
			ec.adb_exec(set_time_cmdString);
			info_append_to_text_area("将系统时间复原：\n" + now_time_date.toString());
			if (set_time_and_restart_checkbox.isSelected()) {
				restart_APP();
			}
		}

		public void input_key(String keys_type) {
			String cmdString = "";
			if (keys_type == "MODE") {
				cmdString = cc.mode_switchString;
			} else if (keys_type == "HOME") {
				cmdString = cc.input_key_eventString + "3";
			} else if (keys_type == "BACK") {
				cmdString = cc.input_key_eventString + "4";
			} else if (keys_type == "MENU") {
				cmdString = cc.input_key_eventString + "82";
			}
			ec.adb_exec(cmdString);
		}

		public void crash_log_monitor() {
			String crash_log_size = ec.adb_exec(cc.du_crash_log_String);
			crash_log_size = CommonOperations.replace_n(crash_log_size);
//			crash_log_size = crash_log_size.split("	")[0];
			String lengthString = "";
//			System.out.print("crash_log_size:"+crash_log_size);
			// 读取crash_log_xml里的length值
			Document doc = CommonOperations.load_xml(crash_log_xml_path);
			try {
				List<Node> org_name_list = doc.selectNodes("//machine[@id='" + last_machine_id + "']");
				lengthString = org_name_list.get(0).getText().toString();
//				System.out.print("lengthString:"+lengthString);
				if (!lengthString.equals(crash_log_size)) {
					crash_log_tip.setVisible(true);
				}
			} catch (Exception e) {
				// 没有找到就新建
				Element rootElm = doc.getRootElement();
				Element machinesElm = rootElm.element("machines");
				Element machinelogElm = machinesElm.addElement("machine");
				machinelogElm.addAttribute("id", last_machine_id);
				machinelogElm.addText(crash_log_size);
				lengthString = crash_log_size;
				CommonOperations.save_xml(doc, crash_log_xml_path);
			}
		}

		public void record_crash_log_length() {
			String crash_log_size = ec.adb_exec(cc.du_crash_log_String);
			crash_log_size = CommonOperations.replace_n(crash_log_size);
//			crash_log_size = crash_log_size.split("	")[0];
			Document doc = CommonOperations.load_xml(crash_log_xml_path);
			List<Node> org_name_list = doc.selectNodes("//machine[@id='" + last_machine_id + "']");
			org_name_list.get(0).setText(crash_log_size);
			CommonOperations.save_xml(doc, crash_log_xml_path);
			crash_log_tip.setVisible(false);
		}
		public void insertGoods(Element cabinetsElem,String vmnumber,String[] goodsidList, String machineType,String[] channelList,String[] goodsPriceList,int cursor){
	        Element cabinetElem=cabinetsElem.addElement("cabinet");
	        cabinetElem.addAttribute("machineType", machineType);
	        cabinetElem.addAttribute("number", vmnumber);
	        int idnum = cursor;
	        int goodslength=goodsidList.length;
	        System.out.println("\ngoodsidList:"+goodsidList.length+"idnum:"+idnum);
	        for(String i:channelList){
	        	if (goodslength==idnum) {
					return;
				}
	        	System.out.println("\n"+idnum);
	        	Element channelElem = cabinetElem.addElement("channel");
	            channelElem.addAttribute("id", i);
	            channelElem.addAttribute("capacity", "20");
	            channelElem.addAttribute("goodsId", goodsidList[idnum]);
	            channelElem.addAttribute("threshold", "3");
	            channelElem.addAttribute("price", goodsPriceList[idnum]);
	            channelElem.addAttribute("wechat-url", "");
	            channelElem.addAttribute("alipay-url", "");
	            channelElem.addAttribute("baidu-url", "");
	            channelElem.addAttribute("desc1", "");
	            channelElem.addAttribute("desc2", "");
	            channelElem.addAttribute("desc3", "");
	            channelElem.addAttribute("weight", "500");
	            channelElem.addAttribute("calorie", "");
	            channelElem.addAttribute("package", "0");
	            idnum += 1;
	            
	        }
		}
		
		public String set_each_goods_to_channel() {
			String smartvm_cfg_pathString = temp_file_path + "smartvm_cfg.xml";
			String goods_info_cfg_pathString = temp_file_path + "goods_info.xml";
			String channel_cfg_pathString = temp_file_path + "channel_cfg.xml";
			//1.创建文档
	        Document doc=DocumentHelper.createDocument();
	        //2.添加标签和属性
	        Element cabinetsElem=doc.addElement("cabinets");
	        cabinetsElem.addAttribute("version", "1.1");
			Document smartvmdocument=CommonOperations.load_xml(smartvm_cfg_pathString);
			Document goodsdocument=CommonOperations.load_xml(goods_info_cfg_pathString);
			List<Node>smartvmlist=smartvmdocument.selectNodes("//cabinet[@id]");
			//System.out.print(smartvmlist.size());
			List<Node>goodslist=goodsdocument.selectNodes("//goods");
			int goodscount = goodslist.size();
			int cursor = 0;
			String[] goodsIdList = new String[goodscount];
			String[] goodsPriceList = new String[goodscount];
			for(int i2=0;i2<goodscount;i2++){
	            goodsIdList[i2] = ((Element) goodslist.get(i2)).attributeValue("id").toString();
	            goodsPriceList[i2] = ((Element) goodslist.get(i2)).attributeValue("price").toString();
			}
			for(int i=0;i<smartvmlist.size();i++){
				String vmnumber = ((Element) smartvmlist.get(i)).attributeValue("id").toString();
				String vmType = ((Element) smartvmlist.get(i)).attributeValue("machineType").toString();
				List<Node>smartlist=smartvmdocument.selectNodes("//cabinet[@id='"+vmnumber+"']//channel");
				int channelNumber = smartlist.size();
				if (goodscount<=channelNumber) {
					channelNumber=goodscount;
				}
				//System.out.println(channelNumber);
				String[] channelList = new String[channelNumber];

				for(int i2=0;i2<goodscount;i2++){
					if(i2<channelNumber){
						channelList[i2] = smartlist.get(i2).getText().toString();
					}
//		            goodsIdList[i2] = ((Element) goodslist.get(i2)).attributeValue("id").toString();
//		            goodsPriceList[i2] = ((Element) goodslist.get(i2)).attributeValue("price").toString();
				}
//				if (cursor>=goodscount) {
//					cursor = 0;
//				}
				insertGoods(cabinetsElem,vmnumber,goodsIdList,vmType,channelList,goodsPriceList,cursor);
				cursor += channelNumber;
				if(cursor>=goodscount){
					break;
				}
			}
			CommonOperations.save_xml(doc, channel_cfg_pathString);
			return channel_cfg_pathString;
		}
		public String clear_channel_goods() {
			String channel_cfg_pathString = temp_file_path + "channel_cfg.xml";
			//1.创建文档
	        Document doc=DocumentHelper.createDocument();
	        //2.添加标签和属性
	        Element cabinetsElem=doc.addElement("cabinets");
	        cabinetsElem.addAttribute("version", "1.1");
	        CommonOperations.save_xml(doc, channel_cfg_pathString);
			return channel_cfg_pathString;
		}
		public void get_log_cmd() {
			//TODO
			String log_cmdString = "adb logcat -v time ";
			String ps_grep_String = "shell ps | grep ";
			String ext_filterString = "";
			boolean is_filter = false;
			if (log_advertisement_CheckBox.isSelected()) {
				is_filter = true;
				ext_filterString += "-e ads ";
				ext_filterString += "-e adplayer ";
			}
			if (log_core_CheckBox.isSelected()) {
				is_filter = true;
				ext_filterString += "-e inboxcore ";
			}
			if (log_dms_CheckBox.isSelected()) {
				is_filter = true;
				ext_filterString += "-e dms ";
			}
			if (log_pay_CheckBox.isSelected()) {
				is_filter = true;
				ext_filterString += "-e pay ";
			}
			if (log_setting_CheckBox.isSelected()) {
				is_filter = true;
				ext_filterString += "-e vmcsettings ";
			}
			if (log_UI_CheckBox.isSelected()) {
				is_filter = true;
				ext_filterString += "-e smartvm ";
				ext_filterString += "-e game ";
			}
			if (log_vcs_CheckBox.isSelected()) {
				is_filter = true;
				ext_filterString += "-e vcs ";
			}
			if (log_vmcservice_CheckBox.isSelected()) {
				is_filter = true;
				ext_filterString += "-e vmcservice ";
			}
			String cmdString = ps_grep_String + ext_filterString;
			cmdString += "| busybox awk '{print $(2)}'";
			String reString = ec.adb_exec(cmdString);
			String[] list = {}; 
			list = reString.split("\n");
			if (is_filter){
				log_cmdString += "| " + grep + " ";
				for(String cid_s : list) {
					log_cmdString += "-e " + cid_s + " "; 
				}
			}
			System.out.print(log_cmdString);
			tags_input_field.setText(log_cmdString);
			Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();  
	        Transferable tText = new StringSelection(log_cmdString);  
	        clip.setContents(tText, null);
	        JOptionPane.showMessageDialog(null, "已将命令内容复制进剪贴板\n命令："+log_cmdString, "提示", JOptionPane.CLOSED_OPTION);
		}
	}

	public class Set_channel_goods_Thread implements Runnable {

		@Override
		public void run() {
			ach.pull_smartvm_cfg_xml();
			ach.pull_goods_info_xml();
			ach.push_config(ach.set_each_goods_to_channel());
			ach.restart_APP();
			ach.info_append_to_text_area("货道配置完成");
		}
	}
	public class Clear_channel_goods_Thread implements Runnable {

		@Override
		public void run() {
			ach.push_config(ach.clear_channel_goods());
			ach.restart_APP();
			ach.info_append_to_text_area("货道配置完成");
		}
	}

	class Pull_main_log_Thread implements Runnable {

		@Override
		public void run() {
			String save_pathString = log_saved_path_field.getText();
			ach.tar_main_log();
			ach.gzip_main_log();
			ach.pull_main_log_gz(save_pathString);
			ach.rm_main_log_gz();
			ach.open_in_system_explorer(save_pathString);
		}
	}

	class Pull_crash_log_Thread implements Runnable {

		@Override
		public void run() {
			ach.open_in_system_explorer(ach.pull_crash_log());
			ach.record_crash_log_length();
		}
	}

	class Push_config_Thread implements Runnable {
		@Override
		public void run() {
			progress_bar_value = 0;
			int all = 1;
			all = all + choosed_configsArrayListString.size();
			install_progress_bar.setValue(0);
			String dialogStr = "";
			for (String tmp : choosed_configsArrayListString) {
				ach.push_config(tmp);
				ach.progress_show(all, progress_bar_value += 1, install_progress_bar);
				dialogStr += tmp + "\n";
			}
			ach.restart_APP();
			install_progress_bar.setValue(100);
			JOptionPane.showMessageDialog(null, "push" + dialogStr + "完成！", "下发配置成功", JOptionPane.PLAIN_MESSAGE);
		}
	}

	class Pull_game_config_Thread implements Runnable {
		@Override
		public void run() {
			String config_dirString = ach.mkdir_by_machineid();
			ach.pull_game_config(config_dirString);
			ach.open_in_system_explorer(config_dirString + "game" + File.separator);
			ach.info_append_to_text_area("获取运行配置完成");
		}
	}

	class Pull_running_config_Thread implements Runnable {
		@Override
		public void run() {
			String config_dirString = ach.mkdir_by_machineid();
			ach.pull_running_config(config_dirString);
			ach.open_in_system_explorer(config_dirString + "config" + File.separator);
			ach.info_append_to_text_area("获取运行配置完成");
		}
	}

	class Modify_running_config_Thread implements Runnable {
		@Override
		public void run() {
			ach.modify_smartvm_cfg_xml();
			ach.modify_config_xml();
			ach.push_smartvm_cfg_xml();
			ach.push_config_xml();
			ach.restart_APP();
			ach.info_append_to_text_area("更新修改基础配置已完成");
		}
	}

	class Display_running_config_Thread implements Runnable {
		@Override
		public void run() {
			ach.pull_smartvm_cfg_xml();
			ach.pull_config_xml();
			ach.download_machine_id_txt();
			ach.parse_smartvm_cfg_and_display();
			ach.parse_config_and_display();
			ach.info_append_to_text_area("刷新基础配置已完成");
		}

	}

	class Get_app_version_Thread implements Runnable {
		@Override
		public void run() {
			ach.get_app_version_String(ach.get_3_package_list());
		}

	}

	class Uninstall_app_Thread implements Runnable {
		@Override
		public void run() {
			String uninstallPackageName = insatlled_app_box.getSelectedItem().toString();
			String responseString = ec.adb_exec(cc.uninstallString + uninstallPackageName);
			ach.info_append_to_text_area("卸载" + uninstallPackageName);
			ach.info_append_to_text_area(responseString);
			ach.add_3package_to_installed_app_box(ach.get_3_package_list());
		}

	}

	class Install_APP_Thread implements Runnable {
		@Override
		public void run() {
			try {
				progress_bar_value = 0;
				int all = 1;
				all = all + choosed_appsArrayListString.size();
				install_progress_bar.setValue(0);
				String dialogStr = "";
				for (String tmp : choosed_appsArrayListString) {
					String commandString = cc.installString + tmp;
					ach.info_append_to_text_area(commandString);
					String reString = ec.adb_exec(commandString);
					ach.info_append_to_text_area(reString);
					ach.progress_show(all, progress_bar_value += 1, install_progress_bar);
					dialogStr += tmp + "\n";
				}
				ach.restart_APP();
				install_progress_bar.setValue(100);
				ach.add_3package_to_installed_app_box(ach.get_3_package_list());
				JOptionPane.showMessageDialog(null, "安装\n" + dialogStr + "完成！", "安装成功", JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	class Uninstall_ALL_Thread implements Runnable {
		@Override
		public void run() {
			try {
				progress_bar_value = 0;
				int all = 0;
				all = all + choosed_appsArrayListString.size() + 1;
				install_progress_bar.setValue(0);
				List<String> package_list = ach.get_3_package_list();
				System.out.println(package_list);

				all = all + package_list.size();
				String dialogStr = "";
				for (String utemp : package_list) {
					String uncmd = cc.uninstallString + utemp;
					ach.info_append_to_text_area(uncmd);
					String reString = ec.adb_exec(uncmd);
					ach.info_append_to_text_area(reString);
					dialogStr += utemp + "\n";
					ach.progress_show(all, progress_bar_value += 1, install_progress_bar);
				}
				install_progress_bar.setValue(100);
				ach.add_3package_to_installed_app_box(ach.get_3_package_list());
				JOptionPane.showMessageDialog(null, "卸载\n" + dialogStr + "完成！", "卸载所有第三方app",
						JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	class Uninstall_and_Install_Thread implements Runnable {
		@Override
		public void run() {
			try {
				progress_bar_value = 0;
				int all = 0;
				all = all + choosed_appsArrayListString.size() + 1;
				install_progress_bar.setValue(0);
				List<String> package_list = ach.get_3_package_list();
				System.out.println(package_list);
				all = all + package_list.size();
				for (String utemp : package_list) {
					String uncmd = cc.uninstallString + utemp;
					ach.info_append_to_text_area(uncmd);
					String reString = ec.adb_exec(uncmd);
					ach.info_append_to_text_area(reString);
					ach.progress_show(all, progress_bar_value += 1, install_progress_bar);
				}
				ach.info_append_to_text_area(cc.remove_apps_dirString);
				ec.adb_exec(cc.remove_apps_dirString);
				ach.info_append_to_text_area(cc.mkdir_apps_dirString);
				ec.adb_exec(cc.mkdir_apps_dirString);
				ach.progress_show(all, progress_bar_value += 1, install_progress_bar);
				String dialogStr = "";
				for (String tmp : choosed_appsArrayListString) {
					String commandString = cc.pushString + tmp + cc.apps_dirString;
					ach.info_append_to_text_area(commandString);
					String reString = ec.adb_exec(commandString);
					ach.info_append_to_text_area(reString);
					ach.progress_show(all, progress_bar_value += 1, install_progress_bar);
					dialogStr += tmp + "\n";
				}
				ach.info_append_to_text_area(ec.adb_exec(cc.start_install_activityString));
				install_progress_bar.setValue(100);
				JOptionPane.showMessageDialog(null, "推送\n" + dialogStr + "完成！并执行Install安装", "卸载并安装",
						JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	class Screenshot_Thread implements Runnable {
		@Override
		public void run() {
			String formatTime = CommonOperations.get_format_time();
			String command1 = "shell /system/bin/screencap -p /sdcard/sc123456.png";
			String command2 = "pull /sdcard/sc123456.png " + screenshot_path + "sc" + formatTime + ".png";
			String command3 = "shell rm -rf /sdcard/sc123456.png";
//			String command4_windows = "cmd.exe /c start " + screenshot_path;
//			String command4_macosx = "open " + screenshot_path;
			String command4 = "";
			String command5 = "shell du -k sdcard/sc123456.png | busybox awk '{print $1}'";
			if (OS_type.equals("mac")) {
				command4 = cc.open_in_macosx + screenshot_path;
			} else if (OS_type.equals("windows")) {
				command4 = cc.open_in_windows + screenshot_path;
			}
			try {
				screenshot_label.setVisible(true);
				screenshot_label.setForeground(Color.ORANGE);
				screenshot_label.setText("截图中……");
				boolean flag = false;
				ec.adb_exec(command1);
				while (!flag) {
					Thread.sleep(1000);
//					String response = ec.adb_exec(command5);
//					String[] a = response.split("sdcard");
//					String picLength = a[0].trim();
					String picLength = ec.adb_exec(command5);
					if (!picLength.equals("0")) {
						flag = true;
					}
				}
				Thread.sleep(100);
				ec.adb_exec(command2);
				ec.adb_exec(command3);
				ach.info_append_to_text_area("截图完成");
				screenshot_label.setText("完成！");
				screenshot_label.setForeground(Color.green);
				int n = JOptionPane.showConfirmDialog(null, "是否立即查看截图？", "截图完成", JOptionPane.OK_CANCEL_OPTION);
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
	class Device_info_Timing_task {
		public void adbdevicesTimerDemo() {
			int delay = 5000;// ms
			int period = 3500;// ms
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					try {
						ach.get_devices_info();
						ach.set_wifi_or_data_off();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, delay, period);
		}

		public void crash_logTimerDemo() {
			int delay = 10000;// ms
			int period = 10000;// ms
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					try {
						if (crash_log_monitor_checkbox.isSelected() && !current_machine_id.equals("")) {
							ach.crash_log_monitor();
						} else {
							crash_log_tip.setVisible(false);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, delay, period);
		}
	}
}
