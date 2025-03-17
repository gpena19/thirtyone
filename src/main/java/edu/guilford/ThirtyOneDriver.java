// ThirtyOneDriver.java
package edu.guilford;

public class ThirtyOneDriver {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Hand playerHand = new Hand();
        for (int i = 0; i < 3; i++) {
            playerHand.addCard(deck.deal());
        }

        System.out.println("Your hand:");
        System.out.println(playerHand);
        System.out.println("Hand value: " + playerHand.getTotalValue());
    }
}
