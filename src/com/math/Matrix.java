package com.math;

import java.util.*;

public class Matrix{
    public double data[][];
    public int rows;
    public int cols;
    public Matrix(double[][] data){
	this.data = data;
	this.rows = this.data.length;
	this.cols = this.data[0].length;
    }
    public Matrix(int rows,int cols){
	this.data = new double[rows][cols];
	this.rows = rows;
	this.cols = cols;
    }
    public double get(int i, int j){
	return this.data[i][j];
    }
    public void random(){
	Random r = new Random();
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		this.data[i][j] = r.nextDouble();
	}
    }
    public void ones(){
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		this.data[i][j] = 1;
	}
    }
    public String toString(){
	String s = "";
	for(double[] row : this.data){
	    for(double elem : row)
		s += elem + " ";
	    s += "\n";
	}
	return s;
    }
    public Matrix dot(Matrix m){
	Matrix o = new Matrix(this.rows,m.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<m.cols;j++){
		for(int k = 0;k<this.cols;k++)
		    o.data[i][j] += this.data[i][k]*m.data[k][j];
	    }
	}
	return o;
    }
    public Matrix times(Matrix m){
	Matrix o = new Matrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++){
		o.data[i][j] = this.data[i][j]*m.data[i][j];
	    }
	}
	return o;
    }
    public Matrix times(double num){
	Matrix o = new Matrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++){
		o.data[i][j] = this.data[i][j]*num;
	    }
	}
	return o;
    }
    public Matrix sum(Matrix m){
	Matrix o = new Matrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++){
		o.data[i][j] = this.data[i][j]+m.data[i][j];
	    }
	}
	return o;
    }
    public Matrix diff(Matrix m){
	Matrix o = new Matrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++){
		o.data[i][j] = this.data[i][j]-m.data[i][j];
	    }
	}
	return o;
    }
    public Matrix div(Matrix m){
	Matrix o = new Matrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++){
		o.data[i][j] = this.data[i][j]/m.data[i][j];
	    }
	}
	return o;
    }
    public Matrix transpose(){
	Matrix o = new Matrix(this.cols,this.rows);
	for(int i = 0;i<this.cols;i++){
	    for(int j = 0;j<this.rows;j++)
		o.data[i][j] = this.data[j][i];
	}
	return o;
    }
}
