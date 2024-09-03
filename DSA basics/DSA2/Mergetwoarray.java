import java.util.Scanner;

public class Mergetwoarray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER EVEN ARRAY1: ");
        int  k=0 ;
        //ar length
        int ar[] = new int[3];
        int ar1[] = new int[3];
        //loop to read ar i
        for(int i=0; i<ar.length; i++)
        {
           ar[i] = sc.nextInt();
        }
        //loop to read ar j
        System.out.println("ENTER EVEN ARRAY2: ");
        for(int j=0; j<ar.length; j++)
        {
                  ar1[j] = sc.nextInt(); 
        }
        //cal size
        int size = ar.length+ar1.length;
        //final new size
        int finalArr[]= new int[size];
        
      //merging two array
        for(int i=0; i<ar.length; i++)
        {
          finalArr[k] = ar[i];
          k++;
        }
        for( int j=0; j<ar1.length; j++)
        {
        finalArr[k] = ar1[j];
        k++;
        }
        for( int i=0; i<size; i++)
        {
        System.out.print("combined array: "+ finalArr[i]);
        }
    }
}
