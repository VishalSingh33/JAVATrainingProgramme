import java.util.Scanner;

public class Sumevenarray {

    public static void main(String[] args) {
        int sum = 0;
         //user input
        Scanner sc = new Scanner(System.in);
         //ar length
        int ar[]= new int[7];
        for(int i =1; i<=6; i++)
        {
            System.out.println("ENTER THE ARRAY's");
            ar[i] = sc.nextInt();
        }
        //loop for EXECUTIION
        for(int i =1; i<=6; i++)
        {
            //sum of all ar condition
            if(ar[i]%2==0)
            {
             sum = sum + ar[i]; 
            }
        }
        System.out.print("THE EVEN ARRAY,s IS: "+ sum);
    }
    
}
