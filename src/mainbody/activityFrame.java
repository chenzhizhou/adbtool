package mainbody;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;


public class activityFrame extends JFrame {

	private JPanel contentPane;
	private JTextField discountNum_textField;
	private JTextArea channel_view_textArea;
	private JTextField sigle_channel_textField;
	ArrayList<Long> start_time_list = new ArrayList<Long>();
	ArrayList<Long> end_time_list = new ArrayList<Long>();
	ArrayList<String> discount_list = new ArrayList<String>();
	private JTextField separate_discount_textField;
	private JTextArea separate_time_view;
	private JRadioButton unitetime_rdbtnNewRadioButton;
	private JRadioButton separate_rdbtnNewRadioButton;
	private JButton reset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					activityFrame frame = new activityFrame();
					frame.setVisible(true);
					newfolder("C:\\inhandTool\\temp\\activity");
					deleteFile("C:\\inhandTool\\temp\\activity\\channel_cfg.xml");
					String command1 = "cmd.exe /c adb pull sdcard/inbox/config/channel_cfg.xml C:\\inhandTool\\temp\\activity";
					Process p = Runtime.getRuntime().exec(command1);
					p.waitFor();
					p.destroy();
				} catch (Exception e) {
					Component tip = null;
					JOptionPane.showMessageDialog(tip, "初始化失败", "初始化失败",JOptionPane.CANCEL_OPTION);
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public activityFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 451, 319);
		setTitle("优惠活动快速配置");
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//开始时间选择器
		SpinnerDateModel datemodel1 = new SpinnerDateModel();
		JSpinner starttime_datespinner = new JSpinner(datemodel1);
		starttime_datespinner.setBounds(10, 39, 81, 37);
		contentPane.add(starttime_datespinner);
		starttime_datespinner.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		starttime_datespinner.setValue(new Date());
		JSpinner.DateEditor starttime_editor = new JSpinner.DateEditor(starttime_datespinner,"yyyy-MM-dd");
		starttime_datespinner.setEditor(starttime_editor);
		//结束时间选择器
		SpinnerDateModel datemodel2 = new SpinnerDateModel();
		JSpinner endtime_datespinner = new JSpinner(datemodel2);
		endtime_datespinner.setBounds(111, 39, 81, 37);
		contentPane.add(endtime_datespinner);
		endtime_datespinner.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		endtime_datespinner.setValue(new Date());
		JSpinner.DateEditor endtime_editor = new JSpinner.DateEditor(endtime_datespinner,"yyyy-MM-dd");
		endtime_datespinner.setEditor(endtime_editor);
		
		JLabel unitLabel = new JLabel("折/分");
		unitLabel.setBounds(397, 180, 54, 15);
		contentPane.add(unitLabel);
		
		JLabel unitLabe2 = new JLabel("折/分");
		unitLabe2.setBounds(151, 225, 54, 15);
		contentPane.add(unitLabe2);
		
		reset = new JButton("重置");
		reset.setForeground(Color.GREEN);
		reset.setBounds(338, 208, 86, 23);
		contentPane.add(reset);
		reset.setVisible(false);
		
		JPanel paystylePanel = new JPanel();
		paystylePanel.setBounds(10, 86, 182, 54);
		contentPane.add(paystylePanel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		paystylePanel.setLayout(gbl_panel);
		
		JCheckBox alipay_chckbxNewCheckBox = new JCheckBox("支付宝");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 0;
		paystylePanel.add(alipay_chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JCheckBox wechat_chckbxNewCheckBox = new JCheckBox("微信");
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 0;
		paystylePanel.add(wechat_chckbxNewCheckBox, gbc_chckbxNewCheckBox_1);
		
		JCheckBox union_chckbxNewCheckBox = new JCheckBox("云闪付");
		GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox_2.gridx = 0;
		gbc_chckbxNewCheckBox_2.gridy = 1;
		paystylePanel.add(union_chckbxNewCheckBox, gbc_chckbxNewCheckBox_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 150, 182, 54);
		contentPane.add(panel);
		
		GridBagLayout gbl2_panel = new GridBagLayout();
		gbl2_panel.columnWidths = new int[]{0, 0, 0};
		gbl2_panel.rowHeights = new int[]{0, 0, 0};
		gbl2_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl2_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl2_panel);
		
		ButtonGroup activityTypeGroup = new ButtonGroup();
		
		JRadioButton discount_rdbtnNewRadioButton = new JRadioButton("优惠打折");
		discount_rdbtnNewRadioButton.setSelected(true);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 0;
		panel.add(discount_rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		JRadioButton sales_rdbtnNewRadioButton = new JRadioButton("消费立减");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_2.gridx = 1;
		gbc_rdbtnNewRadioButton_2.gridy = 0;
		panel.add(sales_rdbtnNewRadioButton, gbc_rdbtnNewRadioButton_2);
		
		JRadioButton onefree_rdbtnNewRadioButton = new JRadioButton("买一送一");
		onefree_rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (onefree_rdbtnNewRadioButton.isSelected()) {
					discountNum_textField.setText("");
					discountNum_textField.setVisible(false);
					separate_discount_textField.setText("");
					separate_discount_textField.setVisible(false);
				}
				else {
					discountNum_textField.setText("10");
					discountNum_textField.setVisible(true);
					if (separate_rdbtnNewRadioButton.isSelected()) {
						separate_discount_textField.setText(discountNum_textField.getText());
						separate_discount_textField.setVisible(true);
					}
				}
			}
		});
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 0;
		gbc_rdbtnNewRadioButton_1.gridy = 1;
		panel.add(onefree_rdbtnNewRadioButton, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton focus_rdbtnNewRadioButton = new JRadioButton("关注有奖");
		focus_rdbtnNewRadioButton.setEnabled(false);
		GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_3.gridx = 1;
		gbc_rdbtnNewRadioButton_3.gridy = 1;
		panel.add(focus_rdbtnNewRadioButton, gbc_rdbtnNewRadioButton_3);

		activityTypeGroup.add(discount_rdbtnNewRadioButton);
		activityTypeGroup.add(sales_rdbtnNewRadioButton);
		activityTypeGroup.add(onefree_rdbtnNewRadioButton);
		activityTypeGroup.add(focus_rdbtnNewRadioButton);
		
		discountNum_textField = new JTextField();
		discountNum_textField.setText("5");
		discountNum_textField.setBounds(10, 214, 131, 37);
		contentPane.add(discountNum_textField);
		discountNum_textField.setColumns(10);
		
		JComboBox<String> smartvm_comboBox = new JComboBox<String>();
		ComboBoxEditor smartvm_comboBox_editor;
		smartvm_comboBox_editor = smartvm_comboBox.getEditor();
		Document channeldocument=load("C:\\inhandTool\\temp\\activity\\channel_cfg.xml");
		List<Node>smartvmlist=channeldocument.selectNodes("//cabinet[@number]");
		String channel_String = "";
		for(int i=0;i<smartvmlist.size();i++){
			String vmnumber = ((Element) smartvmlist.get(i)).attributeValue("number").toString();
//			System.out.println(vmnumber);
			smartvm_comboBox.addItem(vmnumber);
			channel_String += vmnumber + "\n";
			List<Node>channellist=channeldocument.selectNodes("//cabinet[@number='"+vmnumber+"']//channel");
			for(int i1 = 0;i1<channellist.size();i1++){
				String channel_id = ((Element)channellist.get(i1)).attributeValue("id").toString();
				String channel_id_goodsId = ((Element)channellist.get(i1)).attributeValue("goodsId").toString();
//				System.out.println(channel_id+" "+channel_id_goodsId);
				channel_String += channel_id;
				channel_String += ";";
			}
			channel_String += "\n";
		}
		smartvm_comboBox.setBounds(315, 10, 81, 21);
		contentPane.add(smartvm_comboBox);
		smartvm_comboBox.setVisible(false);
		
		sigle_channel_textField = new JTextField();
		sigle_channel_textField.setBounds(315, 41, 109, 48);
		contentPane.add(sigle_channel_textField);
		sigle_channel_textField.setColumns(10);
		sigle_channel_textField.setVisible(false);
		
		channel_view_textArea = new JTextArea();
		contentPane.add(channel_view_textArea);
		channel_view_textArea.setColumns(10);
		JScrollPane channel_view_textArea_ScrollPane = new JScrollPane(channel_view_textArea);
		channel_view_textArea_ScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		channel_view_textArea_ScrollPane.setBounds(new Rectangle(315, 10, 109, 79));
		channel_view_textArea.setBounds(315, 10, 109, 79);
		contentPane.add(channel_view_textArea_ScrollPane);
		channel_view_textArea.setText(channel_String);
//		System.out.println(channel_String);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(202, 10, 103, 79);
		contentPane.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JRadioButton onechannel_rdbtnNewRadioButton = new JRadioButton("单个货道");
		onechannel_rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (onechannel_rdbtnNewRadioButton.isSelected()) {
					smartvm_comboBox.setVisible(true);
					channel_view_textArea.setVisible(false);
					channel_view_textArea_ScrollPane.setVisible(channel_view_textArea.isVisible());
					sigle_channel_textField.setVisible(true);
				}
				else {
					smartvm_comboBox.setVisible(false);
					channel_view_textArea.setVisible(true);
					channel_view_textArea_ScrollPane.setVisible(channel_view_textArea.isVisible());
					sigle_channel_textField.setVisible(false);
				}
			}
		});
		GridBagConstraints gbc1_rdbtnNewRadioButton = new GridBagConstraints();
		gbc1_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc1_rdbtnNewRadioButton.gridx = 0;
		gbc1_rdbtnNewRadioButton.gridy = 0;
		panel_1.add(onechannel_rdbtnNewRadioButton, gbc1_rdbtnNewRadioButton);
		
		JRadioButton randomchannel_rdbtnNewRadioButton = new JRadioButton("随机货道");
		randomchannel_rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (randomchannel_rdbtnNewRadioButton.isSelected()) {
					channel_view_textArea.setText("随机货道\n请填写概率\n默认30%");
				}
			}
		});
		channel_view_textArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (randomchannel_rdbtnNewRadioButton.isSelected()) {
					channel_view_textArea.setText("");
				}
			}
		});
		GridBagConstraints gbc1_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc1_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc1_rdbtnNewRadioButton_1.gridx = 0;
		gbc1_rdbtnNewRadioButton_1.gridy = 1;
		panel_1.add(randomchannel_rdbtnNewRadioButton, gbc1_rdbtnNewRadioButton_1);
		
		JRadioButton allchannel_rdbtnNewRadioButton = new JRadioButton("全部货道");
		allchannel_rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (allchannel_rdbtnNewRadioButton.isSelected()) {
					String channel_String = "";
					for(int i=0;i<smartvmlist.size();i++){
						String vmnumber = ((Element) smartvmlist.get(i)).attributeValue("number").toString();
						smartvm_comboBox.addItem(vmnumber);
						channel_String += vmnumber + "\n";
						List<Node>channellist=channeldocument.selectNodes("//cabinet[@number='"+vmnumber+"']//channel");
						for(int i1 = 0;i1<channellist.size();i1++){
							String channel_id = ((Element)channellist.get(i1)).attributeValue("id").toString();
							String channel_id_goodsId = ((Element)channellist.get(i1)).attributeValue("goodsId").toString();
							channel_String += channel_id;
							channel_String += ";";
						}
						channel_String += "\n";
					}
					channel_view_textArea.setText(channel_String);
				}
			}
		});
		allchannel_rdbtnNewRadioButton.setSelected(true);
		GridBagConstraints gbc1_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc1_rdbtnNewRadioButton_2.gridx = 0;
		gbc1_rdbtnNewRadioButton_2.gridy = 2;
		panel_1.add(allchannel_rdbtnNewRadioButton, gbc1_rdbtnNewRadioButton_2);
		ButtonGroup choosechannel_TypeGroup = new ButtonGroup();
		choosechannel_TypeGroup.add(allchannel_rdbtnNewRadioButton);
		choosechannel_TypeGroup.add(randomchannel_rdbtnNewRadioButton);
		choosechannel_TypeGroup.add(onechannel_rdbtnNewRadioButton);
		
		
		JPanel timeType_panel = new JPanel();
		timeType_panel.setBounds(202, 235, 131, 37);
		contentPane.add(timeType_panel);
		timeType_panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		separate_time_view = new JTextArea("优惠时间段：");
		separate_time_view.setEditable(false);
		separate_time_view.setBounds(202, 99, 131, 108);
		contentPane.add(separate_time_view);
		JScrollPane separate_time_view_textArea_ScrollPane = new JScrollPane(separate_time_view);
		separate_time_view_textArea_ScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		separate_time_view_textArea_ScrollPane.setBounds(new Rectangle(202, 99, 131, 108));
		contentPane.add(separate_time_view_textArea_ScrollPane);
		
		JSpinner start_hour_spinner = new JSpinner();
		start_hour_spinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		start_hour_spinner.setBounds(338, 99, 35, 22);
		contentPane.add(start_hour_spinner);
		
		JSpinner start_minute_spinner = new JSpinner();
		start_minute_spinner.setModel(new SpinnerNumberModel(0, 0, 59, 15));
		start_minute_spinner.setBounds(383, 99, 41, 22);
		contentPane.add(start_minute_spinner);
		
		JSpinner end_hour_spinner = new JSpinner();
		end_hour_spinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		end_hour_spinner.setBounds(338, 145, 35, 22);
		contentPane.add(end_hour_spinner);
		
		JSpinner end_minute_spinner = new JSpinner();
		end_minute_spinner.setModel(new SpinnerNumberModel(59, 0, 59, 15));
		end_minute_spinner.setBounds(383, 145, 41, 22);
		contentPane.add(end_minute_spinner);
		
		
		separate_discount_textField = new JTextField();
		separate_discount_textField.setText("5");
		separate_discount_textField.setBounds(338, 177, 53, 21);
		contentPane.add(separate_discount_textField);
		separate_discount_textField.setColumns(10);
		
		JButton add_time_Button = new JButton("加时段");
		add_time_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				long startunixTimestamp = 0;
				long endunixTimestamp = 0;				
				Object starttime = starttime_datespinner.getValue();
				SimpleDateFormat startsimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		        String starttime2 = startsimpleDateFormat.format(starttime);
		        Date startdate;
				try {
					startdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(starttime2);
					startunixTimestamp = startdate.getTime()/1000;
//					System.out.println(startunixTimestamp);
//					System.out.print(starttime2);
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				
				Object endtime = endtime_datespinner.getValue();
				SimpleDateFormat endsimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		        String endtime2 = endsimpleDateFormat.format(endtime);
		        Date enddate;
				try {
					enddate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtime2);
					endunixTimestamp = enddate.getTime()/1000;
//					System.out.print("\n");
//					System.out.println(endunixTimestamp);
//					System.out.print(endtime2);
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				long days = (endunixTimestamp - startunixTimestamp) / (3600 * 24);
				System.out.println("\n"+days);
				int start_hour = (int) start_hour_spinner.getValue();
				int start_min = (int) start_minute_spinner.getValue();
				long start_timestamp = startunixTimestamp + start_hour * 3600 + start_min * 60;
//				System.out.print(start_timestamp);
				int end_hour = (int) end_hour_spinner.getValue();
				int end_min = (int) end_minute_spinner.getValue();
				long end_timestamp = endunixTimestamp + end_hour * 3600 + end_min * 60 + 59;
//				System.out.print("\n"+end_timestamp);
				separate_time_view.append("\n"+start_hour+":"+start_min+"——"+end_hour+":"+end_min+"——"+separate_discount_textField.getText());
				for(long i = 0; i<=days; i++){
					start_time_list.add(start_timestamp+3600*24*i);
					end_time_list.add(end_timestamp+3600*24*i);
					discount_list.add(separate_discount_textField.getText());
				}
				System.out.println("\n"+start_time_list);
				System.out.println("\n"+end_time_list);
				System.out.println("\n"+discount_list);
			}
		});
		add_time_Button.setBounds(338, 208, 86, 23);
		contentPane.add(add_time_Button);
		
		unitetime_rdbtnNewRadioButton = new JRadioButton("统一");
		unitetime_rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (unitetime_rdbtnNewRadioButton.isSelected()) {
					separate_discount_textField.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					start_hour_spinner.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					end_hour_spinner.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					start_minute_spinner.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					end_minute_spinner.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					add_time_Button.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					separate_time_view.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					separate_time_view_textArea_ScrollPane.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					unitLabel.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
				}
				else {
					separate_discount_textField.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					start_hour_spinner.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					end_hour_spinner.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					start_minute_spinner.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					end_minute_spinner.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					add_time_Button.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					separate_time_view.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					separate_time_view_textArea_ScrollPane.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
					unitLabel.setVisible(!unitetime_rdbtnNewRadioButton.isSelected());
				}
			}
		});
		timeType_panel.add(unitetime_rdbtnNewRadioButton);
		unitetime_rdbtnNewRadioButton.setSelected(true);
		
		separate_rdbtnNewRadioButton = new JRadioButton("分时段");
		timeType_panel.add(separate_rdbtnNewRadioButton);
		
		ButtonGroup timeTypeGroup = new ButtonGroup();
		timeTypeGroup.add(unitetime_rdbtnNewRadioButton);
		timeTypeGroup.add(separate_rdbtnNewRadioButton);
		
		JButton btn_generate_cfg = new JButton("下发");
		btn_generate_cfg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				long startunixTimestamp = 0;
				long endunixTimestamp = 0;
				ArrayList<String> paystyles = new ArrayList<String>();
				String activityType = null;
				
				Object starttime = starttime_datespinner.getValue();
				SimpleDateFormat startsimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		        String starttime2 = startsimpleDateFormat.format(starttime);
		        Date startdate;
				try {
					startdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(starttime2);
					startunixTimestamp = startdate.getTime()/1000;
					System.out.println(startunixTimestamp);
					System.out.print(starttime2);
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				
				Object endtime = endtime_datespinner.getValue();
				SimpleDateFormat endsimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		        String endtime2 = endsimpleDateFormat.format(endtime);
		        Date enddate;
				try {
					enddate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtime2);
					endunixTimestamp = enddate.getTime()/1000;
					System.out.print("\n");
					System.out.println(endunixTimestamp);
					System.out.print(endtime2);
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				
				if (wechat_chckbxNewCheckBox.isSelected()) {
					paystyles.add("2");
				}
				if (alipay_chckbxNewCheckBox.isSelected()) {
					paystyles.add("3");
				}
				if (union_chckbxNewCheckBox.isSelected()) {
					paystyles.add("21");
				}
				if (focus_rdbtnNewRadioButton.isSelected()) {
					activityType = "1";
				}
				if (onefree_rdbtnNewRadioButton.isSelected()) {
					activityType = "2";
				}
				if (sales_rdbtnNewRadioButton.isSelected()) {
					activityType = "3";
				}
				if (discount_rdbtnNewRadioButton.isSelected()) {
					activityType = "4";
				}
				
				
				Document doc=DocumentHelper.createDocument();
		        Element specialofferElem=doc.addElement("special-offer");
		        Element configElem=specialofferElem.addElement("config");
		        Element paystylesElem = null;
		        if (!paystyles.isEmpty()) {
		        	paystylesElem=configElem.addElement("paystyles");
				}
		        for(int i=0; i<paystyles.size();i++){
		        	Element paystyleElem=paystylesElem.addElement("paystyle");
		        	paystyleElem.setText(paystyles.get(i));
		        }
		        Element typeElem=configElem.addElement("type");
		        typeElem.setText(activityType);
		        Element starttimeElem=configElem.addElement("start-time");
		        starttimeElem.setText(String.valueOf(startunixTimestamp));
		        Element endtimeElem=configElem.addElement("end-time");
		        endtimeElem.setText(String.valueOf(endunixTimestamp));
		        Element discountElem=configElem.addElement("discount");
		        if (!onefree_rdbtnNewRadioButton.isSelected()) {
		        	discountElem.setText(discountNum_textField.getText());
				}
		        Element timeSlotsElem=configElem.addElement("timeSlots");
		        if (unitetime_rdbtnNewRadioButton.isSelected()) {
		        	Element timeSlotElem=timeSlotsElem.addElement("timeSlot");
			        timeSlotElem.addAttribute("startTime", String.valueOf(startunixTimestamp));
			        timeSlotElem.addAttribute("endTime", String.valueOf(endunixTimestamp));
			        timeSlotElem.addAttribute("discount", discountNum_textField.getText());
				}
		        if (separate_rdbtnNewRadioButton.isSelected()) {
					for(int i = 0;i<start_time_list.size();i++){
						Element timeSlotElem=timeSlotsElem.addElement("timeSlot");
						timeSlotElem.addAttribute("startTime", String.valueOf(start_time_list.get(i)));
				        timeSlotElem.addAttribute("endTime", String.valueOf(end_time_list.get(i)));
				        timeSlotElem.addAttribute("discount", discount_list.get(i));
					}
				}
		        
		        Element cabinetsElem=specialofferElem.addElement("cabinets");
//		        Element cabinetElem=cabinetsElem.addElement("cabinet");
//		        cabinetElem.addAttribute("id", "master");
//		        for(int i = 1;i<=10;i++){
//		        	Element channelElem = cabinetElem.addElement("channel");
//		        	channelElem.addAttribute("goodsId", "XxXxXxXxXxXxXxXx");
//		        	channelElem.setText(Integer.toString(i));
//		        }
		        if (allchannel_rdbtnNewRadioButton.isSelected()) {
		        	List<Node>smartvmlist=channeldocument.selectNodes("//cabinet[@number]");
		    		for(int i=0;i<smartvmlist.size();i++){
		    			String vmnumber = ((Element) smartvmlist.get(i)).attributeValue("number").toString();
		    			System.out.println("\n"+vmnumber);
		    			Element cabinetElem=cabinetsElem.addElement("cabinet");
		    			cabinetElem.addAttribute("id", vmnumber);
		    			List<Node>channellist=channeldocument.selectNodes("//cabinet[@number='"+vmnumber+"']//channel");
		    			for(int i1 = 0;i1<channellist.size();i1++){
		    				String channel_id = ((Element)channellist.get(i1)).attributeValue("id").toString();
		    				String channel_id_goodsId = ((Element)channellist.get(i1)).attributeValue("goodsId").toString();
		    				System.out.println(channel_id+" "+channel_id_goodsId);
		    				Element channelElem = cabinetElem.addElement("channel");
		    				channelElem.addAttribute("goodsId", channel_id_goodsId);
		    				channelElem.setText(channel_id);
		    			}
		    		}
				}
		        if (randomchannel_rdbtnNewRadioButton.isSelected()) {
		        	int plbint = 30;
		        	if (channel_view_textArea.getText() == "") {
						plbint = 30;
					}
		        	else {
		        		try {
				        	String regEx="[^0-9]";
				        	Pattern p = Pattern.compile(regEx);
				        	Matcher m = p.matcher(channel_view_textArea.getText().trim());
				        	String plb = m.replaceAll("").trim();
				        	plbint = Integer.parseInt(plb);
						} catch (Exception e2) {
							plbint = 30;
						}
					}
		        	List<Node>smartvmlist=channeldocument.selectNodes("//cabinet[@number]");
		    		for(int i=0;i<smartvmlist.size();i++){
		    			String vmnumber = ((Element) smartvmlist.get(i)).attributeValue("number").toString();
		    			System.out.println("\n"+vmnumber);
		    			Element cabinetElem=cabinetsElem.addElement("cabinet");
		    			cabinetElem.addAttribute("id", vmnumber);
		    			List<Node>channellist=channeldocument.selectNodes("//cabinet[@number='"+vmnumber+"']//channel");
		    			int channel_num = 0;
		    			for(int i1 = 0;i1<channellist.size();i1++){
		    				Random rand = new Random();
		    				int random_num = rand.nextInt(100)+ 1;
		    				if (random_num<=plbint || ((i1 == channellist.size() - 1) && channel_num == 0)) {
			    				String channel_id = ((Element)channellist.get(i1)).attributeValue("id").toString();
			    				String channel_id_goodsId = ((Element)channellist.get(i1)).attributeValue("goodsId").toString();
			    				System.out.println(channel_id+" "+channel_id_goodsId);
			    				Element channelElem = cabinetElem.addElement("channel");
			    				channelElem.addAttribute("goodsId", channel_id_goodsId);
			    				channelElem.setText(channel_id);
			    				channel_num += 1;
							}
		    			}
		    		}
				}
		        
		        FileOutputStream out;
				try {
					out = new FileOutputStream("C:\\inhandTool\\temp\\activity\\promotion.xml");
			        OutputFormat format=OutputFormat.createPrettyPrint();
			        format.setEncoding("UTF-8");
			        XMLWriter writer=new XMLWriter(out,format);
			        writer.write(doc);
			        writer.close();
			        String command3 = "cmd.exe /c adb push C:\\inhandTool\\temp\\activity\\promotion.xml sdcard/inbox/game";
			        Process p = Runtime.getRuntime().exec(command3);
					p.waitFor();
					p.destroy();
					RestartAPP();
				}
				catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
				finally {
					if (separate_rdbtnNewRadioButton.isSelected()) {
						reset.setVisible(true);
						add_time_Button.setVisible(false);
					}
				}
			}
		});
		btn_generate_cfg.setBounds(338, 241, 86, 31);
		contentPane.add(btn_generate_cfg);
		
		JLabel label = new JLabel("开始时间：");
		label.setBounds(10, 14, 81, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("结束时间：");
		label_1.setBounds(111, 14, 81, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(375, 150, 16, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(375, 104, 16, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("↓");
		label_4.setBounds(370, 129, 16, 15);
		contentPane.add(label_4);
		
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				start_time_list.clear();
				end_time_list.clear();
				discount_list.clear();
				separate_time_view.setText("优惠时间段：");
				reset.setVisible(false);
				add_time_Button.setVisible(true);
			}
		});
		


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
	public static void newfolder(String path){
		//System.out.print(path);
		File file =new File(path);    
		//如果文件夹不存在则创建    
		if  (!file.exists() && !file.isDirectory()){   
		    file.mkdirs();    
		}
	}
	public static Document load(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename));  //读取XML文件,获得document对象
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
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
}
