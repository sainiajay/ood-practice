package org.game;

import java.util.HashMap;
import java.util.Map;

public class BoardBuilder {
    int boardSize = -1;
    Map<Integer, Integer> jumpMapping = new HashMap<>();

    private static boolean isPerfectSquare(int number) {
        double squareRoot = Math.sqrt(number);
        return Math.floor(squareRoot) == Math.ceil(squareRoot);
    }

    public BoardBuilder setBoardSize(final int size) {
        if(!isPerfectSquare(size)) {
            throw new IllegalArgumentException("Board size should be a perfect square. " +
                    String.format("size: %d", size));
        }
        this.boardSize = size;
        return this;
    }

    public BoardBuilder putSnake(int source, int destination) {
        if(this.boardSize == -1) {
            throw new IllegalStateException("Set board size first.");
        }
        if(destination >= source) {
            throw new IllegalArgumentException("Snake destination should be smaller than the source." +
                    String.format("Source: %d; Destination: %d", source, destination));
        }
        jumpMapping.put(source, destination);
        return this;
    }

    public BoardBuilder putLadder(int source, int destination) {
        if(this.boardSize == -1) {
            throw new IllegalStateException("Set board size first.");
        }
        if(destination <= source) {
            throw new IllegalArgumentException("Ladder destination should be greater than the source." +
                    String.format("Source: %d; Destination: %d", source, destination));
        }
        jumpMapping.put(source, destination);
        return this;
    }

    public Board build() {
        if(this.boardSize == -1) {
            throw new IllegalStateException("Set board size first.");
        }
        return new Board(this.boardSize, this.jumpMapping);
    }
}
