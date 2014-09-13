/**
 * 
 */
package edu.gatech.omscs.cs6310.Interfaces; 


public class CliDisplay {
	
	public static void displayHeatedPlate(HeatedPlate plate) {
		Float[][] results = plate.calculateLatticePoints();
		System.out.println("");
		for(int x = 1; x < results.length-2; x++) {
			for(int y = 1; y < results[x].length; y++) {
				System.out.format("  %.2f  ", results[x][y]);
			}
			System.out.println("");
		}
	}
	
	

}
