package edu.guilford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck = new ArrayList<>();
    private Random rand = new Random();

    public Deck() { build(); shuffle(); }
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }
    public void shuffle() { Collections.shuffle(deck, rand); }
    public Card deal() { return deck.isEmpty() ? null : deck.remove(0); }
}
