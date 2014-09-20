package tpdohp;

import interfaces.CliDisplay;
import interfaces.CliParser;

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
