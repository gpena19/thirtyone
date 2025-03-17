package edu.guilford;

public class Stockpile {
    private Deck deck = new Deck();
    public Card drawCard() { return deck.deal(); }
    public Deck getDeck() { return deck; }
}
