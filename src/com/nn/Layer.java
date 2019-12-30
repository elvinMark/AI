package com.nn;

import com.math.*;
import com.af.*;

public class Layer{
    public Matrix weight;
    public Matrix bias;
    public int input;
    public int output;
    public Matrix in;
    public Matrix out;
    public Matrix delta;
    public Layer(){
    }
    public Layer(int input,int output){
	this.weight = new Matrix(input,output);
	this.bias = new Matrix(1,output);
	this.weight.random();
	this.bias.random();
	this.input = input;
	this.output = output;
    }
    public Matrix forward(Matrix in){
	this.in = in;
	Matrix I = new Matrix(in.rows,1);
	I.ones();
	this.out = ActivationFunction.sigmoid(in.dot(this.weight).sum(I.dot(this.bias)));
	return this.out;
    }
    public Matrix backward(Matrix err){
	Matrix I = new Matrix(err.rows,err.cols);
	I.ones();
	this.delta = err.times(this.out.times(I.diff(this.out)));
	return this.delta.dot(this.weight.transpose());
    }
    public void update(){
	Matrix I = new Matrix(1,this.in.rows);
	I.ones();
	this.weight = this.weight.diff(this.in.transpose().dot(this.delta));
	this.bias =  this.bias.diff(I.dot(this.delta));
    }
}
