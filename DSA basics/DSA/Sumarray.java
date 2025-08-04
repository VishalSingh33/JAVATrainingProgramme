import java.util.Scanner;

public class Sumarray {

    public static void main(String[] args) {
        //user input 
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        //ar length
        int ar[]= new int[7];
        for(int i =1; i<=6; i++)
        {
            System.out.println("ENTER THE ARRAY's");
            ar[i] = sc.nextInt();
        }
        //loop for sum of all ar condition
        for(int i =1; i<=6; i++)
        {
           sum = sum + ar[i] ;
        }
        System.out.println("THE SUM OF ARRAY,s IS: "+ sum);
    }
    
}
