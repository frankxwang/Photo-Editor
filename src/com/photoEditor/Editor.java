package com.photoEditor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class Editor {
	JFrame frame;
	JFileChooser fc;
	JButton open;
	JButton save;
	
	public Editor() {
		// TODO Auto-generated method stub
		frame = new JFrame("Editor");
		
		fc = new JFileChooser();
		
		JPanel panel = new Panel();
		
		open = new JButton("Open a File...");
		open.addActionListener((Panel)panel);
		
		save = new JButton("Save a File...");
		save.addActionListener((Panel)panel);
		
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.add(save, BorderLayout.NORTH);
		frame.add(open, BorderLayout.PAGE_END);
		frame.pack();
	}
	class Panel extends JPanel implements ActionListener{
		Graphics graphics = null;
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == open){
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            BufferedImage img;
		            try{
		            	img = ImageIO.read(file);
		            	graphics = img.getGraphics();
		            }catch(Exception er){
		            	er.printStackTrace();
		            }
		        }
			}
		}
		protected void paintComponent(Graphics g){
			if(graphics != null){
				g = graphics;
				graphics = null;
				System.out.println("ye");
			}
		}
	}
}
