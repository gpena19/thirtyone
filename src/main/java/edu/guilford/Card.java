package edu.guilford;

public class Card implements Comparable<Card> {
    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
    public enum Rank { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() { return suit; }
    public Rank getRank() { return rank; }
    @Override
    public String toString() { return rank + " of " + suit; }

    @Override
    public int compareTo(Card otherCard) {
        return this.rank.ordinal() - otherCard.rank.ordinal();
    }
    public int getValue() {
        return Math.min(10, rank.ordinal() + 1);
    }
}
