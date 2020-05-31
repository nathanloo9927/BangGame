package sample;

import java.util.ArrayList;

public class Player {
    private int playernumber;
    private int range;
    private int health;
    private int maxhealth;
    private ArrayList<Card> hand;
    private boolean banged;
    private boolean targeted;
    private String role;
    private Player left;
    private Player right;

    public Player(int id) {
        playernumber = id;
        range = 1;
        hand = new ArrayList<Card>();
        health = 4;
        maxhealth = 4;
        banged = false;
        targeted = false;
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
        targeted = false;
    }

    // the player played a beer
    public void heal() {
        health++;
    }

    // returns if the player is a target of a bang or not
    public boolean underattack() {
        return targeted;
    }

    // the player is the target of a bang
    public void beingattacked() {
        targeted = true;
    }

    // did the player play a bang
    public boolean didbang() {
        return banged;
    }

    // the player played a bang
    public void modifybanged(boolean bang) {
        banged = bang;
    }

    // get player's hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // draw cards
    public void draw(Card card) {
        hand.add(card);
    }

    // play a card
    public Card playCard(int pos) {
        Card played = hand.get(pos);
        Card bang = new Card("Bang", 0);
        Card missed = new Card("Missed", 1);
        Card beer = new Card("Beer", 2);
        Card nullcard = new Card("Error", -1);
        if (played.equals(bang)) {
            if (!banged) {
                banged = true;
                return hand.remove(pos);
            }
        } else if (played.equals(missed)) {
            if (targeted) {
                targeted = false;
                return hand.remove(pos);
            }
        } else if (played.equals(beer)) {
            if (health < maxhealth) {
                health++;
                return hand.remove(pos);
            }
        }
        return nullcard;
    }

    public Card discardcard(int pos) {
        return hand.remove(pos);
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
