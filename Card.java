public class Card {
    private int value;       // The value of the card (2-10, face cards = 10, Ace can be 11 or 1)
    private String suit;     // The suit of the card (Hearts, Diamonds, Clubs, Spades)
    private String rank;     // The rank of the card (2, 3, 4, ... Jack, Queen, King, Ace)

    // Constructor
    public Card(int value, String suit, String rank) {
        this.value = value;
        this.suit = suit;
        this.rank = rank;
    }

    // Accessors
    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    // Mutators
    public void setValue(int value) {
        this.value = value;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // toString method for printing the details of card
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}