package edu.guilford;

import java.util.Stack;

public class DiscardPile {
    private Stack<Card> pile = new Stack<>();
    public void addCard(Card card) { pile.push(card); }
    public Card drawTopCard() { return pile.isEmpty() ? null : pile.pop(); }
    public Card peekTopCard() { return pile.isEmpty() ? null : pile.peek(); }
}
