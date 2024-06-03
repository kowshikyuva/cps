package com;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileWriter;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import org.jfree.ui.RefineryUtilities;
import javax.swing.JTextField;
import java.util.Random;
import javax.swing.JComboBox;
import java.awt.Cursor;
import java.util.ArrayList;
public class SensorPanel extends JFrame{
	Simulator node;
	JPanel p1,p2;
	int left,top;
	Font f1;
	JButton b1,b2,b3,b4,b5;
	JLabel l1,l2;
	static JLabel status;
	int size;
	JComboBox c1;
	boolean running = false; 
	SendData sd;
public SensorPanel(int s){
	super("CPS Sensors Simulation Screen");
	size = s;
	
	f1 = new Font("Times New Roman",Font.BOLD,14);
	node = new Simulator(30);
	p1 = new JPanel();
	p1.setLayout(new BorderLayout());
	p1.add(node,BorderLayout.CENTER);
	p1.setBackground(new Color(128,0,0));
	getContentPane().add(p1,BorderLayout.CENTER);
	
	p2 = new JPanel();
	p2.setBackground(Color.white);
	p2.setLayout(new MigLayout("wrap 2")); 

	l1 = new JLabel("Sensors ID");
	l1.setFont(f1);
	p2.add(l1,"split 3");
	c1 = new JComboBox();
	c1.setFont(f1);
	p2.add(c1);
	for(int i=1;i<=size;i++){
		c1.addItem("D"+i);
	}

	status = new JLabel("Status:");
	status.setFont(f1);
	p2.add(status,"span,wrap");
	
	b1 = new JButton("Sensors Placements");
	p2.add(b1,"span,split 3");
	b1.setFont(f1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			SensorPlacement.randomNodes(size,600,500,node);
			node.option=0;
			node.repaint();
		}
	});

	b2 = new JButton("Start Simulation");
	p2.add(b2);
	b2.setFont(f1);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			sd = new SendData(node,true);
		}
	});

	b3 = new JButton("Stop Simulation");
	p2.add(b3);
	b3.setFont(f1);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			sd.setRunning(false);
		}
	});

	b4 = new JButton("Strictly & Stealthy Attack Detections");
	p2.add(b4);
	b4.setFont(f1);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			int total = 0;
			int normal = 0;
			int strict = 0;
			int stealthy = 0;
			for(int i=0;i<node.device.size();i++) {
				Sensor sensor  = node.device.get(i);
				total = total + sensor.getTotal();
				normal = normal + sensor.getNormal();
				strict = strict + sensor.getStrict();
				stealthy = stealthy + sensor.getStealthy();
			}
			Chart chart1 = new Chart("Strictly & Stealthy Attack Detections",total,normal,strict,stealthy);
			chart1.pack();
			RefineryUtilities.centerFrameOnScreen(chart1);
			chart1.setVisible(true);
			
		}
	});


	b5 = new JButton("Exit");
	p2.add(b5);
	b5.setFont(f1);
	b5.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			System.exit(0);
		}
	});

	
	getContentPane().add(p2,BorderLayout.NORTH);
	left = getInsets().left;
    top = getInsets().top;
		
}
}