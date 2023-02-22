package com.zyapaar.analytics.properties;

import java.util.Arrays;
import java.util.Scanner;

public class SwapFirstandLastDigit {
    public static void main(String[] args) {

        int num = 456911, count = 0, temp = 0,j ,z;
        temp = num;
        char[] a = String.valueOf(num).toCharArray();

        while(temp>0) {
            temp = temp/10;
            count++;
        }
         count = count -1;
         j = a[count] ;
         z = a[0];
        a[0] = a[count];
        a[count]  = (char)z;

        System.out.println(Arrays.toString(a)); // to print in array
        System.out.println(a); // to print integer


        }
    }
