package edu.guilford;

public class Player {
    private String name;
    private Hand hand = new Hand();
    private int lives = 3;
    private boolean knocked = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void drawCard(Deck deck) {
        Card drawn = deck.deal();
        if (drawn != null) {
            hand.addCard(drawn);
        } else {
            System.out.println(name + " tried to draw a card but the deck was empty.");
        }
    }

    public int getHandValue() {
        return hand.getTotalValue();
    }

    public void discardCard(Card card) {
        hand.removeCard(card);
    }

    public void clearHand() {
        hand = new Hand();
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        lives--;
    }

    public boolean isKnocked() {
        return knocked;
    }

    public void setKnocked(boolean knocked) {
        this.knocked = knocked;
    }

    // ✅ Improved knock logic to avoid infinite games
    public boolean decideToKnock() {
        int value = getHandValue();

        if (value >= 27) {
            knocked = true;
            System.out.println(name + " has knocked!");
            return true;
        }

        if (value >= 23 && Math.random() < 0.4) { // ~40% chance to knock early
            knocked = true;
            System.out.println(name + " has knocked early!");
            return true;
        }

        return false;
    }

    public void takeTurn(DiscardPile discardPile, Stockpile stockpile) {
        boolean drawFromStockpile = Math.random() < 0.5;

        if (drawFromStockpile && stockpile.getDeck().isEmpty()) {
            stockpile.reshuffleFromDiscard(discardPile);
        }

        Card drawnCard = drawFromStockpile ? stockpile.drawCard() : discardPile.drawTopCard();
        if (drawnCard == null) {
            System.out.println(name + " couldn't draw a card. Skipping turn.");
            return;
        }

        hand.addCard(drawnCard);

        // Discard lowest value card
        Card lowest = null;
        for (int i = 0; i < handSize(); i++) {
            Card card = hand.getCard(i);
            if (card != null) {
                if (lowest == null || card.getRank().ordinal() < lowest.getRank().ordinal()) {
                    lowest = card;
                }
            }
        }

        if (lowest != null) {
            hand.removeCard(lowest);
            discardPile.addCard(lowest);
            System.out.println(name + " drew from " + (drawFromStockpile ? "stockpile" : "discard pile"));
            System.out.println(name + " discarded " + lowest);
        } else {
            System.out.println(name + " had no valid card to discard.");
        }
    }

    private int handSize() {
        return hand.toString().split(",").length;
    }
}