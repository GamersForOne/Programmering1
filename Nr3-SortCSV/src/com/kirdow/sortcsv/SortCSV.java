package com.kirdow.sortcsv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SortCSV {
	
	private int width = 800;
	private int height = 600;
	private String title = "CSV Sorter";
	
	private JFrame frame;
	
	private File fromFolder;
	private File toFolder;
	
	public SortCSV() {
		
	}
	
	public void init() {
		frame = new JFrame(title);
		frame.setSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		
		JLabel lblFrom = new JLabel();
		lblFrom.setSize(new Dimension(100, 30));
		lblFrom.setLocation(10, 8);
		Font lblFont = lblFrom.getFont();
		String lblFamily = lblFont.getFamily();
		Font lblNewFont = new Font(lblFamily, Font.BOLD, 13);
		lblFrom.setFont(lblNewFont);
		lblFrom.setText("Search Folder: ");

		final JTextField tfFrom = new JTextField();
		tfFrom.setSize(new Dimension(300, 30));
		tfFrom.setLocation(110, 8);
		
		tfFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Add TextField Change
			}
		});
		
		JButton btnFrom = new JButton();
		btnFrom.setSize(new Dimension(100, 29));
		btnFrom.setLocation(415, 8);
		btnFrom.setText("Browse");
		btnFrom.setFocusPainted(false);
		btnFrom.setContentAreaFilled(false);
		btnFrom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser fcFrom = new JFileChooser();
				fcFrom.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fcFrom.showOpenDialog(frame);
				
				switch (option) {
				case JFileChooser.APPROVE_OPTION:
					fromFolder = fcFrom.getSelectedFile();
					tfFrom.setText(fromFolder.getPath());
					break;
				default:
					break;
				}
			}
		});
		
		//TODO: Add save catalog
		
		//TODO: Add rest of the system
		
		frame.add(lblFrom);
		frame.add(tfFrom);
		frame.add(btnFrom);
		
		lblFrom.setVisible(true);
		tfFrom.setVisible(true);
		btnFrom.setVisible(true);
		

		frame.setVisible(true);
	}
	
}
