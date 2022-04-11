package org.game;
import java.util.*;

public class RandomBoardBuilder extends BoardBuilder {
    private int snakeCount = -1;
    private int ladderCount = -1;

    Set<Integer> positionSet = new HashSet<>();
    private Random random = new Random();

    public RandomBoardBuilder(int boardSize) {
        super();
        super.setBoardSize(boardSize);
    }

    public RandomBoardBuilder setSnakeCount(final int count) {
        this.snakeCount = count;
        return this;
    }

    public RandomBoardBuilder setLadderCount(final int count) {
        this.ladderCount = count;
        return this;
    }

    private void generateLadders(final int count) {
        final int totalCount = positionSet.size() + count;
        while (positionSet.size() < totalCount) {
            int randomStart = random.nextInt(this.boardSize - 1) + 1;
            if(positionSet.contains(randomStart)) {
                continue;
            }
            positionSet.add(randomStart);

            int randomEnd = random.nextInt(this.boardSize - randomStart) + randomStart;
            while(positionSet.contains(randomEnd)) {
                randomEnd = random.nextInt(this.boardSize - randomStart) + randomStart;
            }
            positionSet.add(randomEnd);
            this.putLadder(randomStart, randomEnd);
        }
    }

    private void generateSnakes(final int count) {
        final int totalCount = positionSet.size() + count;
        while (positionSet.size() < totalCount) {
            int randomEnd = random.nextInt(this.boardSize - 1) + 1;
            if(positionSet.contains(randomEnd)) {
                continue;
            }
            positionSet.add(randomEnd);

            int randomStart = random.nextInt(randomEnd) + 1;
            while(positionSet.contains(randomStart)) {
                randomStart = random.nextInt(randomEnd) + 1;
            }
            positionSet.add(randomStart);
            this.putSnake(randomStart, randomEnd);
        }
    }

    @Override
    public Board build() {
        if(this.ladderCount == -1) {
            throw new IllegalStateException("Ladder count is not set yet.");
        }
        if(this.snakeCount == -1) {
            throw new IllegalStateException("Snake count is not set yet.");
        }
        this.generateLadders(this.ladderCount);
        this.generateSnakes(this.snakeCount);
        return super.build();
    }
}
