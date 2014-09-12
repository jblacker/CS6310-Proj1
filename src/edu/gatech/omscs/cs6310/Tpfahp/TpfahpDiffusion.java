package edu.gatech.omscs.cs6310.Tpfahp;

import java.util.Arrays;

import edu.gatech.omscs.cs6310.Interfaces.HeatedPlate;
import edu.gatech.omscs.cs6310.Interfaces.PlateNotInitializedException;

public class TpfahpDiffusion implements HeatedPlate {
	
	private final int MAXIMUM_ITERATIONS = 150;
	private final float MINIMUM_DIFFERENCE = 0.001f; 
	
	private int dimensions;
	private int initTopEdgeTemp;
	private int initBottomEdgeTemp;
	private int initRightEdgeTemp;
	private int initLeftEdgeTemp;
	
	private long lastRunTime;
	
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

	public long getLastRunTime() {
		return this.lastRunTime;
	}
	
	public float[][] getCalculatedDiffusion() {
		return this.latticePoints;
	}

	@Override
	public Float[][] calculateLatticePoints() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void calculateDiffusion() {
		long startTime = System.nanoTime();
		
		float[][] newPlate = createAndInitializePlate();
		float[][] oldPlate = createAndInitializePlate();
		
		float difference;
		int iterations = 0;
		do {
			for (int x = 1; x < dimensions; x++) {
				for (int y = 1; y < dimensions; y++) {
					newPlate[x][y] = (oldPlate[x + 1][y] + oldPlate[x - 1][y] +
							oldPlate[x][y + 1] + oldPlate[x][y - 1]) / 4f;
				}
			}
			
			difference = newPlate[2][2] - oldPlate[2][2];
			
			oldPlate = deepCopySwap(newPlate);
		}
		while(difference >= MINIMUM_DIFFERENCE || iterations < MAXIMUM_ITERATIONS);
		
		this.lastRunTime = System.nanoTime() - startTime;
		this.latticePoints = newPlate;
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
}
