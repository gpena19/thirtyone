package edu.guilford;

import java.util.ArrayList;
import java.util.List;

public class PlayerGroup {
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public void addPlayer(String name) { players.add(new Player(name)); }
    public Player getCurrentPlayer() { return players.get(currentPlayerIndex); }
    public void nextTurn() { currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); }
    public List<Player> getPlayers() { return players; }
}
