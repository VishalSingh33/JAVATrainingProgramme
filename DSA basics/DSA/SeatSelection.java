import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SeatSelection {
    public static void main(String[] args) {
        // Define available seats
        List<String> availableSeats = new ArrayList<>(Arrays.asList("1A", "2A", "3Z", "1X", "5V", "3C", "6C", "6A", "7A", "7B"));

        // Define booked seats
        List<String> bookedSeats = new ArrayList<>(Arrays.asList("2A", "3Z", "1X"));

        // Remove booked seats from available seats
        availableSeats.removeAll(bookedSeats);

        String selectedSeat = "1X";

        // Check if 1X is booked
        if (!bookedSeats.contains(selectedSeat)) {
            bookedSeats.add(selectedSeat);
        } else {
            // If 1X is booked, select a random seat from the remaining available seats
            Random rand = new Random();
            selectedSeat = availableSeats.get(rand.nextInt(availableSeats.size()));
        }

        System.out.println("Selected seat: " + selectedSeat);
    }
}
