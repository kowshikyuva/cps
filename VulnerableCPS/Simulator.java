package com;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;

public class Simulator extends JComponent{
	String col="empty";
	public int option=0;
	public ArrayList<Sensor> device = new ArrayList<Sensor>();
	float dash1[] = {10.0f};
	BasicStroke dashed = new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f);
	BasicStroke rect=new BasicStroke(1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1f,new float[] {2f},0f);
	int size;
	double ALPHA = 5.0;
    double PHETA = 10.0;
    double SINSQR = Math.sin(20*Math.PI/180) * Math.sin(20*Math.PI/180);
    double GAMMA = ALPHA * Math.sqrt(SINSQR / (1-SINSQR));
	int status;
	Sensor source;
public void	setSender(Sensor source, int status){
	this.source = source;
	this.status = status;
}

public Simulator(int size) {
	super.setBackground(new Color(205,133,63));
	this.setBackground(new Color(128,0,0));
	this.size = size;
}

public ArrayList<Sensor> getList(){
	return device;
}
public void removeAll(){
	option=0;
	device.clear();
	col="empty";
	repaint();
}

public void paintComponent(Graphics g1){
	super.paintComponent(g1);
	GradientPaint gradient = new GradientPaint(0, 0, new Color(218,165,32), 175, 175, new Color(255,222,173),true); 
	Graphics2D g = (Graphics2D)g1;
	g.setPaint(gradient);
	g.setStroke(rect);
    Rectangle2D rectangle = new Rectangle2D.Double(500,50,200,40);
	g.setStroke(rect);
	g.draw(rectangle);
	g.drawString("CPS System",550,80);		
	if(option == 0){
		for(int i=0;i<device.size();i++){
			Sensor d = device.get(i);
			if(d.getNode() != null){
				d.draw(g,"fill");
				g.drawString(d.getNode(),d.x+10,d.y+50);
			}
		}
		g.setPaint(gradient);
	}	
	if(option == 1){
		for(int i=0;i<device.size();i++){
			Sensor d = device.get(i);
			if(d.getNode() != null){
				d.draw(g,"fill");
				g.drawString(d.getNode(),d.x+10,d.y+50);
			}
		}
		if (status == 0) {
			g.setColor(Color.green);
			g.drawLine(source.x+10,source.y+10,550,80);
		}
		if (status == 1) {
			g.setColor(Color.red);
			g.drawLine(source.x+10,source.y+10,550,80);
		}
		if (status == 2) {
			g.setColor(Color.blue);
			g.drawLine(source.x+10,source.y+10,550,80);
		}
		g.setPaint(gradient);
	}	
}
}