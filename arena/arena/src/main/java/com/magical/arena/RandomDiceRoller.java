package com.magical.arena;

import java.util.Random;

public class RandomDiceRoller implements DiceRoller {
    private final Random random = new Random();

    @Override
    public int rollDice() {
        return random.nextInt(6) + 1;
    }
}
