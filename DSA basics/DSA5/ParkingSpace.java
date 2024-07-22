import java.util.*;

// Problem Description
// You are the owner of a hotel at a hill station. There is a huge demand for guests 
// but you have a limited car parking available. Given the arrival and departure time of 
// all the cars, find the minimum number of car parking spaces needed.

// Input format
// First line contains integer N - Number of cars.
// Next N lines contain 2 integers A, D - Arrival and departure time of ith car.

public class ParkingSpace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] times = new int[N][2];

        for (int i = 0; i < N; i++) {
            times[i][0] = sc.nextInt();
            times[i][1] = sc.nextInt();
        }

        System.out.println(parkingSpace(times));
    }

    public static int parkingSpace(int[][] times) {
        int n = times.length;
        int[] arrival = new int[n];
        int[] departure = new int[n];

        for (int i = 0; i < n; i++) {
            arrival[i] = times[i][0];
            departure[i] = times[i][1];
        }

        Arrays.sort(arrival);
        Arrays.sort(departure);

        int maxParkingSpaces = 0;
        int currentParkingSpaces = 0;
        int i = 0, j = 0;

        while (i < n && j < n) {
            if (arrival[i] < departure[j]) {
                currentParkingSpaces++;
                maxParkingSpaces = Math.max(maxParkingSpaces, currentParkingSpaces);
                i++;
            } else {
                currentParkingSpaces--;
                j++;
            }
        }

        return maxParkingSpaces;
    }
}

// Explanation:
// Reading the Input:

// The main method reads the number of cars N.
// It then reads the arrival and departure times for each car and stores them in
// a 2D array times.

// Extracting and Sorting Arrival and Departure Times:
// The parkingSpace method extracts arrival times into an arrival array and
// departure times into a departure array.
// Both arrays are then sorted. Sorting helps in efficiently counting the
// overlapping intervals.

// Using Two Pointers:
// Two pointers i and j are used to traverse the arrival and departure arrays,
// respectively.
// If the next event is an arrival (arrival[i] < departure[j]), it means a car
// needs a parking space, so currentParkingSpaces is incremented.
// If the next event is a departure (arrival[i] >= departure[j]), it means a car
// leaves, so currentParkingSpaces is decremented.
// maxParkingSpaces keeps track of the maximum number of cars parked at the same
// time.

// Output the Result:
// The maxParkingSpaces is returned as the minimum number of parking spaces
// required.
// This algorithm efficiently calculates the required parking spaces in
// O(NlogN) time due to the sorting step, which is suitable for handling large
// inputs.
