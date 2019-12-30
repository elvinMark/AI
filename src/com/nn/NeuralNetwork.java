package com.nn;

import com.math.*;
import com.af.*;

public abstract class NeuralNetwork{
    public Layer[] layers = null;
    public int numLayers = 0;
    public abstract Matrix forward(Matrix in);
    public abstract Matrix backward(Matrix err);
    public abstract void update();
    public abstract void train(Matrix in,Matrix out,int N);
}
