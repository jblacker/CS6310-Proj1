package edu.gatech.omscs.cs6310.Twfahp;

import edu.gatech.omscs.cs6310.Interfaces.CliDisplay;

public class Demo {

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 10) {
			System.out.println("Invalid number of arguments");
			return;
		}
		
		TwfahpDiffusion heatedPlate = new TwfahpDiffusion();
		
		heatedPlate.initialize(args);
			
		CliDisplay.displayHeatedPlate(heatedPlate);
		System.out.format("Took %d ns to calculate diffusion", heatedPlate.getCalculationTime());
	}

}
