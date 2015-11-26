package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.Country;
import model.Player;
import model.RiskGame;

public class Observer1 extends JPanel implements Observer {
	List<List<Country>> AllCountry;
	int index;
	int round;
	JButton AttactComplete;
	JTextArea Text;
	List<Player> players;
	Player currentPlayer;
	RiskGame game;
	Country currentCountry;
	Country attacter;
	int reinforcement = 0;
	private BufferedImage map;
	private BufferedImage bottom;

	public Observer1(RiskGame Game) {
		loadImage();
		setLayout(null);
		game = Game;
		update(game);
		setSize(800, 600);
		Text = new JTextArea("Start!");
		add(Text);
		Text.setFont(new Font("Arial Black", Font.BOLD, 20));
		Text.setLocation(220, 480);
		Text.setSize(400, 100);
		Text.setOpaque(false);
		Text.setFocusable(false);

		addMouseListener(new MouseOperation());
		addMouseMotionListener(new MouseOperation());
		AttactComplete = new JButton("Attact Complete");
		add(AttactComplete);
		AttactComplete.setSize(120, 80);
		AttactComplete.setLocation(650, 480);
		AttactComplete.addActionListener(new MouseOperation());
		AttactComplete.setVisible(false);
	}

	private void loadImage(){
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
		// draw map
		g2.drawImage(map, 0, 0, 800, 700 * 2 / 3, null);
		if (attacter == null) {
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
							g2.drawString("" + country.getArmyCount(), country.getX() + 5, country.getY() + 20);
						else
							g2.drawString("" + country.getArmyCount(), country.getX(), country.getY() + 20);
					} else if (country == currentCountry) {
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
		} else if (attacter != null) {
			g2.setColor(Color.WHITE);
			g2.fillOval(attacter.getX(), attacter.getY(), 24, 24);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Arial Black", Font.BOLD, 16));
			if (attacter.getArmyCount() < 10)
				g2.drawString("" + attacter.getArmyCount(), attacter.getX() + 5, attacter.getY() + 20);
			else
				g2.drawString("" + attacter.getArmyCount(), attacter.getX(), attacter.getY() + 20);
			List<Country> a = attacter.getNegibors(AllCountry.get(index));
			for (Country coutr : a) {
				if (coutr != currentCountry) {
					g2.setColor(Color.RED);
					g2.fillOval(coutr.getX(), coutr.getY(), 24, 24);
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("Arial Black", Font.BOLD, 16));
					if (coutr.getArmyCount() < 10)
						g2.drawString("" + coutr.getArmyCount(), coutr.getX() + 5, coutr.getY() + 20);
					else
						g2.drawString("" + coutr.getArmyCount(), coutr.getX(), coutr.getY() + 20);
				} else {
					g2.setColor(Color.red);
					g2.fillOval(coutr.getX() - 20, coutr.getY(), 35, 35);
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("Arial Black", Font.BOLD, 24));
					if (coutr.getArmyCount() < 10)
						g2.drawString("" + coutr.getArmyCount(), coutr.getX() - 15, coutr.getY() + 30);
					else
						g2.drawString("" + coutr.getArmyCount(), coutr.getX() - 20, coutr.getY() + 30);
				}
			}
		}

		// dialoge
		Color color = Color.BLACK;
		if (currentPlayer != null) {
			color = currentPlayer.getColor();
			Text.setForeground(color);
			if (reinforcement != 0) {
				Text.setText(currentPlayer.getName() + "'s turn: " + reinforcement + " Solider remainning");
				AttactComplete.setVisible(false);
			}
			if (reinforcement == 0 && round > 0) {
				Text.setText("No remainning. attacking now !!");
				AttactComplete.setVisible(true);
			}
			if (attacter != null)
				Text.setText("Choose a enmy country");
		}
		if (reinforcement != 0) {
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 260, 135, 60);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Arial Black", Font.BOLD, 15));
			g2.drawString("reinforcement", 10, 280);
			g2.setColor(currentPlayer.getColor());
			g2.setFont(new Font("Arial Black", Font.BOLD, 25));
			g2.drawString("" + reinforcement, 50, 310);
		}

		g2.drawImage(bottom, 0, 465, 1200, 240, null);
	}

	private class MouseOperation implements MouseListener, MouseMotionListener, ActionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (currentCountry != null) {
				// before playing
				if (round == 0) {
					currentCountry.addArmys(1);
					currentCountry = null;
					updateUI();
					game.moveToNext();
					game.play();
				}
				// during playing
				else if (round > 0) {
					// add soldier
					if (reinforcement > 0) {
						game.reinforcement(currentCountry);
						updateUI();
					}
					// attact
					else if (reinforcement == 0) {
						if (attacter == null)
							attacter = currentCountry;
						else if (attacter == currentCountry)
							attacter = null;
						else {
							game.attact(attacter, currentCountry);
							attacter = null;
						}
					}
				}
			}
			updateUI();
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
			if (currentCountry != null)
				if (!currentCountry.isLocateAt(e.getPoint()))
					currentCountry = null;
			if (currentCountry == null && AllCountry.size()!=0) {
				List<Country> countries = null;
				if (attacter == null) {
					countries = AllCountry.get(index);
					for (Country country : countries)
						if (country.isLocateAt(e.getPoint()) && !(reinforcement == 0 && country.getArmyCount() == 1))
							currentCountry = country;
				}
				if (attacter != null) {
					if (attacter.isLocateAt(e.getPoint()))
						currentCountry = attacter;
					countries = attacter.getNegibors(AllCountry.get(index));
					for (Country country : countries)
						if (country.isLocateAt(e.getPoint()))
							currentCountry = country;
				}
				updateUI();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			attacter = null;
			game.moveToNext();
			game.play();

		}
	}
}