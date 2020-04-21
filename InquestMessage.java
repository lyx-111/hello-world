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
		super(f,"��ѯ�Ի���",false);
		Container con=getContentPane();
		con.setLayout(new FlowLayout());
		this.file=file;
		txlnumber=new JTextField(5);
		tname=new JTextField(5);
		inquesNo=new Button("����Ų�ѯ");
		inquesNo.addActionListener(this);
		inquesName=new Button("��������ѯ");
		inquesName.addActionListener(this);
		inquesAll=new Button("��ѯȫ��");
		inquesAll.addActionListener(this);
		con.add(new Label("����Ҫ��ѯ�����"));
		con.add(txlnumber);
		con.add(inquesNo);
		con.add(new Label("����Ҫ��ѯ������"));
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
					show.setText("��ţ�"+basinfor.getXlnumber()+" "+"������"+basinfor.getName()+" "
					+"�绰���룺"+basinfor.getPhnumber()+" "+"�������䣺"+basinfor.getEmail()+" "
					+"��λ��"+basinfor.getDanwei()+" "+"ְ��"+basinfor.getZhiwu());
				}
				else
				{
					String warning="����Ų����ڣ�";
					JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				String warning="����������ţ�";
				JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
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
					show.setText("��ţ�"+basinfor.getXlnumber()+" "+"������"+basinfor.getName()+" "
					+"�绰���룺"+basinfor.getPhnumber()+" "+"�������䣺"+basinfor.getEmail()+" "
					+"��λ��"+basinfor.getDanwei()+" "+"ְ��"+basinfor.getZhiwu());
				}
				else
				{
					String warning="�����������ڣ�";
					JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				String warning="��������������";
				JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		else if(e.getSource()==inquesAll)
		{
			Enumeration enumm=messagetable.elements();
			while(enumm.hasMoreElements())
			{
				TXLBasInfor basinfor=(TXLBasInfor)enumm.nextElement();
				show.append("��ţ�"+basinfor.getXlnumber()+" "+"������"+basinfor.getName()+" "
					+"�绰���룺"+basinfor.getPhnumber()+" "+"�������䣺"+basinfor.getEmail()+" "
					+"��λ��"+basinfor.getDanwei()+" "+"ְ��"+basinfor.getZhiwu()+"\n");
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