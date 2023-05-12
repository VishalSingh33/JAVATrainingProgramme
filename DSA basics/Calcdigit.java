import java.util.Scanner;

public class Calcdigit {

    public static void main(String[] args) {
     //user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the num");
       int a = sc.nextInt();//4445
       
       int sum = 0;
       //loop
       while(a>0)
       {
       sum = sum + 1;
       a =  a /10;
       }
       System.out.println("TOTAL DIGIT IS: "+sum);
       
    }
}
