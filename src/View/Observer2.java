package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;
import model.RiskGame;

public class Observer2 extends JPanel implements Observer{
	private List<Player> players;
	private Player currentPlayer;
	private Point now;
	private BufferedImage boardOne, boardTwo;
	public Observer2(RiskGame game){
		setLayout(null);
		setFont(new Font("Arial Black", Font.BOLD, 20));
		players=game.getAllPlayer();
		currentPlayer = game.getPlayer();
		loadImage();
	}
	
	private void loadImage(){
		try {
			BufferedImage board = ImageIO.read(new File("./picture/PlayerBoard.png"));
			boardOne = board.getSubimage(400, 255, 145, 80);
			boardTwo = board.getSubimage(135, 100, 165,80);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		currentPlayer = ((RiskGame) o).getPlayer();
		updateUI();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(players.size()>0){
			g2.drawImage(boardTwo, 0,0,800,50, null);
			int Length = (GameGUI.SCREEN_LENGTH -50) / players.size();
			int index = 0;
			for(Player a:players){
				if(a==currentPlayer)
					g2.drawImage(boardOne, 25+index * Length, 0, Length, 50, null);
//				else 
//					g2.drawImage(boardTwo, 25+index * Length, 0, Length, 50, null);
				g2.setColor(a.getColor());
				g2.drawString(a.getName(), 25+Length *index + 15, 35);
				index++;
			}
		}
	}
	
}
