
import java.util.Scanner;

public class Numdigits {

    public static void main(String[] args) {
        //user input
        Scanner sc = new Scanner(System.in);
        
        int a = sc.nextInt();//325
        int c = 0;
        //loop condition
        while(a > 0)
        {
        c = c + 1;
        a = a/10;
        }
        System.out.println("Digit sum: "+c);
        
    }
}
