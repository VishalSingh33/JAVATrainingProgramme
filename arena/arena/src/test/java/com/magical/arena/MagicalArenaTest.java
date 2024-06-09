package com.magical.arena;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MagicalArenaTest {
    // private ArenaApplication arena;
    private Player playerA;
    private Player playerB;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        playerA = new Player(50, 5, 10);
        playerB = new Player(100, 10, 5);
        mockRandom = Mockito.mock(Random.class);
        // arena = new ArenaApplication();
    }

    @Test
    void testFightPlayerAWins() {
        // Mocking the dice rolls
        when(mockRandom.nextInt(6)).thenReturn(4, 1, 3, 2); // Player A attacks and Player B defends, then Player B attacks and Player A defends

        
        ArenaApplication.fight(playerA, playerB);
        assertEquals(70, playerB.getHealth());

        ArenaApplication.fight(playerB, playerA);
        assertEquals(45, playerA.getHealth());

        // Continue until Player A wins
        when(mockRandom.nextInt(6)).thenReturn(4, 1); // Player A attacks and Player B defends
        ArenaApplication.fight(playerA, playerB);
        assertFalse(playerB.isAlive());
        assertTrue(playerA.isAlive());
    }

    @Test
    void testFightPlayerBWins() {
        // Mocking the dice rolls
        when(mockRandom.nextInt(6)).thenReturn(1, 4, 6, 2); // Player A attacks and Player B defends, then Player B attacks and Player A defends

        ArenaApplication.fight(playerA, playerB);
        assertEquals(50, playerB.getHealth());

        ArenaApplication.fight(playerB, playerA);
        assertEquals(15, playerA.getHealth());

        // Continue until Player B wins
        when(mockRandom.nextInt(6)).thenReturn(3, 1); // Player B attacks and Player A defends
        ArenaApplication.fight(playerB, playerA);
        assertFalse(playerA.isAlive());
        assertTrue(playerB.isAlive());
    }
}

