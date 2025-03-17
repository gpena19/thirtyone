package edu.guilford;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand = new ArrayList<>();

    public void addCard(Card card) { hand.add(card); }
    public void removeCard(Card card) { hand.remove(card); }
    public Card getCard(int index) { return hand.get(index); }
    public int getTotalValue() {
        int sum = 0;
        for (Card card : hand) sum += Math.min(10, card.getRank().ordinal() + 1);
        return sum;
    }
    public String toString() { return hand.toString(); }
}