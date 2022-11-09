import java.util.Random;

public class Deck {
    private Card[] myCards;

    private int numCards;

    public Deck() {
        this(1, false);
    }

    /**
     * @Constuctor that defines the number of decks to be used, and whether it
     *             should be shuffled. 
     * <p>
     * 1. Create an array of 52 cards. <br>
     * 2. For each deck in the deck, create a deck of 52 cards. <br>
     * 3. Shuffle the deck if shuffle is true.<br>
     * </p>
     * @param  numCards  the number of decks to be used
     * @param shuffle shuffle the deck
     */
    public Deck(int numDecks, boolean shuffle) {
        this.numCards = numDecks * 52;
        this.myCards = new Card[this.numCards];
        int index = 0;
        for (int i = 0; i < numDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (int j = 1; j <= 13; j++) {
                    this.myCards[index] = new Card(suit, j);
                    index++;
                }
            }
        }
        if (shuffle) {
            this.shuffle();
        }
    }

    /**
    *  <i>Shuffles the deck.</i>
    *  <p>
    *  1. The first for loop iterates through the array myCards[]. <br>
    *  2. The second for loop creates a random index for each element in myCards[]. <br>
    *  3. The temp variable is used to swap the value of the current element with the random element.  <br>
    *  </p>
    */
    public void shuffle() {

        Random rand = new Random();
        Card temp;
        int randIndex;

        for (int i = 0; i < myCards.length; i++) {
            randIndex = rand.nextInt(myCards.length);
            temp = myCards[i];
            myCards[i] = myCards[randIndex];
            myCards[randIndex] = temp;
        }
    }

    /**
     * 
     * <i>Deals a card from the deck.</i>
     * <p>
     * 1. Checks if the deck is empty. If it is, it returns null. <br>
     * 2. Creates a new card based on the first card in the array. <br>
     * 3. Copies the value of each card in the array into the next card's slot.<br> 
     * 4. Sets the last card in the array to null. <br>
     * 5. Decrements the size of the array. <br>
     * 6. Returns the top card.<br>
     * </p>
     * 
     * @return the top card
     *
     */
    public Card deal() {

        if (isEmpty()) {
            return null;
        }
        Card topCard = myCards[0];
        for (int i = 0; i < myCards.length - 1; i++) {
            myCards[i] = myCards[i + 1];
        }
        myCards[myCards.length - 1] = null;
        numCards--;
        return topCard;
    }

    /**
     * <i> Returns the size of the deck.</i>
     * 
     * @param numToPrint the number of cards to print
     * 
     * @return the size of the deck
     */
    public void printDeck(int numToPrint) {
        for (int i = 0; i < numToPrint; i++) {
            System.out.printf("% 3d/%d %s %n", i + 1, this.numCards, this.myCards[i].toString());
        }
        System.out.printf("\t\t[%d other] %n", this.numCards - numToPrint);
    }

    /**
     * 
     * <i>Checks if the deck is empty.</i>
     * 
     * @return true if the deck is empty
     */

    public boolean isEmpty() {
        return numCards == 0;
    }
}
