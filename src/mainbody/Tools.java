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
	// Ŀ¼
	public String root_path;
	public String screenshot_path;
	public String temp_file_path;

	private JFrame frame;
	Execute_command ec;
	Action_handler ach;
	Device_info_Timing_task ditt;
	CommonCommands cc;

	// UI����
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

	// ���б���
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
		// ��ѯdevices_info
		ditt.adbdevicesTimerDemo();
		// ��ѯcrash_log
		ditt.crash_logTimerDemo();
	}

	private void devices_info_module() {
		// �豸��Ϣģ��
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("�豸��Ϣ"));
		frame.getContentPane().add(panel);
		// �ۻ��������ʾ��
		device_display_area = new JTextArea();
		device_display_area.setLineWrap(true);
		device_display_area.setEditable(false);
		device_display_area.setBounds(20, 36, 152, 36);
		panel.add(device_display_area);
		JScrollPane device_display_scroll = new JScrollPane(device_display_area);
		device_display_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// ���������ǳ���
		device_display_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		device_display_scroll.setBounds(10, 20, 170, 70);
		panel.add(device_display_scroll);
		// ����Ӧ��
		JButton restart_application_button = new JButton("����Ӧ��");
		restart_application_button.setBounds(193, 20, 100, 50);
		panel.add(restart_application_button);
		restart_application_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.restart_APP();// �������Ӧ��
			}
		});
		// �豸��ͼ
		JButton devices_screenshot_button = new JButton("�豸��ͼ");
		devices_screenshot_button.setBounds(193, 79, 100, 50);
		panel.add(devices_screenshot_button);
		devices_screenshot_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.devices_screenshot();// ����豸��ͼ
			}
		});
		// �豸ѡ��������
		JLabel label_1 = new JLabel("ѡ�����ӵ��豸��");
		label_1.setBounds(10, 103, 161, 15);
//		panel.add(label_1);
		device_selection_box = new JComboBox<String>();
		device_selection_box.setBounds(6, 130, 177, 30);
