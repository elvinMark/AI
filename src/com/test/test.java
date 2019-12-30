package com.test;

import com.math.*;
import com.nn.*;
import com.af.*;
import java.util.*;

public class test{
    public static void main(String args[]){
	//Testing Perceptron
	Perceptron p = new Perceptron(2,1);
	Matrix in;
	Matrix out;
	double[][] data_in = new double[5][2];
	double[][] data_out = new double[5][1];
	in = new Matrix(data_in);
	out = new Matrix(data_out);
	in.random();
	for(int i = 0;i<5;i++){
	    if(in.data[i][1] < 3*in.data[i][0] - 0.2)
		out.data[i][0] = 0;
	    else
		out.data[i][0] = 1;
	}
	System.out.println(in);
	System.out.println(out);
	p.train(in,out,4000);
	System.out.println(p.forward(in));
	//Testing MultiLayerPerceptron
	MultiLayerPerceptron mlp = new MultiLayerPerceptron(2,5,1);
        double[][] d_in = {{0,0},{0,1},{1,0},{1,1}};
	double[][] d_out ={{1},{0},{0},{1}};
	in = new Matrix(d_in);
	out = new Matrix(d_out);
	System.out.println(in);
	System.out.println(out);
	mlp.train(in,out,4000);
	System.out.println(mlp.forward(in));
    }
}
