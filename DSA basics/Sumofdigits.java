import java.util.Scanner;

public class Sumofdigits {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Entre the Num");
        int a = sc.nextInt();
        int sum = 0 ;
//loop for sum of digits
        while (a > 0) {
            
            int c = a % 10;
            a = a / 10;
           sum = sum + c;
        }
        System.out.println("TOTAL OF DIGIT IS: " + sum);
    }
}
