package mainbody;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


import javax.swing.JTextField;
import javax.swing.JLabel;


public class channelFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField priceFiled;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chacfg();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	public static void chacfg() {
		channelFrame frame = new channelFrame();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public channelFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 200, 300);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		newfolder("C:\\inhandTool\\config\\channeltemp");
		JLabel lblNewLabel_1 = new JLabel("一键配置前请同步平台商品");
		lblNewLabel_1.setBounds(10, 150, 164, 15);
		lblNewLabel_1.setForeground(Color.ORANGE);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("一键改所有货道价格");
		btnNewButton_1.setSize(164, 40);
		btnNewButton_1.setLocation(10, 211);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			Process p;
			@Override
			public void mouseClicked(MouseEvent e) {
				newfolder("C:\\inhandTool\\config\\channeltemp");
				Pattern pattern = Pattern.compile("^[\\+\\-]?[\\d]+(\\.[\\d]+)?$");
		        Matcher isNum = pattern.matcher(priceFiled.getText());
		           if( !isNum.matches() ){
		        	   priceFiled.setText("100");
		           }
		        String command1 = "cmd.exe /c adb pull sdcard/inbox/config/channel_cfg.xml C:\\inhandTool\\config\\channeltemp";
		        
				try {
					p = Runtime.getRuntime().exec(command1);
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e3) {
					e3.printStackTrace();
				}
				
				Document channeldocument = null;
				try {
					channeldocument = new SAXReader().read(new File("C:\\inhandTool\\config\\channeltemp\\channel_cfg.xml"));
				} catch (DocumentException e2) {
					e2.printStackTrace();
				}
				//Document channeldocument=load("D:/1/test.xml");
				List<Node>channelPricelist=channeldocument.selectNodes("//channel//@price");
				for(int i=0;i<channelPricelist.size();i++){
					//int price = Integer.parseInt(priceFiled.getText())*100;
					channelPricelist.get(i).setText(priceFiled.getText());
				}
		      //指定文件输出的位置
		        FileOutputStream out;
				try {
					out = new FileOutputStream("C:\\inhandTool\\config\\channeltemp\\channel_cfg.xml");
			        
			        // 指定文本的写出的格式：
			        OutputFormat format=OutputFormat.createPrettyPrint();   //漂亮格式：有空格换行
			        format.setEncoding("UTF-8");
			        
			        //1.创建写出对象
			        XMLWriter writer=new XMLWriter(out,format);
			           
			        //2.写出Document对象
			        writer.write(channeldocument);
			        
			        //3.关闭流
			        writer.close();
			        String command2 = "cmd.exe /c adb push C:\\inhandTool\\config\\channeltemp\\channel_cfg.xml sdcard/inbox/config";
			        p = Runtime.getRuntime().exec(command2);
					p.waitFor();
					p.destroy();
					RestartAPP();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton button = new JButton("一键配置货道商品");
		button.setSize(164, 80);
		button.setLocation(10, 10);
		button.addMouseListener(new MouseAdapter() {
			Process p;
			@Override
			public void mouseClicked(MouseEvent e) {
				newfolder("C:\\inhandTool\\config\\channeltemp");
				String command1 = "cmd.exe /c adb pull sdcard/inbox/config/smartvm_cfg.xml C:\\inhandTool\\config\\channeltemp";
				String command2 = "cmd.exe /c adb pull sdcard/inbox/config/goods_info.xml C:\\inhandTool\\config\\channeltemp";
				try {
					p = Runtime.getRuntime().exec(command1);
					p.waitFor();
					p.destroy();
					p = Runtime.getRuntime().exec(command2);
					p.waitFor();
					p.destroy();
				} catch (IOException | InterruptedException e2) {
					e2.printStackTrace();
				}

				//1.创建文档
		        Document doc=DocumentHelper.createDocument();
		        //2.添加标签和属性
		        Element cabinetsElem=doc.addElement("cabinets");
		        cabinetsElem.addAttribute("version", "1.1");
				Document smartvmdocument=load("C:\\inhandTool\\config\\channeltemp\\smartvm_cfg.xml");
				Document goodsdocument=load("C:\\inhandTool\\config\\channeltemp\\goods_info.xml");
				List<Node>smartvmlist=smartvmdocument.selectNodes("//cabinet[@id]");
				//System.out.print(smartvmlist.size());
				List<Node>goodslist=goodsdocument.selectNodes("//goods");
				int goodscount = goodslist.size();
				int cursor = 0;
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
					String[] goodsIdList = new String[goodscount];
					String[] goodsPriceList = new String[goodscount];
					for(int i2=0;i2<goodscount;i2++){
						if(i2<channelNumber){
							channelList[i2] = smartlist.get(i2).getText().toString();
						}
			            goodsIdList[i2] = ((Element) goodslist.get(i2)).attributeValue("id").toString();
			            goodsPriceList[i2] = ((Element) goodslist.get(i2)).attributeValue("price").toString();
					}
					cursor += channelNumber;
					if (cursor>=goodscount) {
						cursor = 0;
					}
					insertGoods(cabinetsElem,vmnumber,goodsIdList,vmType,channelList,goodsPriceList,cursor);
				}
				//指定文件输出的位置
		        FileOutputStream out;
				try {
					out = new FileOutputStream("C:\\inhandTool\\config\\channeltemp\\channel_cfg.xml");
			        
			        // 指定文本的写出的格式：
			        OutputFormat format=OutputFormat.createPrettyPrint();   //漂亮格式：有空格换行
			        format.setEncoding("UTF-8");
			        
			        //1.创建写出对象
			        XMLWriter writer=new XMLWriter(out,format);
			           
			        //2.写出Document对象
			        writer.write(doc);
			        
			        //3.关闭流
			        writer.close();
			        String command3 = "cmd.exe /c adb push C:\\inhandTool\\config\\channeltemp\\channel_cfg.xml sdcard/inbox/config";
			        p = Runtime.getRuntime().exec(command3);
					p.waitFor();
					p.destroy();
					RestartAPP();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(button);
		
		priceFiled = new JTextField();
		priceFiled.setBounds(57, 180, 73, 21);
		contentPane.add(priceFiled);
		priceFiled.setColumns(10);

		
		JLabel lblNewLabel = new JLabel("价格：");
		lblNewLabel.setBounds(10, 183, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("分");
		label.setBounds(130, 183, 54, 15);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("一键清空货道配置");
		btnNewButton.addMouseListener(new MouseAdapter() {
			Process p;
			@Override
			public void mouseClicked(MouseEvent e) {
				//1.创建文档
		        Document doc=DocumentHelper.createDocument();
		        //2.添加标签和属性
		        Element cabinetsElem=doc.addElement("cabinets");
		        cabinetsElem.addAttribute("version", "1.1");
		      //指定文件输出的位置
		        FileOutputStream out;
				try {
					out = new FileOutputStream("C:\\inhandTool\\config\\channeltemp\\channel_cfg.xml");
			        
			        // 指定文本的写出的格式：
			        OutputFormat format=OutputFormat.createPrettyPrint();   //漂亮格式：有空格换行
			        format.setEncoding("UTF-8");
			        
			        //1.创建写出对象
			        XMLWriter writer=new XMLWriter(out,format);
			           
			        //2.写出Document对象
			        writer.write(doc);
			        
			        //3.关闭流
			        writer.close();
			        String command3 = "cmd.exe /c adb push C:\\inhandTool\\config\\channeltemp\\channel_cfg.xml sdcard/inbox/config";
			        p = Runtime.getRuntime().exec(command3);
					p.waitFor();
					p.destroy();
					RestartAPP();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 100, 164, 40);
		contentPane.add(btnNewButton);
	}

	public static void insertGoods(Element cabinetsElem,String vmnumber,String[] goodsidList, String machineType,String[] channelList,String[] goodsPriceList,int cursor){
        Element cabinetElem=cabinetsElem.addElement("cabinet");
        cabinetElem.addAttribute("machineType", machineType);
        cabinetElem.addAttribute("number", vmnumber);
        int idnum = cursor;
        for(String i:channelList){
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
