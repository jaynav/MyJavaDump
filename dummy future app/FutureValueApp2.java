import java.awt.*;
import javax.swing.*;



// displays the frame; need to use java.swing
public class FutureValueApp2 
{
	public static void main(String[]args)
	{
		JFrame frame = new FutureValueFrame();  // creates a frame named frame 
		frame.setVisible(true);      // makes the frame visible or not
	}
}


// defines the frame
class FutureValueFrame extends JFrame  // makes a class as an extension of frame
{ 
	public FutureValueFrame()
	{
		setTitle("my name is microsoft");   // sets the title name
		//setBounds(267, 200, 267, 200);   // moves and resizes
		setSize(267, 200);          // does the same thing as setBounds
		centerWindow(this);  //this centers the window frame
							 // need to do private void centerWindow when using this 
		                // or it will cause an error and not compile
		
		setResizable(true);        // if false i can not resize the window 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// closes the application/program 
													   //when the user closes the window
													   // also make it the default operation
		JPanel panel = new FutureValueThing();// creates a new JPanel named panel; defines the object  
		this.add(panel); // adds this panel to the frame; acts like a link to the frame
						 // at this point i need to make FutureValueThing extends JPanel  to add the buttons
	}

	
	// centers the frame on the screen 
// need to import  java.awt*;
private void centerWindow(Window w)
{
	Toolkit thetool = Toolkit.getDefaultToolkit();     // returns the toolkit object for the current system
	Dimension dede = thetool.getScreenSize();          // gets the screen size
	setLocation((dede.width-w.getWidth())/2, (dede.height-w.getHeight())/3); // number sets the location based
																			// on screen size
}
}


// this allows me to add the buttons
class FutureValueThing extends JPanel  // look a the comment on line 35
{
	private JButton theButton; // declares button objects
	private JButton exitButton;// named the button and exit button
	private JButton anotherButton;
	public FutureValueThing()// this creates button objects and adds them to the frame
	{
		JPanel panelForTheButtons = new JPanel();// sets the panel to equal to panel for the buttons
		panelForTheButtons.setLayout(new FlowLayout(FlowLayout.RIGHT)); // sets the layout to the right 
		this.setLayout(new BorderLayout());//creates a border  layout manager that controls position in 
										   //the window
		theButton = new JButton("hello world this is the button");
		this.add(theButton, BorderLayout.SOUTH); // BorderLayout.south puts the button in bottom portion of the 
											   	 // window
		exitButton = new JButton("exit");
		panelForTheButtons.add(exitButton);// adds it to the panel for the buttons as defined on line 59
		anotherButton = new JButton("hey");
		panelForTheButtons.add(anotherButton);
		this.add(panelForTheButtons, BorderLayout.NORTH);
	}
}
