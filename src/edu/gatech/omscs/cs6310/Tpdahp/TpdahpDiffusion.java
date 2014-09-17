package edu.gatech.omscs.cs6310.Tpdahp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.omscs.cs6310.Interfaces.HeatedPlate;
import edu.gatech.omscs.cs6310.Interfaces.PlateNotInitializedException;

public class TpdahpDiffusion implements HeatedPlate {

	private final int MAXIMUM_ITERATIONS = 150000;
	private final double MINIMUM_DIFFERENCE = 0.001f;
	
	private int dimension;
	private int initLeftEdgeTemp;
	private int initRightEdgeTemp;
	private int initTopEdgeTemp;
	private int initBottomEdgeTemp;
	
	private long lastRunTime;
	private int lastIterationCount;
	
	private double[][] latticePoints;
	
	@Override
	public int getDimension() {
		return this.dimension;
	}

	@Override
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	@Override
	public int getLeftEdgeTemp() {
		return this.initLeftEdgeTemp;
	}

	@Override
	public void setLeftEdgeTemp(int temp) {
		this.initLeftEdgeTemp = temp;
	}

	@Override
	public int getRightEdgeTemp() {
		return this.initRightEdgeTemp;
	}

	@Override
	public void setRightEdgeTemp(int temp) {
		this.initRightEdgeTemp = temp;
	}

	@Override
	public int getTopEdgeTemp() {
		return this.initTopEdgeTemp;
	}

	@Override
	public void setTopEdgeTemp(int temp) {
		this.initTopEdgeTemp = temp;
	}

	@Override
	public int getBottomEdgeTemp() {
		return this.initBottomEdgeTemp;
	}

	@Override
	public void setBottomEdgeTemp(int temp) {
		this.initBottomEdgeTemp = temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<Double>> calculateLatticePoints() throws PlateNotInitializedException {
		this.calculateDiffusion();
		
		List<List<Double>> points = new ArrayList<List<Double>>();
		
		for (int i = 0; i < this.latticePoints.length; i++) {
			List<Double> temp = new ArrayList<Double>(this.latticePoints[i].length);
			
			for(double d : this.latticePoints[i]) {
				temp.add(new Double(d));
			}
			
			points.add(temp);
		}
		
		return points;
	}

	@Override
	public long getCalculationTime() {
		return this.lastRunTime;
	}
	
	@Override
	public int getIterationsUsed() {
		return this.lastIterationCount;
	}
	
	public void calculateDiffusion() throws PlateNotInitializedException {
		if(this.dimension == 0) {
			throw new PlateNotInitializedException("Dimensions must be set");
		}
		
		long startTime = System.nanoTime();
		
		double[][] newPlate = initializePlate();
		double[][] oldPlate = initializePlate();
		
		double difference;
		
		int iterations = 0;
		
		do {			
			// Update plate temperatures 
			for (int i = 1; i <= this.dimension; i++) {
				for (int j = 1; j <= this.dimension; j++) {
					newPlate[i][j] = (oldPlate[i + 1][j] + oldPlate[i - 1][j] +
									  oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4F;
		        }
			}

			difference = getTempDifference(newPlate, oldPlate);
			iterations++;
			
			oldPlate = deepCopySwap(newPlate);
		} while(difference >= MINIMUM_DIFFERENCE && iterations < MAXIMUM_ITERATIONS);
		
		this.latticePoints = newPlate;
		this.lastRunTime = System.nanoTime() - startTime;
		this.lastIterationCount = iterations;
	}
	
	private double[][] initializePlate() {
		double[][] plate = new double[this.dimension + 2][this.dimension + 2];
		
		//Initialize Top & Bottom edges
		for(int i = 1; i <= this.dimension; i++) {
			plate[0][i] = this.initTopEdgeTemp;
			plate[this.dimension + 1][i] = this.initBottomEdgeTemp;
		}
		
		//Initialize Left & Right edges
		for(int i = 1; i <= this.dimension; i++) {
			plate[i][0] = this.initLeftEdgeTemp;
			plate[i][this.dimension + 1] = this.initRightEdgeTemp;
		}
		
		// Initialize Plate
		for(int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				plate[i][j] = 0d;
			}
		}
				
		return plate;
	}
		
	private double[][] deepCopySwap(double[][] original) {
		if (original == null) {
			return null;
		}
		
		final double[][] result = new double[original.length][original[0].length];
		for(int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
		}		
		
		return result;
	}

	private double getTempDifference(double[][] newPlate, double[][] oldPlate) {
		double totalNewTemp = 0d, totalOldTemp = 0d;
		
		for (int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				totalNewTemp += newPlate[i][j];
				totalOldTemp += oldPlate[i][j];
	        }
		}
		
		return totalNewTemp - totalOldTemp;
	}

}
