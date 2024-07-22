import java.util.Random;

class Player {
    int health;
    int strength;
    int attack;

    public Player(int health, int strength, int attack) {
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

public class MagicalArena {
    private static Random random = new Random();

    public static void main(String[] args) {
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

    private static void fight(Player attacker, Player defender) {
        int attackRoll = rollDice();
        int defendRoll = rollDice();

        int attackDamage = attacker.attack * attackRoll;
        int defendStrength = defender.strength * defendRoll;

        int damageToDefender = attackDamage - defendStrength;
        if (damageToDefender > 0) {
            defender.health -= damageToDefender;
            System.out.println("Attacker hits for " + attackDamage + ", Defender defends " + defendStrength + ", Defender takes " + damageToDefender + " damage. Defender health: " + defender.health);
        } else {
            System.out.println("Attacker hits for " + attackDamage + ", Defender defends " + defendStrength + ", No damage dealt. Defender health: " + defender.health);
        }
    }

    private static int rollDice() {
        return random.nextInt(6) + 1; // Generates a number between 1 and 6
    }
}
