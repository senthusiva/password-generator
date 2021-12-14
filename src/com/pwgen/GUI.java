package com.pwgen;

import javax.swing.*;

class GUI {
	public static void main(String args[]) {
		JFrame frame = new JFrame("Password Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		JButton button = new JButton("Get password!");
		frame.getContentPane().add(button); // Adds Button to content pane of frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}