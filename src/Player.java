public class Player {

	private String myName;
	private int myScore;
	private int myBet;
	private int myMoney;
	Card[] myHand;
	int numCards; //numCards

	/**
	 * @Constructor that defines the name of the player
	 * 
	 * @param name the name of the player
	 * @param myMoney the amount of money the player has
	 * @param myScore the score of the player
	 * @param myBet the bet of the player
	 * @param numCards the size of the player's hand
	 * 
	 */

	public Player(String name) {
		this.myName = name;
		this.myHand = new Card[5];
		this.numCards = 0;
		this.myMoney = 100;
		this.myScore = 0;
		this.myBet = 0;
		this.emptyHand();
	}

	public void emptyHand() {
		for (int i = 0; i < myHand.length; i++) {
			myHand[i] = null;
		}
		numCards = 0;
	}

	/**
	 * 
	 * @param card
	 * @return true if the card was added to the hand
	 */
	public boolean addCard(Card card) {
		if (this.numCards == 5) {
			System.err.printf("%s's hand already has 5 cards; " + "cannot add another card %n", this.myName);
			System.exit(1);
		}

		this.myHand[this.numCards] = card;
		this.numCards++;

		return (this.getHandSum() <= 21);
	}

	/**
	 * 
	 * @return the sum of the cards in the hand
	 */

	public int getHandSum() {
		int handSum = 0;
		int cardNum;
		int numAces = 0;

		for (int i = 0; i < this.numCards; i++) {
			cardNum = this.myHand[i].getNumber();
			if (cardNum == 1) {
				numAces++;
				handSum += 11;
			} else if (cardNum > 10) {
				handSum += 10;
			} else {
				handSum += cardNum;
			}
		}

		while (handSum + 10 <= 21 && numAces > 0) {
			handSum += 10;
			numAces--;
		}

		return handSum;
	}

	public void printHand(boolean showFirstCard) {
		System.out.printf("%s's cards: %n", this.myName);
		for (int i = 0; i < this.numCards; i++) {
			if (i == 0 && !showFirstCard) {
				System.out.println("[hidden]");
			} else {
				System.out.printf(" %s %n", this.myHand[i].toString());
			}
		}
	}

	// reset hand
	public void resetHand() {
		this.emptyHand();
	}

	// Getters and Setters

	public int getScore() {
		return this.myScore;
	}

	public void setScore(int score) {
		this.myScore = score;
	}

	public int getBet() {
		return this.myBet;
	}

	public void setBet(int bet) {
		this.myBet = bet;
	}

	public int getMoney() {
		return this.myMoney;
	}

	public void setMoney(int money) {
		this.myMoney = money;
	}

	public String getName() {
		return this.myName;
	}

	public void setName(String name) {
		this.myName = name;
	}
}
