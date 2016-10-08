package com.photoEditor;

import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Editor {
	JFrame frame;
	JFileChooser fc;
	JButton open;
	JButton save;
	Panel panel;
	boolean[] mouse = new boolean[MouseEvent.BUTTON3];
	
	public Editor() {
		// TODO Auto-generated method stub
		frame = new JFrame("Editor");
		
		fc = new JFileChooser();
		
		panel = new Panel();
		panel.setSize(new Dimension(400, 400));
		panel.setBackground(Color.green);
		
		open = new JButton("Open a File...");
		open.addActionListener(panel);
		
		save = new JButton("Save a File...");
		save.addActionListener(panel);
		
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.add(save, BorderLayout.NORTH);
		frame.add(open, BorderLayout.PAGE_END);
		frame.pack();
		Timer t = new Timer();
		t.schedule(new Update(), 0, 10);
	}
	class Update extends TimerTask{
		@Override
		public void run() {
			(panel).repaint();
		}
	}
	class Panel extends JPanel implements ActionListener, MouseListener{
		Panel(){
			addMouseListener(this);
		}
		Graphics graphics = null;
		BufferedImage img;
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == open){
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            try{
		            	img = ImageIO.read(file);
		            	frame.setSize(new Dimension(img.getWidth(), img.getHeight()));
		            	graphics = img.createGraphics();
		            }catch(Exception er){
		            	er.printStackTrace();
		            }
		        }
			}else if(e.getSource() == save){
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						ImageIO.write(img, "png", new File(fc.getCurrentDirectory().toString() 
								+ "/savedimg.png"));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		protected void paintComponent(Graphics g){
			if(mouse[MouseEvent.BUTTON1]){
				Point p = MouseInfo.getPointerInfo().getLocation();
				graphics.setColor(Color.black);
				int width=50;
				int height=50;
				graphics.fillOval(p.x-width/2, p.y-height/2, width, height);
			}
			g.drawImage(img, 0, 0, null);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e){
			mouse[e.getButton()] = true;
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			mouse[e.getButton()] = false;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
