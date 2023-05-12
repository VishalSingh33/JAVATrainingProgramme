import java.util.Scanner; 

public class Arrayfile1 {

    public static void main(String[] args) {
        //user input
        Scanner sc = new Scanner(System.in);
        //ar length
        int ar[]= new int[3];
        //lopp to read value
        for(int i=0; i<=2; i++)
        {
            System.out.println("Enter the array numb: ");
            
            ar[i]= sc.nextInt();
        }
        //loop to execute
        for(int i=0; i<=2;i++)
        {
            System.out.println("THE ARRSAY IS: "+ar[i]);
        }
    }
}
