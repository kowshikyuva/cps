package com;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
public class Sensor{
	int  x;           
    int  y;           
    int  dia;    
    String node;
	int normal_value,stealthy,strict, total;

public void setStealthy(int stealthy){
	this.stealthy = stealthy;
}
public int getStealthy() {
	return stealthy;
}

public void setTotal(int total){
	this.total = total;
}
public int getTotal() {
	return total;
}

public void setStrict(int strict){
	this.strict = strict;
}
public int getStrict() {
	return strict;
}

public void setNormal(int normal_value){
	this.normal_value = normal_value;
}
public int getNormal() {
	return normal_value;
}

public Sensor(Point center, int radius){
	x   = center.x;
    y   = center.y;
    dia = radius;
}

public void setX(int x){
	this.x=x;
}
public int getX(){
	return x;
}
public void setY(int y){
	this.y=y;
}
public int getY(){
	return y;
}
public void draw(Graphics g,int op) {
	if(op == 0)
		g.fillOval(x, y, dia,dia);
	else
		g.drawOval(x,y,dia,dia);
}
public void draw(Graphics g,String option){
	g.fillRect(x,y,dia,dia);
}
public void draw(Graphics g){
	g.drawRect(x,y,dia,dia);
}
public void setNode(String node){
	this.node=node;
}
public String getNode(){
	return node;
}
}

