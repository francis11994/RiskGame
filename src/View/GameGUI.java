
package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Player.PlayerType;
import model.RiskGame;
import model.RiskMap;

public class GameGUI extends JFrame {
	public static void main(String[] args) {
		new GameGUI().setVisible(true);
	}

	public static int SCREEN_LENGTH = 800;
	public static int SCREEN_HEIGHT = 650;
	private RiskGame game;
	private Observer1 observer1;
	private Observer2 observer2;

	public GameGUI() {
		layoutTheJFrame();
		setUpModelAndObservers();
		registerListeners();
	}

	public void layoutTheJFrame() {
		setTitle("Risk Game");
		setSize(SCREEN_LENGTH, SCREEN_HEIGHT);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public void setUpModelAndObservers() {
		Load();
		observer1 = new Observer1(game);
		game.addObserver((Observer) observer1);
		add(observer1);
		observer1.setLocation(0, 30);

		observer2 = new Observer2(game);
		game.addObserver(observer2);
		observer2.setSize(800, 30);
		observer2.setLocation(50, 0);
		add(observer2);

		Play = new JButton("Finish this turn");
		observer1.add(Play);
		Play.setSize(150, 40);
		Play.setLocation(10, 500);
		Play.addActionListener(new GameListener());
	}

	public void registerListeners() {
		addWindowListener(new GameListener());
	}

	private JButton Play;

	private class GameListener implements WindowListener, ActionListener {

		@Override
		public void windowOpened(WindowEvent e) {
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to resume the previous operation?", null,
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.NO_OPTION) {
				game.restart();
				game.addPlayer(PlayerType.Human, "Player1", Color.BLUE);
				game.addPlayer(PlayerType.Hard, "HardAI", Color.BLACK);
				game.addPlayer(PlayerType.Human, "Player2", Color.GREEN);
				game.addPlayer(PlayerType.Intermediate, "MediumAI", Color.RED);
				game.addPlayer(PlayerType.Human, "Player3", Color.GRAY);
				game.addPlayer(PlayerType.Beginner, "EasyAI", Color.MAGENTA);
				game.randomSetCountry(new RiskMap().getAllCountry());
			}
			game.play();

		}

		@Override
		public void windowClosing(WindowEvent e) {
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to save", null,
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
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

		@Override
		public void actionPerformed(ActionEvent e) {
			game.moveToNext();
			game.play();

		}

	}

	private void Save() {
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

	private void Load() {
		game = new RiskGame();
		try {
			FileInputStream fos = new FileInputStream("myFile");
			ObjectInputStream oos = new ObjectInputStream(fos);
			game = (RiskGame) oos.readObject();
			oos.close();
			fos.close();
		} catch (Exception a) {
		}
	}

}