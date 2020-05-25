package sample;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int players;
    private int turnindex;
    private ArrayList<Player> playerslist;

    public Game(int numplayers) {
        players = numplayers;
        playerslist = new ArrayList<Player>(players);

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

        for (int i = 0; i < players; i++) {
            Random rand = new Random();
            int pos = rand.nextInt(roles.size());
            playerslist.get(i).setRole(roles.remove(pos));
            if (playerslist.get(i).getRole().equals("sheriff")) {
                playerslist.get(i).heal(); // sheriffs start with one extra health
                turnindex = i;
            }
        }
    }

    public int getTurn() {
        return turnindex;
    }

    public void next() {
        turnindex++;
        if (turnindex >= players) {
            turnindex = 0;
        }
    }

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
    }
}
