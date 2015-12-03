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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.BeginnerAI;
import model.HardAI;
import model.Human;
import model.IntermediateAI;
import model.Player;
import model.RiskGame;
import model.RiskMap;

public class MainMenu extends JPanel {
	private RiskGame game;
	private boolean isGaming;
	private Observer1 observer1;
	private Observer2 observer2;
	private MouseOperation mouseListener;
	private Point currentMouse;
	private List<Player> players;
	private BufferedImage background, volumnOpen, volumnMute, menuBackground;
	private JPanel playerSelection,gameMenu;
	private JLabel player1, player2, player3, player4, player5, player6;
	private JComboBox type1, type2, type3, type4, type5, type6;
	private JLabel name1, name2, name3, name4, name5, name6;
	private JButton menu,restart,loadGame, save,sound, menuDone ;
	private JTextArea about;
	private boolean isSounding = true;

	public MainMenu() {
		currentMouse = new Point(0, 0);
		game = null;
		isGaming = false;
		setLayout(null);
		SetUpModelAndButtons();
		repaint();
	}

	public void SetUpModelAndButtons() {
		mouseListener = new MouseOperation();
		addMouseMotionListener(mouseListener);
		addMouseListener(mouseListener);
		try {
			background = ImageIO.read(new File("./picture/MenuBackground.jpg"));
			volumnOpen = ImageIO.read(new File("./picture/VolumnOpen.jpeg"));
			volumnMute = ImageIO.read(new File("./picture/VolumeMust.png"));
			menuBackground = ImageIO.read(new File("./picture/MenuBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		playerSelection = new JPanel();
		playerSelection.setSize(500, 400);
		playerSelection.setLocation(120, 80);
		playerSelection.setBackground(Color.WHITE);
		FlowLayout layout = new FlowLayout(3, 70, 35);
		playerSelection.setLayout(layout);
		player1 = new JLabel("Player1");
		player2 = new JLabel("Player2");
		player3 = new JLabel("Player3");
		player4 = new JLabel("Player4");
		player5 = new JLabel("Player5");
		player6 = new JLabel("Player6");
		String[] typeList = { "none", "Human", "Easy AI", "Medium AI", "Hard AI" };
		type1 = new JComboBox(typeList);
		type2 = new JComboBox(typeList);
		type3 = new JComboBox(typeList);
		type4 = new JComboBox(typeList);
		type5 = new JComboBox(typeList);
		type6 = new JComboBox(typeList);
		type1.addActionListener(new TypeListener1());
		type2.addActionListener(new TypeListener2());
		type3.addActionListener(new TypeListener3());
		type4.addActionListener(new TypeListener4());
		type5.addActionListener(new TypeListener5());
		type6.addActionListener(new TypeListener6());
		name1 = new JLabel("        ");
		name2 = new JLabel("        ");
		name3 = new JLabel("        ");
		name4 = new JLabel("        ");
		name5 = new JLabel("        ");
		name6 = new JLabel("        ");

		playerSelection.add(player1);
		playerSelection.add(type1);
		playerSelection.add(name1);
		playerSelection.add(player2);
		playerSelection.add(type2);
		playerSelection.add(name2);
		playerSelection.add(player3);
		playerSelection.add(type3);
		playerSelection.add(name3);
		playerSelection.add(player4);
		playerSelection.add(type4);
		playerSelection.add(name4);
		playerSelection.add(player5);
		playerSelection.add(type5);
		playerSelection.add(name5);
		playerSelection.add(player6);
		playerSelection.add(type6);
		playerSelection.add(name6);
		playerSelection.setVisible(false);
		add(playerSelection);

		about = new JTextArea("Game Name:\tRisk Game\nTeamates:\tDaniel Green, Kim Francis, Chen Zhao\n"
				+ "\n\tRisk Game is a fascinating game of stragetegy in which"
				+ "\na player can conquer the world. Click 'Create New Game to "
				+ "\nplay a new Game. Click 'Load Game to load previous game. "
				+ "\nAt the beginning of the game, you will be randomly assigned"
				+ "\nsome countries. Your main goal is to conquer all of the"
				+ "\ncountries. set army on your coutries and attact their"
				+ "\ncountries and move soldier to other countries. You can"
				+ "\ntrun in 3 same or differnet cards when you set your army. ");
		about.setFont(new Font("Consolas", Font.PLAIN, 16));
		about.setSize(560, 350);
		about.setLocation(160, 100);
		about.setVisible(false);
		about.setOpaque(false);
		about.setFocusable(false);
		add(about);
		
		menu = new JButton("Menu");
		menu.setSize(130, 50);
		menu.setLocation(10, 550);
		menu.addActionListener(new MenuListener());
		gameMenu=new JPanel();
		gameMenu.setLocation(230,60);
		gameMenu.setSize(350,400);
		gameMenu.setVisible(false);
		gameMenu.setLayout(null);
		gameMenu.setBackground(Color.black);
		
	//	restart,loadGame, save,sound, menuDone
		restart=new JButton("restart");
		restart.setFont(new Font("Consolas", Font.PLAIN, 30));
		restart.setSize(200,50);
		restart.setLocation(80,30);
		restart.addActionListener(new RestartListener());

		loadGame=new JButton("Load Game");
		loadGame.setFont(new Font("Consolas", Font.PLAIN, 30));
		loadGame.setSize(200,50);
		loadGame.setLocation(80,100);
		loadGame.addActionListener(new LoadGameListener());

		save=new JButton("Save Game");
		save.setFont(new Font("Consolas", Font.PLAIN, 30));
		save.setSize(200,50);
		save.setLocation(80,170);
		save.addActionListener(new SaveGameListener());
		
		if(isSounding)
		sound=new JButton("Sound: ON");
		else sound=new JButton("Sound: OFF");
		sound.setFont(new Font("Consolas", Font.PLAIN, 30));
		sound.setSize(200,50);
		sound.setLocation(80,240);
		sound.addActionListener(new GameSoundListener());
		
		menuDone=new JButton("Done");
		menuDone.setFont(new Font("Consolas", Font.PLAIN, 30));
		menuDone.setSize(100,50);
		menuDone.setLocation(130,330);
		menuDone.addActionListener(new MenuDoneListener());
		
		gameMenu.add(restart);
		gameMenu.add(loadGame);
		gameMenu.add(save);
		gameMenu.add(sound);
		gameMenu.add(menuDone);
		
		
	}

	public boolean isGaming(){
		return isGaming;
	}
	
	public void paintComponent(Graphics g) {
		if (!isGaming) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(background, 0, 0, GameGUI.SCREEN_LENGTH, GameGUI.SCREEN_HEIGHT, null);
			g2.drawImage(volumnOpen, 30, 550, 80, 80, null);
			if (!isSounding)
				g2.drawImage(volumnMute, 30, 550, 80, 80, null);

			// draw background
			g2.setFont(new Font("Consolas", Font.PLAIN, 30));
			if (game == null) {
				if (about.isVisible()) {
					g2.setColor(Color.WHITE);
					g2.fillRect(150, 80, 600, 430);
					g2.setColor(Color.BLACK);
					g2.drawRect(500, 450, 200, 45);
					if (isBackFromAbout(currentMouse))
						g2.drawString("Click Here", 520, 480);
					else
						g2.drawString("  Go Back", 520, 480);
				} else {
					g2.setColor(Color.WHITE);
					g2.fillRect(80, 215, 250, 50);
					g2.fillRect(80, 315, 250, 50);
					g2.fillRect(80, 415, 250, 50);
					g2.setColor(Color.BLACK);
					if (isNewGame(currentMouse))
						g2.drawString("Click Here", 100, 250);
					else
						g2.drawString("Creat New Game", 100, 250);
					if (isLoadGame(currentMouse))
						g2.drawString("Click Here", 100, 350);
					else
						g2.drawString("Load Game", 100, 350);
					if (isAbout(currentMouse))
						g2.drawString("Click Here", 100, 450);
					else
						g2.drawString("About", 100, 450);
				}
			} else {
				g2.setColor(Color.WHITE);
				g2.fillRect(590, 540, 160, 50);
				g2.fillRect(350, 540, 160, 50);
				g2.setFont(new Font("Consolas", Font.PLAIN, 24));
				g2.setColor(Color.BLACK);
				if (isStart(currentMouse))
					g2.drawString(" Click Here", 600, 570);
				else
					g2.drawString("Let's Start", 600, 570);
				if (isBack(currentMouse))
					g2.drawString(" Click Here", 350, 570);
				else
					g2.drawString(" Go Back", 350, 570);
			}
		}
	}

	private void loadGame() {
		try {
			FileInputStream fos = new FileInputStream("myFile");
			ObjectInputStream oos = new ObjectInputStream(fos);
			game = (RiskGame) oos.readObject();
			oos.close();
			fos.close();
		} catch (Exception a) {
		}
		if (game == null || (game.getRound()==0&&game.getReinforcement()==0)) {
			JOptionPane.showMessageDialog(null, "No previous record. Please start a new game");
			game=null;
		}
		else
		GameBegin();
	}

	public void GameBegin() {
		isGaming = true;
		playerSelection.setVisible(false);
		type1.setSelectedIndex(0);
		type2.setSelectedIndex(0);
		type3.setSelectedIndex(0);
		type4.setSelectedIndex(0);
		type5.setSelectedIndex(0);
		type6.setSelectedIndex(0);
		removeMouseMotionListener(mouseListener);
		removeMouseListener(mouseListener);

		observer1 = new Observer1(game);
		game.addObserver((Observer) observer1);
		add(observer1);
		observer1.setLocation(0, 50);
		observer1.add(menu);
		observer1.add(gameMenu);

		observer2 = new Observer2(game);
		game.addObserver(observer2);
		observer2.setSize(GameGUI.SCREEN_LENGTH, 50);
		observer2.setLocation(0, 0);
		add(observer2);
		updateUI();
	}

	public boolean isNewGame(Point a) {
		return (60 < a.getX() && a.getX() < 330) && (215 < a.getY() && a.getY() < 265);
	}

	public boolean isBackFromAbout(Point a) {
		return (500 < a.getX() && a.getX() < 650) && (450 < a.getY() && a.getY() < 495);
	}

	public boolean isLoadGame(Point a) {
		return (60 < a.getX() && a.getX() < 330) && (315 < a.getY() && a.getY() < 365);
	}

	public boolean isStart(Point a) {
		return (590 < a.getX() && a.getX() < 750) && (540 < a.getY() && a.getY() < 590);
	}

	public boolean isBack(Point a) {
		return (350 < a.getX() && a.getX() < 510) && (540 < a.getY() && a.getY() < 590);
	}

	public boolean isSound(Point a) {
		return (30 < a.getX() && a.getX() < 110) && (550 < a.getY() && a.getY() < 630);
	}

	public boolean isAbout(Point a) {
		return (60 < a.getX() && a.getX() < 330) && (415 < a.getY() && a.getY() < 465);
	}

	public RiskGame getGame() {
		return game;
	}

	private void addPlayers() {
		Player temp;
		players = new ArrayList<Player>();
		if (type1.getSelectedIndex() != 0) {
			if (type1.getSelectedIndex() == 1)
				temp = new Human(name1.getText(), Color.BLUE);
			else if (type1.getSelectedIndex() == 2)
				temp = new BeginnerAI(name1.getText(), Color.BLUE);
			else if (type1.getSelectedIndex() == 3)
				temp = new IntermediateAI(name1.getText(), Color.BLUE);
			else
				temp = new HardAI(name1.getText(), Color.BLUE);
			players.add(temp);
		}

		if (type2.getSelectedIndex() != 0) {
			if (type2.getSelectedIndex() == 1)
				temp = new Human(name2.getText(), Color.GREEN);
			else if (type2.getSelectedIndex() == 2)
				temp = new BeginnerAI(name2.getText(), Color.GREEN);
			else if (type2.getSelectedIndex() == 3)
				temp = new IntermediateAI(name2.getText(), Color.GREEN);
			else
				temp = new HardAI(name2.getText(), Color.GREEN);
			players.add(temp);
		}

		if (type3.getSelectedIndex() != 0) {
			if (type3.getSelectedIndex() == 1)
				temp = new Human(name3.getText(), Color.BLACK);
			else if (type3.getSelectedIndex() == 2)
				temp = new BeginnerAI(name3.getText(), Color.BLACK);
			else if (type3.getSelectedIndex() == 3)
				temp = new IntermediateAI(name3.getText(), Color.BLACK);
			else
				temp = new HardAI(name3.getText(), Color.BLACK);
			players.add(temp);
		}

		if (type4.getSelectedIndex() != 0) {
			if (type4.getSelectedIndex() == 1)
				temp = new Human(name4.getText(), Color.MAGENTA);
			else if (type4.getSelectedIndex() == 2)
				temp = new BeginnerAI(name4.getText(), Color.MAGENTA);
			else if (type4.getSelectedIndex() == 3)
				temp = new IntermediateAI(name4.getText(), Color.MAGENTA);
			else
				temp = new HardAI(name4.getText(), Color.MAGENTA);
			players.add(temp);
		}

		if (type5.getSelectedIndex() != 0) {
			if (type5.getSelectedIndex() == 1)
				temp = new Human(name5.getText(), Color.RED);
			else if (type5.getSelectedIndex() == 2)
				temp = new BeginnerAI(name5.getText(), Color.RED);
			else if (type5.getSelectedIndex() == 3)
				temp = new IntermediateAI(name5.getText(), Color.RED);
			else
				temp = new HardAI(name5.getText(), Color.RED);
			players.add(temp);
		}

		if (type6.getSelectedIndex() != 0) {
			if (type6.getSelectedIndex() == 1)
				temp = new Human(name6.getText(), Color.GRAY);
			else if (type6.getSelectedIndex() == 2)
				temp = new BeginnerAI(name6.getText(), Color.GRAY);
			else if (type6.getSelectedIndex() == 3)
				temp = new IntermediateAI(name6.getText(), Color.GRAY);
			else
				temp = new HardAI(name6.getText(), Color.GRAY);
			players.add(temp);
		}
	}

	public class MouseOperation implements MouseMotionListener, MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (isSound(currentMouse)) {
				if (isSounding)
					isSounding = false;
				else
					isSounding = true;
			}

			if (game == null && isNewGame(currentMouse) && !about.isVisible()) {
				game = new RiskGame();
				playerSelection.setVisible(true);
			}
			if (game == null && isLoadGame(currentMouse) && !about.isVisible())
				loadGame();
			if (game == null && isAbout(currentMouse) && !about.isVisible()) {
				about.setVisible(true);
			}
			if (game == null && isBackFromAbout(currentMouse) && about.isVisible()) {
				about.setVisible(false);
			}

			if (game != null && isStart(currentMouse)) {
				addPlayers();
				if (players.size() < 2)
					JOptionPane.showMessageDialog(null, "add at least two players");
				else {
					for (Player a : players)
						game.addPlayer(a);
					game.randomSetCountry(new RiskMap().getAllCountry());
					game.play();
					GameBegin();
				}
			}
			if (game != null && isBack(currentMouse)) {
				game = null;
				playerSelection.setVisible(false);
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
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			currentMouse = e.getPoint();
			updateUI();
		}

	}

	public class TypeListener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (type1.getSelectedIndex() != 0)
				name1.setText("Michael ");
			else
				name1.setText("        ");
		}

	}

	public class TypeListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (type2.getSelectedIndex() != 0)
				name2.setText("Chris   ");
			else
				name2.setText("        ");
		}

	}

	public class TypeListener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (type3.getSelectedIndex() != 0)
				name3.setText("Calamusa");
			else
				name3.setText("        ");
		}

	}

	public class TypeListener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (type4.getSelectedIndex() != 0)
				name4.setText("Baird   ");
			else
				name4.setText("        ");
		}

	}

	public class TypeListener5 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (type5.getSelectedIndex() != 0)
				name5.setText("Freier  ");
			else
				name5.setText("        ");
		}

	}

	public class TypeListener6 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (type6.getSelectedIndex() != 0)
				name6.setText("Sprindys");
			else
				name6.setText("        ");
		}

	}

	public class RestartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to restart?", null, JOptionPane.YES_NO_OPTION);	
			if (reply == JOptionPane.YES_OPTION){
			remove(observer1);
			remove(observer2);
			gameMenu.setVisible(false);
			game = null;
			isGaming = false;
			addMouseListener(mouseListener);
			addMouseMotionListener(mouseListener);
			updateUI();
			}
		}

	}

	public class SaveGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to save", null, JOptionPane.YES_NO_OPTION);	
			if (reply == JOptionPane.YES_OPTION){
				try {
					FileOutputStream fos = new FileOutputStream("myFile");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject((RiskGame)game);	
					oos.close();
					fos.close();
				} catch (Exception e1) {
				}		
			
		}
		}
	}
	
	public class MenuDoneListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			gameMenu.setVisible(false);
			observer1.resume();
		}
		
	}
	
	public class LoadGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			RiskGame temp=new RiskGame();
			try {
				FileInputStream fos = new FileInputStream("myFile");
				ObjectInputStream oos = new ObjectInputStream(fos);
				temp = (RiskGame) oos.readObject();
				oos.close();
				fos.close();
			} catch (Exception a) {
			}
			if (temp == null || (temp.getRound()==0&&temp.getReinforcement()==0)) 
				JOptionPane.showMessageDialog(null, "No previous record.");
			else{
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to load game", null, JOptionPane.YES_NO_OPTION);	
			if (reply == JOptionPane.YES_OPTION){
				game=temp;
				remove(observer1);
				remove(observer2);
				gameMenu.setVisible(false);
				GameBegin();
			}
		}
		}
		
	}
	
	public class GameSoundListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(isSounding){
				isSounding=false;
				sound.setText("Sound: OFF");
			}
			else {
				isSounding =true;
				sound.setText("Sound: ON");
			}
		}
		
	}
	public class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!gameMenu.isVisible()){
			gameMenu.setVisible(true);
			observer1.stop();
			}
			else{ gameMenu.setVisible(false);
				observer1.resume();
			}
			
		}
		
	}

	
}
