package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Country;
import model.Player;
import model.RiskGame;
import model.RiskMap.CountryType;

public class Observer1 extends JPanel implements Observer {
	List<List<Country>> AllCountry;
	int index;
	int round;
	JButton AttactComplete;
	List<Player> players;
	Player currentPlayer;
	RiskGame game;
	Country currentCountry;
	Country attacter;
	Country MoveCountry;
	boolean attackDone = false;
	int reinforcement = 0;
	private BufferedImage map;
	private BufferedImage bottom;
	private MouseListener listener;
	private JPanel numberBar;
	private Timer timer;
	private int animation;
	private TimeListener timeListener;
	public Observer1(RiskGame Game) {
		loadImage();
		setLayout(null);
		game = Game;
		update(game);
		setSize(800, 650);
		listener=new MouseOperation();
		addMouseListener(listener);
		addMouseMotionListener((MouseMotionListener)listener);
		AttactComplete = new JButton("Attact Completed");
		add(AttactComplete);
		AttactComplete.setSize(120, 80);
		AttactComplete.setLocation(650, 480);
		AttactComplete.addActionListener((ActionListener)listener);
		AttactComplete.setVisible(false);
		numberBar = new JPanel();
		numberBar.setVisible(false);
		numberBar.setSize(400,200);
		numberBar.setLocation(200,200);
		setNumberBar();
		add(numberBar);
		timeListener = new TimeListener();
		timer=new Timer(100,timeListener);
	}

	private void loadImage() {
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
			AttactComplete.setVisible(true);
		} else {
			str1 = "Select a territory to attact from " + attacter.getname();
			str2 = "";
			AttactComplete.setVisible(true);
		}
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Arial Black", Font.BOLD, 26));
		g2.drawString("Round " + round, 20, 510);
		g2.drawString(currentPlayer.getName(), 20, 540);
		g2.setFont(new Font("Consolas", Font.PLAIN, 18));
		g2.drawString(str1, 150, 500);
		g2.drawString(str2, 150, 520);
		
		//animation
		if(timer.isRunning()){
			List<CountryType> temp = game.getMap().getCountinent();
			//extra continents unit
			if(temp!=null &&temp.size()>0){
				g2.setFont(new Font("Consolas", Font.PLAIN, 30));
				g2.setColor(Color.WHITE);
				if(temp.contains(CountryType.NORTHAMERICA))
					g2.drawString("+ 5 extra unit", 30, 180-animation);
				if(temp.contains(CountryType.SOUTHAMERICA))
					g2.drawString("+ 2 extra unit", 60, 390-animation);
				if(temp.contains(CountryType.ASIA))
					g2.drawString("+ 7 extra unit", 520, 200-animation);
				if(temp.contains(CountryType.AFRICA))
					g2.drawString("+ 3 extra unit", 290, 400-animation);
				if(temp.contains(CountryType.AUSTRALIA))
					g2.drawString("+ 2 extra unit", 570, 420-animation);
				if(temp.contains(CountryType.EUROPE))
					g2.drawString("+ 3 extra unit", 300, 180-animation);
				
			}
		}
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
						if (!attackDone) {
							if (attacter == null)
								attacter = currentCountry;
							else if (attacter == currentCountry)
								attacter = null;
							else {
								game.attact(attacter, currentCountry);
								if(AllCountry.get(index).contains(currentCountry)){
									stop();
									setNumber(attacter.getArmyCount()-1);
									fromCountry(attacter,currentCountry);
									numberBar.setVisible(true);
								}
								else 
								attacter=null;
									
							}
						} else {
							if (MoveCountry == null)
								MoveCountry = currentCountry;
							else if (MoveCountry == currentCountry)
								MoveCountry = null;
							else {
								stop();
								fromCountry(MoveCountry,currentCountry);
								setNumber(MoveCountry.getArmyCount()-1);
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
			if (currentCountry != null && !currentCountry.isLocateAt(e.getPoint()))
				currentCountry = null;
			if (AllCountry.size() != 0) {
				List<Country> countries = null;
				if (attacter == null && MoveCountry == null) {
					for (Country country : AllCountry.get(index)) {
						if (country.isLocateAt(e.getPoint())) {
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
				
				updateUI();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (AttactComplete.getText().equals("Attact Completed")) {
				attacter = null;
				attackDone = true;
				AttactComplete.setText("Rertification Completed");
			} else {
				MoveCountry = null;
				attackDone = false;
				AttactComplete.setVisible(false);
				AttactComplete.setText("Attact Completed");
				game.moveToNext();
				game.play();
				ShowContinentUnit();
			}

		}

	}
		JLabel min, max, str;
		JButton yes, no;
		JSlider slider;
		int from,to;
		public void setNumberBar(){
			numberBar.setLayout(new BorderLayout());
			slider= new JSlider(1,1);
			numberBar.add(slider,BorderLayout.CENTER);
			max= new JLabel("To");
			min = new JLabel("From");
			str= new JLabel("Player moving units");
			yes = new JButton("YES");
			no = new JButton("NO");
			numberBar.add(min, BorderLayout.WEST);
			numberBar.add(max, BorderLayout.EAST);
			numberBar.add(str, BorderLayout.NORTH);
			numberBar.add(yes,BorderLayout.SOUTH);
//			numberBar.add(no,BorderLayout.SOUTH);
			yes.addActionListener(new yesListener());
			no.addActionListener(new noListener());;
			slider.addChangeListener(new sliderListener());
		}
		
		public void stop(){
			removeMouseMotionListener((MouseMotionListener) listener);
		}
		
		public void resume(){
			addMouseMotionListener((MouseMotionListener) listener);
		}
		public void setNumber(int a){
			slider.setMinimum(0);
			slider.setMaximum(a);
			slider.setValue(0);
			updateUI();
		}
		
		public void fromCountry(Country From, Country To){
			from=From.getArmyCount();
			to=To.getArmyCount();
			min.setText("From: "+from);
			max.setText("To "+to);
			str.setText("Player moving units from " + From.getname()+" to "+ To.getname());
			updateUI();
		}
		
		public void ShowContinentUnit(){
			if(timer!=null)
				timer.stop();
			timeListener = new TimeListener();
			timer=new Timer(100,timeListener);
			timer.start();
		}
		
		public class sliderListener implements ChangeListener{

			@Override
			public void stateChanged(ChangeEvent e) {
				min.setText("From "+(from-slider.getValue()));
				max.setText("To "+(to+slider.getValue()));
			}
			
		}
		public class yesListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				numberBar.setVisible(false);
				if(MoveCountry!=null){
					MoveCountry.moveSolider(currentCountry, slider.getValue());
					AttactComplete.doClick();
			}
				else{ attacter.moveSolider(currentCountry,slider.getValue());
						attacter = null;
				}
				resume();
			}
			
		}
		public class noListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				numberBar.setVisible(false);
				addMouseMotionListener((MouseMotionListener) listener);
			}
			
		}
		
		public class TimeListener implements ActionListener{
			private int i;
			public TimeListener(){
				animation=0;
				i=50;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i>0){
					i--;
					animation+=2;
				}
				else {timer.stop();
				animation=0;
				}
				updateUI();
				}
			}
			
}