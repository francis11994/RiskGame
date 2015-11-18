
package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Player.PlayerType;
import model.RiskGame;
import model.RiskMap;

public class GameGUI extends JFrame{
	public static void main(String[] args){
		new GameGUI().setVisible(true);
	}
	public static int SCREEN_LENGTH = 1200;
	public static int SCREEN_HEIGHT = 700;
	private BufferedImage map;
	private RiskGame game;
	private SoldierObserver observer;
	public GameGUI(){
		layoutTheJFrame();
		setUpModelAndObservers();
		registerListeners();
	}
	
	public void layoutTheJFrame(){
		setTitle("Risk Game");
		setSize(SCREEN_LENGTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public void setUpModelAndObservers(){
		Load();
		observer=new SoldierObserver(game);
		game.addObserver((Observer)observer);
		add(observer);
		repaint();
	}
	
	public void registerListeners(){
		addWindowListener(new GameListener());
	}
	
	
	private class GameListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to resume the previous operation?", null, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.NO_OPTION){
				game.restart();
				game.addPlayer(PlayerType.Hard,"ONE",Color.BLUE);
				game.addPlayer(PlayerType.Hard,"ONE",Color.BLACK);
				game.addPlayer(PlayerType.Beginner,"TWO",Color.GREEN);
				game.randomSetCountry(new RiskMap().getAllCountry());
				repaint();
			}
			game.play();
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to save", null, JOptionPane.YES_NO_CANCEL_OPTION);	
			if (reply == JOptionPane.YES_OPTION){
				Save();
				System.exit(0);
			}
			if (reply == JOptionPane.NO_OPTION)
			System.exit(0);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private void Save(){
		try {
			FileOutputStream fos = new FileOutputStream("myFile");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject((RiskGame) game);	
			oos.close();
			fos.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
	}
	
	private void Load(){
		game=new RiskGame();
		try{
			FileInputStream fos=new FileInputStream("myFile");
			ObjectInputStream oos = new ObjectInputStream(fos);
			game=(RiskGame) oos.readObject();
			oos.close();
			fos.close();
		} catch (Exception a){
			a.printStackTrace();
		}
	}
	

}