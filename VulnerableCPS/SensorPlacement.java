package com;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;
public class SensorPlacement{
	static int size=40;
	static Simulator g;
	
public static void randomNodes(int s, int width, int height,Simulator nodes){
	g=nodes;
	randomNodes(s, width, height);
}
public static void randomNodes(int s, int width, int height){
	int x = getXPosition(100,1200);
	int y = getYPosition(100,550);
	for(int i=1;i<=s;i++){
		boolean flag = checkDistance(x,y);
		if(!flag){
			Sensor d = new Sensor(new Point(x, y), size);
			d.setNode("S"+i);
			g.device.add(d);
			MainPanel.dtm.setValueAt(x+"",(i-1),1);
			MainPanel.dtm.setValueAt(y+"",(i-1),2);
		}else{
			i = i - 1;
		}
		x = getXPosition(100,1200);
		y = getYPosition(100,550);
	}	
}	
public static int getXPosition(int start,int end){
	Random rn = new Random();
	int range = end - start + 1;
	return rn.nextInt(range) + start;
}
public static int getYPosition(int start,int end){
	Random rn = new Random();
	int range = end - start + 1;
	return rn.nextInt(range) + start;
}
public static boolean checkDistance(int x,int y){
	boolean flag = false;
	for(int i=0;i<g.device.size();i++){
		Sensor d = g.device.get(i);
		double d1 = getDistance(x,y,d.x,d.y);
		if(d1 < 50){
			flag = true;
			break;
		}
	}
	return flag;
}
public static double getDistance(int n1x,int n1y,int n2x,int n2y) {
	int dx = (n1x - n2x) * (n1x - n2x);
	int dy = (n1y - n2y) * (n1y - n2y);
	int total = dx + dy; 
	return Math.sqrt(total);
}
}