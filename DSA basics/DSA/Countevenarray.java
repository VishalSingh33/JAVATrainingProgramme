//COUNT EVEN ARRAY
import java.util.Scanner;

public class Countevenarray {

    public static void main(String[] args) {
        //user input
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER EVEN ARRAY: ");
        int count=0;
        //ar length declation
        int ar[] = new int[5];
        for(int i=0; i<5; i++)
        {
            //ar user input
           ar[i] = sc.nextInt();
        }
        for(int i = 0 ; i<5; i++)
        {
            //logic
           if(i%2==0)
            {
                //moving to +1 state
               count++;
                
            }
        }
        System.out.println("COUNT OF EVEN ARRAY: "+ count);
    }
}
