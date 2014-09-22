package interfaces;

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
				temp = Integer.parseInt(args[i + 1]);
			}
			catch(NumberFormatException ex) {
				throw new NumberFormatException("Expected an integer every other argument, invalid input. Read: " + args[i+1]);
			}	
			
			if(args[i].equals("-d") || args[i].equals("-D")) {
					if(temp >= 1) {
						plate.setDimension(temp);
					}
					else {
						System.out.println("Dimension argument must be greater than or equal to 1");
						System.exit(1);
						return;
					}
			}
			else if(args[i].equals("-l") || args[i].equals("-L")) {
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						plate.setLeftEdgeTemp(temp);
					}
			}
			else if(args[i].equals("-r") || args[i].equals("-R")) {
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						plate.setRightEdgeTemp(temp);
					}
			}
			else if(args[i].equals("-b") || args[i].equals("-B")) {
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						plate.setBottomEdgeTemp(temp);						
					}
			}
			else if(args[i].equals("-t") || args[i].equals("-T")) {
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						plate.setTopEdgeTemp(temp);
					}
			}
		}
	}
}