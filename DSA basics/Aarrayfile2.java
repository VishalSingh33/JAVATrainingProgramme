package javaapplication1;

import java.util.Scanner; 

public class Aarrayfile2 {

    public static void main(String[] args) {
        //user input
        Scanner sc = new Scanner(System.in);
        //2d array
        int ar[][]= new int[3][3];
        // j & i array given
        for(int i=0; i<=2; i++)
            for(int j=0; j<=2; j++){
        {
            System.out.println("Enter the array numb: ");
            
            ar[i][j]= sc.nextInt();
        }
            }
        //to print array
        for(int i=0; i<=2;i++)
            for(int j=0; j<=2; j++)
            {
        {
            System.out.println("THE ARRSAY IS: "+ar[i][j]);
        }
    }
    }
}
