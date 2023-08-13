import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RC4 extends JFrame
{

	private static final long serialVersionUID = 1L;

	//GUI components for getting the state size from the users
	private JLabel state;
	private JTextField value;
	private JTextArea output;
	private JButton execute,exit;
	private Frame aFrame;

	//handlers for buttons
	private ExecuteButtonHandler exeHandler;
	private ExitButtonHandler ebHandler;

	// initialization of i and j to be zero

	int i = 0;
	int j = 0;

	int keylength = 256;					//generate key of length 256 bytes
	int key[] = new int[keylength];		//this array contains the value for the key generated randomly using math functions in java


	// statesize is the size of the state
	public int statesize;

	//result string variable holds the result of the PRGA and IPRGA algorithm
	public String result = "\n";

	//initializing the state size to be 256. This array holds the values for the RC4 state
	public int s[]= new int[256];

	//PRGA alorithm takes statesize and state array as arguments and performs PRGA 
	public void prga(int state,int s[]) 
	{      
		result += "Running the PRGA algorithm \n";
		//Initializing the counter
		int count = state;  
		//perform looping operation
		while (count > 0) 
		{
			//assigning new values to i and j
			i = (i + 1) % state;
			j = (j + s[i]) % state;
			//swapping operation for PRGA
			int temp = s[j];
			s[j] = s[i];
			s[i] = temp;                    

			count--;

			// prints out the final state in PRGA after swap
			if(count == 0) {
				result+="    State = {";
				for (int d = 0; d < state; d++) 
				{
					result+=s[d] + ",";
				}
				//formatting of output
				result+="}";
				result+="i = " + i + " j = " + j  + "          "+"\n";

			}
		}

	}
	//IPRGA alorithm takes user input statesize and state array as argument and performs IPRGA
	public void iprga(int state,int s[]) 
	{
		result += "\nRunning the IPRGA algorithm \n";
		//Initializing the counter
		int count = state;

		//Performing loop operation      
		while (count > 0) 
		{      
			// prints out the first state in IPRGA after swap
			if(count == state) {
				result+="    State = {";
				for (int d = 0; d < state; d++) {                
					result+=s[d] + ",";
				}
				//formatting of output
				result+="}";
				result+="i = " + i + " j = " + j  + "          "+"\n";
			}

			//Swapping
			int temp = s[j];
			s[j] = s[i];
			s[i] = temp;

			//assigning new values to i and j and passing through fuction NegNumber
			j = NegNumber((j - s[i] + state) % state);
			i = NegNumber((i - 1) % state);
			//decrementing the initialized value 'count'
			count--;
		}
	}

	//To determines if the number gotten is -ve, if -ve it returns the number in addition with the input state
	//and returns back the same number if positive
	public int NegNumber(int number) {
		return number < 0 ? (statesize + number) : number;
	}

	//class for GUI
	public RC4()
	{
		state = new JLabel("Enter the size for RC4 state: ", SwingConstants.RIGHT);
		value = new JTextField(10);
		//SPecify handlers for each button and add (register) ActionListeners to each button.
		execute = new JButton("Execute");
		exeHandler = new ExecuteButtonHandler();
		execute.addActionListener(exeHandler);
		exit = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exit.addActionListener(ebHandler);
		output = new JTextArea(result);
		//JScrollPane scroll = new JScrollPane(result); //place the JTextArea in a scroll pane            
		setTitle("PRGA and IPRGA implementation");            
		JPanel labelPane = new JPanel();
		labelPane.add(state);
		JPanel fieldPane = new JPanel();
		fieldPane.add(value);
		JPanel buttonPane = new JPanel();
		buttonPane.add(execute);
		buttonPane.add(exit);
		setLayout(new BorderLayout());
		add(labelPane, BorderLayout.CENTER);
		add(fieldPane, BorderLayout.LINE_END);
		add(buttonPane, BorderLayout.PAGE_END);            
		setSize(400, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		aFrame = new Frame("Result");
		aFrame.setLocationRelativeTo(null);      
	}        

	//Class for the execute button
	private class ExecuteButtonHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			//this gets the input value entered by the user from the text box 
			statesize = Integer.parseInt(value.getText());        
			String internalState = "";
			String outputState = "";
			System.out.println("Initial state before performing KSA: ");
			internalState = "State = {";
			
			// Initializing the state array s
			for (int l=0; l<statesize; l++)
			{
				s[l] = l;
				internalState = internalState+s[l]+",";
			}
			outputState = internalState.substring(0, internalState.length() - 1);
			outputState+= "}";
			System.out.println(outputState);
			internalState = "";
			outputState = "";

			//int key[] = {2,5,7,4,1,0,3,6};
			//generating key of 256 length randomly using math functions in java
			for(int m=0; m < keylength; m++) {
				key[m] = (int) Math.floor(Math.random() * Math.floor(10));
			}

			int T[] = new int[statesize];
			//creating a temporary array T of size equal to state size
			for(int i = 0; i < statesize; i++) {
				T[i] = key[i%key.length];
			}
			//perform swapping operation as per key-scheduling algorithm (KSA)
			int x = 0;
			for(int i = 0; i < statesize; i++) {
				x = (x+s[i] + T[i]) % statesize;
				int temp = s[x];
				s[x] = s[i];
				s[i] = temp;
			}

			System.out.println("Internal state from KSA after initial permutation: ");
			internalState = "State = {";
			for(int y = 0; y < statesize; y++) {
				internalState = internalState+s[y]+",";
			}
			outputState = internalState.substring(0, internalState.length() - 1);
			outputState+= "}";

			System.out.print(outputState);
			// Calling PRGA function to perform PRGA algorithm 
			prga(statesize,s);
			// Calling IPRGA function to perform IPRGA algorithm
			iprga(statesize,s);
			output.setText("" + result);

			//displaying the output in a frame window
			aFrame.add(output);
			aFrame.pack();
			aFrame.setVisible(true);

			//Displaying the output in the console
			System.out.print(result);

		}
	}

	//Class for the exit button
	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	//main class for RC4
	public static void main(String[] args)
	{
		RC4 rc4 = new RC4();
	}

}
