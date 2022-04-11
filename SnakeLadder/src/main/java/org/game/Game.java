package org.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game {
    private final Board board;
    private final int[] playerPositions;

    public Game(Board board, int playerCount) {
        if(board == null) {
            throw new IllegalArgumentException("Board cannot be null.");
        }
        if(playerCount < 2) {
            throw new IllegalArgumentException("Minimum two players are required to play this game.");
        }
        this.board = board;
        playerPositions = new int[playerCount];
        Arrays.fill(playerPositions, 1);
    }

    public boolean movePlayer(int playerId, int steps) {
        if(playerId < 0 || playerId >= this.playerPositions.length) {
            throw new IllegalArgumentException("Invalid playerId." +
                    String.format("PlayerId: %d", playerId));
        }
        int finalPosition = board.finalPosition(playerPositions[playerId], steps);
        playerPositions[playerId] = finalPosition;
        return finalPosition == board.boardSize();
    }
}
