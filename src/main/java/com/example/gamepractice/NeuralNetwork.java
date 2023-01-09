package com.example.gamepractice;

public class NeuralNetwork {

    //NEED to make the network shape part of the constructor so i can reuse this
    public int[] netWorkShape = {19,21,18,10,6,4};
    public Layer[] layers;

    public class Layer
    {
        public double[][] weightsArray;
        public double[] biasesArray;
        public double[] nodeArray;

        private int n_inputs;
        private int n_nodes;

        public Layer(int n_inputs, int n_nodes)
        {
            this.n_inputs = n_inputs;
            this.n_nodes = n_nodes;

            weightsArray = new double[n_nodes][n_inputs];
            biasesArray = new double[n_nodes];
            nodeArray = new double[n_nodes];
            for(int i = 0; i<this.weightsArray.length;i++)
            {
                for( int j = 0; j<this.weightsArray[i].length;j++)
                {
                    this.weightsArray[i][j]=(Math.random()*2)-1;
                }
            }
            //can comment this out


            for(int i = 0; i<this.biasesArray.length;i++)
            {
                biasesArray[i]=1;
            }

        }

        public void forward(double[] inputsArray)
        {
            nodeArray = new double[n_nodes];

            for(int i = 0; i<n_nodes; i++)
            {
                //sum of the weights times the inputs
                for(int j =0; j<n_inputs;j++)
                {
                    nodeArray[i]+=weightsArray[i][j]*inputsArray[j];
                }

                //add the bias
                nodeArray[i]+=biasesArray[i];
            }
        }

        public void activation()
        {
            for(int i = 0; i<n_nodes;i++)
            {
                if(nodeArray[i]<0)//this is a reLu, could change to sigmoid
                {
                    nodeArray[i] = 0;
                }



            }
        }
    }

    public NeuralNetwork()//this might have to be the nn constructor
    {
        layers = new Layer[netWorkShape.length-1];
        for(int i = 0; i< layers.length;i++)
        {
            layers[i] = new Layer(netWorkShape[i],netWorkShape[i+1]);
        }
    }

    //make another method that inputs the x and y, then converts to an array, then calls this method
    public double[] Brain(double[] inputs)
    {

        for(int i = 0;i< layers.length;i++)
        {
            if(i==0)
            {
                layers[i].forward(inputs);
                layers[i].activation();
            }
            else if(i==layers.length-1)//this is if we dont want activation on our output... propably do so we can comment it out
            {
                layers[i].forward(layers[i-1].nodeArray);
                layers[i].activation();//add "layers[i].activation();" if want activation
            }
            else
            {
                layers[i].forward(layers[i-1].nodeArray);
                layers[i].activation();
            }


        }

        return layers[layers.length-1].nodeArray;
    }


}