//		panel.add(device_selection_box);
		// ��ͼ��Ϣ��ʾ
		screenshot_label = new JLabel("");
		screenshot_label.setBounds(193, 141, 111, 15);
		panel.add(screenshot_label);
		// wifi���翪��
		JLabel wifi_label = new JLabel("WIFI���أ�");
		wifi_label.setBounds(10, 102, 84, 16);
		panel.add(wifi_label);
		wifi_off_radioButton = new JRadioButton("�ر�");
		wifi_off_radioButton.setBounds(65, 121, 58, 23);
		panel.add(wifi_off_radioButton);
		wifi_off_radioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (wifi_off_radioButton.isSelected()) {
					ach.info_append_to_text_area("�ر�wifi");
					ec.adb_exec(cc.set_wifi_disableString);
				}
			}
		});
		wifi_open_radioButton = new JRadioButton("��");
		wifi_open_radioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (wifi_open_radioButton.isSelected()) {
					ach.info_append_to_text_area("��wifi");
					ec.adb_exec(cc.set_wifi_enableString);
				}
			}
		});
		wifi_open_radioButton.setBounds(10, 121, 58, 23);
		panel.add(wifi_open_radioButton);
		wifi_group = new ButtonGroup();
		wifi_group.add(wifi_off_radioButton);
		wifi_group.add(wifi_open_radioButton);
		wifi_status_label = new JLabel("��");
		wifi_status_label.setBounds(78, 102, 61, 16);
		panel.add(wifi_status_label);
		// data���翪��
		JLabel data_label = new JLabel("���ݿ��أ�");
		data_label.setBounds(10, 156, 84, 16);
		panel.add(data_label);
		data_off_radioButton = new JRadioButton("�ر�");
		data_off_radioButton.setBounds(65, 173, 58, 23);
		panel.add(data_off_radioButton);
		data_off_radioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (data_off_radioButton.isSelected()) {
					ach.info_append_to_text_area("�ر���������");
					ec.adb_exec(cc.set_data_disableString);
				}
			}
		});
		data_open_radioButton = new JRadioButton("��");
		data_open_radioButton.setBounds(10, 173, 58, 23);
		panel.add(data_open_radioButton);
		data_open_radioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (data_open_radioButton.isSelected()) {
					ach.info_append_to_text_area("����������");
					ec.adb_exec(cc.set_data_enableString);
				}
			}
		});
		data_group = new ButtonGroup();
		data_group.add(data_off_radioButton);
		data_group.add(data_open_radioButton);
		data_status_label = new JLabel("��");
		data_status_label.setBounds(78, 156, 61, 16);
		panel.add(data_status_label);

	}

	private void device_config_module() {
		// �����ļ�ģ��
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("�����ļ�"));
		frame.getContentPane().add(panel);
		// ��ȡ��������
		JButton get_running_configure_button = new JButton("��ȡ��������");
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
		// ��ȡ�����
		JButton get_active_configuration_button = new JButton("��ȡ�����");
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
		// ������������
		JButton channel_rapid_configuration_button = new JButton("������������");
		channel_rapid_configuration_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				channel_set_frame();
			}
		});
		channel_rapid_configuration_button.setBounds(6, 140, 112, 34);
		panel.add(channel_rapid_configuration_button);
		// ���������ļ�
		JTextArea push_configuration_file_area = new JTextArea("�뽫��Ҫ�·��������ļ���ק����\n");
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
//							// System.out.print("\n��������");
////							JOptionPane.showMessageDialog(null, configstring+"\n�ļ�·�����ܰ������ĺͿո�", "���ʧ��",JOptionPane.WARNING_MESSAGE);
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
//						JOptionPane.showMessageDialog(null, dialog_str + "\n�������ĺͿո�", "���ʧ��",
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
		// ������������ļ�����
		JButton clear_push_configuration_file_area_button = new JButton();
		clear_push_configuration_file_area_button.setToolTipText("��մ��·�����");
		clear_push_configuration_file_area_button.setText("��մ��·�����");
		// clearconfig.setIcon(new
		// ImageIcon(getClass().getResource("/toolIcon/searchreset.png")));
		clear_push_configuration_file_area_button.setBounds(126, 145, 175, 30);
		panel.add(clear_push_configuration_file_area_button);
		clear_push_configuration_file_area_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choosed_configsArrayListString.clear();
				push_configuration_file_area.setText("�뽫��Ҫ�·��������ļ���ק����\n");
			}
		});
		// �����·������ļ�
		JButton push_configuration_file_button = new JButton("�����·������ļ�");
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
		// ������
		JProgressBar config_progress_bar = new JProgressBar();
		config_progress_bar.setStringPainted(true);
		config_progress_bar.setBounds(6, 176, 298, 20);
		panel.add(config_progress_bar);

	}

	private void device_simulate_and_timeset_module() {
		// ����ģ�⡢ʱ���޸�
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("����ģ�⡢ʱ���޸�"));
		frame.getContentPane().add(panel);
		// �޸�Androidϵͳʱ��
		// ʱ��ѡ����
		SpinnerDateModel date_model = new SpinnerDateModel();
		time_selector = new JSpinner(date_model);
		time_selector.setBounds(115, 16, 189, 75);
		panel.add(time_selector);
		time_selector.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		time_selector.setValue(new Date());
		JSpinner.DateEditor date_editor = new JSpinner.DateEditor(time_selector, "yyyy-MM-dd HH:mm:ss");
		time_selector.setEditor(date_editor);
		// ����ʱ�䡢��ԭʱ��
		JButton set_time_button = new JButton("�����豸ʱ��");
		set_time_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.set_android_system_time();
			}
		});
		set_time_button.setBounds(167, 91, 122, 35);
		panel.add(set_time_button);
		JButton reduction_time_button = new JButton("�豸��ԭʱ��");
		reduction_time_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.revert_android_system_time();
			}
		});
		reduction_time_button.setBounds(167, 125, 122, 35);
		panel.add(reduction_time_button);
		// �Ƿ�ͬʱ������ť
		set_time_and_restart_checkbox = new JCheckBox("ͬʱ����");
		set_time_and_restart_checkbox.setBounds(167, 161, 122, 35);
		panel.add(set_time_and_restart_checkbox);
		// ģ��ϵͳ����
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
		// �ݲ�����
