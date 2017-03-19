package com.erik.GreenThink.Demos;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;

//jSerialCom Imports
import com.fazecast.jSerialComm.*;

public class GreenThinkGUI extends JFrame {

	private JPanel contentPane;
	private JTable arduinoTable;
	
	//Referance SerialManger
	private SerialManager serialManage;
	   public GreenThinkGUI(SerialManager serialManage) {
	     this.serialManage = serialManage; 
	   }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GreenThinkGUI frame = new GreenThinkGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GreenThinkGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblWelcomeToGreenthink = new JLabel("Welcome to GreenThink Arduino Wizard");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWelcomeToGreenthink, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWelcomeToGreenthink, 0, SpringLayout.WEST, contentPane);
		contentPane.add(lblWelcomeToGreenthink);
		
		JLabel lblFindArduinos = new JLabel("Detected Arduino(s)");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblFindArduinos, 0, SpringLayout.WEST, contentPane);
		contentPane.add(lblFindArduinos);
		

		
		
		JButton btnFindArduinos = new JButton("Refresh Ports");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblFindArduinos, 6, SpringLayout.SOUTH, btnFindArduinos);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnFindArduinos, 6, SpringLayout.SOUTH, lblWelcomeToGreenthink);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnFindArduinos, 0, SpringLayout.WEST, contentPane);
		btnFindArduinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				SerialPort[] portList = SerialManager.findPorts();
				if(portList != null){
					for(int i=0;i<portList.length;i++){
					System.out.println(portList.SerialManage.getSystemName[i]);
					arduinoTable.setValueAt(portList.get(i).SerialManager.getSystemNames, i+1 , 0);
					arduinoTable.setValueAt(portList.get(i).getSystemPortName(), 1+i, 1);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "No Arduino(s) found!!");
				}
				
				
				
				
			}
		});
		contentPane.add(btnFindArduinos);
		
	
		arduinoTable = new JTable();
		DefaultTableModel model = new DefaultTableModel(5,2);
		arduinoTable.setVisible(true);
		arduinoTable.setModel(model);
		arduinoTable.setShowGrid(true);
		arduinoTable.setGridColor(Color.black);
		arduinoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//arduinoTable.setTableHeader(null);
		arduinoTable.setValueAt("Num",0,0);
		arduinoTable.setValueAt("Port", 0, 1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, arduinoTable, 11, SpringLayout.SOUTH, lblFindArduinos);
		sl_contentPane.putConstraint(SpringLayout.WEST, arduinoTable, 5, SpringLayout.WEST, lblWelcomeToGreenthink);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, arduinoTable, 144, SpringLayout.SOUTH, lblFindArduinos);
		sl_contentPane.putConstraint(SpringLayout.EAST, arduinoTable, 140, SpringLayout.WEST, lblWelcomeToGreenthink);
		contentPane.add(arduinoTable);
		

	
	}
}