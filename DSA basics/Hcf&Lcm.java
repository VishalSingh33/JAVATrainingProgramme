
import java.util.Scanner;

public class Hcf-Lcm {
	public static void main(String[] args) {
		int lcm, gcd;
		Scanner s = new Scanner(System.in);

		System.out.print("Enter the Two Numbers: ");
		int a = s.nextInt();
		int b = s.nextInt();

		// for hcf
		gcd = a;
		while (true) {
			if ((a % gcd == 0) && (b % gcd == 0))
				break;
			else
				gcd--;
		}
		System.out.printf("\nThe HCF of %d and %d is %d.", a, b, gcd);

		// for lcm
		lcm = (a > b) ? a : b; // lcm = Math.max(a, b);
		// Always true
		while (true) {
			if (lcm % a == 0 && lcm % b == 0) 
				break;
			else
			++lcm;
		}
		System.out.printf("\nThe LCM of %d and %d is %d.", a, b, lcm);

	}
}
