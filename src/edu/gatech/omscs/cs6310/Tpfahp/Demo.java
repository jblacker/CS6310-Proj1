package edu.gatech.omscs.cs6310.Tpfahp;

import java.util.regex.*;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length < 10 || args.length > 10)
		{
			System.out.println("Invalid number of arguments");
			return;
		}
		
		TpfahpDiffusion heatedPlate = new TpfahpDiffusion();
		try {
			for(int i = 0; i < args.length; i += 2) {
				if(args[i] == "-d" || args[i] == "-D") {
					int dim = Integer.parseInt(args[i+1]);
					if(dim >= 1) {
						heatedPlate.setDimension(dim);
					}
					else {
						System.out.println("Dimensions must be greater or equal to 1");
					}
				}
				else if(args[i] == "-l" || args[i] == "-L") {
					int left = Integer.parseInt(args[i+1]);
					if(left >= 0 || left <= 100) {
						heatedPlate.setLeftEdgeTemp(left);
					}
					else {
						//TODO: Finish or change to SWITCH if we upgrade to JDK 7
					}
				}
			}
		}
		catch(NumberFormatException ex) {
			System.out.println("Expecting an integer, invalid argument passed.");
			return;
		}
	}

}
