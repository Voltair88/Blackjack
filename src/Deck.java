import java.util.Random;

public class Deck {
    private Card[] myCards;

    private int mySize;

    public Deck() {
        this(1, false);
    }

    /**
     * Constuctor that defines the number of decks to be used, and whether it should
     * be shuffled
     * 
     * @param numDeck number of decks
     * @param shuffle shuffle the deck
     */
    public Deck(int mySize, boolean shuffle) {
        mySize = mySize * 52;
        myCards = new Card[mySize];

        // Create the deck
        int index = 0;

        // for each deck
        for (int i = 0; i < mySize; i++) {
            // for each suit
            for (int j = 0; j < 4; j++) {
                // for each value
                for (int k = 1; k <= 13; k++) {
                    myCards[index] = new Card(Suit.values()[j], k);
                    index++;
                }
            }
        }
        // Shuffle the deck
        if (shuffle) {
            shuffle();
        }
    }

    /**
     * Shuffles the deck
     */

    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < myCards.length; i++) {
            int randomIndex = rand.nextInt(myCards.length);
            Card temp = myCards[i];
            myCards[i] = myCards[randomIndex];
            myCards[randomIndex] = temp;
        }
    }

    /**
     * Deal a card from the top of the deck
     * 
     * @return the card
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
        mySize--;
        return topCard;
    }
}
