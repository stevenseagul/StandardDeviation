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
	JTextField answerField;
	JButton calcStandardDeviationButton;
	JButton helpButton;
	
	
	
	
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
			@Override
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
		grid = new GridBagConstraints();
		grid.gridx = 1;
		grid.gridy = 0;
		grid.insets = new Insets(4, 2, 2, 2);
		mainGUI.add(inputField, grid);
		
		answerField = new JTextField(15);
		grid = new GridBagConstraints();
		grid.gridx = 1;
		grid.gridy = 2;
		grid.insets = new Insets(2, 2, 2, 2);
		mainGUI.add(answerField, grid);
	}
	
	
	//initialize swing labels into mainGUI
	public void initalizeLabels()
	{
		inputLabel = new JLabel("Input: ");
		grid = new GridBagConstraints();
		grid.gridx = 0;
		grid.gridy = 0;
		grid.insets = new Insets(2, 6, 2, 2);
		mainGUI.add(inputLabel, grid);
	}
	
	
	//initialize swing buttons into mainGUI
	public void initalizeButtons()
	{
		calcStandardDeviationButton = new JButton("Calculate Deviation");
		grid = new GridBagConstraints();
		grid.gridx = 1;
		grid.gridy = 1;
		grid.insets = new Insets(2, 30, 5, 5);
		mainGUI.add(calcStandardDeviationButton, grid);
		
		
		calcStandardDeviationButton.addActionListener(new ActionListener() {
			double mean;
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ArrayList<Double> processedInputArray = getInput(inputField);
				if (processedInputArray == null)
					System.out.println("nothing usable entered");
				else if (processedInputArray.size() == 0)
					System.out.println("no values put in");
				else
					mean = calcMean(processedInputArray);
					answerField.setText(String.valueOf(calcStandardDeviation(processedInputArray, mean)));
			}
		});
		
		helpButton = new JButton("Help");
		grid = new GridBagConstraints();
		grid.gridx = 0;
		grid.gridy = 1;
		grid.insets = new Insets(0, 5, 5, 0);
		mainGUI.add(helpButton, grid);
		
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				helpPopUp();
			}
		});
		
		
	}
	
	
	//get inputs from textfield
	public ArrayList<Double> getInput(JTextField inputField)
	{
		ArrayList<Double> processedInputArray = new ArrayList<Double>();
		String rawInput = inputField.getText();
		
		boolean proceed = false;
		
		//regular expression to find all whitespace and replace w/ nothing
		rawInput = rawInput.replaceAll("(\\s+)", "");
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
				//System.out.println(rawInput.charAt(i) + " : is last char " + (i == rawInput.length() - 1));
				
				if (rawInput.charAt(i) == ',')
				{
					double processedInputCurrent = Double.valueOf(rawInput.substring(0, i));
					
					
					rawInput = rawInput.substring(i + 1);
					
					i = -1;
					
					
					processedInputArray.add(processedInputCurrent);
				}
				
				
				else if (i == rawInput.length() - 1)
				{
					
					
					double processedInputCurrent = Double.valueOf(rawInput.substring(0, i + 1));
					
					
					rawInput = rawInput.substring(i + 1);
					
					i = -1;

					
					processedInputArray.add(processedInputCurrent);
				}
			}
			
			System.out.println("processedInputArray = " + processedInputArray);
			return processedInputArray;
		}
		else
		{
			return null;
		}
		
		
		
	}
	
	//calc mean for data
	public double calcMean(ArrayList<Double> processedInputArray)
	{
		System.out.println("calcMean ran");
		
		double total = 0;
		
		for (int i = 0; i <= processedInputArray.size() - 1; i++)
		{
			total += processedInputArray.get(i);
		}
		
		System.out.println("mean: " + total / processedInputArray.size());
		return total / processedInputArray.size();
	}
	
	
	
	public double calcStandardDeviation(ArrayList<Double> processedInputArray, double mean)
	{
		System.out.println("calcStandardDeviation ran");

		double totalDifferences = 0.0;
		
		for (int i = 0; i <= processedInputArray.size() - 1; i++)
		{
			totalDifferences += Math.pow((processedInputArray.get(i) - mean), 2);
		}

		System.out.println("standard deviation: " + Math.sqrt(totalDifferences/processedInputArray.size()));
		return Math.sqrt(totalDifferences/processedInputArray.size());
		
	}
	
	
	//warning popup, happens when button clicked
	public void warningPopUp()
	{
		System.out.println("warningPopUp ran");
		
		JFrame warningGUI = new JFrame();
		JLabel warningText = new JLabel("Invalid Characters");
		
		warningGUI.setTitle("!!!");
		warningGUI.setResizable(false);
		warningGUI.getContentPane().setLayout(new GridBagLayout());
		
		//make mainGUI not focusable
		mainGUI.setFocusableWindowState(false);
		calcStandardDeviationButton.setEnabled(false);
		helpButton.setEnabled(false);
		
		grid = new GridBagConstraints();
		grid.gridx = 0;
		grid.gridy = 0;
		grid.insets = new Insets(10, 10, 10, 10);
		warningGUI.add(warningText, grid);
		
		//refocus mainGUI
		warningGUI.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				mainGUI.setFocusableWindowState(true);
				calcStandardDeviationButton.setEnabled(true);
				helpButton.setEnabled(true);
				warningGUI.setVisible(false);
			}
		});
		
		warningGUI.pack();
		warningGUI.setVisible(true);
	}
	
	public void helpPopUp()
	{
		System.out.println("helpPopUp ran");
		
		JFrame helpGUI = new JFrame();
		JLabel helpText = new JLabel("Enter only numbers/decimals, seperate each with inputs with commas");
		
		helpGUI.setTitle("Help");
		helpGUI.setResizable(false);
		helpGUI.getContentPane().setLayout(new GridBagLayout());
		
		//make mainGUI not focusable
		mainGUI.setFocusableWindowState(false);
		calcStandardDeviationButton.setEnabled(false);
		helpButton.setEnabled(false);
		
		helpText.setFont(new Font("Arial", 5, 14));
		grid = new GridBagConstraints();
		grid.gridx = 0;
		grid.gridy = 0;
		grid.insets = new Insets(10, 10, 10, 10);
		helpGUI.add(helpText, grid);
		
		//refocus mainGUI
		helpGUI.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				mainGUI.setFocusableWindowState(true);
				calcStandardDeviationButton.setEnabled(true);
				helpButton.setEnabled(true);
				helpGUI.setVisible(false);
			}
		});
		
		helpGUI.pack();
		helpGUI.setVisible(true);
	}
}