//		panel.setVisible(false);
	}

	private void show_log_module() {
		// ��־����ģ��
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("��ӡ��־�����ȡ"));
		frame.getContentPane().add(panel);
		// ��־tag�����
		tags_input_field = new JTextField();
		tags_input_field.setBounds(10, 142, 294, 54);
		panel.add(tags_input_field);
		tags_input_field.setColumns(10);
		// ��ȡ��־����
		JButton get_log_cmd_button = new JButton("��ȡ��־����");
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
		
		log_UI_CheckBox = new JCheckBox("UI��Game");
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
		// �ݲ�����
//		panel.setVisible(false);
	}

	private void app_install_module() {
		// Inhand-app��װģ��
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("Inhand-app��װ"));
		frame.getContentPane().add(panel);
		// ����װapp��
		JTextArea push_apps_file_area = new JTextArea("����Ҫ��װ��APK�ļ���ק����\n");
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
//							// System.out.print("\n��������");
////							JOptionPane.showMessageDialog(null, configstring+"\n�ļ�·�����ܰ������ĺͿո�", "���ʧ��",JOptionPane.WARNING_MESSAGE);
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
//						JOptionPane.showMessageDialog(null, dialog_str + "\n�������ĺͿո�", "���ʧ��",
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
		// ����װ
		JButton install_and_restart_button = new JButton("��װapp");
		install_and_restart_button.setBounds(6, 84, 114, 35);
		panel.add(install_and_restart_button);
		install_and_restart_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (choosed_appsArrayListString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�������Ҫ��װ�İ�װ��", "��ʾ", JOptionPane.CANCEL_OPTION);
				} else {
					int n = JOptionPane.showConfirmDialog(null, "�Ƿ�װAPP", "��װ", JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						Install_APP_Thread Iat = new Install_APP_Thread();
						Thread t1 = new Thread(Iat);
						t1.start();
					}
				}
			}
		});
		// ж��ȫ�׵�����app
		JButton uninstall_all_inhand_button = new JButton("ж��All������");
		uninstall_all_inhand_button.setBounds(6, 140, 114, 35);
		panel.add(uninstall_all_inhand_button);
		uninstall_all_inhand_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ��ϵͳ������ APPȫ��ж��", "ȫ��ж��", JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					Uninstall_ALL_Thread uIt = new Uninstall_ALL_Thread();
					Thread t1 = new Thread(uIt);
					t1.start();
				}
			}
		});
		// ж��inhandȫ��app����װapp
		JButton cover_installation_button = new JButton("<html>ж��<br>All������<br>����װ</html>");
		cover_installation_button.setBounds(6, 21, 114, 59);
		panel.add(cover_installation_button);
		cover_installation_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (choosed_appsArrayListString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�������Ҫ��װ�İ�װ��", "��ʾ", JOptionPane.CANCEL_OPTION);
				} else {
					int n = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫȫ��ж�أ�����װAPP", "ж�ز���װ",
							JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						Uninstall_and_Install_Thread It = new Uninstall_and_Install_Thread();
						Thread t1 = new Thread(It);
						t1.start();
					}
				}
			}
		});
		// ��մ���װapp����
		JButton clear_push_app_file_area_button = new JButton();
		clear_push_app_file_area_button.setToolTipText("��մ���װapp");
		clear_push_app_file_area_button.setText("��մ���װapp");
		clear_push_app_file_area_button.setBounds(126, 142, 175, 30);
		panel.add(clear_push_app_file_area_button);
		clear_push_app_file_area_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choosed_appsArrayListString.clear();
				push_apps_file_area.setText("����Ҫ��װ��APK�ļ���ק����\n");
			}
		});
		// ������
		install_progress_bar = new JProgressBar();
		install_progress_bar.setStringPainted(true);
		install_progress_bar.setBounds(6, 176, 298, 20);
		panel.add(install_progress_bar);

	}

	private void app_manager_module() {
		// Ӧ�ù���ģ��
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("Ӧ�ù���"));
		frame.getContentPane().add(panel);
		// �Ѱ�װӦ����Ϣ
		JButton installed_application_information_button = new JButton("�Ѱ�װapp��Ϣ");
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
		// ж��Ӧ��
		JLabel label = new JLabel("ѡ����Ҫж�ص�Ӧ�ã�");
		label.setBounds(10, 67, 196, 15);
		panel.add(label);
		// �Ѱ�װӦ��ѡ���
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
		// ж�ذ�ť
		JButton uninstall_app_button = new JButton("ж��");
		uninstall_app_button.setBounds(89, 113, 117, 29);
		panel.add(uninstall_app_button);
		uninstall_app_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String uninstallPackageName = insatlled_app_box.getSelectedItem().toString();
				if (uninstallPackageName.contains("com")) {
					int n = JOptionPane.showConfirmDialog(null, "ж��" + uninstallPackageName, "ж��",
							JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						Uninstall_app_Thread uat = new Uninstall_app_Thread();
						Thread t1 = new Thread(uat);
						t1.start();
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫж�صİ�", "��ʾ", JOptionPane.CANCEL_OPTION);
				}
			}
		});
		// ˢ�°�ť
		JButton refresh_app_button = new JButton("ˢ��");
		refresh_app_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ach.add_3package_to_installed_app_box(ach.get_3_package_list());
			}
		});
		refresh_app_button.setBounds(206, 84, 63, 29);
		panel.add(refresh_app_button);
		// ������ʾ��
		JLabel lblNewLabel = new JLabel("�����汾�ŵ���(��.��)");
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
		// ��־����ģ��
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("��־����"));
		frame.getContentPane().add(panel);
