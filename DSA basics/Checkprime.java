package javaapplication1;

import java.util.Scanner;

public class Checkprime {

    public static void main(String[] args) {
//user input
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the num");
        int a = sc.nextInt();
//condition
        if (a % 2 == 1 && a / a == 1) 
        {
            System.out.println("THIS IS PRIME MUM");
        } 
        else 
        {
            System.out.println("THIS NUM IS NOT PRIME");
        }
    }
}
