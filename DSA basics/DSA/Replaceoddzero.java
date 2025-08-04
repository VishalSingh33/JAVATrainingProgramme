import java.util.Scanner;

public class Replaceoddzero {

    public static void main(String[] args) {
      //user input  
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the arrays");
        //ar length
        int ar[] = new int[5];
        //loop for reading value
        for(int i=0 ; i<5; i++)
        {
            ar[i] = sc.nextInt();
        }
        //loop for executing
        for(int i =0 ; i<5; i++)
        {
            //condition
            if(ar[i]%2==1)
            {
                ar[i] = 0;
            }
        }
        //loop for pritning ar
         for(int i = 0 ; i<5 ; i++)
        {
       System.out.println("UPDATED ARRAY NOW: "+ ar[i]);
        }
    }
}
