import java.util.Scanner;

public class GameRunner {

	public static void main(String[] args) {

		// init 
		Scanner scan = new Scanner(System.in);
		Deck deck = new Deck(1, true);
		Player player = new Player("Player");
		Player dealer = new Player("Dealer");

		player.addCard(deck.deal());
		dealer.addCard(deck.deal());
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());

		System.out.println("Cards have been dealt");
		player.printHand(true);
		dealer.printHand(false);
		System.out.println("\n");

		boolean playerDone = false;
		boolean dealerDone = false;
		String ans;

		while (!playerDone || !dealerDone) {
			if (!playerDone) {
				System.out.println("Your current sum: " + player.getHandSum());
				System.out.println("Hit or Stay? (h/s) ");
				ans = scan.next();
				System.out.println();
				if (ans.compareToIgnoreCase("H") == 0) {
					playerDone = !player.addCard(deck.deal());
					System.out.println("You drew a " + player.myHand[player.numCards - 1].toString());
					player.printHand(true);
					System.out.println("\n");
				} else {
					playerDone = true;
				}
			}
			if (!dealerDone) {
				if (dealer.getHandSum() < 17) {
					System.out.println("Dealer hits");
					dealer.addCard(deck.deal());
				} else {
					System.out.println("Dealer stays");
					dealerDone = true;
				}
			}

			System.out.println();
		}
		scan.close();

		player.printHand(true);
		dealer.printHand(true);

		int playerSum = player.getHandSum();
		int dealerSum = dealer.getHandSum();

		if (playerSum > 21) {
			System.out.println("You busted");
		} else if (dealerSum > 21) {
			System.out.println("Dealer busted");
		} else if (playerSum > dealerSum) {
			System.out.println("You win");
		} else if (playerSum < dealerSum) {
			System.out.println("Dealer wins");
		} else {
			System.out.println("Push");
		}
		System.out.println("Your sum: " + player.getHandSum());
		System.out.println("Dealers sum: " + dealer.getHandSum());
	}
}
