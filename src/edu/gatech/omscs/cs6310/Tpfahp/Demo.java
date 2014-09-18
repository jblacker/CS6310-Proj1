package edu.gatech.omscs.cs6310.Tpfahp;

import edu.gatech.omscs.cs6310.Interfaces.CliDisplay;
import edu.gatech.omscs.cs6310.Interfaces.CliParser;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 10) {
			System.out.println("Invalid number of arguments");
			return;
		}
		
		TpfahpDiffusion heatedPlate = new TpfahpDiffusion();
		
		// Initialize plate with command line arguments
		CliParser.initialize(args, heatedPlate);

		// Calculate diffusion and display results
		CliDisplay.displayHeatedPlate(heatedPlate);
	}
}