//		// tag����ѡ��
//		JCheckBox save_log_with_tag_checkbox = new JCheckBox("tag����");
//		save_log_with_tag_checkbox.setBackground(Color.WHITE);
//		save_log_with_tag_checkbox.setBounds(10, 20, 103, 23);
//		panel.add(save_log_with_tag_checkbox);
//		// ��־tagѡ���
//		JLabel label = new JLabel("Tags:");
//		label.setBounds(10, 46, 41, 23);
//		panel.add(label);
//		JComboBox<String> common_tags_combobox = new JComboBox<String>();
//		common_tags_combobox.setBounds(47, 47, 118, 22);
//		panel.add(common_tags_combobox);
//		// ֹͣ������־
//		JButton stop_save_log_button = new JButton("Stop");
//		stop_save_log_button.setFont(new Font("����", Font.PLAIN, 10));
//		stop_save_log_button.setBounds(223, 108, 69, 39);
//		panel.add(stop_save_log_button);
//		stop_save_log_button.setEnabled(false);
//		// ��ʼ������־
//		JButton start_save_log_button = new JButton();
//		start_save_log_button.setFont(new Font("����", Font.PLAIN, 10));
//		start_save_log_button.setText("Start");
//		start_save_log_button.setBounds(135, 108, 78, 39);
//		panel.add(start_save_log_button);
		// ѡ����־����·��
		JButton select_log_save_path_button = new JButton("ѡ����־����·��");
		select_log_save_path_button.setBounds(10, 107, 118, 39);
		panel.add(select_log_save_path_button);
		select_log_save_path_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String path = FileChooser.pathChooser() + File.separator;
				log_saved_path_field.setText(path);
			}
		});
		// ���ȡlog
		JButton zip_and_pull_log_button = new JButton("Pull main��־");
		zip_and_pull_log_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ��pull main log��", "ȷ�ϣ�", JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					Pull_main_log_Thread pmlt = new Pull_main_log_Thread();
					Thread t1 = new Thread(pmlt);
					t1.start();
				}
			}
		});
		zip_and_pull_log_button.setBounds(6, 24, 118, 39);
		panel.add(zip_and_pull_log_button);
		// ��־����·��
		JLabel label_1 = new JLabel("·��:");
		label_1.setBounds(10, 81, 41, 23);
		panel.add(label_1);
		log_saved_path_field = new JTextField();
		log_saved_path_field.setColumns(10);
		log_saved_path_field.setBounds(47, 75, 260, 30);
		panel.add(log_saved_path_field);
		String log_saved_path = root_path + "device-log" + File.separator;
		log_saved_path_field.setText(log_saved_path);
		// ������־
		// ���������־
		JButton save_crash_log_button = new JButton("��ȡ������־");
		save_crash_log_button.setBounds(10, 158, 114, 38);
		panel.add(save_crash_log_button);
		save_crash_log_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "�Ƿ��ȡcrash_log.txt��", "ȷ�ϣ�", JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					Pull_crash_log_Thread pclt = new Pull_crash_log_Thread();
					Thread t1 = new Thread(pclt);
					t1.start();
				}
			}
		});
		// ������־������ʾ
		crash_log_tip = new JLabel("CrashLog.txt�и��£�");
		crash_log_tip.setBounds(135, 158, 157, 15);
		crash_log_tip.setForeground(Color.RED);
		crash_log_tip.setVisible(false);
		panel.add(crash_log_tip);
		// ������ؿ���
		crash_log_monitor_checkbox = new JCheckBox("������־��ؿ���");
		crash_log_monitor_checkbox.setSelected(true);
		crash_log_monitor_checkbox.setBounds(135, 173, 136, 23);
		panel.add(crash_log_monitor_checkbox);
		// �ݲ�����
