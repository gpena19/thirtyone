package edu.guilford;

public class Player {
    private String name;
    private Hand hand = new Hand();

    public Player(String name) { this.name = name; }
    public String getName() { return name; }
    public Hand getHand() { return hand; }
    public void drawCard(Deck deck) { hand.addCard(deck.deal()); }
    public int getHandValue() {
        return hand.getTotalValue();
    }
    public void discardCard(Card card) { hand.removeCard(card); }
    public void clearHand() { hand = new Hand(); }  
}