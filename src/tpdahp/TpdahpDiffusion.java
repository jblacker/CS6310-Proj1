package tpdahp;

import interfaces.BaseHeatedPlate;
import interfaces.PlateNotInitializedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class TpdahpDiffusion extends BaseHeatedPlate {
	
	private double[][] latticePoints;
	
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
	
	/**
	 * Calculate the time and iterations required to diffuse heat through a plate
	 * @throws PlateNotInitializedException
	 */
	public void calculateDiffusion() throws PlateNotInitializedException {
		if(this.dimension == 0)
			throw new PlateNotInitializedException("Dimensions must be set");		
		
		long startTime = System.nanoTime();
		
		double[][] newPlate = createAndInitializePlate();
		double[][] oldPlate = createAndInitializePlate();
		
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

			// Calculate temperature difference between original and updated plate
			difference = getTempDifference(newPlate, oldPlate);
			iterations++;
			
			// Update original plate with updated plate temperatures
			oldPlate = deepCopySwap(newPlate);
		} while(difference >= MAX_DIFF_PERCENT && iterations < MAXIMUM_ITERATIONS);
		
		this.latticePoints = newPlate;
		this.lastRunTime = System.nanoTime() - startTime;
		this.lastIterationCount = iterations;
	}
	
	/**
	 * Initialize the plate based on dimension and edge temperatures
	 * @return Returns a plate as an Array of Floats
	 */
	private double[][] createAndInitializePlate() {
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
		
	/**
	 * Copy the updated plate to the original plate
	 * @param original - The original plate
	 * @return Returns a copy of the original plate
	 */
	private double[][] deepCopySwap(double[][] original) {
		if (original == null)
			return null;		
		
		// Copy array
		final double[][] result = new double[original.length][original[0].length];
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
	private double getTempDifference(double[][] newPlate, double[][] oldPlate) {
		double totalNewTemp = 0d, totalOldTemp = 0d;
		
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