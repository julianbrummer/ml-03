/**
 * Package for Machine Learning exercises.
 * @author Julian Brummer
 * @author Alexander Petri
 */
package uni.ml.exercise;


import java.io.File;
import java.io.IOException;

import uni.ml.dataset.Dataset;
import uni.ml.dataset.DatasetSplit;
import uni.ml.dataset.DatasetView;
import uni.ml.learning.BoostingForestModel;

/**
 * The main class for Exercise03 Task02.
 * @author Julian Brummer
 *
 */
public class Exercise03Task02 {


	public static void main(String[] args) {
		Dataset dataset = new Dataset();
		if (args.length > 0) {
			try {
				dataset.loadFromFile(new File(args[0]));
				DatasetSplit split = dataset.randomSplit(2.0f/3.0f);
				
				System.out.println("Dataset: " + dataset.name());
				System.out.println();

				System.out.println("Training set:");
				System.out.println(split.trainingSet);
				System.out.println();
				System.out.println("Test set:");
				System.out.println(split.testSet);
				System.out.println();
				
				int numIterations = Integer.parseInt(args[1]);
				int maxDepth = Integer.parseInt(args[2]);
				DatasetView sampledSet = split.trainingSet;
				BoostingForestModel model = new BoostingForestModel(numIterations, maxDepth);
				model.trainModel(sampledSet, dataset.lastAttribute());
				model.print();

				System.out.println("Dataset: " + dataset.name());
				System.out.println("Boosting Iterations: " + numIterations);
				System.out.println("MaxDepth: " + maxDepth);
				System.out.println("Size of training set: " + split.trainingSet.numInstances());
				System.out.println("Size of test set: " + split.testSet.numInstances());
				System.out.println("Correctly classified: " + model.testModel(split.testSet, dataset.lastAttribute()));		
				System.out.println(model.trainAndTestModel(dataset, 2.0f/3.0f, 10, dataset.lastAttribute()));
			
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		
	}

}
