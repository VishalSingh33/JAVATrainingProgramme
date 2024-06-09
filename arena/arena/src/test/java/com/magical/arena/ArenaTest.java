package com.magical.arena;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {

    private Player playerA;
    private Player playerB;
    private DiceRoller mockDiceRoller;

    @BeforeEach
    void setUp() {
        playerA = new Player(50, 5, 10);
        playerB = new Player(100, 10, 5);
        mockDiceRoller = Mockito.mock(DiceRoller.class);
        ArenaApplication.setDiceRoller(mockDiceRoller);
    }

    @Test
    void testFightPlayerAWins() {
        // Mocking the dice rolls
        when(mockDiceRoller.rollDice()).thenReturn(
            5, 2, // Player A attacks, Player B defends
            4, 3, // Player B attacks, Player A defends
            5, 2, // Player A attacks, Player B defends (repeating to ensure Player B loses)
            5, 2, 
            5, 2);

        // Fight sequence
        ArenaApplication.fight(playerA, playerB);
        assertEquals(70, playerB.getHealth());

        ArenaApplication.fight(playerB, playerA);
        assertEquals(45, playerA.getHealth());

        // Continue until Player A wins
        ArenaApplication.fight(playerA, playerB);
        assertEquals(40, playerB.getHealth());

        ArenaApplication.fight(playerA, playerB);
        assertEquals(10, playerB.getHealth());

        ArenaApplication.fight(playerA, playerB);
        assertFalse(playerB.isAlive());
        assertTrue(playerA.isAlive());
    }

    @Test
    void testFightPlayerBWins() {
        // Mocking the dice rolls
        when(mockDiceRoller.rollDice()).thenReturn(
            1, 4,  // Player A attacks, Player B defends
            6, 2,  // Player B attacks, Player A defends
            3, 1,  // Player B attacks, Player A defends
            3, 1,  // Player B attacks, Player A defends
            3, 1); // Player B attacks, Player A defends

        // First attack: Player A attacks, Player B defends
        ArenaApplication.fight(playerA, playerB);
        assertEquals(100, playerB.getHealth()); // Expecting no damage dealt to Player B

        // Second attack: Player B attacks, Player A defends
        ArenaApplication.fight(playerB, playerA);
        assertEquals(30, playerA.getHealth());

        // Third attack: Player B attacks, Player A defends
        ArenaApplication.fight(playerB, playerA);
        assertEquals(20, playerA.getHealth());

        // Fourth attack: Player B attacks, Player A defends
        ArenaApplication.fight(playerB, playerA);
        assertEquals(10, playerA.getHealth());

        // Fifth attack: Player B attacks, Player A defends
        ArenaApplication.fight(playerB, playerA);
        assertFalse(playerA.isAlive()); // Player A should be dead
        assertTrue(playerB.isAlive());
    }

    
}
