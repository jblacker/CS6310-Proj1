package edu.gatech.omscs.cs6310.Twfahp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.omscs.cs6310.Interfaces.HeatedPlate;
import edu.gatech.omscs.cs6310.Interfaces.PlateNotInitializedException;

public class TwfahpDiffusion implements HeatedPlate {

	private final int MAXIMUM_ITERATIONS = 150;
	private final Float MINIMUM_DIFFERENCE = 0.001F;
	
	private int dimension;
	private int initLeftEdgeTemp;
	private int initRightEdgeTemp;
	private int initTopEdgeTemp;
	private int initBottomEdgeTemp;
	
	private long lastRunTime;
	
	private Float[][] latticePoints;
	
	public TwfahpDiffusion() {
		this.dimension = 0;
		this.initLeftEdgeTemp = 0;
		this.initRightEdgeTemp = 0;
		this.initTopEdgeTemp = 0;
		this.initBottomEdgeTemp = 0;
	}	
	
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
	public List<List<Float>> calculateLatticePoints() {
		List<List<Float>> points = new ArrayList<List<Float>>();
		
		for (int i = 0; i < this.latticePoints.length; i++) {
			List<Float> temp = new ArrayList<Float>(this.latticePoints[i].length);
			
			for (int j = 0; j < this.latticePoints[i].length; j++) {
				temp.add(this.latticePoints[i][j]);
			}
			
			points.add(temp);
		}
		
		return points;
	}

	@Override
	public long getCalculationTime() {
		return this.lastRunTime;
	}
	
	public void calculateDiffusion() throws PlateNotInitializedException {
		if(this.dimension == 0) {
			throw new PlateNotInitializedException("Dimensions must be set");
		}
		
		long startTime = System.nanoTime();
		
		Float[][] newPlate = initializePlate();
		Float[][] oldPlate = initializePlate();
		
		Float difference;
		
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
	}
	
	private Float[][] initializePlate() {
		Float[][] plate = new Float[this.dimension + 2][this.dimension + 2];
		
		//Initialize Top & Bottom edges
		for(int i = 1; i <= this.dimension; i++) {
			plate[0][i] = (float) this.initTopEdgeTemp;
			plate[this.dimension + 1][i] = (float) this.initBottomEdgeTemp;
		}
		
		//Initialize Left & Right edges
		for(int i = 1; i <= this.dimension; i++) {
			plate[i][0] = (float) this.initLeftEdgeTemp;
			plate[i][this.dimension + 1] = (float) this.initRightEdgeTemp;
		}
		
		// Initialize Plate
		for(int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				plate[i][j] = 0F;
			}
		}
				
		return plate;
	}
		
	private Float[][] deepCopySwap(Float[][] original) {
		if (original == null) {
			return null;
		}
		
		final Float[][] result = new Float[original.length][original[0].length];
		for(int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
		}		
		
		return result;
	}

	private Float getTempDifference(Float[][] newPlate, Float[][] oldPlate) {
		Float totalNewTemp = 0F, totalOldTemp = 0F;
		
		for (int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				totalNewTemp += newPlate[i][j];
				totalOldTemp += oldPlate[i][j];
	        }
		}
		
		return totalNewTemp - totalOldTemp;
	}
}