//		panel.setVisible(false);
	}

	private void base_config_module() {
		// ��������ģ��
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("��������"));
		frame.getContentPane().add(panel);
		// ������ʾ���޸�
		// ��ǩ��ʾ
		JLabel labelorgName = new JLabel("��������");
		labelorgName.setBounds(10, 27, 54, 15);
		panel.add(labelorgName);
		JLabel labelServerAddress = new JLabel("ƽ̨��ַ��");
		labelServerAddress.setBounds(10, 58, 68, 15);
		panel.add(labelServerAddress);
		JLabel labelVMCport = new JLabel("VMC���ڣ�");
		labelVMCport.setBounds(10, 91, 68, 15);
		panel.add(labelVMCport);
		JLabel lblNewLabel_1 = new JLabel("��ǰ���ң�");
		lblNewLabel_1.setBounds(10, 130, 68, 15);
		panel.add(lblNewLabel_1);
		// �������ð�ť
		JButton update_configuration_button = new JButton("��������");
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
		// ˢ�����ð�ť
		JButton refresh_configuration_button = new JButton("ˢ��");
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
		// ��������������
		org_name_combobox = new JComboBox<String>();
		org_name_combobox.setBounds(74, 24, 174, 25);
		org_name_combobox.addItem("");
		org_name_combobox.setEditable(true);
		panel.add(org_name_combobox);
		// ��������ַ������
		server_address_combobox = new JComboBox<String>();
		server_address_combobox.setBounds(74, 55, 174, 25);
		server_address_combobox.addItem("");
		server_address_combobox.setEditable(true);
		panel.add(server_address_combobox);
		// ����������
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
		// ��ǰ����������
		current_manufacturer_combobox = new JComboBox<String>();
		current_manufacturer_combobox.addItem("----");
		current_manufacturer_combobox.setBounds(74, 125, 164, 25);
		panel.add(current_manufacturer_combobox);
		// ֻ�޸����񴮿�ѡ���
		only_matser_serial_chckbx = new JCheckBox("ֻ�޸����񴮿�");
		only_matser_serial_chckbx.setBounds(181, 88, 123, 25);
		panel.add(only_matser_serial_chckbx);
		// �޸��ۻ������
		JButton change_machine_id_button = new JButton("�����ۻ������");
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
		// ��Ϣ
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("��Ϣ"));
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
		
		JLabel tip_1 = new JLabel("һ������ǰ��ͬ��ƽ̨��Ʒ");
		tip_1.setBounds(10, 150, 164, 15);
		tip_1.setForeground(Color.ORANGE);
		contentPane.add(tip_1);
		
		JButton set_channel_goods_button = new JButton("һ�����û�����Ʒ");
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
		
