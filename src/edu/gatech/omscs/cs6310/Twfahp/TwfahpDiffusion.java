package edu.gatech.omscs.cs6310.Twfahp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.omscs.cs6310.Interfaces.BaseHeatedPlate;
import edu.gatech.omscs.cs6310.Interfaces.PlateNotInitializedException;

/**
 * Calculate diffusion of temperature on a plate using Floats 
 */
public class TwfahpDiffusion extends BaseHeatedPlate {
	
	private Float[][] latticePoints;	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<List<Float>> calculateLatticePoints() throws PlateNotInitializedException {
		// Calculate the diffusion for the heated plate
		this.calculateDiffusion();
		
		List<List<Float>> points = new ArrayList<List<Float>>();
		
		// Convert latticePoints array to a List of Lists
		for (Float[] axis : this.latticePoints) {
				points.add(Arrays.asList(axis));
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
		
		// Initialize the plates
		Float[][] newPlate = createAndInitializePlate();
		Float[][] oldPlate = createAndInitializePlate();
		
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

			// Calculate temperature difference between original and updated plate
			difference = getTempDifference(newPlate, oldPlate);
			iterations++;
			
			// Update original plate with updated plate temperatures
			oldPlate = deepCopySwap(newPlate);
		} while(difference >= MAX_DIFF_PERCENT && iterations < MAXIMUM_ITERATIONS);
		
		// Set final values
		this.latticePoints = newPlate;
		this.lastRunTime = System.nanoTime() - startTime;
		this.lastIterationCount = iterations;
	}
	
	/**
	 * Initialize the plate based on dimension and edge temperatures
	 * @return Returns a plate as an Array of Floats
	 */
	private Float[][] createAndInitializePlate() {
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
		
	/**
	 * Copy the updated plate to the original plate
	 * @param original - The original plate
	 * @return Returns a copy of the original plate
	 */
	private Float[][] deepCopySwap(Float[][] original) {
		if (original == null)
			return null;		
		
		// Copy array
		final Float[][] result = new Float[original.length][original[0].length];
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
	private Float getTempDifference(Float[][] newPlate, Float[][] oldPlate) {
		Float totalNewTemp = 0F, totalOldTemp = 0F;
		
		// Calculate total temperature of the plates
		for (int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				totalNewTemp += newPlate[i][j];
				totalOldTemp += oldPlate[i][j];
	        }
		}
		
		if (totalOldTemp == 0)
			return 100F;
		
		return ((totalNewTemp - totalOldTemp) / totalOldTemp) * 100;
	}
}