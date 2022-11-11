public class Card {

    /**
     * @mySuit Clubs, Diamonds, Hearts, Spades
     */
    private final Suit mySuit;

    /**
     * @myValue Rank is one of thirteen possible values
     *       Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen,
     *       King
     */
    private int myValue;

    /**
     * @card Constructor
     * 
     * @param suit  Clubs, Diamonds, Hearts, Spades
     * 
     * @param value Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack,
     *              Queen, King
     */
    public Card(Suit suit, int value) {
        this.mySuit = suit;

        if (value < 1 || value > 13) {
            throw new IllegalArgumentException("Invalid card value");
        } else {
            this.myValue = value;
        }
    }

    /**
     * @return the value of the card
     */
    public int getValue() {
        return this.myValue;
    }

    public String toString() {

        // create array of strings for the card values
        String[] cardValues = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
                "Queen", "King" };

        return cardValues[this.myValue - 1] + " of " + this.mySuit;
    }
}
