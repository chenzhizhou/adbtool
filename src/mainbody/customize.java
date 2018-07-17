package mainbody;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxEditor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class customize extends JFrame {

	private static JFrame frame;
	private JPanel contentPane;
	private JTextField addTextField;
	Document document = null;
	String savePath = "";
	private JComboBox<String> exsitItem;
	private JLabel lblNewLabel_1;
	private JTextField tag_textField;
	private JLabel lblTag;
	private JLabel lblNewLabel_2;
	private JButton delete_item;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new customize("serveraddress");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public customize(String type) {
		initCFGxml();
		switch (type) {
		case "serveraddress":
			setTitle("��ӷ�������ַ");
			savePath = "C:\\inhandTool\\serveraddress.xml";
			try {
				document = new SAXReader().read(new File(savePath));
			} catch (DocumentException e2) {
				initCFGxml();
			}
			break;

		case "orgname":
			setTitle("��ӻ�������");
			savePath = "C:\\inhandTool\\orgname.xml";
			try {
				document = new SAXReader().read(new File(savePath));
			} catch (DocumentException e2) {
				initCFGxml();
			}
			break;
		case "log_tag":
			setTitle("�����־tag");
			savePath = "C:\\inhandTool\\log_tag.xml";
			try {
				document = new SAXReader().read(new File(savePath));
			} catch (DocumentException e2) {
				initCFGxml();
			}
			break;
		default:
			break;
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 300, 338, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTag = new JLabel("Tag:");
		lblTag.setBounds(10, 67, 54, 15);
		contentPane.add(lblTag);
		lblTag.setVisible(false);
		
		tag_textField = new JTextField();
		tag_textField.setBounds(59, 61, 150, 21);
		tag_textField.setVisible(false);
		contentPane.add(tag_textField);
		tag_textField.setColumns(10);
		
		addTextField = new JTextField();
		addTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				addTextField.setText("");
			}
		});
		addTextField.setBounds(59, 30, 150, 21);
		contentPane.add(addTextField);
		if (type.equals("log_tag")) {
			addTextField.setText("����������");
			lblTag.setVisible(true);
			tag_textField.setVisible(true);
		}
		addTextField.setColumns(20);
		
		JButton add = new JButton("ADD+");
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fileName = "";
				File file = null;
				if (type.equals("serveraddress")) {
					fileName = "C:\\inhandTool\\serveraddress.xml";
				}
				if (type.equals("orgname")) {
					fileName = "C:\\inhandTool\\orgname.xml";
				}
				if (type.equals("log_tag")) {
					fileName = "C:\\inhandTool\\log_tag.xml";
				}
				Document itemdocument = null;
				file = new File(fileName);
				try {
					itemdocument = new SAXReader().read(file);
				} catch (DocumentException e8) {
					// TODO Auto-generated catch block
					e8.printStackTrace();
				}
				
				if (addTextField.getText().equals("") && !type.equals("log_tag")) {
					JOptionPane.showMessageDialog(null, "�ղ��ܱ����","Add failed", JOptionPane.WARNING_MESSAGE);
				}
				else if (tag_textField.getText().equals("") && type.equals("log_tag")) {
					JOptionPane.showMessageDialog(null, "�ղ��ܱ����","Add failed", JOptionPane.WARNING_MESSAGE);
				}
				else if (addTextField.getText().equals("") && type.equals("log_tag")) {
					JOptionPane.showMessageDialog(null, "�ղ��ܱ����","Add failed", JOptionPane.WARNING_MESSAGE);
				}
				else{
					if (type.equals("log_tag")) {
						Element rootElm = itemdocument.getRootElement();
						Element itemsElm=rootElm.element("items");
						Element items = itemsElm.addElement("item");
						items.addText(addTextField.getText()+":"+tag_textField.getText());
					}
					else {
						Element rootElm = itemdocument.getRootElement();
						Element itemsElm=rootElm.element("items");
						Element items = itemsElm.addElement("item");
						items.addText(addTextField.getText());
					}
			        FileOutputStream out;
					try {
						out = new FileOutputStream(fileName);
				        OutputFormat format=OutputFormat.createPrettyPrint();   //Ư����ʽ���пո���
				        format.setEncoding("UTF-8");
				        XMLWriter writer=new XMLWriter(out,format);
				        writer.write(itemdocument);
				        writer.close();
				        Component tip = null;
						JOptionPane.showMessageDialog(tip, "������","Add succeeded", JOptionPane.WARNING_MESSAGE);
						addressReadnow(exsitItem,type);
					}
					catch(IOException e1){
						e1.printStackTrace();
					}
					
				}


			}
		});
		add.setBounds(219, 19, 93, 63);
		contentPane.add(add);
		
		exsitItem = new JComboBox<String>();
		exsitItem.getEditor();
		exsitItem.setBounds(129, 161, 118, 21);
		contentPane.add(exsitItem);
		
		JButton clear = new JButton("��ջָ�Ĭ��");
		clear.setFont(new Font("����", Font.PLAIN, 10));
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fileName1 = "C:\\inhandTool\\serveraddress.xml";
		    	String fileName2 = "C:\\inhandTool\\orgname.xml";
		    	String fileName3 = "C:\\inhandTool\\log_tag.xml";
		    	if (type.equals("serveraddress")) {
		    		File file = new File(fileName1);
		    		file.delete();
				}
		    	if (type.equals("orgname")) {
		    		File file = new File(fileName2);
		    		file.delete();
				}
		    	if (type.equals("log_tag")) {
		    		File file = new File(fileName3);
		    		file.delete();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				initCFGxml();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Component tip = null;
				JOptionPane.showMessageDialog(tip, "�ѻָ�Ĭ��","�ָ�Ĭ��", JOptionPane.WARNING_MESSAGE);
				if (type.equals("serveraddress")) {
					addressReadnow(exsitItem,"serveraddress");
				}
				if (type.equals("orgname")) {
					addressReadnow(exsitItem,"orgname");
				}
				if (type.equals("log_tag")) {
					addressReadnow(exsitItem,"log_tag");
				}
			}
		});
		clear.setBounds(10, 158, 109, 29);
		contentPane.add(clear);
		
		JLabel lblNewLabel = new JLabel("������");
		lblNewLabel.setBounds(10, 33, 54, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("���У�");
		lblNewLabel_1.setBounds(129, 142, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Tip��Ҳ����C:\\inhandTool���޸������ļ������޸�");
		lblNewLabel_2.setBounds(10, 117, 302, 15);
		contentPane.add(lblNewLabel_2);
		
		delete_item = new JButton(" X ");
		delete_item.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String itemStr = exsitItem.getSelectedItem().toString();
				String fileName = "";
				File file = null;
				if (type.equals("serveraddress")) {
					fileName = "C:\\inhandTool\\serveraddress.xml";
				}
				if (type.equals("orgname")) {
					fileName = "C:\\inhandTool\\orgname.xml";
				}
				if (type.equals("log_tag")) {
					fileName = "C:\\inhandTool\\log_tag.xml";
				}
				Document itemdocument = null;
				file = new File(fileName);
				try {
					itemdocument = new SAXReader().read(file);
				} catch (DocumentException e8) {
					// TODO Auto-generated catch block
					e8.printStackTrace();
				}
		    	List<Node>itemlist=itemdocument.selectNodes("//item[text()='"+itemStr+"']");
		    	System.out.print(itemlist.get(0).getText());
		    	Element elea = (Element) itemlist.get(0);
		    	elea.getParent().remove(elea);
		        FileOutputStream out;
				try {
					out = new FileOutputStream(fileName);
			        OutputFormat format=OutputFormat.createPrettyPrint();   //Ư����ʽ���пո���
			        format.setEncoding("UTF-8");
			        XMLWriter writer=new XMLWriter(out,format);
			        writer.write(itemdocument);
			        writer.close();
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�","delete succeeded", JOptionPane.WARNING_MESSAGE);
					addressReadnow(exsitItem,type);
				}
				catch(IOException e1){
					e1.printStackTrace();
				}
			}
		});
		delete_item.setForeground(Color.RED);
		delete_item.setBounds(257, 160, 55, 23);
		contentPane.add(delete_item);
				
		
		if (type.equals("serveraddress")) {
			addressReadnow(exsitItem,"serveraddress");
		}
		if (type.equals("orgname")) {
			addressReadnow(exsitItem,"orgname");
		}
		if (type.equals("log_tag")) {
			addressReadnow(exsitItem,"log_tag");
		}

		
	}
	public void addressReadnow(JComboBox<String> exsitItem,String type){
		String now = "";
		Document document = null;
		try {
			document = new SAXReader().read(new File(savePath));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (type.equals("serveraddress")) {
			now = tool.serverAddress.getSelectedItem().toString();
			tool.serverAddress.removeAllItems();
			tool.serverAddress.addItem(now);
		}
		if (type.equals("orgname")) {
			now = tool.orgName.getSelectedItem().toString();
			tool.orgName.removeAllItems();
			tool.orgName.addItem(now);
		}
		if (type.equals("log_tag")) {
			now = tool.commonTagscomboBox.getSelectedItem().toString();
			tool.commonTagscomboBox.removeAllItems();
			tool.commonTagscomboBox.addItem(now);
		}
		exsitItem.removeAllItems();
		List<Node>itemlist=document.selectNodes("//item");
		for(int i=0 ;i<itemlist.size();i++){
			exsitItem.addItem(itemlist.get(i).getStringValue());
			if (type.equals("serveraddress")) {
				tool.serverAddress.addItem(itemlist.get(i).getStringValue());
			}
			if (type.equals("orgname")) {
				tool.orgName.addItem(itemlist.get(i).getStringValue());
			}
			if (type.equals("log_tag")) {
				tool.commonTagscomboBox.addItem(itemlist.get(i).getStringValue());
			}
		}
	}
	public static void addressRead(JComboBox<String> serverAddress, String type) {
		String path=null;
		switch (type) {
		case "serveraddress":
			path = "C:\\inhandTool\\serveraddress.xml";
			break;

		case "orgname":
			path = "C:\\inhandTool\\orgname.xml";
			break;
		case "log_tag":
			path = "C:\\inhandTool\\log_tag.xml";
			break;
		default:
			break;
		}
		Document itemdocument = null;
		try {
			itemdocument = new SAXReader().read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Node>itemlist=itemdocument.selectNodes("//item");
		for(int i=0 ;i<itemlist.size();i++){
			serverAddress.addItem(itemlist.get(i).getStringValue());
		}
	}
    public static void initCFGxml(){
    	String fileName1 = "C:\\inhandTool\\serveraddress.xml";
    	String fileName2 = "C:\\inhandTool\\orgname.xml";
    	String fileName3 = "C:\\inhandTool\\log_tag.xml";
    	File file1 = new File(fileName1);
    	File file2 = new File(fileName2);
    	File file3 = new File(fileName3);
        if (!file1.exists()){
        	//1.�����ĵ�
	        Document doc=DocumentHelper.createDocument();
	        //2.��ӱ�ǩ������
	        Element rootElem=doc.addElement("smartvm");
	        rootElem.addAttribute("version", "1.0");
	        Element itemsElm=rootElem.addElement("items");
	        Element item1 = itemsElm.addElement("item");
	        item1.addText("182.150.21.232:10081");
	        Element item2 = itemsElm.addElement("item");
			item2.addText("40.125.208.200");
			Element item3 = itemsElm.addElement("item");
			item3.addText("mall.inhand.com.cn");
			//ָ���ļ������λ��
	        FileOutputStream out;
			try {
				out = new FileOutputStream(fileName1);
		        
		        // ָ���ı���д���ĸ�ʽ��
		        OutputFormat format=OutputFormat.createPrettyPrint();   //Ư����ʽ���пո���
		        format.setEncoding("UTF-8");
		        
		        //1.����д������
		        XMLWriter writer=new XMLWriter(out,format);
		           
		        //2.д��Document����
		        writer.write(doc);
		        
		        //3.�ر���
		        writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        }
        if (!file2.exists()){
        	//1.�����ĵ�
	        Document doc=DocumentHelper.createDocument();
	        //2.��ӱ�ǩ������
	        Element rootElem=doc.addElement("smartvm");
	        rootElem.addAttribute("version", "1.0");
	        Element itemsElm=rootElem.addElement("items");
	        Element item1 = itemsElm.addElement("item");
	        item1.addText("lizy_inhand");
			//ָ���ļ������λ��
	        FileOutputStream out;
			try {
				out = new FileOutputStream(fileName2);
		        
		        // ָ���ı���д���ĸ�ʽ��
		        OutputFormat format=OutputFormat.createPrettyPrint();   //Ư����ʽ���пո���
		        format.setEncoding("UTF-8");
		        
		        //1.����д������
		        XMLWriter writer=new XMLWriter(out,format);
		           
		        //2.д��Document����
		        writer.write(doc);
		        
		        //3.�ر���
		        writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        }
        if (!file3.exists()){
        	//1.�����ĵ�
	        Document doc=DocumentHelper.createDocument();
	        //2.��ӱ�ǩ������
	        Element rootElem=doc.addElement("smartvm");
	        rootElem.addAttribute("version", "1.0");
	        Element itemsElm=rootElem.addElement("items");
	        Element item = itemsElm.addElement("item");
	        item.addText("");
	        Element item1 = itemsElm.addElement("item");
	        item1.addText("���:Player PlayerPolicy PlayerController VideoPlayer AdService");
	        Element item2 = itemsElm.addElement("item");
	        item2.addText("����:WSDeliverGoods ObtainQRIndentTask");
	        Element item3 = itemsElm.addElement("item");
	        item3.addText("��������:WSConnectTask Steper ObtainTokenTask");
	        Element item4 = itemsElm.addElement("item");
	        item4.addText("Զ������:ReportAdsTask DeviceManagerService");
			//ָ���ļ������λ��
	        FileOutputStream out;
			try {
				out = new FileOutputStream(fileName3);
		        
		        // ָ���ı���д���ĸ�ʽ��
		        OutputFormat format=OutputFormat.createPrettyPrint();   //Ư����ʽ���пո���
		        format.setEncoding("UTF-8");
		        
		        //1.����д������
		        XMLWriter writer=new XMLWriter(out,format);
		           
		        //2.д��Document����
		        writer.write(doc);
		        
		        //3.�ر���
		        writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        }
    }
}
