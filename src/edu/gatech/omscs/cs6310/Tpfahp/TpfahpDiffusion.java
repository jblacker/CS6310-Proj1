package edu.gatech.omscs.cs6310.Tpfahp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.omscs.cs6310.Interfaces.BaseHeatedPlate;
import edu.gatech.omscs.cs6310.Interfaces.PlateNotInitializedException;

public class TpfahpDiffusion extends BaseHeatedPlate {
	
	private final float MINIMUM_DIFFERENCE = 0.001f; 
		
	private float[][] latticePoints;	
		
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
		if(this.dimension == 0)
			throw new PlateNotInitializedException("Dimensions must be set");
		
		long startTime = System.nanoTime();
		
		float[][] newPlate = createAndInitializePlate();
		float[][] oldPlate = createAndInitializePlate();
		
		float difference;
		int iterations = 0;
		do {
			for (int x = 1; x <= dimension; x++) {
				for (int y = 1; y <= dimension; y++) {
					newPlate[x][y] = (oldPlate[x + 1][y] + oldPlate[x - 1][y] +
							oldPlate[x][y + 1] + oldPlate[x][y - 1]) / 4f;
				}
			}
			
			difference = this.getTempDifference(newPlate, oldPlate);
			
			oldPlate = deepCopySwap(newPlate);
			iterations++;
		}
		while(difference >= MINIMUM_DIFFERENCE && iterations < MAXIMUM_ITERATIONS);
		
		this.latticePoints = newPlate;
		this.lastRunTime = System.nanoTime() - startTime;
		this.lastIterationCount = iterations;
	}
	
	private float[][] createAndInitializePlate() {
		float[][] plate = new float[dimension + 2][dimension + 2];
		
		//Initialize Top & bottom
		for(int i = 0; i < dimension + 1; i++) {
			plate[i][0] = initTopEdgeTemp;
			plate[i][dimension + 1] = initBottomEdgeTemp;
		}
		
		//Initialize Left & Right
		for(int i = 0; i < dimension + 1; i++) {
			plate[0][i] = initLeftEdgeTemp;
			plate[dimension + 1][i] = initRightEdgeTemp;
		}
		
		//Initialize Inner Plate
		for(int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
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
		
		for (int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				totalNewTemp += newPlate[i][j];
				totalOldTemp += oldPlate[i][j];
	        }
		}
		
		return totalNewTemp - totalOldTemp;
	}
}
