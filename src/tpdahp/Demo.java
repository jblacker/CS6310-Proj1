package tpdahp;

import tpdahp.TpdahpDiffusion;
import interfaces.CliDisplay;
import interfaces.CliParser;

public class Demo {

	public static void main(String[] args) {
		if(args.length != 10) {
			System.out.println("Invalid number of arguments");
			return;
		}
		
		TpdahpDiffusion heatedPlate = new TpdahpDiffusion();
		
		// Initialize plate with command line arguments
		CliParser.initialize(args, heatedPlate);

		// Calculate diffusion and display results
		CliDisplay.displayHeatedPlate(heatedPlate);	
	}
}