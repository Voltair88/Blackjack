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
		System.out.printf("Hello %s, welcome to Blackjack!%n", name);
		Player player = new Player(name);
		while (playAgain) {
			playAgain = false;
			System.out.printf("Round %d%n", round);
			System.out.println("You have $" + player.getMoney() + " to start with.");
			System.out.printf("How much would you like to bet?%n");
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

			boolean playerDone = false;
			boolean dealerDone = false;
			String ans;

			while (!playerDone || !dealerDone) {
				if (!playerDone) {
					System.out.println("Hit or Stay? (h/s) ");
					ans = scan.next();
					System.out.println();
					if (ans.equalsIgnoreCase("h")) {
						player.addCard(deck.deal());
						player.printHand(true);
						playerDone = playerDone || player.getHandSum() > 21;
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
			System.out.println();
			dealer.printHand(true);
			System.out.println();

			int playerSum = player.getHandSum();
			int dealerSum = dealer.getHandSum();

			if (playerSum > 21) {
				System.out.println(name + " busted");
				dealer.setMoney(dealer.getMoney() + game.getMoneyOnTable());
			} else if (dealerSum > 21) {
				System.out.println("Dealer busted");
				player.setMoney(player.getMoney() + game.getMoneyOnTable());
			} else if (playerSum > dealerSum) {
				System.out.println(name + " wins");
				player.setMoney(player.getMoney() + game.getMoneyOnTable());
				System.out.println("You won $" + game.getMoneyOnTable() + "!");
				System.out.println("You now have $" + player.getMoney() + ".");
			} else if (playerSum < dealerSum) {
				System.out.println("Dealer wins");
				dealer.setMoney(dealer.getMoney() + game.getMoneyOnTable());
			} else if (player.getMoney() == 0) {
				System.out.println("You are out of money! Game over!");
				System.exit(0);
			} else if (playerSum > 21 && dealerSum > 21) {
				System.out.println("Player and dealer both busted, you lose!");
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
				game.setRound(round++);
				player.emptyHand();
				dealer.emptyHand();
				playAgain = true;
				playerDone = false;
				dealerDone = false;
			}
			if (player.getMoney() == 0) {
				System.out.println("You are out of money!");
				playAgain = false;
			}
		}
		scan.close();

	}
}
