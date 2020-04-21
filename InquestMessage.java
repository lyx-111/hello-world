import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class InquestMessage extends JDialog implements ActionListener
{
	Hashtable messagetable=null;
	JTextField txlnumber,tname;
	Button inquesNo,inquesAll,inquesName;
	JTextArea show=new JTextArea(10,48);
	FileInputStream inOne=null;
	ObjectInputStream inTwo=null;
	File file=null;
	public InquestMessage(JFrame f,File file)
	{
		super(f,"查询对话框",false);
		Container con=getContentPane();
		con.setLayout(new FlowLayout());
		this.file=file;
		txlnumber=new JTextField(5);
		tname=new JTextField(5);
		inquesNo=new Button("按序号查询");
		inquesNo.addActionListener(this);
		inquesName=new Button("按姓名查询");
		inquesName.addActionListener(this);
		inquesAll=new Button("查询全部");
		inquesAll.addActionListener(this);
		con.add(new Label("输入要查询的序号"));
		con.add(txlnumber);
		con.add(inquesNo);
		con.add(new Label("输入要查询的姓名"));
		con.add(tname);
		con.add(inquesName);
		con.add(inquesAll);
		con.add(show);
		setBounds(100,200,660,270);
		addWindowListener(new WindowAdapter()
		                  {
		                  	public void windowClosing(WindowEvent e)
		                  	{
		                  		setVisible(false);
		                  	}
		                  });
	}
	public void actionPerformed(ActionEvent e)
	{
		show.setText(null);
		readHashtable();
		if(e.getSource()==inquesNo)
		{
			String number="";
			number=txlnumber.getText();
			if(number.length()>0)
			{
				if(messagetable.containsKey(number))
				{
					TXLBasInfor basinfor=(TXLBasInfor)messagetable.get(number);
					show.setText("序号："+basinfor.getXlnumber()+" "+"姓名："+basinfor.getName()+" "
					+"电话号码："+basinfor.getPhnumber()+" "+"电子邮箱："+basinfor.getEmail()+" "
					+"单位："+basinfor.getDanwei()+" "+"职务："+basinfor.getZhiwu());
				}
				else
				{
					String warning="该序号不存在！";
					JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				String warning="必须输入序号！";
				JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if(e.getSource()==inquesName)
		{
			String name="";
			name=tname.getText();
			if(name.length()>0)
			{
				if(messagetable.containsKey(name))
				{
					TXLBasInfor basinfor=(TXLBasInfor)messagetable.get(name);
					show.setText("序号："+basinfor.getXlnumber()+" "+"姓名："+basinfor.getName()+" "
					+"电话号码："+basinfor.getPhnumber()+" "+"电子邮箱："+basinfor.getEmail()+" "
					+"单位："+basinfor.getDanwei()+" "+"职务："+basinfor.getZhiwu());
				}
				else
				{
					String warning="该姓名不存在！";
					JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				String warning="必须输入姓名！";
				JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		else if(e.getSource()==inquesAll)
		{
			Enumeration enumm=messagetable.elements();
			while(enumm.hasMoreElements())
			{
				TXLBasInfor basinfor=(TXLBasInfor)enumm.nextElement();
				show.append("序号："+basinfor.getXlnumber()+" "+"姓名："+basinfor.getName()+" "
					+"电话号码："+basinfor.getPhnumber()+" "+"电子邮箱："+basinfor.getEmail()+" "
					+"单位："+basinfor.getDanwei()+" "+"职务："+basinfor.getZhiwu()+"\n");
			}
		}
		
	}
	
	public void readHashtable()
	{
		try{
			inOne=new FileInputStream(file);
			inTwo=new ObjectInputStream(inOne);
			messagetable=(Hashtable)inTwo.readObject();
			inOne.close();
			inTwo.close();
		}
		catch(Exception ee){}
	}
}