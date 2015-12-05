package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import SongPlayer.SongPlayer;
import model.Card;
import model.Card.CardType;
import model.Country;
import model.Player;
import model.Player.PlayerType;
import model.RiskGame;
import model.RiskMap.CountryType;

public class Observer1 extends JPanel implements Observer {
	List<List<Country>> AllCountry;
	int index;
	JLabel min, max, str;
	JButton yes, no;
	JSlider slider;
	int from, to;
	int round;
	JButton AttactComplete, retreat, roll, cardTurn, cardTurnCancel;
	List<Player> players;
	Player currentPlayer;
	RiskGame game;
	Country currentCountry;
	Country attacter;
	Country MoveCountry;
	boolean attackDone = false;
	int reinforcement = 0;
	private List<Card> selectedCard;
	private BufferedImage map, bottom, gameOver, win, cardButton, cardModel, cannon, solider, horseman, cardFrame,
			redTick;
	private MouseListener listener;
	private JPanel numberBar, attackPanel, cardPanel;
	private Timer timer, time, wowTimer;
	private int animation, wowAnimation;
	private JTextArea attackInformation, attackFrom, attackTo;
	private TimeListener timeListener;
	private boolean isCard = false;
	private Point currentPoint;

	public Observer1(RiskGame Game) {
		currentPoint = new Point(0, 0);
		loadImage();
		setLayout(null);
		game = Game;
		update(game);
		setSize(800, 650);
		listener = new MouseOperation();
		addMouseListener(listener);
		addMouseMotionListener((MouseMotionListener) listener);
		AttactComplete = new JButton("Attact Completed");
		add(AttactComplete);
		AttactComplete.setSize(120, 80);
		AttactComplete.setLocation(650, 480);
		AttactComplete.addActionListener((ActionListener) listener);
		AttactComplete.setVisible(false);
		numberBar = new JPanel();
		numberBar.setVisible(false);
		numberBar.setSize(450, 200);
		numberBar.setLocation(200, 200);
		setNumberBar();
		add(numberBar);
		timeListener = new TimeListener(50, 0);
		timer = new Timer(100, timeListener);
		attackPanel = new JPanel();
		attackPanel.setSize(450, 250);
		attackPanel.setLocation(200, 150);
		attackPanel.setLayout(null);
		attackPanel.setVisible(false);
		add(attackPanel);

		roll = new JButton("Roll");
		roll.setSize(90, 40);
		roll.setLocation(330, 190);
		roll.addActionListener(new RollListener());
		retreat = new JButton("Retreat");
		retreat.setSize(90, 40);
		retreat.setLocation(200, 190);
		retreat.addActionListener(new RetreatListener());
		attackInformation = new JTextArea("adawdw\netwefewa\newrfewa");
		attackInformation.setLocation(120, 40);
		attackInformation.setSize(230, 200);
		attackInformation.setFocusable(false);
		attackInformation.setBackground(null);
		attackFrom = new JTextArea("from");
		attackFrom.setLocation(20, 100);
		attackFrom.setSize(90, 200);
		attackFrom.setFocusable(false);
		attackFrom.setBackground(null);
		attackTo = new JTextArea("To");
		attackTo.setLocation(360, 100);
		attackTo.setSize(90, 200);
		attackTo.setFocusable(false);
		attackTo.setBackground(null);
		attackPanel.add(roll);
		attackPanel.add(retreat);
		attackPanel.add(attackFrom);
		attackPanel.add(attackTo);

		attackPanel.add(attackInformation);
		wowAnimation = 0;
		wowTimer = new Timer(100, new WowTimerListener());
		wowTimer.start();

		cardTurn = new JButton("Submit");
		cardTurnCancel = new JButton("Cancel");
		cardPanel = new CardPanel();
		cardPanel.setSize(660, 250);
		cardPanel.setLocation(100, 150);
		cardPanel.setLayout(null);
		add(cardPanel);
		cardPanel.setVisible(false);
		cardTurn.addActionListener(new CanTurnListener());
		cardTurnCancel.addActionListener(new CardCancelListener());
		cardPanel.addMouseListener(new ClickCardListener());

	}

