package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;
import model.RiskGame;

public class Observer2 extends JPanel implements Observer {
	private List<Player> players;
	private Player currentPlayer;
	private Point now;

	public Observer2(RiskGame game) {
		setLayout(new GridLayout());
		setFont(new Font("Arial Black", Font.BOLD, 20));
		players = game.getAllPlayer();
		currentPlayer = game.getPlayer();
		model();
	}

	public void model() {
		removeAll();
		for (Player a : players) {
			JLabel temp;
			if (currentPlayer.equals(a)) {
				temp = new JLabel(">" + a.getName());
				temp.setFont(new Font("Arial Black", Font.BOLD, 22));
				temp.setForeground(a.getColor());
				temp.setBorder(temp.getBorder());
				temp.setBackground(Color.WHITE);
			} else {
				temp = new JLabel(a.getName());
				temp.setFont(new Font("Arial Black", Font.BOLD, 15));
				temp.setForeground(a.getColor());
			}
			add(temp);

		}
		updateUI();
	}

	@Override
	public void update(Observable o, Object arg) {
		currentPlayer = ((RiskGame) o).getPlayer();
		model();
	}

}
