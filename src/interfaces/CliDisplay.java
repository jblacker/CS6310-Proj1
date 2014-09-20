package interfaces; 

import java.util.List;

public class CliDisplay {

	/**
	 * Calculates diffusion of heat and displays results
	 * @param plate
	 */
	public static void displayHeatedPlate(HeatedPlate plate) {
		try {
			// Calculate diffusion
			List<List<Number>> results = plate.calculateLatticePoints();
			System.out.println("");

			// Display results of calculation
			for(int x = 1; x < results.size()-1; x++) {
				for(int y = 1; y < results.get(x).size()-1; y++) {
					System.out.format("%10.2f", results.get(x).get(y).floatValue());					
				}
				System.out.println("");
			}

			// Display total calculation time, iterations and memory usage
			System.out.println("");
			System.out.format("Calculation Time: %d ns %n", plate.getCalculationTime());
			System.out.format("Iterations Used: %d%n", plate.getIterationsUsed());
			System.out.format("Memory Usage: %d MB %n", MemInfo.getCurrentMemoryUsage());
		}
		catch(PlateNotInitializedException ex) {
			System.out.println("Heated Plate not initialized properly.");
		}
	}
}