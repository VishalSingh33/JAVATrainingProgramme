import java.util.Scanner;

public class Secondlargerarray {

    public static void main(String[] args) {
     //user input    
       Scanner sc = new Scanner(System.in);
       int max1 = 0;
       int max2 = 0;
       //ar length
       int ar[] = new int[4];
       //loop for reading value
       for(int i=0 ; i<4 ; i++)
       {
           System.out.println("ENTER THE ARRAY's");
           ar[i] = sc.nextInt();
        }
       ////loop for condition
       for(int i=0 ; i<4 ; i++)
       {
           //condition
           if(ar[i]>max1)
           {
            max2 = max1;
            //changing max1 back to same value
            max1 = ar[i];
           }
       }
       System.out.println("Second Larger ARRAY,s YOU HAVE: "+max2);
    }
    
}
       
