package sample;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();

        // add bangs - x25
        for (int i = 0; i < 25; i++) {
            Card card = new Card("Bang", 0);
            cards.add(card);
        }

        // add misses - x12
        for (int i = 0; i < 12; i++) {
            Card card = new Card("Missed", 1);
            cards.add(card);
        }

        // add beers - x6
        for (int i = 0; i < 6; i++) {
            Card card = new Card("Beer", 2);
            cards.add(card);
        }
    }

    public Card draw() {
        Random rand = new Random();
        int pos = rand.nextInt(cards.size());
        return cards.remove(pos);
    }

    public void shufflediscard(ArrayList<Card> newcards) {
        cards.addAll(newcards);
    }

    public int remaining() {
        return cards.size();
    }
}
