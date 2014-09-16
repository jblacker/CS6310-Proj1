/**
 * 
 */
package edu.gatech.omscs.cs6310.Interfaces; 

import java.util.List;


public class CliDisplay {
	
	public static void displayHeatedPlate(HeatedPlate plate) {
		List<List<Number>> results = plate.calculateLatticePoints();
		System.out.println("");
		for(int x = 1; x < results.size()-2; x++) {
			for(int y = 1; y < results.get(x).size()-2; y++) {
				System.out.format("  %.2f  ", results.get(x).get(y).floatValue());
			}
			System.out.println("");
		}
	}
	
	

}
