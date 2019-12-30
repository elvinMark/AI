package com.nn;

import com.math.*;
import com.af.*;
import com.nn.*;

public class MultiLayerPerceptron extends NeuralNetwork{
    public int input;
    public int hidden;
    public int output;
    public Matrix in;
    public Matrix out;
    public MultiLayerPerceptron(int input,int hidden,int output){
	this.input = input;
	this.hidden = hidden;
	this.output = output;
	this.layers = new Layer[2];
	this.layers[0] = new Layer(input,hidden);
	this.layers[1] = new Layer(hidden,output);
	this.numLayers = 2;
    }
    public Matrix forward(Matrix in){
	Matrix o = in;
	this.in = in;
	for(int i = 0;i<this.numLayers;i++)
	    o = this.layers[i].forward(o);
	this.out = o;
	return o;
    }
    public Matrix backward(Matrix err){
	Matrix o = err;
	for(int i = this.numLayers-1;i>=0;i--)
	    o = this.layers[i].backward(o);
	return o;
    }
    public void update(){
	for(int i = 0;i<this.numLayers;i++)
	    this.layers[i].update();
    }
    public void train(Matrix in,Matrix out,int N){
	for(int i = 0;i<N;i++){
	    this.forward(in);
	    this.backward(this.out.diff(out));
	    this.update();
	}
    }
}
