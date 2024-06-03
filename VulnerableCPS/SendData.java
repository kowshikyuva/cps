package com;
import java.util.ArrayList;
import java.util.Random;
public class SendData extends Thread{
	Simulator node;
	Sensor source;
	boolean running;
public SendData(Simulator node, boolean running){
	this.node = node;
	this.running = running;
	start();
}
public void setRunning(boolean r) {
	running = r;
}
public boolean getRunning() {
	return running;
}
public int getSensor() {
	Random r = new Random();
	return r.nextInt(node.device.size());
}
public int getData() {
	Random r = new Random();
	int max = 55;
	int min = 15;
	int range = max - min + 1;
	return r.nextInt(range) + min;
}

public void run(){
	try{
		while(getRunning()) {
			Sensor source = node.device.get(getSensor());
			int data = getData();
			int status = 0;
			if(data > 15 && data < 45) {
				source.setTotal(source.getTotal() + 1);
				source.setNormal(source.getNormal() + 1);
				status = 0;
				SensorPanel.status.setText(source.getNode()+" sending normal eigen vector value "+data);
			}
			else if(data > 45 && data < 50) {
				source.setTotal(source.getTotal() + 1);
				source.setStrict(source.getStrict() + 1);
				status = 1;
				SensorPanel.status.setText(source.getNode()+" detected strictly stealthy eigen vector value "+data+" Normal sensor range value is 15 to 45");
			}
			else if(data > 45 && data < 55) {
				source.setTotal(source.getTotal() + 1);
				source.setStealthy(source.getStealthy() + 1);
				status = 2;
				SensorPanel.status.setText(source.getNode()+" detected stealthy eigen vector value "+data+" Normal sensor range value is 15 to 45");
			}
			for(int k=0;k<4;k++){
				node.setSender(source,status);
				node.option=1;
				node.repaint();
				sleep(300);
				node.option=0;
				node.repaint();
				sleep(50);
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}