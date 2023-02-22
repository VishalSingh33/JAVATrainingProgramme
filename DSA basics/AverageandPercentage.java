package com.zyapaar.analytics.properties;

import java.util.Arrays;
import java.util.Scanner;

public class AverageandPercentage {
    public static void main(String[] args) {
        int i; float total = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter n");
        int n = sc.nextInt();

        int[] a =  new int[n];
        System.out.println("Enter marks");
        for( i=0; i<n; i++)
        {
            a[i]=sc.nextInt();
        }

        for(i=0; i<n; i++)
        {
          total = total + a[i];
        }
        System.out.println("total marks: "+ total );

       float average = total / n;
       System.out.print("Average: " + average);

        System.out.println("oum: ");
        int oum  =  sc.nextInt();
        int outOfMarks = oum * n;

        int Percentage = (int)((total*100)/outOfMarks);
        System.out.print("Percentage: " + Percentage + "% ");
      


        }
    }
