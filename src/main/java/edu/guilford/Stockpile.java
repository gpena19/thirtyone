package edu.guilford;

public class Stockpile {
    private Deck deck = new Deck();

    public Card drawCard() {
        return deck.deal();
    }

    public Deck getDeck() {
        return deck;
    }

    public void reshuffleFromDiscard(DiscardPile discardPile) {
        while (!discardPile.isEmpty()) {
            Card card = discardPile.drawTopCard();
            if (card != null) {
                deck.addCard(card);
            }
        }
        deck.shuffle();
        System.out.println("Reshuffled discard pile into stockpile.");
    }
}