	private void loadImage() {
		try {
			map = ImageIO.read(new File("./picture/RiskMap.PNG"));
			bottom = ImageIO.read(new File("./picture/Box.jpeg"));
			gameOver = ImageIO.read(new File("./picture/gameOver.png"));
			cardButton = ImageIO.read(new File("./picture/CardButton.png"));
			win = ImageIO.read(new File("./picture/Win.png"));
			cardModel = ImageIO.read(new File("./picture/CardModel.jpg"));
			cannon = ImageIO.read(new File("./picture/Cannon.png"));
			solider = ImageIO.read(new File("./picture/Solider.png"));
			horseman = ImageIO.read(new File("./picture/Horseman.png"));
			cardFrame = ImageIO.read(new File("./picture/CardFrame.png"));
			redTick = ImageIO.read(new File("./picture/RedTick.png"));
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
		if (currentPlayer.getCardsHeld() != null && currentPlayer.getCardsHeld().size() == 5
				&& currentPlayer.getType().equals(PlayerType.Human)) {
			cardPanel.setVisible(true);
			stop();
		}
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
		g2.drawImage(map, 0, 0, 800, 466, null);
		g2.drawImage(bottom, 0, 465, 1200, 290, null);
		// beginning
		if (attacter == null & MoveCountry == null) {
			int i = 0;
			for (List<Country> countries : AllCountry) {
				for (Country country : countries) {
					if (country != currentCountry) {
						g2.setColor(players.get(i).getColor());
						g2.fillOval(country.getX(), country.getY(), 24, 24);
						g2.setColor(Color.WHITE);
						g2.setFont(new Font("Arial Black", Font.BOLD, 16));
						if (country.getArmyCount() < 10)
							g2.drawString("" + country.getArmyCount(), country.getX() + 5, country.getY() + 20);
						else
							g2.drawString("" + country.getArmyCount(), country.getX(), country.getY() + 20);
					} else if (country == currentCountry) {
						g2.setColor(players.get(i).getColor());
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
		}
		// attacking done
		if (MoveCountry != null) {
			g2.setColor(Color.WHITE);
			g2.fillOval(MoveCountry.getX(), MoveCountry.getY(), 24, 24);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Arial Black", Font.BOLD, 16));
			if (MoveCountry.getArmyCount() < 10)
				g2.drawString("" + MoveCountry.getArmyCount(), MoveCountry.getX() + 5, MoveCountry.getY() + 20);
			else
				g2.drawString("" + MoveCountry.getArmyCount(), MoveCountry.getX(), MoveCountry.getY() + 20);
			for (Country country : MoveCountry.getCanMoveCountry(AllCountry.get(index))) {
				if (country != currentCountry) {
					g2.setColor(currentPlayer.getColor());
					g2.fillOval(country.getX(), country.getY(), 24, 24);
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("Arial Black", Font.BOLD, 16));
					if (country.getArmyCount() < 10)
						g2.drawString("" + country.getArmyCount(), country.getX() + 5, country.getY() + 20);
					else
						g2.drawString("" + country.getArmyCount(), country.getX(), country.getY() + 20);
				} else if (country == currentCountry) {
					g2.setColor(currentPlayer.getColor());
					g2.fillOval(country.getX() - 20, country.getY(), 35, 35);
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("Arial Black", Font.BOLD, 24));
					if (country.getArmyCount() < 10)
						g2.drawString("" + country.getArmyCount(), country.getX() - 15, country.getY() + 30);
					else
						g2.drawString("" + country.getArmyCount(), country.getX() - 20, country.getY() + 30);
				}
			}
		}
		// attacking
		else if (attacter != null) {
			g2.setColor(Color.WHITE);
			g2.fillOval(attacter.getX(), attacter.getY(), 24, 24);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Arial Black", Font.BOLD, 16));
			if (attacter.getArmyCount() < 10)
				g2.drawString("" + attacter.getArmyCount(), attacter.getX() + 5, attacter.getY() + 20);
			else
				g2.drawString("" + attacter.getArmyCount(), attacter.getX(), attacter.getY() + 20);
			List<Country> a = attacter.getEnemyNegibors(AllCountry.get(index));
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

		// reinforcement in game
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

		// dialogue
		if (!(AllCountry != null && AllCountry.size() == 1)) {

			String str1, str2;
			if (reinforcement != 0) {
				str1 = "select a territory to reinforce with additional units. ";
				str2 = "You have " + reinforcement + " units left";
			} else if (attackDone) {
				if (MoveCountry != null) {
					str1 = "select a territory to move spare troops from or";
					str2 = "press the Fortification Completed button to end.";

				} else {
					str1 = "select a connected territory to move troops";
					str2 = "into.";
				}
			} else if (attacter == null) {
				str1 = "Select a territory to attack from or press the ";
				str2 = "Attacks Completed button.";
				if (currentPlayer.getType().equals(PlayerType.Human))
					AttactComplete.setVisible(true);
			} else {
				str1 = "Select a territory to attact from " + attacter.getname();
				str2 = "";
				if (currentPlayer.getType().equals(PlayerType.Human))
					AttactComplete.setVisible(true);
			}
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Arial Black", Font.BOLD, 26));
			g2.drawString("Round " + round, 20, 510);
			g2.drawString(currentPlayer.getName(), 20, 540);
			g2.setFont(new Font("Consolas", Font.PLAIN, 18));
			g2.drawString(str1, 150, 500);
			g2.drawString(str2, 150, 520);
		}

		// animation
		if (timer.isRunning()) {
			g2.setFont(new Font("Consolas", Font.PLAIN, 35));
			g2.setColor(Color.RED);
			if (isCard) {
				g2.drawString("+ " + currentPlayer.getCardUnit2() + " unit (Card)", 0, 280 - animation);
			} else {
				List<CountryType> temp = game.getMap().getCountinent();
				// extra continents unit
				if (temp != null && temp.size() > 0) {
					if (temp.contains(CountryType.NORTHAMERICA))
						g2.drawString("+ 5 extra unit", 30, 180 - animation);
					if (temp.contains(CountryType.SOUTHAMERICA))
						g2.drawString("+ 2 extra unit", 70, 390 - animation);
					if (temp.contains(CountryType.ASIA))
						g2.drawString("+ 7 extra unit", 520, 200 - animation);
					if (temp.contains(CountryType.AFRICA))
						g2.drawString("+ 3 extra unit", 290, 400 - animation);
					if (temp.contains(CountryType.AUSTRALIA))
						g2.drawString("+ 2 extra unit", 570, 420 - animation);
					if (temp.contains(CountryType.EUROPE))
						g2.drawString("+ 3 extra unit", 300, 180 - animation);

				}
			}
		}

		if (wowTimer.isRunning()) {
			g2.setFont(new Font("Consolas", Font.PLAIN, 70));
			g2.setColor(Color.WHITE);
			String temp = "";
			if (round == 0)
				temp = "Reinforcement";
			else if (reinforcement > 0 && index == 0 && round == 1)
				temp = "Game Start";
			else if (reinforcement > 0)
				temp = "Reinforcement ";
			else if (!attackDone)
				temp = "Attacking now";
			else
				temp = "Fortification now";
			g2.drawString(temp, 100, 330 - wowAnimation);

		}

		if (AllCountry != null && AllCountry.size() == 1) {
			if (MainMenu.isSounding) {
				SongPlayer.playFile(null, "./songfiles/win.wav");
			}
			g2.setColor(Color.RED);
			g2.setFont(new Font("Consolas", Font.BOLD, 120));
			g2.drawString(currentPlayer.getName(), 200, 150);
			g2.drawImage(win, 200, 160, 500, 250, null);
		}

		if (currentPlayer.getType().equals(PlayerType.Human)) {
			if (isAtCard(currentPoint))
				g2.drawImage(cardButton, 0, 320, 140, 140, null);
			else
				g2.drawImage(cardButton, 0, 330, 120, 120, null);

		}
	}

	public boolean isAtCard(Point a) {

		return a.getX() > 0 && a.getX() < 120 && a.getY() > 330 && a.getY() < 450;
	}

	private class MouseOperation implements MouseListener, MouseMotionListener, ActionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (isAtCard(currentPoint) && !attackPanel.isVisible() && !numberBar.isVisible()) {
				if (MainMenu.isSounding) {
					SongPlayer.playFile(null, "./songfiles/clickButton.wav");
				}
				if (cardPanel.isVisible()) {
					cardPanel.setVisible(false);
					resume();
				} else {
					selectedCard = new ArrayList<Card>();
					cardPanel.setVisible(true);
					stop();
				}
			}
			if (currentCountry != null) {
				// before playing
				if (round == 0) {
					currentCountry.addArmys(1);
					currentCountry = null;
					updateUI();
					game.moveToNext();
					game.play();
					if (round > 0)
						wowTimer.start();
				}
				// during playing
				else if (round > 0) {
					// add soldier
					if (MainMenu.isSounding) {
						SongPlayer.playFile(null, "./songfiles/reinforcement.wav");
					}
					if (reinforcement > 0) {
						game.reinforcement(currentCountry);
						if (reinforcement == 0)
							wowTimer.start();
						updateUI();
					}
					// attact
					else if (reinforcement == 0) {
						if (!attackDone) {
							if (attacter == null) {
								attacter = currentCountry;
								if (MainMenu.isSounding) {
									SongPlayer.playFile(null, "./songfiles/reinforcement.wav");
								}
							} else if (attacter == currentCountry)
								attacter = null;
							else if (!numberBar.isVisible()) {
								attackFrom.setText("From:\n" + attacter.getname() + "\n\t" + attacter.getArmyCount());
								attackTo.setText(
										"To: \n" + currentCountry.getname() + "\n\t" + currentCountry.getArmyCount());
								attackInformation.setText(
										"   Click 'Roll' to begin attacking \n Or click 'Retreat' to give up this attack");
								if (MainMenu.isSounding) {
									SongPlayer.playFile(null, "./songfiles/attack.wav");
								}
								attackPanel.setVisible(true);
								updateUI();
								stop();
							}
						} else {
							if (MoveCountry == null) {
								MoveCountry = currentCountry;
								if (MainMenu.isSounding) {
									SongPlayer.playFile(null, "./songfiles/reinforcement.wav");
								}
							} else if (MoveCountry == currentCountry) {
								MoveCountry = null;
								if (MainMenu.isSounding) {
									SongPlayer.playFile(null, "./songfiles/reinforcement.wav");
								}
							} else {
								stop();
								if (MainMenu.isSounding) {
									SongPlayer.playFile(null, "./songfiles/moveSoldier.wav");
								}
								fromCountry(MoveCountry, currentCountry);
								setNumber(MoveCountry.getArmyCount() - 1);
								numberBar.setVisible(true);
							}
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
			currentPoint = e.getPoint();
			if (currentCountry != null && !currentCountry.isLocateAt(e.getPoint()))
				currentCountry = null;
			if (AllCountry.size() > 1) {
				List<Country> countries = null;
				if (attacter == null && MoveCountry == null) {
					for (Country country : AllCountry.get(index)) {
						if (country.isLocateAt(e.getPoint())) {
							if (MainMenu.isSounding) {
								SongPlayer.playFile(null, "./songfiles/passButton.wav");
							}
							if (reinforcement == 0 && country.getArmyCount() == 1)
								return;
							else
								currentCountry = country;
						}
					}
				}
				if (attacter != null) {
					if (attacter.isLocateAt(e.getPoint()))
						currentCountry = attacter;
					else {
						for (Country country : attacter.getEnemyNegibors(AllCountry.get(index)))
							if (country.isLocateAt(e.getPoint()))
								currentCountry = country;
					}
				}
				if (MoveCountry != null) {
					if (MoveCountry.isLocateAt(e.getPoint()))
						currentCountry = MoveCountry;
					for (Country country : MoveCountry.getCanMoveCountry(AllCountry.get(index))) {
						if (country.isLocateAt(e.getPoint()))
							currentCountry = country;
					}
				}
			}

			updateUI();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (AttactComplete.getText().equals("Attact Completed")) {
				if (MainMenu.isSounding) {
					SongPlayer.playFile(null, "./songfiles/attackComplete.wav");
				}
				attacter = null;
				attackDone = true;
				AttactComplete.setText("Rertification Completed");
				wowTimer.start();
			} else {
				if (MainMenu.isSounding) {
					SongPlayer.playFile(null, "./songfiles/attackComplete.wav");
				}
				MoveCountry = null;
				attackDone = false;
				AttactComplete.setVisible(false);
				AttactComplete.setText("Attact Completed");
				game.moveToNext();
				game.play();
				ShowContinentUnit();
				wowTimer.start();
			}

		}

	}

	public void setNumberBar() {
		FlowLayout layout = new FlowLayout();
		layout.setHgap(50);
		layout.setVgap(40);
		numberBar.setLayout(layout);
		slider = new JSlider(1, 1);
		max = new JLabel("To");
		min = new JLabel("From");
		str = new JLabel("Player moving units from                                     ");
		yes = new JButton("Yes");
		no = new JButton("Cancel");
		no.setSize(500, 500);
		numberBar.add(str);
		numberBar.add(min);
		numberBar.add(slider);
		numberBar.add(max);
		numberBar.add(yes);
		numberBar.add(no);
		// numberBar.add(no,BorderLayout.SOUTH);
		yes.addActionListener(new yesListener());
		no.addActionListener(new noListener());
		;
		slider.addChangeListener(new sliderListener());
	}

	public void stop() {
		removeMouseMotionListener((MouseMotionListener) listener);
		removeMouseListener(listener);
	}

	public void resume() {
		addMouseMotionListener((MouseMotionListener) listener);
		addMouseListener(listener);
	}

	public void setNumber(int a) {
		slider.setMinimum(0);
		slider.setMaximum(a);
		slider.setValue(0);
		updateUI();
	}

	public void fromCountry(Country From, Country To) {
		from = From.getArmyCount();
		to = To.getArmyCount();
		min.setText("From: " + from);
		max.setText("To " + to);
		str.setText("        " + "Player moving units from " + From.getname() + " to " + To.getname() + "        ");
		updateUI();
	}

	public void ShowContinentUnit() {
		if (timer != null)
			timer.stop();
		timeListener = new TimeListener(50, 0);
		timer = new Timer(80, timeListener);
		timer.start();
	}

	public class sliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			min.setText("From " + (from - slider.getValue()));
			max.setText("To " + (to + slider.getValue()));

		}

	}

	public class yesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			numberBar.setVisible(false);
			if (MoveCountry != null) {
				if (MainMenu.isSounding) {
					SongPlayer.playFile(null, "./songfiles/reinforcement.wav");
				}
				MoveCountry.moveSolider(currentCountry, slider.getValue());
				AttactComplete.doClick();
			} else {
				if (MainMenu.isSounding) {
					SongPlayer.playFile(null, "./songfiles/moveSoldier.wav");
				}
				attacter.moveSolider(currentCountry, slider.getValue());
				attacter = null;
			}
			resume();
		}

	}

	public class noListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			numberBar.setVisible(false);
			resume();
		}

	}

	public class TimeListener implements ActionListener {
		private int i;

		public TimeListener(int a, int b) {
			animation = b;
			i = a;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (i > 0) {
				i--;
				animation += 2;
			} else {
				timer.stop();
				isCard = false;
			}
			updateUI();
		}
	}

	public class RollListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (MainMenu.isSounding) {
				SongPlayer.playFile(null, "./songfiles/dice.wav");
			}
			attackInformation.setText("");
			attackFrom.setText("From:\n" + attacter.getname() + "\n" + attacter.getArmyCount());
			attackTo.setText("To: \n" + currentCountry.getname() + "\n" + currentCountry.getArmyCount());
			updateUI();
			time = new Timer(1000, new TimerAttackListener());
			time.start();
		}

	}

	public class TimerAttackListener implements ActionListener {
		private int count = 1;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (count % 4 != 0 && !(attacter.getArmyCount() == 1 || currentCountry.getArmyCount() == 0)) {
				count++;
				attackInformation.setText(attackInformation.getText() + game.attactRoll(attacter, currentCountry));
				attackFrom.setText("From:\n" + attacter.getname() + "\n" + attacter.getArmyCount());
				attackTo.setText("To: \n" + currentCountry.getname() + "\n" + currentCountry.getArmyCount());
			} else {
				time.stop();
				if (attacter.getArmyCount() == 1) {
					attacter = null;
					currentCountry = null;
					attackPanel.setVisible(false);
					resume();
				} else if (currentCountry != null && currentCountry.getArmyCount() == 0) {
					attacter.moveSolider(currentCountry, 1);
					attackPanel.setVisible(false);
					numberBar.setVisible(true);
					setNumber(attacter.getArmyCount() - 1);
					fromCountry(attacter, currentCountry);
				}

			}
			updateUI();

		}

	}

	public class RetreatListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (MainMenu.isSounding) {
				SongPlayer.playFile(null, "./songfiles/retreat.wav");
			}
			currentCountry = null;
			attackPanel.setVisible(false);
			resume();
		}
	}

	public class WowTimerListener implements ActionListener {
		private int count = 1;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (count % 25 != 0) {
				count++;
				wowAnimation += 8;
				updateUI();
			} else {
				count++;
				wowTimer.stop();
				wowAnimation = 0;
				updateUI();
			}

		}

	}

	public class CanTurnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (selectedCard != null && selectedCard.size() == 3) {
				if (MainMenu.isSounding) {
					SongPlayer.playFile(null, "./songfiles/submitCard.wav");
				}
				if (selectedCard.get(0).CanTurnIn(selectedCard.get(1), selectedCard.get(2))) {
					currentPlayer.submitCard(selectedCard.get(0), selectedCard.get(1), selectedCard.get(2));
					cardPanel.setVisible(false);
					game.addCardUnit();
					resume();
					isCard = true;
					ShowContinentUnit();
				}
			}

		}

	}

	public class CardCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentPlayer.getCardsHeld() != null && currentPlayer.getCardsHeld().size() < 5) {
				cardPanel.setVisible(false);
				resume();
			}
		}

	}

	public class CardPanel extends JPanel {
		public CardPanel() {
			cardTurn.setSize(80, 40);
			cardTurnCancel.setSize(80, 40);
			cardTurn.setLocation(300, 190);
			cardTurnCancel.setLocation(400, 190);
			add(cardTurn);
			add(cardTurnCancel);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(cardFrame, 0, 0, 660, 250, null);
			if (currentPlayer.getCardsHeld() != null && currentPlayer.getCardsHeld().size() > 0) {
				// draw card1
				g2.drawImage(cardModel, 40, 30, 100, 160, null);
				drawCard(g2, 0);
				if (currentPlayer.getCardsHeld().size() > 1) {
					// draw card2
					g2.drawImage(cardModel, 160, 30, 100, 160, null);
					drawCard(g2, 1);
					if (currentPlayer.getCardsHeld().size() > 2) {
						// draw card3
						g2.drawImage(cardModel, 280, 30, 100, 160, null);
						drawCard(g2, 2);
						if (currentPlayer.getCardsHeld().size() > 3) {
							// draw card4
							g2.drawImage(cardModel, 400, 30, 100, 160, null);
							drawCard(g2, 3);
							if (currentPlayer.getCardsHeld().size() > 4) {
								// draw card5
								g2.drawImage(cardModel, 520, 30, 100, 160, null);
								drawCard(g2, 4);

							}
						}
					}
				}
			}
			for (Card temp : selectedCard) {
				int a = currentPlayer.getCardsHeld().indexOf(temp);
				g2.drawImage(redTick, 40 + 120 * a, 30, 100, 160, null);
			}
		}
	}

	public void drawCard(Graphics2D g2, int i) {
		Card temp = currentPlayer.getCardsHeld().get(i);
		BufferedImage a;
		if (temp.getType().equals(CardType.Soldier)) {
			a = solider;
		} else if (temp.getType().equals(CardType.Horseman)) {
			a = horseman;
		} else if (temp.getType().equals(CardType.Cannon)) {
			a = cannon;
		} else
			a = cardButton;
		g2.drawImage(a, 50 + 120 * i, 50, 70, 70, null);
		g2.setFont(new Font("Consolas", Font.BOLD, 20));
		g2.drawString(currentPlayer.getCardsHeld().get(i).getType().toString(), 50 + 120 * i, 160);
	}

	public boolean isLocatedCard1(Point a) {
		return 40 < a.getX() && a.getX() < 140 && 30 < a.getY() && a.getY() < 190;
	}

	public boolean isLocatedCard2(Point a) {
		return 160 < a.getX() && a.getX() < 260 && 30 < a.getY() && a.getY() < 190;
	}

	public boolean isLocatedCard3(Point a) {
		return 280 < a.getX() && a.getX() < 380 && 30 < a.getY() && a.getY() < 190;
	}

	public boolean isLocatedCard4(Point a) {
		return 400 < a.getX() && a.getX() < 500 && 30 < a.getY() && a.getY() < 190;
	}

	public boolean isLocatedCard5(Point a) {
		return 520 < a.getX() && a.getX() < 620 && 30 < a.getY() && a.getY() < 190;
	}

	public class ClickCardListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (isLocatedCard1(e.getPoint()))
				if (currentPlayer.getCardsHeld() != null && currentPlayer.getCardsHeld().size() > 0) {
					if (selectedCard.contains(currentPlayer.getCardsHeld().get(0)))
						selectedCard.remove(currentPlayer.getCardsHeld().get(0));
					else
						selectedCard.add(currentPlayer.getCardsHeld().get(0));
				}
			if (isLocatedCard2(e.getPoint()))
				if (currentPlayer.getCardsHeld() != null && currentPlayer.getCardsHeld().size() > 1) {
					if (selectedCard.contains(currentPlayer.getCardsHeld().get(1)))
						selectedCard.remove(currentPlayer.getCardsHeld().get(1));
					else
						selectedCard.add(currentPlayer.getCardsHeld().get(1));
				}
			if (isLocatedCard3(e.getPoint()))
				if (currentPlayer.getCardsHeld() != null && currentPlayer.getCardsHeld().size() > 2) {
					if (selectedCard.contains(currentPlayer.getCardsHeld().get(2)))
						selectedCard.remove(currentPlayer.getCardsHeld().get(2));
					else
						selectedCard.add(currentPlayer.getCardsHeld().get(2));
				}
			if (isLocatedCard4(e.getPoint()))
				if (currentPlayer.getCardsHeld() != null && currentPlayer.getCardsHeld().size() > 3) {
					if (selectedCard.contains(currentPlayer.getCardsHeld().get(3)))
						selectedCard.remove(currentPlayer.getCardsHeld().get(3));
					else
						selectedCard.add(currentPlayer.getCardsHeld().get(3));
				}
			if (isLocatedCard5(e.getPoint()))
				if (currentPlayer.getCardsHeld() != null && currentPlayer.getCardsHeld().size() > 4) {
					if (selectedCard.contains(currentPlayer.getCardsHeld().get(4)))
						selectedCard.remove(currentPlayer.getCardsHeld().get(4));
					else
						selectedCard.add(currentPlayer.getCardsHeld().get(4));
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

	}

}