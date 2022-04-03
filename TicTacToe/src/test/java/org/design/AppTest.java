package org.design;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        TicTacToe game = new TicTacToe(3);
        assertFalse(game.makeMove(1, 1, 1), "No one should win yet. (1)");
        assertFalse(game.makeMove(2, 2, 1), "No one should win yet. (2)");
    }
}
