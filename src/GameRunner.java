import java.util.Scanner;
import java.util.Random;

public class GameRunner {

	public static void main(String[] args) {

		// init 
		Scanner scan = new Scanner(System.in);
		Deck deck = new Deck(1, true);
		Boolean playAgain = true;
		Game game = new Game(1);
		int round = 1;
		System.out.println("What is your name?");
		String name = scan.nextLine();
		System.out.printf("Hello %s, welcome to Blackjack!%n", name);
		System.out.printf("You can enter '0' at any given time to quit.%n%n");
		Player player = new Player(name);
		Player dealer = new Player("Dealer");
		// game loop
		while (playAgain) {
			playAgain = false;
			System.out.printf("Round %d%n", round);
			System.out.println("You have $" + player.getMoney() + " to start with.");
			if (player.getMoney() < 3) {
				System.out.println("You don't have enough money to play!");
				System.out.println("Goodbye!");
				System.exit(0);
			}
			System.out.printf("You can bet from 2 to 500 dollars.%nHow much would you like to bet?%n");
			// check if it is valid input
			while (true) {
				int bet = 0;
				while (true) {
					try {
						bet = Integer.parseInt(scan.nextLine());
						break;
					} catch (NumberFormatException e) {
						System.out.println("Please enter a valid number.");
					}
				}
				// check if it is bet is valid
				if (bet > 1 && bet < 501) {
					player.setBet(bet);
					player.setMoney(player.getMoney() - bet);
					game.setMoneyOnTable(game.getMoneyOnTable() + bet);
					break;
				} else if (bet == 0) {
					System.out.println("You have chosen to quit the game.");
					System.exit(0);
				} else {
					System.out.println("Please enter a valid bet.");
				}
			}
			// dealer bet
			Random rand = new Random();
			int dealerBet = rand.nextInt(499) + 2;
			dealer.setBet(dealerBet);
			dealer.setMoney(dealer.getMoney() - dealerBet);
			game.setMoneyOnTable(game.getMoneyOnTable() + dealerBet);
			System.out.println("The dealer has bet $" + dealerBet + ".");
			System.out.println("The money on the table is now $" + game.getMoneyOnTable() + ".");
			// deal cards
			deck.shuffle();
			player.addCard(deck.deal());
			dealer.addCard(deck.deal());
			player.addCard(deck.deal());
			dealer.addCard(deck.deal());
			System.out.printf("Cards have been dealt %n %n");
			// if player or dealer has blackjack then round ends

			player.printHand(true);
			System.out.printf("sum: %d %n %n", player.getHandSum());
			dealer.printHand(false);

			boolean playerDone = false;
			boolean dealerDone = false;
			String ans;

			// player loop
			while (!playerDone || !dealerDone) {
				if (!playerDone) {
					System.out.println("Hit or Stay? (h/s) ");
					ans = scan.next();
					System.out.println();
					if (ans.equalsIgnoreCase("h")) {
						player.addCard(deck.deal());
						player.printHand(true);
						System.out.printf("sum: %d %n %n", player.getHandSum());
						playerDone = playerDone || player.getHandSum() > 21;
					} else if (ans.equalsIgnoreCase("s")) {
						playerDone = true;
					} else if (ans.equals("0")) {
						System.out.println("You have chosen to quit the game.");
						System.exit(0);
					} else {
						System.out.println("Please enter a valid input.");
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

			// check for bust and blackjack and print results of round and update money
			if (playerSum > 21 && dealerSum <= 21) {
				System.out.println(name + " busted");
				dealer.setMoney(dealer.getMoney() + game.getMoneyOnTable());
			} else if (playerSum > dealerSum && playerSum <= 21 || dealerSum > 21) {
				System.out.println(name + " wins");
				player.setMoney(player.getMoney() + game.getMoneyOnTable());
				System.out.println("You won $" + game.getMoneyOnTable() + "!");
				System.out.println("You now have $" + player.getMoney() + ".");
			} else if (playerSum == 21) {
				System.out.printf("Blackjack! %n");
				player.setMoney(player.getMoney() + game.getMoneyOnTable() * 2);
			} else if (playerSum < dealerSum && dealerSum <= 21 && playerSum <= 21) {
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

			// check if player wants to play again
			System.out.println("Would you like to play again? (y/n)");
			ans = scan.next();
			// check if input is valid
			while (!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n")) {
				System.out.println("Invalid input. Please try again.");
				ans = scan.next();
			}
			if (ans.compareToIgnoreCase("n") == 0) {
				playAgain = false;
				System.out.println("Thanks for playing!");
			} else {
				game.setRound(round++);
				// reset player and dealer hands
				player.emptyHand();
				dealer.emptyHand();
				playAgain = true;
			}
		}
		scan.close();
	}
}
