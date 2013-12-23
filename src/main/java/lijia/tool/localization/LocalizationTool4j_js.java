package lijia.tool.localization;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class LocalizationTool4j_js {
	final static String prefix="js_sen_js";
	final static String resource="com.founder.enp.sendic.WebMessage";
	
	final static String target="d:/tmp";
	final static StringBuffer outputProper = new StringBuffer();
	final static String encoding = "utf-8";
	final static LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
	final static HashMap<String,String> map1 = new HashMap<String,String>();
	int ii=1;
	private String filepath ;
	final JFrame f = new JFrame();
	final TextField  label =new TextField(50);
	final java.awt.TextField key = new TextField(50);
	final java.awt.TextField value = new TextField(50);
	final Button button_ok = new Button("ok");
	final Button button_pass = new Button("pass");
	final Object o = new Object();
	File file ;
	static boolean isstop = true;
	public LocalizationTool4j_js(String filepath){
		this.filepath = filepath;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Font f =  new Font("宋体",Font.PLAIN,12);
        UIManager.put("Label.font",f);
        UIManager.put("Label.foreground",Color.black);
        UIManager.put("Button.font",f);
        UIManager.put("Menu.font",f);
        UIManager.put("MenuItem.font",f);
        UIManager.put("List.font",f);
        UIManager.put("CheckBox.font",f);
        UIManager.put("RadioButton.font",f);
        UIManager.put("ComboBox.font",f);
        UIManager.put("TextArea.font",f);
        UIManager.put("EditorPane.font",f);
        UIManager.put("ScrollPane.font",f);
        UIManager.put("ToolTip.font",f);
        UIManager.put("TextField.font",f);
        UIManager.put("TableHeader.font",f);
        UIManager.put("Table.font",f);
		
		
		LocalizationTool4j_js tool = new LocalizationTool4j_js("d:/tmp/sen.js");
		try {
			tool.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException{
		file = new File(filepath);
		initgui();
		String content = null;
		try {
			content = org.apache.commons.io.FileUtils.readFileToString(file, encoding);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		StringBuffer buffer = new StringBuffer();
		boolean isIn = false;
		for(int i=0; i<content.length(); i++) {
			char c = content.charAt(i);
			if(isChinese(c)){
				buffer.append(c);
				if(!isIn){
					isIn = true;
				}
			}else{
//				isstop = true;
				if(isIn){
					if(isNW(c)){
						buffer.append(c);
						continue;
					}
					if(map1.containsKey(buffer.toString())){
						translate(buffer.toString(),map1.get(buffer.toString()));
					}else{
						translate(buffer.toString(),prefix+"_msg"+ii);
					}
//					while(isstop){
//						try {
//							Thread.sleep(100L);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
					synchronized (o) {
					try {
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					}
					buffer.setLength(0);
					isIn = false;
				}
				System.out.print(c);
				outputProper.append(c);
				
			}
		}
		writeJsp();
		printPreportiesFile();
		System.exit(0);
		
	}
	
	private void writeJsp() throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(new File(new File(target),file.getName()+".target"), outputProper.toString(), encoding);
	}
	private void initgui() {
		f.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		label.setEnabled(false);
		f.add(label, "Center");
		f.add(key, "Center");
		f.add(value, "Center");
		f.add(button_ok, "Center");
		f.add(button_pass, "Center");
		button_pass.addActionListener(new PassListener());
		button_ok.addActionListener(new OKListener());
		
	}
	private void printPreportiesFile() throws IOException {
		outputProper.setLength(0);
		for(Map.Entry<String, String> entry:map.entrySet()){
			outputProper.append(entry.getKey()+"=En"+entry.getValue()+"\n");
			System.out.println(entry.getKey()+"=En"+entry.getValue());
		}
		
		org.apache.commons.io.FileUtils.writeStringToFile(new File(new File(target),"WebMessage.properties_bak"), outputProper.toString(), encoding);
	}
	
	public static boolean isChinese(char c) {  
		  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
  
            return true;  
  
        }  
  
        return false;  
  
    }  
//	private boolean isChinese(char c) {
//		int n = (int)c;
//		return 19968 <= n && n <40623;
//	}
	private boolean isNW(char c) {
		int n = (int)c;
		if(n<= 'z' && n>='a'){
			return true;
		}
		if(n<= 'Z' && n>='A'){
			return true;
		}
		if(n<= '9' && n>='0'){
			return true;
		}
		return n=='_';
	}
	private void translate(final String l,String k) {
		label.setText(l);
		key.setText(k);
		value.setText(l);
//		f.addWindowListener(new WindowListener(){
//
//			@Override
//			public void windowActivated(WindowEvent arg0) {
//				
//			}
//
//			@Override
//			public void windowClosed(WindowEvent arg0) {
//				f.dispose();
//			}
//
//			@Override
//			public void windowClosing(WindowEvent arg0) {
//				f.dispose();
//				
//			}
//
//			@Override
//			public void windowDeactivated(WindowEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void windowDeiconified(WindowEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void windowIconified(WindowEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void windowOpened(WindowEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});

		
		f.pack();
		f.setVisible(true);
	}
	
	 class  PassListener implements  ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.print(label.getText());
			f.setVisible(false);
//			isstop = false;
			synchronized (o) {
			o.notify();
			}
//			System.out.println("pass");
			
		}
		
	}
	 class  OKListener implements  ActionListener{
		
	
			public void actionPerformed(ActionEvent arg0) {
				if(map.containsKey(key.getText()) && !value.getText().equals(map.get(key.getText()))){
					int flag = JOptionPane.showConfirmDialog(f, null, "提示",
					          JOptionPane.ERROR_MESSAGE);
				}else{
//					iLocalization.getPrepertiseString('com.founder.enp.dicmgr.WebMessage','new_source_jsp_msg2')
					System.out.print("iLocalization.getPrepertiseString('"+resource+"','"+key.getText()+"')");
					outputProper.append("iLocalization.getPrepertiseString('"+resource+"','"+key.getText()+"')");
					if(!(map.containsKey(key.getText()) && value.getText().equals(map.get(key.getText())))){
						map.put(key.getText(), value.getText());
						map1.put(value.getText(), key.getText());
					}
					if(key.getText().equals(prefix+"_msg"+ii)){
						ii++;
					}
					f.setVisible(false);
//					isstop = false;
					synchronized (o) {
					o.notify();
					}
				}
				
					

//					  if (flag == JOptionPane.YES_OPTION)
//					  {
//					  }	
//				System.out.println("ok");
			}
		
	}
	
}
