import java.util.Scanner;

public class Prime {

    public static void main(String[] args) {

        int N = 7;
        // condition
        int count = 0;
        if (N <= 1) {
            System.out.println("THIS NUM IS NOT PRIME");
        }
        for (int i = 2; i <= N; i++) {
            if (N % i == 0) {
                count++;
            }
        }
        System.out.println(count);
        if (count < 2) {
            System.out.println(N + " THIS NUM IS PRIME");
        } else {
            System.out.println(N + " THIS NUM IS NOT PRIME");
        }
    }
}
