package edu.gatech.omscs.cs6310.Gallhp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class AboutDialog extends JDialog implements ActionListener{

	/**
	 * A dialog displaying information about the application
	 */
	private static final long serialVersionUID = 6238061178288799788L;

	private final JPanel contentPanel = new JPanel();
	
	private final String aboutData = "<html> "
			+ "<h1>Project 1 - Heated Plate</h1>"
			+ "<br />"
			+ "<h3>Designed by Team 31</h3>"
			+ "<div style=\"text-color:blue; text-size:medium;\">"
			+ "<ul>"
			+ "<li>Jordan Blacker</li>"
			+ "<li>Derek Finlinson</li>"
			+ "<li>Stephen Padgett</li>"
			+ "<li>Bradley Massey</li>"
			+ "</ul>"
			+ "</div>"
			+ "</html>";

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTextPane aboutText = new JTextPane();
			aboutText.setEditable(false);
			aboutText.setContentType("text/html");
			aboutText.setText(aboutData);
			contentPanel.add(aboutText, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setAlwaysOnTop(true);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);		
	}

}
