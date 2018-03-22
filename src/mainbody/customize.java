package mainbody;

import java.awt.BorderLayout;
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

public class customize extends JFrame {

	private static JFrame frame;
	private JPanel contentPane;
	private JTextField addTextField;
	private JTextField exsitConfigs;
	private ComboBoxEditor exsitItemeditor;
	Document document = null;
	String savePath = "";
	private JComboBox exsitItem;
	private JLabel lblNewLabel_1;

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
		switch (type) {
		case "serveraddress":
			setTitle("��ӷ�������ַ");
			savePath = "C:\\inhandTool\\serveraddress.xml";
			try {
				document = new SAXReader().read(new File(savePath));
			} catch (DocumentException e2) {
				e2.printStackTrace();
			}
			break;

		case "orgname":
			setTitle("��ӻ�������");
			savePath = "C:\\inhandTool\\orgname.xml";
			try {
				document = new SAXReader().read(new File(savePath));
			} catch (DocumentException e2) {
				e2.printStackTrace();
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
		
		addTextField = new JTextField();
		addTextField.setBounds(59, 30, 150, 21);
		contentPane.add(addTextField);
		addTextField.setColumns(20);
		
		JButton add = new JButton("ADD+");
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (addTextField.getText().equals("")) {
					Component tip = null;
					JOptionPane.showMessageDialog(tip, "�ղ��ܱ����","Add failed", JOptionPane.WARNING_MESSAGE);
				}
				else{
					Element rootElm = document.getRootElement();
					Element itemsElm=rootElm.element("items");
					Element items = itemsElm.addElement("item");
					items.addText(addTextField.getText());
					
					
					//ָ���ļ������λ��
			        FileOutputStream out;
					try {
						out = new FileOutputStream(savePath);
				        
				        // ָ���ı���д���ĸ�ʽ��
				        OutputFormat format=OutputFormat.createPrettyPrint();   //Ư����ʽ���пո���
				        format.setEncoding("UTF-8");
				        
				        //1.����д������
				        XMLWriter writer=new XMLWriter(out,format);
				           
				        //2.д��Document����
				        writer.write(document);
				        
				        //3.�ر���
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
		add.setBounds(219, 19, 93, 42);
		contentPane.add(add);
		
		exsitItem = new JComboBox();
		exsitItemeditor = exsitItem.getEditor();
		exsitItem.setBounds(162, 162, 150, 21);
		contentPane.add(exsitItem);
		
		JButton clear = new JButton("��ջָ�Ĭ��");
		clear.setFont(new Font("����", Font.PLAIN, 10));
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fileName1 = "C:\\inhandTool\\serveraddress.xml";
		    	String fileName2 = "C:\\inhandTool\\orgname.xml";
		    	if (type.equals("serveraddress")) {
		    		File file = new File(fileName1);
		    		file.delete();
				}
		    	if (type.equals("orgname")) {
		    		File file = new File(fileName2);
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
			}
		});
		clear.setBounds(10, 158, 109, 29);
		contentPane.add(clear);
		
		exsitConfigs = new JTextField();
		exsitConfigs.setBounds(162, 134, 150, 21);
		contentPane.add(exsitConfigs);
		exsitConfigs.setColumns(10);
		exsitConfigs.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("������");
		lblNewLabel.setBounds(10, 33, 54, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("���У�");
		lblNewLabel_1.setBounds(123, 168, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		if (type.equals("serveraddress")) {
			addressReadnow(exsitItem,"serveraddress");
		}
		if (type.equals("orgname")) {
			addressReadnow(exsitItem,"orgname");
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
    	File file1 = new File(fileName1);
    	File file2 = new File(fileName2);
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
			item2.addText("121.42.28.70");
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
    }
}
