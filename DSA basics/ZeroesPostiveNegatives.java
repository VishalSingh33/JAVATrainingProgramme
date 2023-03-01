

import java.util.*;

public class ZeroesPostiveNegatives {
	public static void main(String[] args) {

		int count =0, countP =0, countN= 0;
		Scanner sc =  new Scanner(System.in);
		System.out.println("Array Length: ");
		int c = sc.nextInt();

		int[] a = new int[c];

		System.out.println("Enter Array's: ");
		for(int i=0; i<a.length; i++){
			a[i] = Integer.parseInt(sc.next());
		}

		for(int i = 0; i< a.length; i++){
			if(a[i] == 0)
			{
				count++;
			} else if (a[i] > 0) {
				countP++;
			} else if (a[i] < 0) {
				countN++;
			}
		}
		System.out.println("No. of Zeroes: " + count);
		System.out.println("No. of Postives: " + countP);
		System.out.println("No. of Negatives: " + countN);



	}
}
