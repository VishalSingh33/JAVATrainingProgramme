package com.magical.arena;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArenaApplication {

	private static DiceRoller diceRoller = new RandomDiceRoller();

	public static void setDiceRoller(DiceRoller diceRoller) {
		ArenaApplication.diceRoller = diceRoller;
	}

	public static void main(String[] args) {
		// SpringApplication.run(ArenaApplication.class, args);

		Player playerA = new Player(50, 5, 10);
		Player playerB = new Player(100, 10, 5);

		while (playerA.isAlive() && playerB.isAlive()) {
			fight(playerA, playerB);
			if (playerB.isAlive()) {
				fight(playerB, playerA);
			}
		}

		if (playerA.isAlive()) {
			System.out.println("Player A wins!");
		} else {
			System.out.println("Player B wins!");
		}
	}

	public static void fight(Player attacker, Player defender) {
		int attackRoll = diceRoller.rollDice();
		int defendRoll = diceRoller.rollDice();

		int attackDamage = attacker.getAttack() * attackRoll;
		int defendStrength = defender.getStrength() * defendRoll;

		int damageToDefender = attackDamage - defendStrength;
		if (damageToDefender > 0) {
			defender.setHealth(defender.getHealth() - damageToDefender);

			System.out.println("Attacker hits for " + attackDamage + ", Defender defends " +
					defendStrength + ", Defender takes " + damageToDefender +
					" damage. Defender health: " + defender.getHealth());
		} else {
			System.out.println("Attacker hits for " + attackDamage + ", Defender defends " +
					defendStrength + ", No damage dealt. Defender health: " + defender.getHealth());
		}
	}
}
