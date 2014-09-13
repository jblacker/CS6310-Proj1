package edu.gatech.omscs.cs6310.Gallhp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TemperaturePlate extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1968715371000403908L;

	/**
	 * Create the panel.
	 */
	public TemperaturePlate(float temp) {
		this.setLayout(new BorderLayout());
		
		Color background = generateTemperatureColor(temp);
		this.setBackground(background);
		
		JLabel tempLabel = new JLabel(String.format("%.2f", temp), SwingConstants.CENTER);
		Font labelFont = tempLabel.getFont();
		tempLabel.setFont(labelFont.deriveFont(labelFont.getStyle() | Font.BOLD));
		
		this.add(tempLabel, BorderLayout.CENTER);
	}
	
	private static Color generateTemperatureColor(float temp) {
		int t = (int)Math.floor(temp);
		
		int red = (255 * t) / 100;
		int blue = (255 * (100 - t)) / 100;
		
		return new Color(red, 0, blue);
	}

}
