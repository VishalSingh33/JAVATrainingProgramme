//Smaller array
import java.util.Scanner;

public class Smallerarrya {

    public static void main(String[] args) {
        //user input
        Scanner sc = new Scanner(System.in);
        //min value to comapre
        int min = 9999;
        //ar length
        int ar[]= new int[7];
        for(int i =1; i<=6; i++)
        {
            System.out.println("ENTER THE ARRAY's");
            ar[i] = sc.nextInt();
        }
        //loop execution
        for(int i =1; i<=6; i++)
        {
            //comapring min value to min condition
            if(min > ar[i])
            {
                min = ar[i];
            }
        }
        System.out.println("THE MIN ARRAY,s IS: "+ min);
    }
    
}
