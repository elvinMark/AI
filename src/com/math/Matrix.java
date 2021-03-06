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
    public void zeros(){
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		this.data[i][j] = 0;
	}
    }
    public void ones(){
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		this.data[i][j] = 1;
	}
    }
    public void eye(){
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++){
		if(i==j)
		    this.data[i][j] = 1;
		else
		    this.data[i][j] = 0;
	    }
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
    public Matrix[] LU(){
	Matrix[] out = new Matrix[2];
	Matrix L,U;
	Vector z;
	double s;
	L = new Matrix(this.rows,this.cols);
	U = new Matrix(this.rows,this.cols);
	L.eye();
	for(int i=0;i<this.rows;i++){
	    for(int j=i;j<this.cols;j++){
		s = 0;
		for(int k=0;k<i;k++)
		    s += L.data[i][k]*U.data[k][j];
		U.data[i][j] = this.data[i][j] - s;
	    }
	    for(int j=i+1;j<this.rows;j++){
		s = 0;
		for(int k=0;k<i;k++)
		    s += L.data[j][k]*U.data[k][i];
		L.data[j][i] = (this.data[j][i] - s)/U.data[i][i];
	    }
	}
	out[0] = L;
	out[1] = U;
	return out;
    }
    /*
    public Matrix[] QR(){
	Matrix[] out = new Matrix[2];
	Matrix Q,R;
	Q = new Matrix(this.rows,this.cols);
	R = new Matrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<i;j++){
		R.data[i][j] = 0;
		for(int k = 0;k<this.cols;k++)
		    R.data[i][j] += this.data[i][k]*Q.data[j][k];
		for(int k = 0;k<this.cols;k++)
		    Q.data[i][k] += R.data[i][j]*Q.data[j][k];
	    }
	    for(int j = 0;j<this.cols;j++){
		Q.data[i][j] = this.data[i][j] - Q.data[i][j];
		R.data[i][i] += Q.data[i][j]*Q.data[i][j];
	    }
	    R.data[i][i] = Math.sqrt(R.data[i][i]);
	    for(int j = 0;j<this.cols;j++)
		Q.data[i][j] = Q.data[i][j]/R.data[i][i];
	}
	out[0] = R;
	out[1] = Q;
	return out;
    }*/
    public Matrix[] QR(){
	Matrix[] out = new Matrix[2];
	Matrix Q,R;
	Q = new Matrix(this.rows,this.cols);
	R = new Matrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<i;j++){
		R.data[j][i] = 0;
		for(int k = 0;k<this.cols;k++)
		    R.data[j][i] += this.data[k][i]*Q.data[k][j];
		for(int k = 0;k<this.cols;k++)
		    Q.data[k][i] += R.data[j][i]*Q.data[k][j];
	    }
	    for(int j = 0;j<this.cols;j++){
		Q.data[j][i] = this.data[j][i] - Q.data[j][i];
		R.data[i][i] += Q.data[j][i]*Q.data[j][i];
	    }
	    R.data[i][i] = Math.sqrt(R.data[i][i]);
	    for(int j = 0;j<this.cols;j++)
		Q.data[j][i] = Q.data[j][i]/R.data[i][i];
	}
	out[0] = Q;
	out[1] = R;
	return out;
    }
    public Matrix[] eigen(int N){
	Matrix[] out;
	Matrix[] qr;
	out = new Matrix[2];
	out[1] = new Matrix(this.rows,this.cols);
	out[1].eye();
	System.out.println(out[1]);
        out[0] = this;
	for(int i = 0;i<N;i++){
	    qr = out[0].QR();
	    out[0] = qr[1].dot(qr[0]);
	    out[1] = out[1].dot(qr[0]);
	}
	return out;
    }
    public Matrix inv(){
	Matrix[] lu;
	Matrix out;
	Vector z;
	double s;
	lu = this.LU();
	z = new Vector(this.rows);
	out = new Matrix(this.rows,this.cols);
	for(int i  = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++){
		s = 0;
		for(int k = 0;k<j;k++)
		    s += lu[0].data[j][k]*z.data[k];
		z.data[j] = (i==j? 1 : 0) - s;
	    }
	    for(int j = this.cols-1;j>=0;j--){
		s = 0;
		for(int k = j+1;k<this.cols;k++)
		    s += lu[1].data[j][k]*out.data[k][i];
		out.data[j][i] = (z.data[j] - s)/lu[1].data[j][j];
	    }
	}
	return out;
    }
    public double det(){
	double s = 1;
	Matrix[] out;
	out = this.LU();
	for(int i =0; i<this.rows;i++)
	    s *= out[1].data[i][i];
	return s;
    }
}
