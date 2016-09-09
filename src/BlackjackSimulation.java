import java.util.ArrayList;


public class BlackjackSimulation {

	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<Player>();
		Player a = new Player(100);
		players.add(a);
		Player b = new Player(100);
		players.add(b);
		Player c = new Player(100);
		players.add(c);
		Player d = new Player(100);
		players.add(d);
		House dealer = new House();
		Blackjack blackjack = new Blackjack(dealer, players, 4);
		//blackjack.playGameNumRounds(10, 5);
		blackjack.playGameTilPlayersBroke(5);
	}
}
