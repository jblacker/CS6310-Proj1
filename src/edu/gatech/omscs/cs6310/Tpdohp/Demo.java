package edu.gatech.omscs.cs6310.Tpdohp;

import edu.gatech.omscs.cs6310.Interfaces.CliDisplay;
import edu.gatech.omscs.cs6310.Interfaces.CliParser;
import edu.gatech.omscs.cs6310.Tpdahp.TpdahpDiffusion;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TpdohpDiffusion heatedPlate = new TpdohpDiffusion();
		
		if(args.length != 10) {
			System.out.println("Invalid number of arguments");
			return;
		}
		
		// Initialize plate with command line arguments
		CliParser.initialize(args, heatedPlate);

		// Calculate diffusion and display results
		CliDisplay.displayHeatedPlate(heatedPlate);	
	}

}
