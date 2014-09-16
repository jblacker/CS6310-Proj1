/**
 * 
 */
package edu.gatech.omscs.cs6310.Interfaces; 

import java.util.List;


public class CliDisplay {
	
	public static void displayHeatedPlate(HeatedPlate plate) {
		try {
			List<List<Number>> results = plate.calculateLatticePoints();
			System.out.println("");
			for(int x = 1; x < results.size()-1; x++) {
				for(int y = 1; y < results.get(x).size()-1; y++) {
					System.out.format("%10.2f", results.get(x).get(y).floatValue());
				}
				System.out.println("");
			}
		}
		catch(PlateNotInitializedException ex) {
			System.out.println("Heated Plate not initialized properly.");
		}
	}
	
	

}
