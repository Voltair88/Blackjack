public class Game {
	private int numRounds;
	private int moneyOnTable;

	/**
	 * @Constructor that defines the number of rounds to be played
	 * 
	 * @param numRounds the number of rounds to be played
	 * @param moneyOnTable the amount of money on the table
	 */
	public Game(int numRounds) {
		this.numRounds = numRounds;
		this.moneyOnTable = 0;
	}

	public void setMoneyOnTable(int money) {
		this.moneyOnTable = money;
	}

	public int getMoneyOnTable() {
		return this.moneyOnTable;
	}

	public void setRound(int round) {
		this.numRounds = round;
	}

	public int getRound() {
		return this.numRounds;
	}
}
