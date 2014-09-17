package edu.gatech.omscs.cs6310.Tpdahp;

import edu.gatech.omscs.cs6310.Interfaces.CliDisplay;
import edu.gatech.omscs.cs6310.Tpdahp.TpdahpDiffusion;

public class Demo {

	public static void main(String[] args) {
		if(args.length != 10) {
			System.out.println("Invalid number of arguments");
			return;
		}
		
		TpdahpDiffusion heatedPlate = new TpdahpDiffusion();
		
		heatedPlate.initialize(args);
		
		CliDisplay.displayHeatedPlate(heatedPlate);		
	}

}
