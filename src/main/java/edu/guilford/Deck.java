package edu.guilford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck = new ArrayList<>();
    private Random rand = new Random();

    public Deck() {
        build();
        shuffle();
    }

    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public int size() {
        return deck.size();
    }

    public void shuffle() {
        Collections.shuffle(deck, rand);
    }

    public Card deal() {
        return isEmpty() ? null : deck.remove(0);
    }

    public void addCard(Card card) {
        if (card != null) {
            deck.add(card);
        }
    }
}