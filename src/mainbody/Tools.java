package mainbody;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import java.awt.Component;
import javax.swing.Box;

public class Tools {

	private JFrame frame;


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
	 */
	public Tools() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 950, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 3, 10, 10));
		devices_info_module();
		device_config_module();	
		device_simulate_and_timeset_module();
		show_log_module();
		app_install_module();
		
		
		
		

		
		JPanel panel6 = new JPanel();
		panel6.setBackground(Color.WHITE);
		panel6.setBorder(BorderFactory.createTitledBorder("功能模块6"));
		frame.getContentPane().add(panel6);
		
		JPanel panel7 = new JPanel();
		panel7.setBackground(Color.WHITE);
		panel7.setBorder(BorderFactory.createTitledBorder("功能模块7"));
		frame.getContentPane().add(panel7);
		
		JPanel panel8 = new JPanel();
		panel8.setBackground(Color.WHITE);
		panel8.setBorder(BorderFactory.createTitledBorder("功能模块8"));
		frame.getContentPane().add(panel8);
		
		JPanel panel9 = new JPanel();
		panel9.setBackground(Color.WHITE);
		panel9.setBorder(BorderFactory.createTitledBorder("功能模块9"));
		frame.getContentPane().add(panel9);
	}
	private void devices_info_module() {
		//功能模块1
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("功能模块1"));
		frame.getContentPane().add(panel);
		//售货机编号显示区
		JTextArea device_display_area = new JTextArea();
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
		//设备截图
	    JButton devices_screenshot_button = new JButton("设备截图");
		devices_screenshot_button.setBounds(193, 79, 100, 50);
		panel.add(devices_screenshot_button);
		//设备选择下拉框
		JLabel label_1 = new JLabel("选择连接的设备：");
		label_1.setBounds(10, 103, 161, 15);
		panel.add(label_1);
		JComboBox<String> device_selection_box = new JComboBox<String>();
		device_selection_box.setBounds(10, 130, 170, 30);
		panel.add(device_selection_box);
		//设备连接提示
		JLabel label_2 = new JLabel("此处显示设备连接状态");
		label_2.setBounds(10, 172, 161, 15);
		panel.add(label_2);
		//截图信息提示
		JLabel label_3 = new JLabel("此处显示截图信息");
		label_3.setBounds(193, 141, 111, 15);
		panel.add(label_3);
	}
	private void device_config_module() {
		//功能模块2
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("功能模块2"));
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
		//功能模块3
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("功能模块3"));
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
		//功能模块4
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("功能模块4"));
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
		//功能模块5
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("功能模块5"));
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
		//仅安装
		JButton install_and_restart_button = new JButton("单独安装app");
		install_and_restart_button.setBounds(6, 84, 104, 35);
		panel.add(install_and_restart_button);
		//卸载inhand全套app
		JButton uninstall_all_inhand_button = new JButton("卸载inhand全套");
		uninstall_all_inhand_button.setBounds(6, 140, 114, 35);
		panel.add(uninstall_all_inhand_button);
		//卸载inhand全套app并安装app
		JButton cover_installation_button = new JButton("<html>卸载<br>全套app<br>并安装</html>");
		cover_installation_button.setBounds(6, 21, 104, 59);
		panel.add(cover_installation_button);
		//清空待安装app内容
		JButton clear_push_app_file_area_button = new JButton();
		clear_push_app_file_area_button.setToolTipText("清空待安装app");
		clear_push_app_file_area_button.setText("清空待安装app");
		clear_push_app_file_area_button.setBounds(126, 142, 175, 30);
		panel.add(clear_push_app_file_area_button);
		//进度条
		JProgressBar install_progress_bar = new JProgressBar();
		install_progress_bar.setStringPainted(true);
		install_progress_bar.setBounds(6, 176, 298, 20);
		panel.add(install_progress_bar);
	}
}
