import java.util.Scanner;

public class Fabonacciseries {

    public static void main(String[] args) {
//user input
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number");
        int n = s.nextInt();

        int a = 0, b = 1;
        System.out.print("Fabonacci series");
        System.out.print(a + " ");
        System.out.print(b + " ");
        //loop 
        for(int i = 2 ; i<n ; i++) {
            //logic
            int sum = a + b;
            a = b;
            b = sum;
            System.out.println("the series is: " + sum);
        }
    }
}