//		JButton set_all_channel_price_button = new JButton("һ�������л����۸�");
//		set_all_channel_price_button.setSize(164, 40);
//		set_all_channel_price_button.setLocation(10, 211);
//		contentPane.add(set_all_channel_price_button);
//		
//		JTextField price_input_filed = new JTextField();
//		price_input_filed.setBounds(57, 180, 73, 21);
//		contentPane.add(price_input_filed);
//		price_input_filed.setColumns(10);
//		
//		JLabel tip_2 = new JLabel("�۸�");
//		tip_2.setBounds(10, 183, 54, 15);
//		contentPane.add(tip_2);
//		
//		JLabel tip_3 = new JLabel("��");
//		tip_3.setBounds(130, 183, 54, 15);
//		contentPane.add(tip_3);
		
		JButton clear_all_channel_goods_button = new JButton("һ����ջ�������");
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
			// ȡ���豸ѡ��Ĺ���
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

		// ��Ϣ��ʾ
		public void info_append_to_text_area(Object text) {
			String s = String.valueOf(text);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			info_area.append("\n" + sdf.format(d) + " " + s);
			info_area.setCaretPosition(info_area.getText().length());
		}

		// �豸�Ͽ�����UI�仯
		public void disconnect_ui_change() {
			server_address_combobox.setSelectedItem("----");
			serial_port_combobox.setSelectedItem("----");
			org_name_combobox.setSelectedItem("----");
			current_manufacturer_combobox.setSelectedItem("----");
			insatlled_app_box.removeAllItems();
			crash_log_tip.setVisible(false);
		}

		public void modify_machine_id() {
			JFrame frame2 = new JFrame("�����ۻ������");
			frame2.getContentPane().setLayout(null);
			frame2.setBounds(300, 300, 300, 200);
			frame2.setVisible(true);
			JTextField MachineId = new JTextField(last_machine_id);
			MachineId.setBounds(80, 10, 120, 30);
			frame2.getContentPane().add(MachineId);
			JButton changeId = new JButton("ȷ���޸�");
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
						info_append_to_text_area("�޸��ۻ������Ϊ��" + s);
						restart_APP();
						JOptionPane.showMessageDialog(null, "�Ѹ����ۻ������Ϊ" + s, "�����ۻ������", JOptionPane.PLAIN_MESSAGE);
						frame2.dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}

		// ����Ӧ��
		public void restart_APP() {
			String cmd = cc.restart_app_command;
			info_append_to_text_area(cmd);
			ec.adb_exec(cmd);
//    		JOptionPane.showMessageDialog(null, "������Ӧ��", "����Ӧ��",JOptionPane.PLAIN_MESSAGE);
		}

		// ��ȡ�ۻ������
		public String get_machine_id() {
			String cmd = cc.get_machine_id;
			String response = ec.adb_exec(cmd);
			response = CommonOperations.replace_trn(response);
			device_display_area.setText("\n�ۻ������:\n" + response);
			return response;
		}

		// ����wifi������״̬Ϊoff����Ϊ���ܻᱻ�Զ�����
		public void set_wifi_or_data_off() {
			if (wifi_off_radioButton.isSelected()) {
				ec.adb_exec(cc.set_wifi_disableString);
			}
			if (data_off_radioButton.isSelected()) {
				ec.adb_exec(cc.set_data_disableString);
			}
		}

		// ��ȡadb devices
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
					// ��ԭ���翪�ذ�ť
					wifi_group.clearSelection();
					data_group.clearSelection();
					// ���»�ȡ��������
					info_append_to_text_area(String.format("��⵽%s����", current_machine_id));
					info_append_to_text_area("ˢ�»������á���");
					Display_running_config_Thread drct = new Display_running_config_Thread();
					Thread t = new Thread(drct);
					t.start();
					// ���»�ȡ�Ѱ�װӦ�ò���ӵ��Ѱ�װӦ��ѡ�����
					add_3package_to_installed_app_box(get_3_package_list());
					info_append_to_text_area(String.format("%s������", current_machine_id));
				} else {
					info_append_to_text_area(String.format("%s�Ͽ�����", last_machine_id));
					disconnect_ui_change();
				}
			}
			last_machine_id = current_machine_id;
