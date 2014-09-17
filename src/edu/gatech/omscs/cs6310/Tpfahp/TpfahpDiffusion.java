package edu.gatech.omscs.cs6310.Tpfahp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.omscs.cs6310.Interfaces.HeatedPlate;
import edu.gatech.omscs.cs6310.Interfaces.PlateNotInitializedException;

public class TpfahpDiffusion implements HeatedPlate {
	
	private final int MAXIMUM_ITERATIONS = 150000;
	private final float MINIMUM_DIFFERENCE = 0.001f; 
	
	private int dimensions;
	private int initTopEdgeTemp;
	private int initBottomEdgeTemp;
	private int initRightEdgeTemp;
	private int initLeftEdgeTemp;
	
	private long lastRunTime;
	private int lastIterationCount;
	
	private float[][] latticePoints;	
	
	public TpfahpDiffusion()
	{
		this.dimensions = 0;
		this.initTopEdgeTemp = 0;
		this.initBottomEdgeTemp = 0;
		this.initRightEdgeTemp = 0;
		this.initLeftEdgeTemp = 0;		
		this.latticePoints = null;
	}
	
	@Override
	public int getDimension() {
		return this.dimensions;
	}

	@Override
	public void setDimension(int dimension) {
		this.dimensions = dimension;

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
	
	@Override
	public long getCalculationTime() {
		return this.lastRunTime;
	}
	
	@Override
	public int getIterationsUsed() {
		return this.lastIterationCount;
	}
	
	public float[][] getCalculatedDiffusion() {
		return this.latticePoints;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<Float>> calculateLatticePoints() throws PlateNotInitializedException {
		calculateDiffusion();
		List<List<Float>> points = new ArrayList<List<Float>>();
		for(int x = 0; x < latticePoints.length; x++) {
			List<Float> temp = new ArrayList<Float>(latticePoints[x].length);
			for(float f : latticePoints[x]) {
				temp.add(new Float(f));
			}
			points.add(temp);
		}
		
		return points;
	}
	
	public void calculateDiffusion() throws PlateNotInitializedException {
		if(this.dimensions == 0)
			throw new PlateNotInitializedException("Dimensions must be set");
		
		long startTime = System.nanoTime();
		
		float[][] newPlate = createAndInitializePlate();
		float[][] oldPlate = createAndInitializePlate();
		
		float difference;
		int iterations = 0;
		do {
			for (int x = 1; x <= dimensions; x++) {
				for (int y = 1; y <= dimensions; y++) {
					newPlate[x][y] = (oldPlate[x + 1][y] + oldPlate[x - 1][y] +
							oldPlate[x][y + 1] + oldPlate[x][y - 1]) / 4f;
				}
			}
			
			difference = this.getTempDifference(newPlate, oldPlate);
			
			oldPlate = deepCopySwap(newPlate);
			iterations++;
		}
		while(difference >= MINIMUM_DIFFERENCE || iterations < MAXIMUM_ITERATIONS);
		
		this.latticePoints = newPlate;
		this.lastRunTime = System.nanoTime() - startTime;
		this.lastIterationCount = iterations;
	}
	
	private float[][] createAndInitializePlate() {
		float[][] plate = new float[dimensions + 2][dimensions + 2];
		
		//Initialize Top & bottom
		for(int i = 0; i < dimensions + 1; i++) {
			plate[i][0] = initTopEdgeTemp;
			plate[i][dimensions + 1] = initBottomEdgeTemp;
		}
		
		//Initialize Left & Right
		for(int i = 0; i < dimensions + 1; i++) {
			plate[0][i] = initLeftEdgeTemp;
			plate[dimensions + 1][i] = initRightEdgeTemp;
		}
		
		//Initialize Inner Plate
		for(int i = 1; i <= this.dimensions; i++) {
			for (int j = 1; j <= this.dimensions; j++) {
				plate[i][j] = 0f;
			}
		}
		
		return plate;
	}
	
	private float[][] deepCopySwap(float[][] original) {
		if (original == null) return null;
		
		final float[][] result = new float[original.length][original[0].length];
		for(int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
		}
		
		return result;
	}
	
	private float getTempDifference(float[][] newPlate, float[][] oldPlate) {
		float totalNewTemp = 0f, totalOldTemp = 0f;
		
		for (int i = 1; i <= this.dimensions; i++) {
			for (int j = 1; j <= this.dimensions; j++) {
				totalNewTemp += newPlate[i][j];
				totalOldTemp += oldPlate[i][j];
	        }
		}
		
		return totalNewTemp - totalOldTemp;
	}
}
