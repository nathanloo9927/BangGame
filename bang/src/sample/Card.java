package sample;

public class Card {
    private String effect;
    private int id;

    // id list:
    // 0 - bang
    // 1 - miss
    // 2 - heal

    public Card(String event, int cardid) {
        effect = event;
        id = cardid;
    }

    public String getName() {
        return effect;
    }

    public int getid() {
        return id;
    }
}
