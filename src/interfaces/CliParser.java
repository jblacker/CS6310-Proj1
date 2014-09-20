package interfaces;

public class CliParser {
	
	/**
	 * Initialize the dimension and initial temperatures of the heated plate
	 * @param args
	 */
	public static void initialize(String[] args, HeatedPlate plate) {
		// Loop over arguments and initialize the plate
		for(int i = 0; i < args.length; i++) {
			int temp;
			
			try {
				temp = Integer.parseInt(args[++i]);
			}
			catch(NumberFormatException ex) {
				throw new NumberFormatException("Expected an integer every other argument, invalid input. Read: " + args[i+1]);
			}	
			
			if(args[i] == "-d" || args[i] == "-D") {
					if(temp >= 1) {
						plate.setDimension(temp);
					}
					else {
						System.out.println("Dimension argument must be greater than or equal to 1");
						System.exit(1);
						return;
					}
			}
			else if(args[i] == "-l" || args[i] == "-L") {
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						plate.setLeftEdgeTemp(temp);
					}
			}
			else if(args[i] == "-r" || args[i] == "-R") {
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						plate.setRightEdgeTemp(temp);
					}
			}
			else if(args[i] == "-b" || args[i] == "-B") {
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						plate.setBottomEdgeTemp(temp);						
					}
			}
			else if(args[i] == "-t" || args[i] == "-T") {
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