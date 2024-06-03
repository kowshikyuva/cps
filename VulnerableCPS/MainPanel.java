package com;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JFrame;
public class MainPanel extends JFrame{
	JLabel l1,l2,l3;
	JLabel title;
	JButton b1;
	JTextField tf1,tf2;
	Font f1;
	JTable table;
	static DefaultTableModel dtm;
	JScrollPane jsp;
public MainPanel(){
	setLayout(null);

	title = new JLabel("<html><body><center>The Vulnerability of Cyber-Physical System Under Stealthy Attacks</center></body></html>");
	title.setFont(new Font("Times New Roman",Font.BOLD,18));
	add(title);
	title.setBounds(80,20,1000,50);
	
	f1 = new Font("Times New Roman",Font.BOLD,14);
	l1 = new JLabel("CPS Simulation Parameters Defintion Screen");
	l1.setBounds(80,70,400,30);
	add(l1);
	l1.setFont(f1);

	l2 = new JLabel("Sensor Size");
	l2.setFont(f1);
	l2.setBounds(100,120,150,30);
	add(l2);

	tf1 = new JTextField();
	tf1.setBounds(200,120,80,30);
	add(tf1);
	tf1.setFont(f1);

	b1 = new JButton("Save Parameters");
	b1.setBounds(100,220,150,30);
	add(b1);
	b1.setFont(f1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			String size = tf1.getText();
			if(size.length() <= 0 || size == null){
				JOptionPane.showMessageDialog(null,"Please enter sensor network size");
				tf1.grabFocus();
				return;
			}
			int s = 0;
			try{
				s = Integer.parseInt(size.trim());
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null,"Sensor size must be numeric only");
				tf1.grabFocus();
				return;
			}
			clearTable();
			for(int i=1;i<=s;i++){
				Object row[] = {"S"+i,"-","-"};
				dtm.addRow(row);
			}
			SensorPanel dp = new SensorPanel(s);
			dp.setExtendedState(JFrame.MAXIMIZED_BOTH);
			dp.setVisible(true);
		}
	});
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(int row_no,int column_no){
			return false;
		}
	};
	table = new JTable(dtm);
	table.getTableHeader().setFont(new Font("Courier New",Font.BOLD,14));
	table.setFont(new Font("Courier New",Font.BOLD,13));
	table.setRowHeight(30);
	jsp = new JScrollPane(table);
	jsp.setBounds(10,270,600,300);
	dtm.addColumn("Sensor ID");
	dtm.addColumn("Sensor X Location");
	dtm.addColumn("Sensor Y Location");
	add(jsp);
}
public void clearTable(){
	for(int i=dtm.getRowCount()-1;i>=0;i--){
		dtm.removeRow(i);
	}
}
public static void main(String a[])throws Exception{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	MainPanel mp = new MainPanel();
	mp.setVisible(true);
	mp.setExtendedState(JFrame.MAXIMIZED_BOTH);
}
}