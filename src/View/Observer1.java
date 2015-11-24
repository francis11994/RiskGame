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
import javax.swing.JTextArea;

import model.Country;
import model.Dice;
import model.Player;
import model.RiskGame;

public class Observer1 extends JPanel implements Observer {
	List<List<Country>> AllCountry;
	int index;
	int round;
	JTextArea Text;
	List<Player> players;
	Player currentPlayer;
	RiskGame game;
	Country currentCountry;
	int reinforcement = 0;
	private BufferedImage map;
	private BufferedImage bottom;
	static boolean firstClick;
	Country attackingCountry;

	public Observer1(RiskGame Game) {
		setLayout(null);
		firstClick = true;
		game = Game;
		update(game);
		setSize(800, 600);
		Text = new JTextArea("Start!");
		add(Text);
		Text.setFont(new Font("Arial Black", Font.BOLD, 20));
		Text.setLocation(220, 480);
		Text.setSize(550, 100);
		Text.setOpaque(false);
		Text.setFocusable(false);
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
		reinforcement = game.getReinforcement();
	}

	@Override
	public void update(Observable o, Object arg) {
		update((RiskGame) o);
		updateUI();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(map, 0, 0, 800, 700 * 2 / 3, null);
		int i = 0;
		if (currentPlayer != null && reinforcement != 0)
			Text.setText(currentPlayer.getName() + "'s turn: " + reinforcement + " Solider remainning");
		for (List<Country> countries : AllCountry) {
			Player a = players.get(i);
			for (Country country : countries) {
				if (country != currentCountry) {
					g2.setColor(a.getColor());
					g2.fillOval(country.getX(), country.getY(), 24, 24);
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("Arial Black", Font.BOLD, 16));
					if (country.getArmyCount() < 10)
						g2.drawString("" + country.getArmyCount(), country.getX() + 5, country.getY() + 20);
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
		if (reinforcement != 0) {
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 260, 135, 60);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Arial Black", Font.BOLD, 15));
			g2.drawString("reinforcement", 10, 280);
			g2.setColor(Color.RED);
			g2.setFont(new Font("Arial Black", Font.BOLD, 25));
			g2.drawString("" + reinforcement, 50, 310);
		}

		g2.drawImage(bottom, 0, 465, 1200, 240, null);
	}

	private class MouseOperation implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (round == 0) {
				if (currentCountry != null) {
					currentCountry.addArmys(1);
					updateUI();
					game.moveToNext();
					game.play();
				}
			} else if (round > 0) {
				if (currentCountry != null) {
					if (reinforcement > 0) {
						currentCountry.addArmys(1);
						reinforcement--;
						updateUI();
					}
					if (reinforcement == 0) {
						if (currentCountry != null) {

							if (firstClick) {
								attackingCountry = currentCountry;
								firstClick = !firstClick;
							} else {
								attack(attackingCountry, currentCountry);
								firstClick = !firstClick;
							}
						}
						// game.moveToNext();
						// game.play();
					}
				}

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
			if (firstClick) {
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
			} else if (!firstClick) {
				for (Country c : attackingCountry.getNegibors(AllCountry.get(index))) {
					if (c.isLocateAt(e.getPoint())) {
						currentCountry = c;
						updateUI();
						return;
					}
				}
				currentCountry = null;
			}

		}
	}

	public void attack(Country attacker, Country defender) {
		if (attacker.getArmyCount() >= 2) {
			Dice d = new Dice();
			Dice a = new Dice();
			a.roll();
			d.roll();
			Text.setText(currentPlayer.getName() + "'s turn: " + attacker.getname() + " Rolled " + a.getNumber()
					+ "\n the defender " + defender.getname() + " Rolled " + d.getNumber() + " \n");
			if (a.getNumber() > d.getNumber()) {
				defender.removeArmys(1);
			} else if (a.getNumber() < d.getNumber()) {
				attacker.removeArmys(1);
			}
			if (defender.getArmyCount() == 0) {
				List<List<Country>> world = game.getAllCountry();
				for (int i = 0; i < world.size(); i++)
					world.get(i).remove(defender);
				world.get(index).add(defender);
				attacker.moveSolider(defender, 1);
				AllCountry = world;
			}
		}
		updateUI();
	}
}