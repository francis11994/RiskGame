package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Country;
import model.Player;
import model.RiskGame;

public class Observer1 extends JPanel implements Observer {
	List<List<Country>> AllCountry;
	int index;
	int round;
	List<Player> players;
	Player currentPlayer;
	RiskGame game;
	Country currentCountry;
	int reinforcement;
	private BufferedImage map;
	private BufferedImage bottom;
	public Observer1(RiskGame Game) {
		game = Game;
		update(game);
		setSize(800,600);
		addMouseListener(new MouseOperation());
		addMouseMotionListener(new MouseOperation());
		try {
			map = ImageIO.read(new File("./picture/RiskMap.PNG"));
			bottom = ImageIO.read(new File("./picture/Box.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void update(RiskGame game) {
		AllCountry = game.getAllCountry();
		index = game.getIndex();
		round = game.getRound();
		players = game.getAllPlayer();
		currentPlayer = game.getPlayer();
		currentCountry = null;
		reinforcement=game.getReinforcement();
	}

	@Override
	public void update(Observable o, Object arg) {
		update((RiskGame) o);
		updateUI();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(map, 0, 0, 800,700*2/3, null);
		int i = 0;

		for (List<Country> countries : AllCountry) {
			Player a = players.get(i);
			for (Country country : countries) {
				if (country != currentCountry) {
					g2.setColor(a.getColor());
					g2.fillOval(country.getX(), country.getY(), 24, 24);
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("Arial Black", Font.BOLD, 16));
					if (country.getArmyCount() < 10)
						g2.drawString("" + country.getArmyCount(), country.getX() +5, country.getY() + 20);
					else
						g2.drawString("" + country.getArmyCount(), country.getX(), country.getY() + 20);
				} else {
					g2.setColor(a.getColor());
					g2.fillOval(country.getX() - 20, country.getY(), 35, 35);
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("Arial Black", Font.BOLD, 24));
					if (country.getArmyCount() < 10)
						g2.drawString("" + country.getArmyCount(), country.getX() - 15, country.getY() + 30);
					else
						g2.drawString("" + country.getArmyCount(), country.getX() - 20, country.getY() + 30);
				}
			}
			i++;
		}
		if(reinforcement!=0){
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 260, 135, 60);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Arial Black", Font.BOLD, 15));
			g2.drawString("reinforcement", 10, 280);
			g2.setColor(Color.RED);
			g2.setFont(new Font("Arial Black", Font.BOLD, 25));
			g2.drawString(""+reinforcement, 50, 310);
		}
		
		g2.drawImage(bottom,0,465,1200,240,null);
	}

	private class MouseOperation implements  MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (currentCountry != null){
				currentCountry.addArmys(1);
			updateUI();
			game.moveToNext();
			game.play();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			List<Country> countries = AllCountry.get(index);
				for (Country country : countries) {
					if (country.isLocateAt(e.getPoint())) {
						currentCountry = country;
						updateUI();
						return;
					}
				}
				currentCountry = null;
			updateUI();

		}
	}
}