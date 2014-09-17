package edu.gatech.omscs.cs6310.Tpfahp;

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
		
		TpfahpDiffusion heatedPlate = new TpfahpDiffusion();
		
		heatedPlate.initialize(args);
		
		CliDisplay.displayHeatedPlate(heatedPlate);
	}
}
