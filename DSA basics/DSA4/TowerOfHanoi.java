import java.util.ArrayList;

public class TowerOfHanoi {
    ArrayList<String> moves; // To store the moves

    public TowerOfHanoi() {
        moves = new ArrayList<>();
    }

    public ArrayList<String> towerOfHanoi(int n) {
        moveDisks(n, "A", "C", "B"); // A = source, C = destination, B = auxiliary
        return moves;
    }

    private void moveDisks(int n, String source, String destination, String auxiliary) {
        if (n == 1) {
            moves.add("Move disk 1 from " + source + " to " + destination);
        } else {
            moveDisks(n - 1, source, auxiliary, destination);
            moves.add("Move disk " + n + " from " + source + " to " + destination);
            moveDisks(n - 1, auxiliary, destination, source);
        }
    }

    public static void main(String[] args) {
        TowerOfHanoi hanoi = new TowerOfHanoi();
        int n = 3; // Number of disks
        ArrayList<String> solution = hanoi.towerOfHanoi(n);
        for (String move : solution) {
            System.out.println(move);
        }
    }
}
