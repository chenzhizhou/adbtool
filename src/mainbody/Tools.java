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
		panel6.setBorder(BorderFactory.createTitledBorder("����ģ��6"));
		frame.getContentPane().add(panel6);
		
		JPanel panel7 = new JPanel();
		panel7.setBackground(Color.WHITE);
		panel7.setBorder(BorderFactory.createTitledBorder("����ģ��7"));
		frame.getContentPane().add(panel7);
		
		JPanel panel8 = new JPanel();
		panel8.setBackground(Color.WHITE);
		panel8.setBorder(BorderFactory.createTitledBorder("����ģ��8"));
		frame.getContentPane().add(panel8);
		
		JPanel panel9 = new JPanel();
		panel9.setBackground(Color.WHITE);
		panel9.setBorder(BorderFactory.createTitledBorder("����ģ��9"));
		frame.getContentPane().add(panel9);
	}
	private void devices_info_module() {
		//����ģ��1
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("����ģ��1"));
		frame.getContentPane().add(panel);
		//�ۻ��������ʾ��
		JTextArea device_display_area = new JTextArea();
		device_display_area.setLineWrap(true);
		device_display_area.setEditable(false);
		device_display_area.setBounds(20, 36, 152, 36);
		panel.add(device_display_area);
		JScrollPane device_display_scroll = new JScrollPane(device_display_area);
		device_display_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//���������ǳ���
		device_display_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		device_display_scroll.setBounds(10, 20, 170, 70);
		panel.add(device_display_scroll);
		//����Ӧ��
		JButton restart_application_button = new JButton("����Ӧ��");
	    restart_application_button.setBounds(193, 20, 100, 50);
	    panel.add(restart_application_button);
		//�豸��ͼ
	    JButton devices_screenshot_button = new JButton("�豸��ͼ");
		devices_screenshot_button.setBounds(193, 79, 100, 50);
		panel.add(devices_screenshot_button);
		//�豸ѡ��������
		JLabel label_1 = new JLabel("ѡ�����ӵ��豸��");
		label_1.setBounds(10, 103, 161, 15);
		panel.add(label_1);
		JComboBox<String> device_selection_box = new JComboBox<String>();
		device_selection_box.setBounds(10, 130, 170, 30);
		panel.add(device_selection_box);
		//�豸������ʾ
		JLabel label_2 = new JLabel("�˴���ʾ�豸����״̬");
		label_2.setBounds(10, 172, 161, 15);
		panel.add(label_2);
		//��ͼ��Ϣ��ʾ
		JLabel label_3 = new JLabel("�˴���ʾ��ͼ��Ϣ");
		label_3.setBounds(193, 141, 111, 15);
		panel.add(label_3);
	}
	private void device_config_module() {
		//����ģ��2
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("����ģ��2"));
		frame.getContentPane().add(panel);
		//��ȡ��������
		JButton get_running_configure_button = new JButton("��ȡ��������");
		get_running_configure_button.setBounds(6, 20, 112, 34);
		panel.add(get_running_configure_button);
		//��ȡ�����
		JButton get_active_configuration_button = new JButton("��ȡ�����");
		get_active_configuration_button.setBounds(6, 55, 112, 34);
		panel.add(get_active_configuration_button);
		//������������
		JButton channel_rapid_configuration_button = new JButton("������������");
		channel_rapid_configuration_button.setBounds(6, 140, 112, 34);
		panel.add(channel_rapid_configuration_button);
		//���������ļ�
		JTextArea push_configuration_file_area = new JTextArea("�뽫��Ҫ�·��������ļ���ק����\n�ļ�·�����ܰ������ĺͿո�\n");
		push_configuration_file_area.setLineWrap(true);
		push_configuration_file_area.setEditable(false);
		panel.add(push_configuration_file_area);
		JScrollPane push_configuration_file_area_scroll = new JScrollPane(push_configuration_file_area);
		push_configuration_file_area_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		push_configuration_file_area_scroll.setBounds(126, 20, 178, 90);
		push_configuration_file_area_scroll.setViewportView(push_configuration_file_area);
		panel.add(push_configuration_file_area_scroll);
		//������������ļ�����
		JButton clear_push_configuration_file_area_button = new JButton();
		clear_push_configuration_file_area_button.setToolTipText("��մ��·�����");
		clear_push_configuration_file_area_button.setText("��մ��·�����");
		//clearconfig.setIcon(new ImageIcon(getClass().getResource("/toolIcon/searchreset.png")));
		clear_push_configuration_file_area_button.setBounds(126, 145, 175, 30);
		panel.add(clear_push_configuration_file_area_button);
		//�����·������ļ�
		JButton push_configuration_file_button = new JButton("�����·������ļ�");
		push_configuration_file_button.setBounds(124, 110, 180, 34);
		panel.add(push_configuration_file_button);
		//������
		JProgressBar config_progress_bar = new JProgressBar();
		config_progress_bar.setStringPainted(true);
		config_progress_bar.setBounds(6, 176, 298, 20);
		panel.add(config_progress_bar);
		
	}
	private void device_simulate_and_timeset_module() {
		//����ģ��3
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("����ģ��3"));
		frame.getContentPane().add(panel);
		//�޸�Androidϵͳʱ��
		//ʱ��ѡ����
		SpinnerDateModel date_model = new SpinnerDateModel();
		JSpinner time_selector = new JSpinner(date_model);
		time_selector.setBounds(115, 16, 189, 75);
		panel.add(time_selector);
		time_selector.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		time_selector.setValue(new Date());
		JSpinner.DateEditor date_editor = new JSpinner.DateEditor(time_selector,"yyyy-MM-dd HH:mm:ss");
		time_selector.setEditor(date_editor);
		//����ʱ�䡢��ԭʱ��
		JButton set_time_button = new JButton("�����豸ʱ��");
		set_time_button.setBounds(167, 91, 122, 35);
		panel.add(set_time_button);
		JButton reduction_time_button = new JButton("�豸��ԭʱ��");
		reduction_time_button.setBounds(167, 125, 122, 35);
		panel.add(reduction_time_button);
		//�Ƿ�ͬʱ������ť
		JCheckBox set_time_and_restart_checkbox = new JCheckBox("ͬʱ����");
		set_time_and_restart_checkbox.setBounds(167, 161, 122, 35);
		panel.add(set_time_and_restart_checkbox);
		//ģ��ϵͳ����
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
		//����ģ��4
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("����ģ��4"));
		frame.getContentPane().add(panel);
		//�����ǩ
		JLabel label = new JLabel("adb logcat -v time ");
		label.setBounds(10, 27, 153, 23);
		panel.add(label);
		JLabel label_1 = new JLabel("adb logcat -v time -s ");
		label_1.setBounds(10, 56, 153, 23);
		panel.add(label_1);
		JLabel label_2 = new JLabel("Tags:");
		label_2.setBounds(10, 91, 41, 23);
		panel.add(label_2);
		//��־tagѡ���
		JComboBox<String> common_tags_combobox = new JComboBox<String>();
		common_tags_combobox.setBounds(47, 92, 118, 22);
		panel.add(common_tags_combobox);
		//��־tag�����
		JTextField tags_input_field = new JTextField();
		tags_input_field.setBounds(10, 117, 294, 54);
		panel.add(tags_input_field);
		tags_input_field.setColumns(10);
		//��ӳ�����־tag
		JLabel label_3 = new JLabel("��ӳ���Tag��");
		label_3.setBounds(10, 176, 93, 15);
		panel.add(label_3);
		JButton add_log_tag_button = new JButton("+");
		add_log_tag_button.setFont(new Font("����", Font.PLAIN, 12));
		add_log_tag_button.setBounds(94, 173, 40, 22);
		panel.add(add_log_tag_button);
		//��ӡ��־��ť
		JButton show_log_button = new JButton("��ӡ������־");
		show_log_button.setBounds(175, 20, 93, 39);
		panel.add(show_log_button);
		JButton show_log_with_filter_button = new JButton("��ӡ������־");
		show_log_with_filter_button.setBounds(175, 75, 93, 39);
		panel.add(show_log_with_filter_button);
		//�����־����
		JButton clear_log_buffer_button = new JButton("�����־����");
		clear_log_buffer_button.setBounds(186, 173, 118, 23);
		panel.add(clear_log_buffer_button);
	}
	private void app_install_module() {
		//����ģ��5
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("����ģ��5"));
		frame.getContentPane().add(panel);
		//����װapp��
		JTextArea push_apps_file_area = new JTextArea("����Ҫ��װ��APK�ļ���ק����\n�ļ�·�����ܰ������ĺͿո�\n");
		push_apps_file_area.setLineWrap(true);
		push_apps_file_area.setEditable(false);
		panel.add(push_apps_file_area);
		JScrollPane push_apps_file_area_scroll = new JScrollPane(push_apps_file_area);
		push_apps_file_area_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		push_apps_file_area_scroll.setBounds(126, 20, 178, 104);
		push_apps_file_area_scroll.setViewportView(push_apps_file_area);
		panel.add(push_apps_file_area_scroll);
		//����װ
		JButton install_and_restart_button = new JButton("������װapp");
		install_and_restart_button.setBounds(6, 84, 104, 35);
		panel.add(install_and_restart_button);
		//ж��inhandȫ��app
		JButton uninstall_all_inhand_button = new JButton("ж��inhandȫ��");
		uninstall_all_inhand_button.setBounds(6, 140, 114, 35);
		panel.add(uninstall_all_inhand_button);
		//ж��inhandȫ��app����װapp
		JButton cover_installation_button = new JButton("<html>ж��<br>ȫ��app<br>����װ</html>");
		cover_installation_button.setBounds(6, 21, 104, 59);
		panel.add(cover_installation_button);
		//��մ���װapp����
		JButton clear_push_app_file_area_button = new JButton();
		clear_push_app_file_area_button.setToolTipText("��մ���װapp");
		clear_push_app_file_area_button.setText("��մ���װapp");
		clear_push_app_file_area_button.setBounds(126, 142, 175, 30);
		panel.add(clear_push_app_file_area_button);
		//������
		JProgressBar install_progress_bar = new JProgressBar();
		install_progress_bar.setStringPainted(true);
		install_progress_bar.setBounds(6, 176, 298, 20);
		panel.add(install_progress_bar);
	}
}
