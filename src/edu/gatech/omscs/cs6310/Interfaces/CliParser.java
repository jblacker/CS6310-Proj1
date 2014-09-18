package edu.gatech.omscs.cs6310.Interfaces;

public class CliParser {
	
	/**
	 * Initialize the dimension and initial temperatures of the heated plate
	 * @param args
	 */
	public static void initialize(String[] args, HeatedPlate plate) {
		// Loop over arguments and initialize the plate
		for(int i = 0; i < args.length; i += 2) {
			int temp;
			
			try {
				temp = Integer.parseInt(args[i+1]);
			}
			catch(NumberFormatException ex) {
				throw new NumberFormatException("Expected an integer every other argument, invalid input. Read: " + args[i+1]);
			}	
			
			switch(args[i]) {
				case "-d":
				case "-D":
					if(temp >= 1) {
						plate.setDimension(temp);
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
						plate.setLeftEdgeTemp(temp);
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
						plate.setRightEdgeTemp(temp);
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
						plate.setBottomEdgeTemp(temp);
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
						plate.setTopEdgeTemp(temp);
						break;
					}
			}
		}
	}
}