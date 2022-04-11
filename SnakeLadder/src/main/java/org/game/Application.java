
package org.game;

public class Application {
  public static void main(String[] args) {
    Board board = new RandomBoardBuilder(64)
            .setLadderCount(3)
            .setSnakeCount(5)
            .build();
  }
}
