public class Game {
	// number of rounds of game
	private int numRounds;
	// money on the table
	private int moneyOnTable;

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
