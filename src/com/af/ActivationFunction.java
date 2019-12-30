package com.af;

import com.math.*;
import com.nn.*;

public class ActivationFunction{
    public static double sigmoid(double x){
	return 1/(1+Math.exp(-x));
    }
    public static Matrix sigmoid(Matrix m){
	Matrix o = new Matrix(m.rows,m.cols);
	for(int i = 0;i<m.rows;i++){
	    for(int j = 0;j<m.cols;j++)
		o.data[i][j] = sigmoid(m.data[i][j]); 
	}
	return o;
    }
}
