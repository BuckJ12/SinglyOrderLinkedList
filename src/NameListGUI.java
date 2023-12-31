import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameListGUI {
	private SortedLinkedList nameList;
	private JFrame frame;
	private JTextArea outputTextArea;
	private JTextField inputTextField;
	private final String COMMANDS="Commands\n" +
			"  h or * shows this Help Menu\n"+
			"  ? Checks if List Is in order\n"+
			"  s or | Sorts The List\n"+
			"  +(Item) Inserts Item into List in alphabetical order\n"+
			"  -(Item) Removes Specified Item from List\n"+
			"  @ Prints the List\n"+
			"  <(Item) Puts item at the front of the list\n"+
			"  >(Item) Appends Item to the End of the list\n"+
			"  ^ Removes First Item in the List\n"+
			"  ? Checks if List is in Alphabetical Order\n"+
			"  ~ Clears Output Area\n"+
			"  ! Clears The List\n"+
			"  . or x or q or e Exits Program\n";

	public NameListGUI() {
		nameList = new SortedLinkedList();
		// Set the font and font size
		Font textFont = new Font("Verdana", Font.PLAIN, 20); 
		frame = new JFrame("Name List");
		frame.setSize(1200, 900);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);

		
		outputTextArea.setFont(textFont);
		outputTextArea.setText(COMMANDS);
		
		frame.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);


		inputTextField = new JTextField();
		inputTextField.setFont(textFont);


		inputTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processCommand(inputTextField.getText());
				inputTextField.setText("");
			}
		});
		frame.add(inputTextField, BorderLayout.SOUTH);

		frame.setVisible(true);


		frame.requestFocus();
		inputTextField.requestFocusInWindow();
	}

	private void processCommand(String input) {
		String line = input.trim();

		// Ignore empty input
		if (line.length() == 0) {
			return; 
		}

		char ch = line.charAt(0);
		String name = capitalizeFirstLetter(line.substring(1).trim());

		switch (ch) {
		case '@':
			outputTextArea.append(nameList.getList() + "\n");
			break;
		case '+':
			nameList.insertInOrder(name);
			outputTextArea.append("Inserted " + name + "\n");
			break;
		case '-':
			if (nameList.remove(name)){
				outputTextArea.append("Removed " + name + "\n");
			} else {
				outputTextArea.append(name + " Not Found in List\n");
			}
			break;
		case '<':
			nameList.insertFront(name);
			outputTextArea.append("Inserted " + name + " at begining of list \n");
			break;
		case '>':
			nameList.insertRear(name);
			outputTextArea.append("Inserted " + name + " at End of list \n");
			break;
		case '^':

			outputTextArea.append("Removed " + nameList.removeFront()+ " from front\n");
			break;
		case '?':
			if (nameList.isInOrder()) {
				outputTextArea.append("The list is in alphabetical order.\n");
			} else {
				outputTextArea.append("The list is not in alphabetical order.\n");
			}
			break;
		case 's':
		case '|':
			String strTemp;
			outputTextArea.append((strTemp=nameList.getList()) + "\n");
			if(!nameList.isInOrder()) {
				nameList = nameList.Sort();
				outputTextArea.append("|Sorting List|\n");
				outputTextArea.append(nameList.getList() + "\n");
			}else if(strTemp!="List is empty"){
				outputTextArea.append("|List Already Sorted|\n");
			}

			break;
		case '~':
			outputTextArea.setText("");
			break;
		case 'h':
		case '*':
			outputTextArea.append(COMMANDS);
			break;
		case '!':
			nameList=new SortedLinkedList();
			outputTextArea.append(nameList.getList() + "\n");
			break;
		case '.':
		case 'x':
		case 'q':
		case 'e':
			System.exit(0);
			break;
		default:
			outputTextArea.append("Invalid command: " + ch + "\n" + "  h or * shows the Help Menu\n" );
		}
	}

	public static String capitalizeFirstLetter(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		// Capitalize the first letter and convert the rest to lowercase
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new NameListGUI();
			}
		});
	}
}
