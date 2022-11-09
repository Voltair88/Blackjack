import java.util.Scanner;
import java.util.Random;

/**
 * @TODO: add betting  
 */
public class GameRunner {

	public static void main(String[] args) {

		// init 
		Scanner scan = new Scanner(System.in);
		Deck deck = new Deck(1, true);
		Player dealer = new Player("Dealer");

		System.out.println("What is your name?");
		String name = scan.nextLine();
		System.out.println("Hello " + name + ", welcome to the game of Blackjack!");
		Player player = new Player(name);

		Game game = new Game(1);
		System.out.println("Round " + game.getRound() + " is starting!");
		System.out.println("You have $" + player.getMoney() + " to start with.");
		System.out.println("How much money would you like to bet?");
		int bet = scan.nextInt();
		game.setMoneyOnTable(bet);
		player.setBet(bet);
		player.setMoney(player.getMoney() - bet);
		// dealer bet
		Random rand = new Random();
		int dealerBet = rand.nextInt(100) + 1;
		dealer.setBet(dealerBet);
		dealer.setMoney(dealer.getMoney() - dealerBet);
		game.setMoneyOnTable(game.getMoneyOnTable() + dealerBet);
		System.out.println("The dealer has bet $" + dealerBet + ".");
		System.out.println("The money on the table is now $" + game.getMoneyOnTable() + ".");
		deck.shuffle();

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

		player.printHand(true);
		dealer.printHand(true);

		int playerSum = player.getHandSum();
		int dealerSum = dealer.getHandSum();

		if (playerSum > 21) {
			System.out.println(name + " busted");
		} else if (dealerSum > 21) {
			System.out.println("Dealer busted");
		} else if (playerSum > dealerSum) {
			System.out.println(name + " win");
		} else if (playerSum < dealerSum) {
			System.out.println("Dealer wins");
		} else {
			System.out.println("Push");
		}
		System.out.println("Your sum: " + player.getHandSum());
		System.out.println("Dealers sum: " + dealer.getHandSum());

		// reset game
		player.resetHand();
		dealer.resetHand();
		deck.shuffle();

		scan.close();

	}
}
