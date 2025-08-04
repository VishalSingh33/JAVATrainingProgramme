import java.util.Scanner;

public class Largerarray {

    public static void main(String[] args) {
      //taking input from user  
       Scanner sc = new Scanner(System.in);
       int max = 0;
       int ar[] = new int[8];
       //loop for checking one to another
       for(int i=1 ; i<=7 ; i++)
       {
           System.out.println("ENTER THE ARRAY's");
           ar[i] = sc.nextInt();
        }
       //loop to check max value and print
       for(int i=1 ; i<=7 ; i++)
       {
           //condition to check a[] max value
           if(max<ar[i])
       {
           //storing max to value to same
           max = ar[i];
       }
       }
       System.out.println("Larger ARRAY,s YOU HAVE: "+max);
    }
}
       
