package ann;

import java.util.*;

public class OneHiddenLayer extends ANN{

	List<Neuron> hiddenLayer;

	int numHiddenNeurons = 10;


	public OneHiddenLayer(Map<Input, Output> trainingData, Map<Input, Output> testingData) {
		generator = new Random();
		this.trainingData = trainingData;
		this.testingData = testingData;

		// to be completed
		inLayer=new LinkedList<>();//initialisation List and Activation
		outLayer=new LinkedList<>();
		hiddenLayer=new LinkedList<>();
		Activation act=new Linear();
		// to be completed
		for (int i = 0; i < 10; i++) {       //initialize outlayer and inputlayer
			outLayer.add(new Neuron(act));
		}
		Iterator<Neuron> outiterator = outLayer.iterator();
		for (int i = 0; i < numHiddenNeurons; i++) {
			Neuron neuron=new Neuron(act);
			hiddenLayer.add(neuron);
			while(outiterator.hasNext()){
				neuron.addChild(outiterator.next());
			}
			outiterator=outLayer.iterator();
		}

		Iterator<Neuron> hiddeniterator = hiddenLayer.iterator();
		for (Neuron neuronout:outLayer) {
			while(hiddeniterator.hasNext()){
				neuronout.addParent(hiddeniterator.next());
			}
			hiddeniterator=hiddenLayer.iterator();
			neuronout.initWeights();
		}

		for (int i = 0; i < 784; i++) {
			InputNeuron inputNeuron=new InputNeuron(255);
			inLayer.add(inputNeuron);
			while(hiddeniterator.hasNext()){
				inputNeuron.addChild(hiddeniterator.next());
			}
			hiddeniterator=hiddenLayer.iterator();
		}
		Iterator<InputNeuron> initerator = inLayer.iterator();
		for (Neuron neuron: hiddenLayer) {
			while(initerator.hasNext()){
				neuron.addParent(initerator.next());
			}
			initerator=inLayer.iterator();
			neuron.initWeights();
		}
	}


	public Output feed(Input in){
		// to be completed
		double[] ret=new double[10];

		Iterator<InputNeuron> inputNeuronIterator = inLayer.iterator();
		Iterator<Double> inIterator = in.iterator();
		while(inputNeuronIterator.hasNext()){
			inputNeuronIterator.next().feed(inIterator.next());
		}

		Iterator<Neuron> hiddeniterator=hiddenLayer.iterator();
		while (hiddeniterator.hasNext()){
			hiddeniterator.next().feed();
		}
		for (int j=0;j<10;j++) {
			outLayer.get(j).feed();
			ret[j]=outLayer.get(j).getCurrentOutput();
			//System.out.println("ret[j] = " + ret[j]);
		}

		return new Output(ret);
	}



	public Map<Integer,Double> train(int nbIterations) {

		// to be completed
		return null;
	}



}