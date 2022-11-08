public class Card {

    /*
     * @card Suits is one of four possible values
     * Clubs, Diamonds, Hearts, Spades
     */
    private final Suit mySuit;

    /*
     * @card Rank is one of thirteen possible values
     * Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
     */
    private final int myValue;

    /*
     * @card Constructor
     * 
     * @param suit Clubs, Diamonds, Hearts, Spades
     * 
     * @param value Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack,
     * Queen, King
     */
    public Card(Suit suit, int value) {
        mySuit = suit;
        myValue = value;
    }

    /*
     * Returns the value of the card
     * 
     * @return the value
     */
    public int getNumber() {
        return myValue;
    }

    /*
     * Returns the suit of the card
     * 
     * @return the suit
     */
    public String toString() {

        String[] valuetoStringArray;
        valuetoStringArray = new String[13];
        valuetoStringArray[0] = "Ace";
        valuetoStringArray[1] = "Two";
        valuetoStringArray[2] = "Three";
        valuetoStringArray[3] = "Four";
        valuetoStringArray[4] = "Five";
        valuetoStringArray[5] = "Six";
        valuetoStringArray[6] = "Seven";
        valuetoStringArray[7] = "Eight";
        valuetoStringArray[8] = "Nine";
        valuetoStringArray[9] = "Ten";
        valuetoStringArray[10] = "Jack";
        valuetoStringArray[11] = "Queen";
        valuetoStringArray[12] = "King";

        return valuetoStringArray[myValue - 1] + " of " + mySuit;
    }
    /*
     * switch (myValue) {
     * case 1:
     * valuetoString = "Ace";
     * break;
     * case 2:
     * valuetoString = "Two";
     * break;
     * case 3:
     * valuetoString = "Three";
     * break;
     * case 4:
     * valuetoString = "Four";
     * break;
     * case 5:
     * valuetoString = "Five";
     * break;
     * case 6:
     * valuetoString = "Six";
     * break;
     * case 7:
     * valuetoString = "Seven";
     * break;
     * case 8:
     * valuetoString = "Eight";
     * break;
     * case 9:
     * valuetoString = "Nine";
     * break;
     * case 10:
     * valuetoString = "Ten";
     * break;
     * case 11:
     * valuetoString = "Jack";
     * break;
     * case 12:
     * valuetoString = "Queen";
     * break;
     * case 13:
     * valuetoString = "King";
     * break;
     * default:
     * valuetoString = "Error";
     * break;
     * }
     * 
     * return valuetoString + " of " + mySuit;
     */
}
