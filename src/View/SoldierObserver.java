package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class SoldierObserver extends JPanel implements Observer{
	List<List<Country>> AllCountry;
	int index;
	int round;
	List<Player> players;
	Player currentPlayer;
	RiskGame game;
	Country currentCountry;
	private BufferedImage map;
	public SoldierObserver(RiskGame Game){
		game=Game;
		update(game);
		setSize(GameGUI.SCREEN_LENGTH, GameGUI.SCREEN_HEIGHT);
		setLayout(null);
		addMouseListener(new MouseOperation());
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
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(map,0,0,GameGUI.SCREEN_LENGTH, GameGUI.SCREEN_HEIGHT,null);
		int i=0;
		for(List<Country> countries:AllCountry){
			Player a=players.get(i);
			for(Country country:countries){
				g2.setColor(a.getColor());
				g2.fillRect(country.getX(),country.getY(),50,50);
				g2.setColor(Color.WHITE);
				g2.drawString(a.getName()+"\n"+country.getArmyCount(), country.getX(),country.getY()+20);
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
		return clicked;
	}
	
	private class MouseOperation implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(getClickedCountry(e.getPoint())){
				currentCountry.addArmys(1);
				repaint();
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
		
	}
}
