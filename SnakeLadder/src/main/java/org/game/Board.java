package org.game;

import java.util.*;

public class Board {
    private int boardSize;
    final Map<Integer, Integer> jumpMappings;

    public Board(final int boardSize, final Map<Integer, Integer> jumpMappings) {
        this.boardSize = boardSize;
        this.jumpMappings = Map.ofEntries((Map.Entry<Integer, Integer>[]) jumpMappings.entrySet().toArray());
    }

    public int boardSize() {
        return boardSize;
    }

    public int finalPosition(final int position, final int steps) {
        if(position < 1 || position >= this.boardSize) {
            throw new IllegalArgumentException(
                    String.format("Invalid position. Position %d; BoardSize: %d", position, this.boardSize));
        }
        if(steps < 1) {
            throw new IllegalArgumentException("Invalid steps. " +
                    String.format("Steps: %d", steps));
        }
        int nextPosition = position + steps;
        if(nextPosition > this.boardSize) {
            return position;
        }
        if(jumpMappings.containsKey(nextPosition)) {
            return jumpMappings.get(nextPosition);
        }
        return nextPosition;
    }
}
