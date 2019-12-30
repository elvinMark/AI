package com.math;

import java.util.*;

public class Vector{
    public double[] data;
    public int len;
    public Vector(double[] data){
	this.data = data;
	this.len = data.length;
    }
    public Vector(int length){
	this.data = new double[length];
	this.len = length;
    }
    public double get(int i){
	return this.data[i];
    }
    public void random(){
	Random r = new Random();
	for(int i = 0;i<this.len;i++)
	    this.data[i] = r.nextDouble();
    }
    public String toString(){
	String s = "";
	for(double elem : this.data)
	    s += elem + " ";
	s += "\n";
	return s;
    }
    public double dot(Vector v){
	double s = 0;
	for(int i = 0;i<this.len;i++)
	    s += this.data[i]*v.data[i];
	return s;
    }
    public double norm(){
	double s = 0;
	for(double elem : this.data)
	    s += elem*elem;
	return Math.sqrt(s);
    }
    public void normalize(){
	double n = this.norm();
	for(int i = 0;i<this.len;i++)
	    this.data[i] /=n;
    }
}
