import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*;

public class invoiceApp 
// displays the frame; need to use java.swing

{
	public static void main(String[]args)
	{
		JFrame frame = new InvoiceCalculatorFrame();  				// creates a frame named frame 
		frame.setVisible(true);      							// makes the frame visible or not
	}
}


// defines the frame
class InvoiceCalculatorFrame extends JFrame  							// makes a class as an extension of frame 
{ 
	public InvoiceCalculatorFrame()
	{
		setTitle("Invoice Total Calculator");   				// sets the title name
		setBounds(267, 200, 267, 200);   						// moves and resizes
		//setSize(267, 200);          							// does the same thing as setBounds
		centerWindow(this);  									//this centers the window frame
							 									// need to do private void centerWindow when using this 
		                										// or it will cause an error and not compile
		
		setResizable(false);        							// if false i can not resize the window 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// closes the application/program 
													   			//when the user closes the window
													   			// also make it the default operation
		JPanel panel = new invoiceCalculator();					// creates a new JPanel named panel; defines the object  
		this.add(panel); 										// adds this panel to the frame; acts like a link to the frame
						 										// at this point i need to make FutureValueThing extends JPanel  to add the buttons
	}

// centers the frame on the screen 
// need to import  java.awt*;
private void centerWindow(Window w)
{
	Toolkit thetoolKit = Toolkit.getDefaultToolkit();     			// returns the toolkit object for the current system
	Dimension screenSize = thetoolKit.getScreenSize();          			// gets the screen size
	setLocation((screenSize.width-w.getWidth())/2, (screenSize.height-w.getHeight())/2); // number sets the location based on screen size

}
}

// this allows me to add the buttons
class invoiceCalculator extends JPanel implements ActionListener // look a the comment on line 34
{
	private JButton CalculateButton; 							// declares button objects and
	private JButton exitButton;									// named the button and exit button
	
	private JLabel SubtotalLabel;
	private JLabel DiscountPercentLabel;
	private JLabel DiscountLabel;
	private JLabel InvoiceTotalLabel;
	
	private JTextField SubtotalTextField;
	private JTextField DiscountPercentTextField;
	private JTextField DiscountLabelTextField;
	private JTextField InvoiceTotalLabelTextField;
	
	public invoiceCalculator()	// this creates button objects and adds them to the frame
	{
		JPanel displayButton = new JPanel();					// sets the panel to equal to panel for the buttons
		JPanel displayTextAndLabel = new JPanel();  			// displays text and label
		
		displayButton.setLayout(new FlowLayout(FlowLayout.RIGHT)); // sets the layout to the right 
		displayTextAndLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	
		this.setLayout(new BorderLayout());						//creates a border  layout manager that controls position in the window
		this.add(displayTextAndLabel, BorderLayout.CENTER);
		this.add(displayButton, BorderLayout.SOUTH); 			
		
		CalculateButton = new JButton("Calculate");// creates a button
		exitButton = new JButton("Exit");
	
		CalculateButton.addActionListener(this);  //implements the action listener; need to import java.awt.event 
		exitButton.addActionListener(this);
		
		displayButton.add(CalculateButton);
		displayButton.add(exitButton);						// adds it to the panel for the buttons as defined on line 59
		
		SubtotalLabel = new JLabel("Subtotal:");  				// sets title
		DiscountPercentLabel = new JLabel("Discount Percent:");
		DiscountLabel = new JLabel("Discount:");
		InvoiceTotalLabel = new JLabel ("   Invoice Total:");
		SubtotalTextField = new JTextField(10); 				//sets the text field to ten columns
		DiscountPercentTextField = new JTextField(10);
		DiscountLabelTextField = new JTextField(10);
		InvoiceTotalLabelTextField = new JTextField(10);
	
		DiscountPercentTextField.setEditable(false); 			// set field so it can not be edited
		DiscountLabelTextField.setEditable(false);
		InvoiceTotalLabelTextField.setEditable(false); 
		
		displayTextAndLabel.add(SubtotalLabel); 				// adds it to displayTextAndLabel
		displayTextAndLabel.add(SubtotalTextField);
		displayTextAndLabel.add(DiscountPercentLabel);
		displayTextAndLabel.add(DiscountPercentTextField);
		displayTextAndLabel.add(DiscountLabel);
		displayTextAndLabel.add(DiscountLabelTextField);
		displayTextAndLabel.add(InvoiceTotalLabel);
		displayTextAndLabel.add(InvoiceTotalLabelTextField);
		
	}

	// setting a how to handle an event  for the buttons
	public void actionPerformed(ActionEvent e) 
	{
			Object source = e.getSource();
			if (source == exitButton)
				System.exit(0);     // closes the application
			else if (source == CalculateButton)
				{
				double subtotal = Double.parseDouble(SubtotalTextField.getText());
				double discountPercent = 0;
				if (subtotal >= 200)
					discountPercent = .2;
				else if (subtotal >= 100)
					discountPercent = .1;
				else 
					discountPercent = 0;
			double discountAmount = subtotal* discountPercent;
			double InvoiceTotal = subtotal - discountAmount;
			NumberFormat currency = NumberFormat.getCurrencyInstance(); // need this in order to display in text field
			NumberFormat percent = NumberFormat.getPercentInstance();
			
			//display on application
			DiscountPercentTextField.setText(percent.format(discountPercent));
			DiscountLabelTextField.setText(currency.format(discountAmount));
			InvoiceTotalLabelTextField.setText(currency.format(InvoiceTotal));	
				}
	
	}
}
