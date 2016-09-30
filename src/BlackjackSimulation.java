import java.util.ArrayList;


public class BlackjackSimulation {

	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<Player>();
		Player a = new Player("Brett", 100, 2);
		players.add(a);
		Player b = new Player("Ryan", 100, 2);
		players.add(b);
		Player c = new Player("Luis", 100, 2);
		players.add(c);
		Player d = new Player("Judy", 100, 2);
		players.add(d);
		House dealer = new House();
		Blackjack blackjack = new Blackjack(dealer, players, 4);
		blackjack.playGameTilPlayersBroke(5);
		for(int i = 0; i < blackjack.getPlayers().size(); i++) {
			System.out.println(blackjack.getPlayers().get(i).getName() + " Max Money: " + blackjack.getPlayers().get(i).getMaxMoney());
		}
	}
}
