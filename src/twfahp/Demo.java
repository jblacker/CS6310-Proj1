package twfahp;

import interfaces.CliDisplay;
import interfaces.CliParser;

public class Demo {

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// Verify number of arguments
		if(args.length != 10) {
			System.out.println("Invalid number of arguments");
			return;
		}
		
		TwfahpDiffusion heatedPlate = new TwfahpDiffusion();
		
		// Initialize plate with command line arguments
		CliParser.initialize(args, heatedPlate);		
		
		// Calculate diffusion and display results
		CliDisplay.displayHeatedPlate(heatedPlate);
	}
}