package com.kirdow.sortcsv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SortCSV {
	
	//This is the size of the window
	private int width = 800;
	private int height = 600;
	//This is the window title
	private String title = "CSV Sorter";
	
	//This is the frame for the program
	private JFrame frame;
	
	//These are the paths to the folders we are loading from and saving to
	private File fromFolder;
	private File toFolder;
	
	private boolean hasFromFolder = false;
	private boolean hasToFolder = false;
	
	private final int LBL_X = 10;
	private final int TF_X = 120;
	private final int BTN_X = 425;
	private final int LBL_W = 100;
	private final int TF_W = 300;
	private final int BTN_W = 100;
	
	private JButton btnRun;
	
	private JList listFrom;
	private JList listTo;
	
	
	
	public SortCSV() {
		
	}
	
	//This is where we initialize the Frame and such
	public void init() {
		//First we initialize the frame, we use javax.swing.JFrame for this, we can pass in the title
		//as a parameter, so we do that
		frame = new JFrame(title);
		//Then we set the preferred size of the content pane and then pack the frame.
		//This will make the the inside of the frame the size we want
		//Doing just frame.setSize, sets the whole window size, and we want the inside of the frame
		//to be a specific size
		frame.getContentPane().setPreferredSize(new Dimension(BTN_X + BTN_W, 442));
		frame.pack();
		//We set the location to the center of the screen
		frame.setLocationRelativeTo(null);
		//We set so the program will terminat when we press the X or we do Alt+F4
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//We set so the user can't resize the window
		frame.setResizable(false);
		//We set the layout to null, this makes it so we can freely move our components in the JFrame
		frame.setLayout(null);
		
		//We create a new label for the "Select search directory"
		JLabel lblFrom = new JLabel();
		//We set the size
		lblFrom.setSize(new Dimension(100, 30));
		//and location.
		lblFrom.setLocation(LBL_X, 8);
		//We get the font which is in use
		Font lblFont = lblFrom.getFont();
		//I want the same family, so I get the family
		String lblFamily = lblFont.getFamily();
		//Create a new Font with the font family we just got, we make it bold and give it the size of 13
		Font lblNewFont = new Font(lblFamily, Font.BOLD, 13);
		//and then set the new font as the current label font
		lblFrom.setFont(lblNewFont);
		//Lastly we set the text.
		lblFrom.setText("Search Folder: ");

		//We create a new JTextField, we will create a lambda style class/method
		//so we make it final so we can access it from within
		final JTextField tfFrom = new JTextField();
		//We set the size
		tfFrom.setSize(new Dimension(300, 30));
		//and location.
		tfFrom.setLocation(TF_X, 8);
		//We add an document listener which checks for if the text gets changed
		tfFrom.getDocument().addDocumentListener(new DocumentListener() {
			//This method will get called manually from the 3 methods below
			public void update() {
				//We create a new File object with the path being inside the JTextField
				File file = new File(tfFrom.getText());
				//We set the old fromFolder to null
				fromFolder = null;
				//We then check if the file exists
				if (file.exists())
				{
					//If the file exists, we check if it is a folder
					if (file.isDirectory())
					{
						//If it is, we set the fromFolder to the new File object
						//and tell the program that we have a fromFolder
						hasFromFolder = true;
						fromFolder = file;
						System.out.println("true");
					}
					else
					{
						//If it is not a directory, we tell the program we don't have a fromFolder
						//and keep fromFolder as null
						hasFromFolder = false;
						System.out.println("false");
					}
				}
				else
				{
					//If the file doesn't exist, we tell the program we don't have a fromFolder
					//and keep fromFolder as null
					hasFromFolder = false;
					System.out.println("false");
				}
				
				//We then call a function named updateButtons()
				updateButtons();
			}
			
			//These 3 is the events we need
			//All of them should call the update method above
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			public void removeUpdate(DocumentEvent e) {
				update();
			}

			public void changedUpdate(DocumentEvent e) {
				update();
			}
		});
		
		//We create a new JButton which will be the "Browse for directory" button
		//for the search directory
		JButton btnFrom = new JButton();
		//We set the size
		btnFrom.setSize(new Dimension(100, 29));
		//and location.
		btnFrom.setLocation(BTN_X, 8);
		//We set the text to 'Browse'
		btnFrom.setText("Browse");
		//We turn of FocusPainted and ContentAreaFilled.
		//this makes the button look more up-to-date/Windows 8 - 10
		//I will make support for windows 7 look later.
		btnFrom.setFocusPainted(false);
		btnFrom.setContentAreaFilled(false);
		//We then add an action listener for when the button is pressed
		btnFrom.addActionListener(new ActionListener(){
			//This gets called when it's pressed
			public void actionPerformed(ActionEvent e) {
				//First we create a JFileChooser object
				JFileChooser fcFrom = new JFileChooser();
				//and make it for Folders Only
				fcFrom.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				//We then show the dialog and return an option
				int option = fcFrom.showOpenDialog(frame);
				
				//We then check for what option it is using a switch statement
				switch (option) {
				//Is the option an APPROVE_OPTION?
				case JFileChooser.APPROVE_OPTION:
					//If so, we set fromFolder to the selected file
					fromFolder = fcFrom.getSelectedFile();
					//We tell the program that he whave a fromFolder
					hasFromFolder = true;
					//And also set the JTextField content to the path
					tfFrom.setText(fromFolder.getPath());
					//We also make sure to break
					break;
				//This will run for all other options
				default:
					break;
				}
			}
		});
		
		//We create a new label for the "Select save directory"
		JLabel lblTo = new JLabel();
		//We set the size
		lblTo.setSize(new Dimension(100, 30));
		//and location
		lblTo.setLocation(LBL_X, 46);
		//We already setup the font for the previous label
		//so we use the same here
		lblTo.setFont(lblNewFont);
		//Lastly we set the text to 'Save Folder: '
		lblTo.setText("Save Folder: ");
		
		//We create a new JTextField, and like the last time, we make it final
		//so that the ActionListener can access it
		final JTextField tfTo = new JTextField();
		//We set the size
		tfTo.setSize(new Dimension(300, 30));
		//and location.
		tfTo.setLocation(TF_X, 46);
		//We create a document listener for when the text change
		tfTo.getDocument().addDocumentListener(new DocumentListener(){
			//When the text change, we will manually call this method from below
			public void update() {
				//So first, we create a file with the path being the tfTo content
				File file = new File(tfTo.getText());
				//We set toFolder to null
				toFolder = null;
				//Check if the file exists
				if (file.exists())
				{
					//If it does, we check if the file is a folder
					if (file.isDirectory())
					{
						//If it is, we set toFolder to the file and tell the program
						//that we have a toFolder
						hasToFolder = true;
						toFolder = file;
					}
					else
					{
						//If it isn't a folder, we tell the program that we have no toFolder
						hasToFolder = false;
					}
				}
				else
				{
					//If the file doesn't exist, we also tell the program that we have no toFolder
					hasToFolder = false;
				}
				
				//And lastly we update the buttons
				updateButtons();
			}
			
			//These 3 are the events called when text is changed.
			//It manually calls the update method above
			public void insertUpdate(DocumentEvent e) {
				update();
			}
			
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			
			public void changedUpdate(DocumentEvent e) {
				update();
			}
		});
		
		JButton btnTo = new JButton();
		btnTo.setSize(new Dimension(100, 29));
		btnTo.setLocation(BTN_X, 46);
		btnTo.setText("Browse");
		btnTo.setFocusPainted(false);
		btnTo.setContentAreaFilled(false);
		btnTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fcTo = new JFileChooser();
				fcTo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fcTo.showSaveDialog(frame);
				
				switch (option) {
				case JFileChooser.APPROVE_OPTION:
					toFolder = fcTo.getSelectedFile();
					hasToFolder = true;
					tfTo.setText(toFolder.getPath());
					break;
				default:
					break;
				}
			}
		});
		
		btnRun = new JButton();
		btnRun.setSize(BTN_X + BTN_W - LBL_X, 29);
		btnRun.setLocation(LBL_X, 412);
		btnRun.setText("RUN");
		btnRun.setFocusPainted(false);
		btnRun.setContentAreaFilled(false);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runSorter();
			}
		});
		
		listFrom = new JList();
		listTo = new JList();
		
		listFrom.setBackground(Color.WHITE);
		listTo.setBackground(Color.WHITE);
		
		listFrom.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		listTo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		listFrom.setSize(new Dimension((int)(frame.getPreferredSize().getWidth() - 24) / 2, 300));
		listTo.setSize(new Dimension((int)(frame.getPreferredSize().getWidth() - 24) / 2, 300));
		
		listFrom.setLocation(8, 104);
		listTo.setLocation(16 + ((int)(frame.getPreferredSize().getWidth() - 24) / 2), 104);
		
		JLabel lblListFrom = new JLabel();
		JLabel lblListTo = new JLabel();
		
		lblListFrom.setText("Found files");
		lblListTo.setText("Files to sort");
		
		lblListFrom.setFont(new Font(lblFamily, Font.PLAIN, 12));
		lblListTo.setFont(new Font(lblFamily, Font.PLAIN, 12));
		
		lblListFrom.setLocation(8, 84);
		lblListTo.setLocation((int)listTo.getLocation().getX(), 84);
		
		lblListFrom.setSize(100, 16);
		lblListTo.setSize(100, 16);
		
		MouseAdapter adapt = new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				JList list = (JList)evt.getSource();
				JList other = list.equals(listFrom) ? (listTo) : (listFrom);
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					list.setSelectedIndex(index);
					
					Object obj = list.getSelectedValue();
					if (obj != null) {
						//TODO: Add element to the other
					}
				}
			}
		};
		
		listFrom.addMouseListener(adapt);
		listTo.addMouseListener(adapt);
		
		frame.add(lblFrom);
		frame.add(tfFrom);
		frame.add(btnFrom);
		frame.add(lblTo);
		frame.add(tfTo);
		frame.add(btnTo);
		frame.add(listFrom);
		frame.add(lblListFrom);
		frame.add(listTo);
		frame.add(lblListTo);
		frame.add(btnRun);
		
		
		lblFrom.setVisible(true);
		tfFrom.setVisible(true);
		btnFrom.setVisible(true);
		lblTo.setVisible(true);
		tfTo.setVisible(true);
		btnTo.setVisible(true);
		btnRun.setVisible(true);;
		

		frame.setVisible(true);
		
		updateButtons();
	}
	
	private void updateButtons() {
		if (hasFromFolder && hasToFolder) {
			btnRun.setEnabled(false);
		} else {
			btnRun.setEnabled(true);
		}
	}
	
	private void runSorter() {
		
	}
	
}
