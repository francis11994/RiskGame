package View;

import java.awt.Color;
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
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Country;
import model.Player;
import model.RiskGame;

public class SoldierObserver extends JPanel implements Observer{
	List<List<Country>> AllCountry;
	int index;
	int round;
	List<Player> players;
	Player currentPlayer;
	RiskGame game;
	Country currentCountry;
	private BufferedImage map;
//	private JButton Finish=new JButton("Finish this turn");
	public SoldierObserver(RiskGame Game){
		game=Game;
		update(game);
		setSize(GameGUI.SCREEN_LENGTH, GameGUI.SCREEN_HEIGHT);
		setLayout(null);
//		add(Finish);
//		Finish.addActionListener(new MouseOperation());
//		Finish.setSize(50,50);
		addMouseListener(new MouseOperation());
		addMouseMotionListener(new MouseOperation());
		try {
			map=ImageIO.read(new File("./picture/RiskMap.PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void update(RiskGame game){
		AllCountry = game.getAllCountry();
		index=game.getIndex();
		round = game.getRound();
		players = game.getAllPlayer();
		currentPlayer = game.getPlayer();
		currentCountry=null;
	}
	@Override
	public void update(Observable o, Object arg) {
		update((RiskGame)o);	
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(map,0,0,GameGUI.SCREEN_LENGTH, GameGUI.SCREEN_HEIGHT,null);
		int i=0;
		
		for(List<Country> countries:AllCountry){
			Player a=players.get(i);
			for(Country country:countries){
				if(country!=currentCountry){
				g2.setColor(a.getColor());
				//if(country.getArmyCount()<10)
				g2.fillOval(country.getX(),country.getY(),28,28);
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Arial Black", Font.BOLD,20));
				if(country.getArmyCount()<10)
				g2.drawString(""+country.getArmyCount(), country.getX()+5,country.getY()+20);
				else g2.drawString(""+country.getArmyCount(), country.getX(),country.getY()+20);
				}
				else {
					g2.setColor(a.getColor());
					g2.fillOval(country.getX()-20,country.getY()+20,40,40);
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("Arial Black", Font.BOLD,30));
					if(country.getArmyCount()<10)
					g2.drawString(""+country.getArmyCount(), country.getX()-15,country.getY()+50);
					else g2.drawString(""+country.getArmyCount(), country.getX()-20,country.getY()+50);
				}
			}
			i++;
		}
	}

	public boolean getClickedCountry(Point point){
		boolean clicked=false;
		for(List<Country> countrys:AllCountry)
			for(Country country:countrys){
				if(country.isLocateAt(point)){
					currentCountry=country;
					clicked=true;
				}
			}
		if(!clicked)
			currentCountry=null;
		repaint();
		return clicked;
	}
	
	private class MouseOperation implements ActionListener,MouseListener, MouseMotionListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(currentCountry!=null)
				currentCountry.addArmys(1);
			repaint();
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
			getClickedCountry(e.getPoint());

		}

		@Override
		public void actionPerformed(ActionEvent e) {
		
		}
		
	}
}
