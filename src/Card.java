public class Card {

    /**
     * @card Suits is one of four possible values
     *       Clubs, Diamonds, Hearts, Spades
     */
    private final Suit mySuit;

    /**
     * @card Rank is one of thirteen possible values
     *       Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen,
     *       King
     */
    private final int myValue;

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
     * Returns the value of the card
     * 
     * @return the value
     */
    public int getNumber() {
        return myValue;
    }

    public String toString() {

        String valuetoString = "";

        switch (myValue) {
        case 1:
            valuetoString = "Ace";
            break;
        case 2:
            valuetoString = "Two";
            break;
        case 3:
            valuetoString = "Three";
            break;
        case 4:
            valuetoString = "Four";
            break;
        case 5:
            valuetoString = "Five";
            break;
        case 6:
            valuetoString = "Six";
            break;
        case 7:
            valuetoString = "Seven";
            break;
        case 8:
            valuetoString = "Eight";
            break;
        case 9:
            valuetoString = "Nine";
            break;
        case 10:
            valuetoString = "Ten";
            break;
        case 11:
            valuetoString = "Jack";
            break;
        case 12:
            valuetoString = "Queen";
            break;
        case 13:
            valuetoString = "King";
            break;
        default:
            valuetoString = "Error";
            break;
        }

        return valuetoString + " of " + mySuit.toString();
    }
}
