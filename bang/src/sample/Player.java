package sample;

import java.util.ArrayList;

public class Player {
    private int playernumber;
    private int range;
    private int health;
    private ArrayList<Card> hand;
    private boolean yourturn;
    private boolean banged;
    private boolean targeted;
    private boolean discardphase;
    private String role;
    private Player left;
    private Player right;

    public Player(int id) {
        playernumber = id;
        range = 1;
        hand = new ArrayList<Card>();
        health = 4;
        // deal 4 cards to start
        for (int i = 0; i < 4; i++) {
            hand.add(Deck.deck.draw());
        }
        yourturn = false;
        banged = false;
        targeted = false;
        discardphase = false;
        role = "";
        left = null;
        right = null;
    }

    // health of the player
    public int getHealth() {
        return health;
    }

    public int getid() {
        return playernumber;
    }

    // if the player was banged and didn't play a missed
    public void takedamage() {
        health--;
    }

    // the player played a beer
    public void heal() {
        health++;
    }

    // returns if it's the player's turn or not
    public boolean shouldgo() {
        return yourturn;
    }

    // begins your turn, draw 2 cards and you should be able to bang
    public void beginturn() {
        yourturn = true;
        hand.add(Deck.deck.draw());
        hand.add(Deck.deck.draw());
        banged = false;
    }

    // returns if the player is a target of a bang or not
    public boolean underattack() {
        return targeted;
    }

    // the player is the target of a bang
    public void beingattacked() {
        targeted = true;
    }

    // the player chose what to do
    public void react() {
        targeted = false;
    }

    // did the player play a bang
    public boolean didbang() {
        return banged;
    }

    // the player played a bang
    public void usedbang() {
        banged = true;
    }

    // is the player in the discard phase
    public boolean disphase() {
        return discardphase;
    }

    // the player has to discard if health is lower than hand size
    public boolean canendturn() {
        if (health < hand.size() && yourturn) {
            discardphase = true;
            return false;
        } else {
            discardphase = false;
            yourturn = false;
            return true;
        }
    }

    // get player's hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // play a card
    public void playCard(int pos) {
        hand.remove(pos);
    }

    // set the player's role
    public void setRole(String newrole) {
        role = newrole;
    }

    public String getRole() {
        return role;
    }

    // set the players sitting next to the person
    public void setLeft(Player person) {
        left = person;
    }

    public Player getLeft() {
        return left;
    }

    public void setRight(Player person) {
        right = person;
    }

    public Player getRight() {
        return right;
    }

    public boolean equals(Player other) {
        return playernumber == other.getid();
    }

    // is it a valid target for a bang?
    public boolean validtarget(Player other) {
        if (other.equals(left) || other.equals(right)) {
            return true;
        } else if (range == 2 && (other.equals(left.getLeft()) || other.equals(right.getRight()))) {
            return true;
        } else if (range == 3 && (other.equals(left.getLeft().getLeft()) || other.equals(right.getRight().getRight()))) {
            return true;
        } else {
            return false;
        }
    }
}
