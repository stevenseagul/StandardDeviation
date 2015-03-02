package standardDeviation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;


public class StandardDeviation 
{
	GridBagConstraints grid = new GridBagConstraints();
	JTextField inputField = new JTextField(15);
	
	
	
	public static void main(String[] args)
	{
		new StandardDeviation();
	}
	
	
	
	public StandardDeviation()
	{
		//init mainGUI
		JFrame mainGUI = new JFrame();
		mainGUI.getContentPane().setLayout(new GridBagLayout());
		mainGUI.setTitle("Standard Deviation");
		mainGUI.setResizable(false);
		mainGUI.setSize(400, 400);
		
		
		initalizeTextFields(mainGUI);
		initalizeLabels(mainGUI);
		initalizeButtons(mainGUI);
		
		
		mainGUI.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		
		mainGUI.pack();
		mainGUI.setVisible(true);
	}
	
	
	
	public void initalizeTextFields(JFrame mainGUI)
	{
		grid.gridx = 0;
		grid.gridy = 0;
		mainGUI.add(inputField, grid);
	}
	
	
	
	public void initalizeLabels(JFrame mainGUI)
	{
		
	}
	
	
	
	public void initalizeButtons(JFrame mainGUI)
	{
		JButton calcStandardDeviationButton = new JButton("Calculate Deviation");
		grid.gridx = 0;
		grid.gridy = 1;
		grid.insets = new Insets(5, 5, 5, 5);
		mainGUI.add(calcStandardDeviationButton, grid);
		
		calcStandardDeviationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				getInput(inputField);
				calcStandardDeviation();
			}
		});
	}
	
	
	
	public ArrayList<Double> getInput(JTextField inputField)
	{
		ArrayList<Double> processedInput = new ArrayList<Double>();
		String rawInput = inputField.getText();
		String garbage = "";
		
		
		for (int i = 0; i <= rawInput.length() - 1; i++)
		{
			//regular expression to find all whitespace and replace w/ nothing
			rawInput = rawInput.replaceAll("\\s+", "");
			
			
			if (rawInput.charAt(i) == ',')
			{
				double processedDouble = Double.valueOf(rawInput.substring(0, i));
				
				garbage = garbage + rawInput.substring(0, i);
				rawInput = rawInput.substring(i + 1);
				i = 0;
				
				
				System.out.println(processedDouble);
				
				processedInput.add(processedDouble);
			}
			else if (i == rawInput.length() - 1)
			{
				double processedDouble = Double.valueOf(rawInput.substring(0, i + 1));
				
				garbage = garbage + rawInput.substring(0, i + 1);
				rawInput = rawInput.substring(i + 1);
				i = 0;
				
				
				System.out.println(processedDouble);
				
				processedInput.add(processedDouble);
			}
		}
		
		
		
		System.out.println(processedInput);
		return processedInput;
		
	}
	
	
	
	public void calcStandardDeviation()
	{
		
	}
	
	
}
