public class Player {

	private String myName;
	private int myScore;
	private int myBet;
	private int myMoney;
	private int numCards;
	private Card[] myHand = new Card[10];

	/**
	 * @Constructor that defines the name of the player
	 * 
	 * @param name the name of the player
	 * 
	 */

	public Player(String name) {
		this.myName = name;
		this.myMoney = 100;
		this.myScore = 0;
		this.myBet = 0;
	}

	// remove all cards from the player's hand
	public void emptyHand() {
		for (int i = 0; i < 10; i++) {
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
		int cardNum = 0;
		int numAces = 0;
		for (int i = 0; i < this.numCards; i++) {
			cardNum = this.getHand()[i].getValue();
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

	/**
	 * 
	 * @param showFirstCard true if the first card should be shown
	 */
	public void printHand(boolean showFirstCard) {
		System.out.printf("%s's cards:%n", this.myName);
		for (int i = 0; i < this.numCards; i++) {
			if (i == 0 && !showFirstCard) {
				System.out.println("[hidden]");
			} else {
				System.out.printf(" %s %n", this.myHand[i].toString());
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
