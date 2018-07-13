package mainbody;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
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

import org.dom4j.Document;
import org.dom4j.DocumentException;
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
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Rectangle;

public class activityFrame extends JFrame {

	private JPanel contentPane;
	private JTextField discountNum_textField;
	private JTextArea channel_view_textArea;
	private JTextField sigle_channel_textField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//开始时间选择器
		SpinnerDateModel datemodel1 = new SpinnerDateModel();
		JSpinner starttime_datespinner = new JSpinner(datemodel1);
		starttime_datespinner.setBounds(10, 10, 81, 37);
		contentPane.add(starttime_datespinner);
		starttime_datespinner.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		starttime_datespinner.setValue(new Date());
		JSpinner.DateEditor starttime_editor = new JSpinner.DateEditor(starttime_datespinner,"yyyy-MM-dd");
		starttime_datespinner.setEditor(starttime_editor);
		//结束时间选择器
		SpinnerDateModel datemodel2 = new SpinnerDateModel();
		JSpinner endtime_datespinner = new JSpinner(datemodel2);
		endtime_datespinner.setBounds(111, 10, 81, 37);
		contentPane.add(endtime_datespinner);
		endtime_datespinner.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		endtime_datespinner.setValue(new Date());
		JSpinner.DateEditor endtime_editor = new JSpinner.DateEditor(endtime_datespinner,"yyyy-MM-dd");
		endtime_datespinner.setEditor(endtime_editor);
		
		JPanel paystylePanel = new JPanel();
		paystylePanel.setBounds(10, 62, 182, 54);
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
		panel.setBounds(10, 126, 182, 54);
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
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 0;
		gbc_rdbtnNewRadioButton_1.gridy = 1;
		panel.add(onefree_rdbtnNewRadioButton, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton focus_rdbtnNewRadioButton = new JRadioButton("关注有奖");
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
		discountNum_textField.setBounds(10, 190, 182, 37);
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
		GridBagConstraints gbc1_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc1_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc1_rdbtnNewRadioButton_1.gridx = 0;
		gbc1_rdbtnNewRadioButton_1.gridy = 1;
		panel_1.add(randomchannel_rdbtnNewRadioButton, gbc1_rdbtnNewRadioButton_1);
		
		JRadioButton allchannel_rdbtnNewRadioButton = new JRadioButton("全部货道");
		allchannel_rdbtnNewRadioButton.setSelected(true);
		GridBagConstraints gbc1_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc1_rdbtnNewRadioButton_2.gridx = 0;
		gbc1_rdbtnNewRadioButton_2.gridy = 2;
		panel_1.add(allchannel_rdbtnNewRadioButton, gbc1_rdbtnNewRadioButton_2);
		ButtonGroup choosechannel_TypeGroup = new ButtonGroup();
		choosechannel_TypeGroup.add(allchannel_rdbtnNewRadioButton);
		choosechannel_TypeGroup.add(randomchannel_rdbtnNewRadioButton);
		choosechannel_TypeGroup.add(onechannel_rdbtnNewRadioButton);
		

		
		
		JButton btn_generate_cfg = new JButton("New button");
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
		        discountElem.setText(discountNum_textField.getText());
		        Element timeSlotsElem=configElem.addElement("timeSlots");
		        Element timeSlotElem=timeSlotsElem.addElement("timeSlot");
		        timeSlotElem.addAttribute("startTime", String.valueOf(startunixTimestamp));
		        timeSlotElem.addAttribute("endTime", String.valueOf(endunixTimestamp));
		        timeSlotElem.addAttribute("discount", discountNum_textField.getText());
		        
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
		    			System.out.println(vmnumber);
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
		        
		        FileOutputStream out;
				try {
					out = new FileOutputStream("D:\\1.xml");
			        OutputFormat format=OutputFormat.createPrettyPrint();
			        format.setEncoding("UTF-8");
			        XMLWriter writer=new XMLWriter(out,format);
			        writer.write(doc);
			        writer.close();
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_generate_cfg.setBounds(260, 190, 164, 61);
		contentPane.add(btn_generate_cfg);

		
	}

	public static void generate_cfg(){
		Document doc=DocumentHelper.createDocument();
        Element specialofferElem=doc.addElement("special-offer");
        Element configElem=specialofferElem.addElement("config");
        Element paystylesElem=configElem.addElement("paystyles");
        Element paystyleElem=paystylesElem.addElement("paystyle");
        paystyleElem.setText("2");
        Element typeElem=configElem.addElement("type");
        typeElem.setText("4");
        Element starttimeElem=configElem.addElement("start-time");
        starttimeElem.setText("1531324800");
        Element endtimeElem=configElem.addElement("end-time");
        endtimeElem.setText("1531756799");
        Element discountElem=configElem.addElement("discount");
        discountElem.setText("5");
        Element timeSlotsElem=configElem.addElement("timeSlots");
        Element timeSlotElem=timeSlotsElem.addElement("timeSlot");
        timeSlotElem.addAttribute("startTime", "1531324800");
        timeSlotElem.addAttribute("endTime", "1531756799");
        timeSlotElem.addAttribute("discount", "5");
        
        Element cabinetsElem=specialofferElem.addElement("cabinets");
        Element cabinetElem=cabinetsElem.addElement("cabinet");
        cabinetElem.addAttribute("id", "master");
        for(int i = 1;i<=10;i++){
        	Element channelElem = cabinetElem.addElement("channel");
        	channelElem.addAttribute("goodsId", "XxXxXxXxXxXxXxXx");
        	channelElem.setText(Integer.toString(i));
        }
        FileOutputStream out;
		try {
			out = new FileOutputStream("D:\\1.xml");
	        OutputFormat format=OutputFormat.createPrettyPrint();
	        format.setEncoding("UTF-8");
	        XMLWriter writer=new XMLWriter(out,format);
	        writer.write(doc);
	        writer.close();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
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
}
