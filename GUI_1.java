/* Om Choksi
   CNT 4714 - Spring 2021
   Project 1 - Event-driven Enterprise Simulation
   Saturday, January 30th, 2021
*/
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.TextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.List; 
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;

public class GUI_1 {
	
	//Visual GUI aspects
	private JFrame frmNileDotCom;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnNewButton;
	private JButton btnConfirmItem;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_1;
	
	//Rounding to 2 decimals
	public static DecimalFormat df = new DecimalFormat("###.00");
	
	//Variable
	public int itemNum;
	public int progress;
	public int numItems;
	public int quantity;
	public int iD;
	public double totalPrice;
	public double discount;
	public double sum;
	public int indexHolder;
	
	//Lists to capture data from inventory
	public static List<Integer> itemID = new ArrayList<>();
	public static List<String> description = new ArrayList<>();
	public static List<Boolean> status = new ArrayList<>();
	public static List<Double> price = new ArrayList<>();
	
	//Lists to capture program data
	public static List<Integer> CitemID = new ArrayList<>();
	public static List<String> Cdescription = new ArrayList<>();
	public static List<String> CviewOrder = new ArrayList<>();
	public static List<String> CommaViewOrder = new ArrayList<>();
	public static List<Double> Cprice = new ArrayList<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException{
		//Reading in inventory file
		File inventoryFile = new File("inventory.txt");
		Scanner sc = new Scanner(inventoryFile);
		sc.useDelimiter(",|\\n");

		
		while(sc.hasNextLine()) {
			itemID.add(Integer.parseInt(sc.next()));
			description.add(sc.next());
			status.add(Boolean.parseBoolean(sc.next().replaceAll(" ", "")));
			price.add(Double.parseDouble(sc.next()));
			sc.nextLine();
		}
		sc.close();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_1 window = new GUI_1();
					window.frmNileDotCom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public GUI_1() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		//Writing to file
		Writer wr = new FileWriter("transactions.txt");
		
		frmNileDotCom = new JFrame();
		frmNileDotCom.setTitle("Nile Dot Com - Spring 2021");
		frmNileDotCom.setBounds(100, 100, 872, 493);
		frmNileDotCom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNileDotCom.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter number of items on this order:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(80, 101, 230, 27);
		frmNileDotCom.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField.setDisabledTextColor(Color.BLACK);
		textField.setSelectionColor(Color.BLACK);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
			}
		});
		textField.setForeground(new Color(204, 204, 0));
		textField.setBounds(320, 101, 528, 27);
		frmNileDotCom.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter item ID for item #1:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(80, 137, 230, 27);
		frmNileDotCom.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter quantity for item #1:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(80, 174, 230, 27);
		frmNileDotCom.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Item #1 info\r\n: ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(80, 211, 230, 27);
		frmNileDotCom.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Order subtotal for 0 item(s):");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(80, 248, 230, 27);
		frmNileDotCom.getContentPane().add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_1.setDisabledTextColor(Color.BLACK);
		textField_1.setSelectionColor(Color.BLACK);
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(textField_1.getText());
			}
		});
		textField_1.setForeground(new Color(204, 204, 0));
		textField_1.setColumns(10);
		textField_1.setBounds(320, 137, 528, 27);
		frmNileDotCom.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_2.setDisabledTextColor(Color.BLACK);
		textField_2.setSelectionColor(Color.BLACK);
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(textField_1.getText());
			}
		});
		textField_2.setForeground(new Color(204, 204, 0));
		textField_2.setColumns(10);
		textField_2.setBounds(320, 174, 528, 27);
		frmNileDotCom.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_3.setDisabledTextColor(Color.BLACK);
		textField_3.setSelectionColor(Color.BLACK);
		textField_3.setForeground(new Color(204, 204, 0));
		textField_3.setColumns(10);
		textField_3.setBounds(320, 211, 528, 27);
		frmNileDotCom.getContentPane().add(textField_3);
		textField_3.setEnabled(false);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_4.setDisabledTextColor(Color.BLACK);
		textField_4.setSelectionColor(Color.BLACK);
		textField_4.setForeground(new Color(204, 204, 0));
		textField_4.setColumns(10);
		textField_4.setBounds(320, 248, 528, 27);
		frmNileDotCom.getContentPane().add(textField_4);
		textField_4.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setBounds(0, 360, 848, 86);
		frmNileDotCom.getContentPane().add(panel);
		panel.setLayout(null);
		
		itemNum = 1;
		
		btnNewButton = new JButton("Process item #" + itemNum);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexHolder = 0;

				//Checking for negative values and blank values
				while (Integer.parseInt(textField.getText()) < 0 || textField.getText().equals( "" )) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number of items", "Message", JOptionPane.INFORMATION_MESSAGE);
					textField.setText("");
				}
				while (Integer.parseInt(textField_1.getText()) < 0 || textField.getText().equals( "" )) {
					JOptionPane.showMessageDialog(null, "Please enter a non-negative ID", "Message", JOptionPane.INFORMATION_MESSAGE);
					textField_1.setText("");
				}
				while (Integer.parseInt(textField_2.getText()) < 0 || textField.getText().equals( "" )) {
					JOptionPane.showMessageDialog(null, "Please enter a valid value of quantity", "Message", JOptionPane.INFORMATION_MESSAGE);
					textField_2.setText("");
				}
				
				//Checking for valid ID and getting ID info
				while (iD == 0) {
					for (int i = 0; i < itemID.size(); i++)
					{
						if (itemID.get(i) == Integer.parseInt(textField_1.getText())) {
							iD = Integer.parseInt(textField_1.getText());
							indexHolder = i;
							break;
						}
					}
					if (iD == 0) {
						JOptionPane.showMessageDialog(null, "Item ID " + Integer.parseInt(textField_1.getText()) + " not in file", "Message", 
								JOptionPane.INFORMATION_MESSAGE);
						textField_1.setText("");
					}
					else if (status.get(indexHolder) == false) {
						JOptionPane.showMessageDialog(null, "Sorry... that item is out of stock, please try another item", "Message", JOptionPane.INFORMATION_MESSAGE);
						textField_1.setText("");
						textField_2.setText("");
						iD = 0;
					}
				}
				
				//Checking if item is in stock
				/*
				while (status.get(indexHolder) == false) {
					JOptionPane.showMessageDialog(null, "Sorry... that item is out of stock, please try another item", "Message", JOptionPane.INFORMATION_MESSAGE);
					textField_1.setText("");
					textField_2.setText("");
				}
				*/
				
				//Gathering info from other text fields
				numItems = Integer.parseInt(textField.getText());
				quantity = Integer.parseInt(textField_2.getText());
				
				//Getting discount
				if (quantity >= 1 && quantity <= 4) {
					discount = 0.0;
				}
				else if (quantity >= 5 && quantity <= 9) {
					discount = 0.10;
				}
				else if (quantity >= 10 && quantity <= 14) {
					discount = 0.15;
				}
				else {
					discount = 0.20;
				}
				
				//Item info logic
				totalPrice = price.get(indexHolder) * quantity;
				totalPrice = totalPrice - (totalPrice * discount);
				textField_3.setText(iD + "  " + description.get(indexHolder) + "  $" + df.format(price.get(indexHolder)) + "  " + quantity
						+ "  " + discount * 100 + "%  $" + df.format(totalPrice));
				
				//Capture data
				/*
				CitemID.add(iD);
				Cdescription.add(description.get(indexHolder));
				Cprice.add(totalPrice);
				CviewOrder.add(iD + "  " + description.get(indexHolder) + "  $" + df.format(price.get(indexHolder)) + "  " + quantity
						+ "  " + discount * 100 + "%  $" + df.format(totalPrice));
				CommaViewOrder.add(iD + ", " + description.get(indexHolder) + ", $" + df.format(price.get(indexHolder)) + ", " + quantity
						+ ", " + discount * 100 + "%, $" + df.format(totalPrice));
				iD = 0;
				*/
				
				//Setting buttons and text fields
				btnNewButton.setEnabled(false);
				btnConfirmItem.setEnabled(true);
				textField.setEnabled(false);
				//textField_1.setEnabled(false);
				//textField_2.setEnabled(false);
				lblNewLabel_3.setText("Item #" + itemNum + " info\r\n: ");
			}
		});
		btnNewButton.setForeground(Color.GREEN);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 34, 140, 27);
		panel.add(btnNewButton);
		
		btnConfirmItem = new JButton("Confirm item #1");
		btnConfirmItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Processing variables and data
				CitemID.add(iD);
				Cdescription.add(description.get(indexHolder));
				Cprice.add(totalPrice);
				CviewOrder.add(iD + "  " + description.get(indexHolder) + "  $" + df.format(price.get(indexHolder)) + "  " + quantity
						+ "  " + discount * 100 + "%  $" + df.format(totalPrice));
				CommaViewOrder.add(iD + ", " + description.get(indexHolder) + ", $" + df.format(price.get(indexHolder)) + ", " + quantity
						+ ", " + discount * 100 + "%, $" + df.format(totalPrice));
				iD = 0;
				progress++;
				sum = 0;
				textField_1.setEnabled(false);
				textField_2.setEnabled(false);
				
				//Item accepted message
				JOptionPane.showMessageDialog(null, "Item #" + itemNum + " accepted", "Message", JOptionPane.INFORMATION_MESSAGE);
				
				//Updating item numbers and clearing input
				lblNewLabel_4.setText("Order subtotal for " + itemNum + " item(s):");
				itemNum++;
				
				//Checking to see if last item is confirmed
				if (progress == numItems) {
					//Finishing up text
					btnNewButton.setText("Process item");
					btnNewButton.setEnabled(false);
					btnConfirmItem.setText("Confirm item");
					btnConfirmItem.setEnabled(false);
					lblNewLabel_1.setText("");
					lblNewLabel_2.setText("");
					textField_1.setText("");
					textField_2.setText("");
					btnNewButton_2.setEnabled(true);
					btnNewButton_3.setEnabled(true);
					
					//Setting order total 
					for (int i = 0; i < Cprice.size(); i++) {
						sum += Cprice.get(i);
					}
					textField_4.setText("$" + df.format(sum));
				}
				else {
					//Setting up text
					btnNewButton.setText("Process item #" + itemNum);
					btnConfirmItem.setText("Confirm item #" + itemNum);
					lblNewLabel_1.setText("Enter item ID for item #" + itemNum + ":");
					lblNewLabel_2.setText("Enter quantity for item #" + itemNum + ":");
					textField_1.setText("");
					textField_2.setText("");
					textField_1.setEnabled(true);
					textField_2.setEnabled(true);
					
					//Setting order total 
					for (int i = 0; i < Cprice.size(); i++) {
						sum += Cprice.get(i);
					}
					textField_4.setText("$" + df.format(sum));
					
					//Setting up buttons
					btnConfirmItem.setEnabled(false);
					btnNewButton.setEnabled(true);
					btnNewButton_2.setEnabled(true);
					btnNewButton_3.setEnabled(true);
				}
			}
		});
		btnConfirmItem.setForeground(Color.GREEN);
		btnConfirmItem.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnConfirmItem.setBackground(Color.BLACK);
		btnConfirmItem.setBounds(160, 34, 143, 27);
		btnConfirmItem.setEnabled(false);
		panel.add(btnConfirmItem);
		
		btnNewButton_2 = new JButton("View order");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = "";
				
				for (int i = 0; i< CviewOrder.size(); i++) {
					i++;
					str = str + i + ". ";
					i--;
					str = str + CviewOrder.get(i) +"\n";
				}
				JOptionPane.showMessageDialog(null, str, "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_2.setForeground(Color.GREEN);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_2.setBackground(Color.BLACK);
		btnNewButton_2.setBounds(313, 34, 119, 27);
		btnNewButton_2.setEnabled(false);
		panel.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Finish order");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a zzz");
				
				//Florida tax
				double tax = 0.06;
				
				String str = "";
				str = "Date: " + ft.format(dNow) + "\n\n" + "Number of Line items: " + numItems + "\n\n" + 
						"Item# / ID/ 	         Title           / Quantity / Discount % / Subtotal: \n\n";
				for (int i = 0; i< CviewOrder.size(); i++) {
					i++;
					str = str + i + ". ";
					i--;
					str = str + CviewOrder.get(i) +"\n";
				}
				str = str + "\n\n" + "Order subtotal: $" + df.format(sum) + "\n\n" + "Tax rate:\t  "  + (tax * 100) +"%"
						+ "\n\n" + "Tax amount:\t  $" + df.format(sum * tax) + "\n\n" + "Order total:  $" + df.format(sum + (sum * tax))
					+ "\n\nThanks for shopping at Nile Dot Com! ";
				
				//Message window
				JOptionPane.showMessageDialog(null, str, "Message", JOptionPane.INFORMATION_MESSAGE);
				btnNewButton_3.setEnabled(false);
				
				//Writing to text file
				SimpleDateFormat ft2 = new SimpleDateFormat("ddMMyyyykkmm");
				String str2 = "";
				
				for (int i = 0; i < CommaViewOrder.size(); i++) {
					str2 = str2 + ft2.format(dNow) + ", " + CommaViewOrder.get(i) + ", " + ft.format(dNow) + "\n";
				}
				
				try {
					wr.write(str2);
					wr.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				btnNewButton.setEnabled(false);
				btnNewButton.setText("Process item #" + itemNum);
				btnConfirmItem.setText("Confirm item #" + itemNum);
				btnConfirmItem.setEnabled(false);
			}
		});
		btnNewButton_3.setForeground(Color.GREEN);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_3.setBackground(Color.BLACK);
		btnNewButton_3.setBounds(442, 34, 119, 27);
		btnNewButton_3.setEnabled(false);
		panel.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("New order");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//resetting global variables
				itemNum = 1;
				progress = 0;
				numItems = 0;
				quantity = 0;
				iD = 0;
				totalPrice = 0;
				discount = 0;
				
				//resetting arrayLists
				CitemID.clear();
				CviewOrder.clear();
				Cprice.clear();
				Cdescription.clear();
				CommaViewOrder.clear();
				
				//Clearing text and buttons
				btnNewButton.setEnabled(true);
				btnNewButton.setText("Process item #" + itemNum);
				btnConfirmItem.setText("Confirm item #" + itemNum);
				btnConfirmItem.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				btnNewButton_3.setEnabled(false);
				
				//resetting text fields and labels
				textField.setText("");
				textField.setEnabled(true);
				textField_1.setText("");
				textField_1.setEnabled(true);
				textField_2.setText("");
				textField_2.setEnabled(true);
				textField_3.setText("");
				textField_4.setText("");
				
				lblNewLabel_1.setText("Enter item ID for item #" + itemNum + ":");
				lblNewLabel_2.setText("Enter quantity for item #" + itemNum + ":");
				lblNewLabel_3.setText("Item #" + itemNum + " info\r\n: ");
				lblNewLabel_4.setText("Order subtotal for 0 item(s):");
				
			}
		});
		btnNewButton_4.setForeground(Color.GREEN);
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_4.setBackground(Color.BLACK);
		btnNewButton_4.setBounds(571, 34, 111, 27);
		panel.add(btnNewButton_4);
		
		btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					wr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		btnNewButton_1.setForeground(Color.GREEN);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setBounds(692, 34, 85, 27);
		panel.add(btnNewButton_1);
	}
}
