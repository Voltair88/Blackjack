import java.util.Scanner;
import java.util.Random;

public class GameRunner {

	public static void main(String[] args) {

		// init 
		Scanner scan = new Scanner(System.in);
		Deck deck = new Deck(1, true);
		Player dealer = new Player("Dealer");
		Boolean playAgain = true;
		Game game = new Game(1);
		int round = 1;
		// game loop
		System.out.println("What is your name?");
		String name = scan.nextLine();
		System.out.println("Hello " + name + ", welcome to the game of Blackjack!");
		Player player = new Player(name);
		while (playAgain) {
			playAgain = false;
			System.out.println("Round " + round++ + " is starting!");
			System.out.println("You have $" + player.getMoney() + " to start with.");
			System.out.println("How much money would you like to bet?");
			int bet = scan.nextInt();
			game.setMoneyOnTable(bet);
			player.setBet(bet);
			player.setMoney(player.getMoney() - bet);
			// dealer bet
			Random rand = new Random();
			int dealerBet = rand.nextInt(499) + 2;
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
					if (ans.equalsIgnoreCase("h")) {
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
				dealer.setMoney(dealer.getMoney() + game.getMoneyOnTable());
			} else if (dealerSum > 21) {
				System.out.println("Dealer busted");
				player.setMoney(player.getMoney() + game.getMoneyOnTable());
			} else if (playerSum > dealerSum) {
				System.out.println(name + " win");
				player.setMoney(player.getMoney() + game.getMoneyOnTable());
			} else if (playerSum < dealerSum) {
				System.out.println("Dealer wins");
				dealer.setMoney(dealer.getMoney() + game.getMoneyOnTable());
			} else {
				System.out.println("Push");
				player.setMoney(player.getMoney() + player.getBet());
				dealer.setMoney(dealer.getMoney() + dealer.getBet());
			}
			game.setMoneyOnTable(0);
			System.out.println("Your sum: " + player.getHandSum());
			System.out.println("Dealers sum: " + dealer.getHandSum());

			System.out.println("Would you like to play again? (y/n)");
			ans = scan.next();
			if (ans.compareToIgnoreCase("n") == 0) {
				playAgain = false;
			} else {
				game.setRound(game.getRound() + 1);
				player.resetHand();
				dealer.resetHand();
				playAgain = true;
			}
		}
		scan.close();

	}
}
