package edu.gatech.omscs.cs6310.Twfahp;

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
		
		TwfahpDiffusion heatedPlate = new TwfahpDiffusion();
		
		CliParser.initialize(args, heatedPlate);			
		CliDisplay.displayHeatedPlate(heatedPlate);		
	}

}
