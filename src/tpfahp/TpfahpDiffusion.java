package tpfahp;

import interfaces.BaseHeatedPlate;
import interfaces.PlateNotInitializedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class TpfahpDiffusion extends BaseHeatedPlate {
		
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
	
	/**
	 * Calculate the time and iterations required to diffuse heat through a plate
	 * @throws PlateNotInitializedException
	 */
	public void calculateDiffusion() throws PlateNotInitializedException {
		if(this.dimension == 0)
			throw new PlateNotInitializedException("Dimensions must be set");
		
		long startTime = System.nanoTime();
		
		float[][] newPlate = createAndInitializePlate();
		float[][] oldPlate = createAndInitializePlate();
		
		float difference;
		int iterations = 0;
		do {
			// Update plate temperatures 
			for (int x = 1; x <= dimension; x++) {
				for (int y = 1; y <= dimension; y++) {
					newPlate[x][y] = (oldPlate[x + 1][y] + oldPlate[x - 1][y] +
							oldPlate[x][y + 1] + oldPlate[x][y - 1]) / 4f;
				}
			}
			
			// Calculate temperature difference between original and updated plate
			difference = this.getTempDifference(newPlate, oldPlate);
			
			// Update original plate with updated plate temperatures
			oldPlate = deepCopySwap(newPlate);
			iterations++;
		}
		while(difference >= MAX_DIFF_PERCENT && iterations < MAXIMUM_ITERATIONS);
		
		this.latticePoints = newPlate;
		this.lastRunTime = System.nanoTime() - startTime;
		this.lastIterationCount = iterations;
	}
	
	/**
	 * Initialize the plate based on dimension and edge temperatures
	 * @return Returns a plate as an Array of Floats
	 */
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
	
	/**
	 * Copy the updated plate to the original plate
	 * @param original - The original plate
	 * @return Returns a copy of the original plate
	 */
	private float[][] deepCopySwap(float[][] original) {
		if (original == null)
			return null;
		
		// Copy array
		final float[][] result = new float[original.length][original[0].length];
		for(int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
		}
		
		return result;
	}
	
	/**
	 * Get the temperature difference between the original and updated plates
	 * @param newPlate - The updated plate
	 * @param oldPlate - The original plate
	 * @return Returns the difference in total temperature as a Float
	 */
	private float getTempDifference(float[][] newPlate, float[][] oldPlate) {
		float totalNewTemp = 0f, totalOldTemp = 0f;
		
		// Calculate total temperature of plates
		for (int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				totalNewTemp += newPlate[i][j];
				totalOldTemp += oldPlate[i][j];
	        }
		}
		
		if (totalOldTemp == 0)
			return 100;
		
		return ((totalNewTemp - totalOldTemp) / totalOldTemp) * 100;
	}
}
