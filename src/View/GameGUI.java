package View;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.RiskGame;


public class GameGUI extends JFrame{
	public static void main(String[] args){
		new GameGUI().setVisible(true);
	}
	public static int SCREEN_LENGTH = 800;
	public static int SCREEN_HEIGHT = 700;
	private MainMenu mainMenu;
	
	public GameGUI(){
		layoutTheJFrame();
		SetUpModelAndObserver();
	}
	
	private void layoutTheJFrame(){
		setTitle("Risk Game");
		setSize(SCREEN_LENGTH, SCREEN_HEIGHT);
		setLocation(800,0);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	private void SetUpModelAndObserver(){
		addWindowListener(new GameListener());
		mainMenu = new MainMenu();
		add(mainMenu);
		mainMenu.setSize(SCREEN_LENGTH, SCREEN_HEIGHT);
		
	}
	
	private class GameListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
		}

		@Override
		public void windowClosing(WindowEvent e) {
			if(mainMenu.isGaming()){
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to save", null, JOptionPane.YES_NO_CANCEL_OPTION);	
			if (reply == JOptionPane.YES_OPTION){
				Save();
				System.exit(0);
			}
			if (reply == JOptionPane.NO_OPTION)
			System.exit(0);
			}
			else System.exit(0);
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
			oos.writeObject((RiskGame)mainMenu.getGame());	
			oos.close();
			fos.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
	}
	
}