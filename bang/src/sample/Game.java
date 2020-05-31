package sample;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int players;
    private int turnindex;
    private ArrayList<Player> playerslist;
    private ArrayList<Card> discard;
    private boolean discardphase;
    private boolean banged;
    private Deck deck;

    public Game(int numplayers) {
        players = numplayers;
        playerslist = new ArrayList<Player>(players);
        discardphase = false;
        banged = false;
        deck = new Deck();
        discard = new ArrayList<Card>();

        // initialize the players
        for (int i = 0; i < players; i++) {
            Player newplayer = new Player(i);
            playerslist.add(newplayer);
        }

        // set the players sitting next to the player
        for (int i = 0; i < players; i++) {
            if (i == 0) {
                playerslist.get(i).setLeft(playerslist.get(players-1));
                playerslist.get(i).setRight(playerslist.get(i+1));
            } else if (i == players - 1) {
                playerslist.get(i).setLeft(playerslist.get(i-1));
                playerslist.get(i).setRight(playerslist.get(0));
            } else {
                playerslist.get(i).setLeft(playerslist.get(i-1));
                playerslist.get(i).setRight(playerslist.get(i+1));
            }
        }

        // assign roles to the players
        ArrayList<String> roles = new ArrayList<String>(players);
        roles.add("sheriff");
        roles.add("renegade");
        roles.add("outlaw");
        roles.add("outlaw");
        if (players >= 5) {
            roles.add("deputy");
        }
        if (players >= 6) {
            roles.add("outlaw");
        }
        if (players >= 7) {
            roles.add("deputy");
        }

        // set the roles for each player and draw cards
        for (int i = 0; i < players; i++) {
            Random rand = new Random();
            int pos = rand.nextInt(roles.size());
            playerslist.get(i).setRole(roles.remove(pos));
            playerslist.get(i).draw(deck.draw());
            playerslist.get(i).draw(deck.draw());
            playerslist.get(i).draw(deck.draw());
            playerslist.get(i).draw(deck.draw());
            if (playerslist.get(i).getRole().equals("sheriff")) {
                playerslist.get(i).heal(); // sheriffs start with one extra health
                turnindex = i;
                playerslist.get(turnindex).draw(deck.draw());
            }
        }
    }

    public int getTurn() {
        return turnindex+1;
    }

    public boolean indiscard() {
        return discardphase;
    }

    // get the hand of the current player's turn
    public ArrayList<Card> getHand() {
        return playerslist.get(turnindex).getHand();
    }

    // if you are banged and you want to take the hit
    public void takehit() {
        playerslist.get(turnindex).takedamage();
    }

    // show the stats of the player
    public int healthstat() {
        return playerslist.get(turnindex).getHealth();
    }

    public String rolestat() {
        return playerslist.get(turnindex).getRole();
    }

    public boolean playedbang() {
        return playerslist.get(turnindex).didbang();
    }

    public void play(int pos) {
        Card card;
        Card error = new Card("Error", -1);
        if (discardphase) {
            card = playerslist.get(turnindex).discardcard(pos);
        } else {
            card = playerslist.get(turnindex).playCard(pos);
        }
        if (!card.equals(error)) {
            discard.add(card);
        }
    }

    // attempt to end the player's turn. If it has more cards in hand than health, it must discard
    public void attemptend() {
        Player playing = playerslist.get(turnindex);
        int health = playing.getHealth();
        int handsize = playing.getHand().size();
        if (health < handsize) {
            discardphase = true;
        } else {
            discardphase = false;
            next();
        }
    }
    public void start() {
        playerslist.get(turnindex).draw(deck.draw());
        playerslist.get(turnindex).draw(deck.draw());
    }

    public void next() {
        turnindex++;
        if (turnindex >= players) {
            turnindex = 0;
        }
        if (deck.remaining() < 2) {
            deck.shufflediscard(discard);
            discard.clear();
        }
        playerslist.get(turnindex).draw(deck.draw());
        playerslist.get(turnindex).draw(deck.draw());
        playerslist.get(turnindex).modifybanged(false);
    }

    public int left() {
        return deck.remaining();
    }
/*
    public void action(Card card, int loc, Player other) {
        switch (card.getid()) {
            case 0:
                if (!playerslist.get(turnindex).didbang() && playerslist.get(turnindex).validtarget(other)) {
                    other.beingattacked();
                    playerslist.get(turnindex).playCard(loc);
                }
                break;
            case 1:
                if (playerslist.get(turnindex).underattack()) {
                    playerslist.get(turnindex).react();
                    playerslist.get(turnindex).playCard(loc);
                }
                break;
            case 2:
                playerslist.get(turnindex).heal();
                playerslist.get(turnindex).playCard(loc);
                break;
            default:
                System.out.println("unknown card played");
        }
    }*/
}