//	    	System.out.print("last:"+last_machine_id);
//	    	System.out.print("now:"+current_machine_id);
			// ȥ��ѡ���豸����
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
//				device_display_area.setText("\n�ۻ������:\n"+ach.get_machine_id());
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

		// �豸��Ļ��ͼ
		public void devices_screenshot() {
			Screenshot_Thread prtscT = new Screenshot_Thread();
			Thread pt = new Thread(prtscT);
			pt.start();
		}

		// ��������ʾ
		public void progress_show(int all, int now, JProgressBar progressBar) {
			double prog = (double) now / (double) all;
			int progbarValue = (int) (prog * 100);
			progressBar.setValue(progbarValue);
		}

		// ��ȡ������Ӧ�ð���
		public List<String> get_3_package_list() {
			info_append_to_text_area("��ȡ������Ӧ�ð���");
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

		// �����������������Ӧ��ѡ���
		public void add_3package_to_installed_app_box(List<String> package_list) {
			insatlled_app_box.removeAllItems();
			insatlled_app_box.addItem("ѡ��Ҫɾ����Ӧ��");
			for (String packagename : package_list) {
				insatlled_app_box.addItem(packagename);
			}
		}

		// ��ȡ����ʾ�Ѱ�װapp�汾��Ϣ
		public void get_app_version_String(List<String> package_list) {
			info_append_to_text_area("�汾��Ϣ��\n");
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
			JOptionPane.showMessageDialog(null, dialogString, "APP�汾", JOptionPane.INFORMATION_MESSAGE);
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
			// ��������
			Document smartvm_cfgxml_doc = CommonOperations.load_xml(temp_file_path + "smartvm_cfg.xml");
			List<Node> org_name_list = smartvm_cfgxml_doc.selectNodes("//org-name");
			String org_nameString = org_name_list.get(0).getText().toString();
			org_name_combobox.setSelectedItem(org_nameString);
			// ���ں�
			List<Node> smartvmlist = smartvm_cfgxml_doc.selectNodes("//cabinet[@id='master']");
			String master_serial = ((Element) smartvmlist.get(0)).attributeValue("serial").toString();
			serial_port_combobox.setSelectedItem(master_serial);
			// ����
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
			// ��ǰ����
			List<Node> current_vendor_numlist = smartvm_cfgxml_doc.selectNodes("//current-vendor");
			String current_vendor_numString = current_vendor_numlist.get(0).getText().toString();
			String vendor_xpathString = String.format("//vendor[@id='%s']", current_vendor_numString);
			List<Node> current_vendor_namelist = smartvm_cfgxml_doc.selectNodes(vendor_xpathString);
			String current_vendor_nameString = current_vendor_namelist.get(0).getText().toString();
			current_manufacturer_combobox.setSelectedItem(current_vendor_nameString);
		}

		public void parse_config_and_display() {
			String fileNameString = temp_file_path + "config.xml";
			// ��������ַ
			Document configxml_doc = CommonOperations.load_xml(fileNameString);
			List<Node> server_address_list = configxml_doc.selectNodes("//server-address");
			String server_addressString = server_address_list.get(0).getText().toString();
			server_address_combobox.setSelectedItem(server_addressString);
//			System.out.print(server_addressString);
		}

		public void modify_smartvm_cfg_xml() {
			String fileNameString = temp_file_path + "smartvm_cfg.xml";
			Document smartvm_cfgxml_doc = CommonOperations.load_xml(fileNameString);
			// ��������
			List<Node> org_name_list = smartvm_cfgxml_doc.selectNodes("//org-name");
			org_name_list.get(0).setText(org_name_combobox.getSelectedItem().toString());
			// ���ں�
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
			// ��ǰ����
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
			// ��������ַ
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
			info_append_to_text_area("���main��־�ļ�����please wait");
			info_append_to_text_area(ec.adb_exec(cmdString));
		}

		public void gzip_main_log() {
			String cmdString = cc.gzip_logcat_main_logString;
			info_append_to_text_area("ѹ��main��־�ļ�����please wait");
			info_append_to_text_area(ec.adb_exec(cmdString));
		}

		public void pull_main_log_gz(String save_pathString) {
			String cmdString = cc.pull_logcat_main_logString + save_pathString;
			CommonOperations.mkdir(save_pathString);
			info_append_to_text_area("pull main��־�ļ�����please wait");
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
			info_append_to_text_area("pull crash_log.txt����please wait");
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
			info_append_to_text_area("��ϵͳʱ�����Ϊ��\n" + time.toString());
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
			info_append_to_text_area("��ϵͳʱ�临ԭ��\n" + now_time_date.toString());
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
			// ��ȡcrash_log_xml���lengthֵ
			Document doc = CommonOperations.load_xml(crash_log_xml_path);
			try {
				List<Node> org_name_list = doc.selectNodes("//machine[@id='" + last_machine_id + "']");
				lengthString = org_name_list.get(0).getText().toString();
//				System.out.print("lengthString:"+lengthString);
				if (!lengthString.equals(crash_log_size)) {
					crash_log_tip.setVisible(true);
				}
			} catch (Exception e) {
				// û���ҵ����½�
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
			//1.�����ĵ�
	        Document doc=DocumentHelper.createDocument();
	        //2.��ӱ�ǩ������
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
			//1.�����ĵ�
	        Document doc=DocumentHelper.createDocument();
	        //2.��ӱ�ǩ������
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
	        JOptionPane.showMessageDialog(null, "�ѽ��������ݸ��ƽ�������\n���"+log_cmdString, "��ʾ", JOptionPane.CLOSED_OPTION);
		}
	}

	public class Set_channel_goods_Thread implements Runnable {

		@Override
		public void run() {
			ach.pull_smartvm_cfg_xml();
			ach.pull_goods_info_xml();
			ach.push_config(ach.set_each_goods_to_channel());
			ach.restart_APP();
			ach.info_append_to_text_area("�����������");
		}
	}
	public class Clear_channel_goods_Thread implements Runnable {

		@Override
		public void run() {
			ach.push_config(ach.clear_channel_goods());
			ach.restart_APP();
			ach.info_append_to_text_area("�����������");
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
			JOptionPane.showMessageDialog(null, "push" + dialogStr + "��ɣ�", "�·����óɹ�", JOptionPane.PLAIN_MESSAGE);
		}
	}

	class Pull_game_config_Thread implements Runnable {
		@Override
		public void run() {
			String config_dirString = ach.mkdir_by_machineid();
			ach.pull_game_config(config_dirString);
			ach.open_in_system_explorer(config_dirString + "game" + File.separator);
			ach.info_append_to_text_area("��ȡ�����������");
		}
	}

	class Pull_running_config_Thread implements Runnable {
		@Override
		public void run() {
			String config_dirString = ach.mkdir_by_machineid();
			ach.pull_running_config(config_dirString);
			ach.open_in_system_explorer(config_dirString + "config" + File.separator);
			ach.info_append_to_text_area("��ȡ�����������");
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
			ach.info_append_to_text_area("�����޸Ļ������������");
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
			ach.info_append_to_text_area("ˢ�»������������");
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
			ach.info_append_to_text_area("ж��" + uninstallPackageName);
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
				JOptionPane.showMessageDialog(null, "��װ\n" + dialogStr + "��ɣ�", "��װ�ɹ�", JOptionPane.PLAIN_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "ж��\n" + dialogStr + "��ɣ�", "ж�����е�����app",
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
				JOptionPane.showMessageDialog(null, "����\n" + dialogStr + "��ɣ���ִ��Install��װ", "ж�ز���װ",
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
				screenshot_label.setText("��ͼ�С���");
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
				ach.info_append_to_text_area("��ͼ���");
				screenshot_label.setText("��ɣ�");
				screenshot_label.setForeground(Color.green);
				int n = JOptionPane.showConfirmDialog(null, "�Ƿ������鿴��ͼ��", "��ͼ���", JOptionPane.OK_CANCEL_OPTION);
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
