import java.util.Scanner;
//sorting
public class Ascenarraysort {

    public static void main(String[] args) {
        //input from user
       Scanner sc = new Scanner(System.in);
       //declartion of temp
       int temp = 0 ;
       //ar length
       int ar[] = new int[6];
       for(int i=0 ; i<5 ; i++)
       {
           System.out.println("ENTER THE ARRAY's");
           ar[i] = sc.nextInt();
        }
       for(int i=0 ; i<5 ; i++)
       {
           for(int j=0 ; j<5 ; j++)
       {
           if(ar[j]>ar[j+1])
       {
           //switch values
           temp=ar[j];
           ar[j]=ar[j+1];
           temp=ar[j+1];
       }
       }
       }
       for(int i=0 ; i<5 ; i++)
       {

       System.out.println("ASCENDING  ARRAY,s YOU HAVE: "+ ar[i]);
       }
}
}
       
