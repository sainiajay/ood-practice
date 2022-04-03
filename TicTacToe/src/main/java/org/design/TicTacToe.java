package org.design;

import java.util.Objects;

public class TicTacToe {
    private final int BOARD_SIZE;
    private final int[][] board;

    public TicTacToe(final int boardSize) {
        BOARD_SIZE = boardSize;
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for(int row = 0; row < BOARD_SIZE; row++) {
            for(int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = -1;
            }
        }
    }

    /**
     * Makes a move for a given player. Return true if the move turns out to be a winning move.
     * THOUGHT: //
     * @param player id of the player (either 0 or 1).
     * @param moveX X component of the move. (base 0)
     * @param moveY Y component of the move. (base 0)
     * @return True if it's winning move, else False
     * @throws IllegalArgumentException when move is illegal
     */
    public boolean makeMove(final int player, final int moveX, final int moveY) throws IllegalArgumentException {
        if(player < 1 || player > 2) {
            throw new IllegalArgumentException("Invalid player id");
        }
        if(moveX < 0 || moveX >= BOARD_SIZE) {
            throw new IllegalArgumentException("Invalid moveX");
        }
        if(moveY < 0 || moveY >= BOARD_SIZE) {
            throw new IllegalArgumentException("Invalid moveY");
        }
        if(board[moveX][moveY] != -1) {
            throw new IllegalArgumentException("Move already made.");
        }

        board[moveX][moveY] = player;

        //check vertical
        for(int row = 0; row < BOARD_SIZE; row++) {
            if(board[row][moveY] != player) return false;
        }

        //check horizontal
        for(int col = 0; col < BOARD_SIZE; col++) {
            if(board[moveX][col] != player) return false;
        }

        //check diagonal, if the move made is in diagonal
        if(moveX == moveY || moveX + moveY == BOARD_SIZE - 1) {
            for(int index = 0; index < BOARD_SIZE; index++) {
                if(board[index][index] != player) return false;
            }
        }

        return true;
    }
}
