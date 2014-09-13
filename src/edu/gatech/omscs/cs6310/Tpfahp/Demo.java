package edu.gatech.omscs.cs6310.Tpfahp;

import edu.gatech.omscs.cs6310.Interfaces.CliDisplay;

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
		
		TpfahpDiffusion heatedPlate = new TpfahpDiffusion();
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
						heatedPlate.setLeftEdgeTemp(temp);
						break;
					}
					else {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
				case "-r":
				case "-R":
					if(temp > 100 || temp < 0) {
						heatedPlate.setRightEdgeTemp(temp);
						break;
					}
					else {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
				case "-b":
				case "-B":
					if(temp > 100 || temp < 0) {
						heatedPlate.setBottomEdgeTemp(temp);
						break;
					}
					else {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
				case "-t":
				case "-T":
					if(temp > 100 || temp < 0) {
						heatedPlate.setTopEdgeTemp(temp);
						break;
					}
					else {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}	
			}
		}
		
		CliDisplay.displayHeatedPlate(heatedPlate);
		System.out.format("Took % ns to calculate diffusion");
	}
}
