//Simpl even array
import java.util.Scanner;

public class Simplevenarray {

    public static void main(String[] args) {
        //USER INPUT
        Scanner sc = new Scanner(System.in);
        //ar length
        int ar[]= new int[7];
        //loop raeding value
        for(int i =1; i<=6; i++)
        {
            System.out.println("ENTER THE ARRAY's");
            ar[i] = sc.nextInt();
        }
        //loop to execute
        for(int i =1; i<=6; i++)
        {
            //even condition
            if(ar[i]%2==0)
            {
              System.out.print("THE EVEN ARRAY,s IS: "+ ar[i]);
            }
        }
       
    }
    
}
