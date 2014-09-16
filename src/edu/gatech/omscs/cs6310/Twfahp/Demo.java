package edu.gatech.omscs.cs6310.Twfahp;

import edu.gatech.omscs.cs6310.Interfaces.CliDisplay;
import edu.gatech.omscs.cs6310.Interfaces.PlateNotInitializedException;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 10)
		{
			System.out.println("Invalid number of arguments");
			return;
		}
		
		TwafhpDiffusion heatedPlate = new TwafhpDiffusion();
		
		for(int i = 0; i < args.length; i += 2) {
			int temp;
			
			try {
				temp = Integer.parseInt(args[i+1]);
			}
			catch(NumberFormatException ex) {
				System.out.println("Expected an integer every other argument, invalid input.");
				return;
			}	
			
			switch(args[i]) {
				case "-d":
				case "-D":
					if(temp >= 1) {
						heatedPlate.setDimension(temp);
						break;
					}
					else {
						System.out.println("Dimension argument must be greater than or equal to 1");
						System.exit(1);
						return;
					}
				case "-l":
				case "-L":
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						heatedPlate.setLeftEdgeTemp(temp);
						break;
						
					}
				case "-r":
				case "-R":
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						heatedPlate.setRightEdgeTemp(temp);
						break;
						
					}
				case "-b":
				case "-B":
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						heatedPlate.setBottomEdgeTemp(temp);
						break;
						
					}
				case "-t":
				case "-T":
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						heatedPlate.setTopEdgeTemp(temp);
						break;
						
					}	
			}
		}

		try {
			heatedPlate.calculateDiffusion();
			
			CliDisplay.displayHeatedPlate(heatedPlate);
		} catch (PlateNotInitializedException e) {
			System.out.println(e.getMessage());
			System.exit(1);
			return;
		}
	}

}
