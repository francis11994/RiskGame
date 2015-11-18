package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapGUI extends JFrame implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6651612684817245081L;

	Image background = null;
	public static void main(String [] args)
	{
		MapGUI window = new MapGUI();
		window.setVisible(true);
	}
	
	public MapGUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		setSize(1000, 800);
		setLocation(100, 30);
		
		try {
			background = ImageIO.read(new File("map.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel jp = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g.create();
				g2.drawImage(background, 0, 0,1000, 800, null);
			}
			
		};
		
		jp.setSize(1000, 800);
		jp.setBackground(Color.WHITE);
		jp.setPreferredSize(new Dimension(1000, 800));
		this.add(jp);
	}
}
