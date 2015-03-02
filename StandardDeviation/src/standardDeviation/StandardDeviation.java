package standardDeviation;

//Jared Jacobsen
//2015-3-1 Standard Deviation

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;


public class StandardDeviation 
{
	GridBagConstraints grid = new GridBagConstraints();
	
	JFrame mainGUI;
	JLabel inputLabel;
	JTextField inputField;
	JButton calcStandardDeviationButton;
	
	
	
	
	public static void main(String[] args)
	{
		new StandardDeviation();
	}
	
	
	
	public StandardDeviation()
	{
		//init mainGUI
		mainGUI = new JFrame();
		mainGUI.getContentPane().setLayout(new GridBagLayout());
		mainGUI.setTitle("Standard Deviation");
		mainGUI.setResizable(false);
		mainGUI.setSize(400, 400);
		
		
		initalizeTextFields();
		initalizeLabels();
		initalizeButtons();
		
		//proper exit
		mainGUI.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		
		mainGUI.pack();
		mainGUI.setVisible(true);
	}
	
	
	//initialize swing textfields into mainGUI
	public void initalizeTextFields()
	{
		inputField = new JTextField(15);
		grid.gridx = 1;
		grid.gridy = 0;
		grid.insets = new Insets(4, 2, 2, 2);
		mainGUI.add(inputField, grid);
	}
	
	
	//initialize swing labels into mainGUI
	public void initalizeLabels()
	{
		inputLabel = new JLabel("Input: ");
		grid.gridx = 0;
		grid.gridy = 0;
		grid.insets = new Insets(2, 6, 2, 2);
		mainGUI.add(inputLabel, grid);
	}
	
	
	//initialize swing buttons into mainGUI
	public void initalizeButtons()
	{
		calcStandardDeviationButton = new JButton("Calculate Deviation");
		grid.gridx = 1;
		grid.gridy = 1;
		grid.insets = new Insets(2, 30, 5, 5);
		mainGUI.add(calcStandardDeviationButton, grid);
		
		
		calcStandardDeviationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ArrayList<Double> processedDouble = getInput(inputField);
				if (processedDouble == null)
					System.out.println("nothing usable entered");
				else if (processedDouble.size() == 0)
					System.out.println("no values put in");
				else
					calcStandardDeviation(processedDouble);
			}
		});
		
		
	}
	
	
	//get inputs from textfield
	public ArrayList<Double> getInput(JTextField inputField)
	{
		ArrayList<Double> processedInput = new ArrayList<Double>();
		String rawInput = inputField.getText();
		String garbage = "";
		
		boolean proceed = false;
		
		//regular expression to find all whitespace and replace w/ nothing
		rawInput = rawInput.replaceAll("\\s+", "");
		if (rawInput.matches("\\w+"))
		{
			warningPopUp();
			proceed = false;
		}
		else
		{
			proceed = true;	
		}
			
		
		
		
		if (proceed == true)
		{
			
			//go thru string and get values
			for (int i = 0; i <= rawInput.length() - 1; i++)
			{
				if (rawInput.charAt(i) == ',')
				{
					double processedDouble = Double.valueOf(rawInput.substring(0, i));
					
					garbage = garbage + rawInput.substring(0, i);
					rawInput = rawInput.substring(i + 1);
					i = 0;
					
					
					processedInput.add(processedDouble);
				}
				else if (i == rawInput.length() - 1)
				{
					double processedDouble = Double.valueOf(rawInput.substring(0, i + 1));
					
					garbage = garbage + rawInput.substring(0, i + 1);
					rawInput = rawInput.substring(i + 1);
					i = 0;

					
					processedInput.add(processedDouble);
				}
			}
			
			return processedInput;
		}
		else
		{
			return null;
		}
		
		
		
	}
	
	
	
	public void calcStandardDeviation(ArrayList<Double> processedDouble)
	{
		
	}
	
	
	//warning popup, happens when button clicked
	public void warningPopUp()
	{
		JFrame warningGUI = new JFrame();
		JLabel warningText = new JLabel("Invalid Characters");
		
		warningGUI.setTitle("!!!");
		warningGUI.setResizable(false);
		warningGUI.getContentPane().setLayout(new GridBagLayout());
		
		//make mainGUI not focusable
		mainGUI.setFocusableWindowState(false);
		calcStandardDeviationButton.setEnabled(false);
		
		grid.gridx = 0;
		grid.gridy = 0;
		warningGUI.add(warningText, grid);
		
		//refocus mainGUI
		warningGUI.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				mainGUI.setFocusableWindowState(true);
				calcStandardDeviationButton.setEnabled(true);
			}
		});
		
		warningGUI.setSize(120, 50);
		warningGUI.setVisible(true);
	}
}
