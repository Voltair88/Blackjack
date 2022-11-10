public class Player {

	private String myName;
	private int myScore;
	private int myBet;
	private int myMoney;
	private Card[] myHand = new Card[10];
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
		this.myMoney = 100;
		this.myScore = 0;
		this.myBet = 0;
		this.emptyHand();
	}

	public void emptyHand() {
		for (int i = 0; i < 5; i++) {
			this.myHand[i] = null;
		}
		this.numCards = 0;
	}

	/**
	 * 
	 * @param card the card to be added to the player's hand
	 * @return true if the card was added to the player's hand
	 */
	public boolean addCard(Card card) {
		if (this.numCards == 5) {
			System.err.printf("%s's hand already has 5 cards; " + "cannot add another card %n", this.myName);
			System.exit(1);
		}
		this.myHand[this.numCards] = card;
		this.numCards++;
		return (card != null);
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
			} else if (cardNum > 1) {
				handSum += cardNum;
			} else {
				System.err.printf("getHandSum(): card number is invalid %n");
				System.exit(1);
			}
		}

		while (handSum + 10 <= 21 && numAces > 0) {
			handSum += 10;
			numAces--;
		}

		return handSum;
	}

	/**
	 * 
	 * @param showFirstCard true if the first card should be shown
	 */
	public void printHand(boolean showFirstCard) {
		System.out.println(this.myName + "'s Hand: ");
		for (int i = 0; i < this.numCards; i++) {
			if (i == 0 && !showFirstCard) {
				System.out.println("[hidden]");
			} else {
				System.out.printf("%s %n", this.myHand[i].toString());
			}
		}
	}

	/**
	 * 
	 * @return the score of the player
	 */
	public int getScore() {
		return this.myScore;
	}

	/**
	 * 
	 * @param score the score of the player
	 */
	public void setScore(int score) {
		this.myScore = score;
	}

	/**
	 * 
	 * @return the bet of the player
	 */
	public int getBet() {
		return this.myBet;
	}

	/**
	 * 
	 * @param bet the bet of the player
	 */
	public void setBet(int bet) {
		this.myBet = bet;
	}

	/**
	 * 
	 * @return the money of the player
	 */
	public int getMoney() {
		return this.myMoney;
	}

	/**
	 * 
	 * @param money the money of the player
	 */
	public void setMoney(int money) {
		this.myMoney = money;
	}

	/**
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return this.myName;
	}

	/**
	 * 
	 * @param name the name of the player
	 */
	public void setName(String name) {
		this.myName = name;
	}

	/**
	 * 
	 * @return the hand of the player
	 */
	public Card[] getHand() {
		return this.myHand;
	}

	/**
	 * 
	 * @param hand the hand of the player
	 */
	public void setHand(Card[] hand) {
		this.myHand = hand;
	}
}
